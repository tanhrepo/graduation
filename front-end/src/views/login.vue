<template>
  <div class="login">
    <div class="container right-panel-active">
      <!-- Sign Up -->
      <div class="container__form container--signup">
        <form action="#" class="form" id="form1">
          <h2 class="form__title">Sign Up</h2>
          <input type="text" placeholder="User" class="input" />
          <input type="email" placeholder="Email" class="input" />
          <input type="password" placeholder="Password" class="input" />
          <button class="btn">Sign Up</button>
        </form>
      </div>

      <!-- Sign In -->
      <div class="container__form container--signin">
        <form action="#" class="form" id="form2">
          <h2 class="form__title">Sign In</h2>
          <input type="email" placeholder="Email" class="input" />
          <input type="password" placeholder="Password" class="input" />
          <a href="#" class="link">Forgot your password?</a>
          <button class="btn">Sign In</button>
        </form>
      </div>

      <!-- Overlay -->
      <div class="container__overlay">
        <div class="overlay">
          <div class="overlay__panel overlay--left">
            <button class="btn" id="signIn">Sign In</button>
          </div>
          <div class="overlay__panel overlay--right">
            <button class="btn" id="signUp">Sign Up</button>
          </div>
        </div>
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
import {getCodeImg} from '@/api/login'

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
    this.getCodeImgS()

  },
  mounted() {
    this.loginView()
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
    },
    getCodeImgS(){
      getCodeImg(getCode).then(res => {
        console.log(res)
      })
    },
    // 界面动画
    loginView(){
      const signInBtn = document.getElementById("signIn");
      const signUpBtn = document.getElementById("signUp");
      const fistForm = document.getElementById("form1");
      const secondForm = document.getElementById("form2");
      const container = document.querySelector(".container");

      signInBtn.addEventListener("click", () => {
        container.classList.remove("right-panel-active");
      });

      signUpBtn.addEventListener("click", () => {
        container.classList.add("right-panel-active");
      });

      fistForm.addEventListener("submit", (e) => e.preventDefault());
      secondForm.addEventListener("submit", (e) => e.preventDefault());

    }
  }
}
</script>

<style lang="scss" scoped>
@import '~@/styles/modules/login.scss';
:root {
  /* COLORS */
  --white: #e9e9e9;
  --gray: #333;
  --blue: #0367a6;
  --lightblue: #008997;

  /* RADII */
  --button-radius: 0.7rem;

  /* SIZES */
  --max-width: 758px;
  --max-height: 420px;

  font-size: 16px;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
  Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}


</style>
