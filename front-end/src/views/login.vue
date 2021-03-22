<template>
  <div class="login">
    <div class="login-left">
      <div class="login-logo">
        <img src="" /><span>5G行业专网运营管理系统</span>
      </div>
      <img src="" class="login-illustration" />
    </div>
    <div class="login-right">
      <div class="login-box">
        <div class="product-name">
          <article>
            <img src="" width="100%" />
          </article>
          <p class="welcome-chinese">欢迎登录风物志博客社区</p>
          <p class="welcome-txt">AI WIND</p>
        </div>

        <el-form class="login-form" :model="loginForm" :rules="rules" ref="loginForm">
          <el-form-item prop="username">
            <el-input v-model="loginForm.username" placeholder="请输入账号"
              ><i slot="prefix" class="iconfont icon-user"></i
            ></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input
              type="password"
              v-model="loginForm.password"
              placeholder="请输入密码"
              @keyup.enter.native="handleLogin"
              ><i slot="prefix" class="iconfont icon-lock"></i
            ></el-input>
          </el-form-item>
          <el-form-item prop="code">
            <el-input
              v-model="loginForm.code"
              auto-complete="off"
              placeholder="请输入验证码"
              style="width: 63%"
              maxlength="4"
              @keyup.enter.native="handleLogin"
              ><i slot="prefix" class="iconfont icon-safe"></i
            ></el-input>
            <div class="login-code">
              <img :src="codeUrl" @click="getCode" />
            </div>
          </el-form-item>
          <div class="fe-mb">
            <el-checkbox label="记住密码" v-model="loginForm.rememberMe"
              >记住密码</el-checkbox
            >
          </div>
          <el-form-item style="width: 100%">
            <el-button
              :loading="loading"
              size="medium"
              type="primary"
              style="width: 100%"
              @click.native.prevent="handleLogin"
            >
              <span v-if="!loading">登 录</span>
              <span v-else>登 录 中...</span>
            </el-button>
          </el-form-item>
        </el-form>
      </div>
      <div class="copyright">
        <p class="fe-flex-center">
          <span>© 2021 重庆师范大学 谭虹 版权所有</span>
        </p>
        <p>A Product of TanHong.</p>
      </div>
    </div>
  </div>
</template>

<script>
import { mapMutations } from 'vuex'
import Cookies from 'js-cookie'
import { encrypt } from '@/utils/rsaEncrypt'
import Config from '@/settings'
import { setToken } from '@/utils/auth'
import { getAction, postAction } from "@/api/manage"
import Services from '@/api/services'

const getCode = Services.login.getCode
const login = Services.login.login

export default {
  data () {
    return {
      loginForm: {
        username: '', // 账号
        password: '', // 密码
        rememberMe: true, // 记住密码
        code: '', // 验证码value
        uuid: '', // 验证码的key
      },
      // 验证码图片
      codeUrl: '',
      // cookie里的密码
      cookiePass: '',
      rules: {
        username: [{ required: true, message: '账号不能为空', trigger: 'blur' }],
        password: [{ required: true, message: '密码不能为空', trigger: 'blur' }],
        code: [{ required: true, message: '验证码不能为空', trigger: 'change' }]
      },
      loading: false, // 登录中状态
    }
  },
  created () {
    // 本地环境便于使用默认设置账号密码
    // if (this.$config.ENV === 'dev') {
    this.loginForm.username = 'admin'
    this.loginForm.password = 'ReTiNA2021'
    // }
    // 获取验证码
    this.getCode()
    // 获取用户名密码等Cookie
    this.getCookie()
  },
  methods: {
    ...mapMutations('user', ['setUserInfo',]),
    // 获取验证码
    getCode () {
      getAction(getCode).then(res => {
        this.codeUrl = res.verificationImage
        this.loginForm.uuid = res.verificationId
      })
    },
    // 获取cookie里的账号密码
    getCookie () {
      const username = Cookies.get('username')
      let password = Cookies.get('password')
      const rememberMe = Cookies.get('rememberMe')
      // 保存cookie里面的加密后的密码
      this.cookiePass = password === undefined ? '' : password
      password = password === undefined ? this.loginForm.password : password
      this.loginForm = {
        username: username === undefined ? this.loginForm.username : username,
        password: password,
        rememberMe: rememberMe === undefined ? false : Boolean(rememberMe),
        code: ''
      }
    },
    // 登录
    handleLogin () {
      this.$refs.loginForm.validate(valid => {
        // 缓存用户信息
        const user = {
          userName: this.loginForm.username,
          password: this.loginForm.password,
          rememberMe: this.loginForm.rememberMe,
          verificationCode: this.loginForm.code,
          verificationId: this.loginForm.uuid
        }
        // 密码加密
        if (user.password !== this.cookiePass) {
          user.password = encrypt(user.password)
        }
        if (valid) {
          this.loading = true
          // 设置用户信息cookie
          if (user.rememberMe) {
            Cookies.set('username', user.userName, { expires: Config.passCookieExpires })
            Cookies.set('password', user.password, { expires: Config.passCookieExpires })
            Cookies.set('rememberMe', user.rememberMe, { expires: Config.passCookieExpires })
          } else {
            Cookies.remove('username')
            Cookies.remove('password')
            Cookies.remove('rememberMe')
          }
          // 调用登录接口
          postAction(login, user).then((res) => {
            this.loading = false
            // 设置鉴权token
            setToken(res.token, user.rememberMe)
            // 存储用户名
            this.setUserInfo({
              userName: user.userName,
            })
            // 登陆成功路由跳转
            this.$router.push({ path: '/' })
          }).catch(() => {
            // 登录失败刷新验证码
            this.loading = false
            this.getCode()
          })
        } else {
          // console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
@import '~@/styles/modules/login.scss';
</style>
