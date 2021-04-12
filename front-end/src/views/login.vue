<template>
  <div class="login">
    <div class="left-img">
      <img src="@/assets/images/login/login_bg.svg" alt="">
    </div>

    <div class="login-page">

      <transition name="loginForm" mode="out-in">
        <!--        登录-->
        <div class="form" v-if="formShow" key="SignIn">
          <p style="text-align: left"><span class="login-title">风物社区</span></p>
          <el-form status-icon ref="signIn" :rules="SignInRules" :model="signIn">
            <el-form-item prop="username">
              <el-input size="large"
                        prefix-icon="iconfont icon-user"
                        placeholder="用户名"
                        v-model="signIn.username">
              </el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input size="large"
                        type="password"
                        prefix-icon="iconfont icon-password"
                        placeholder="密码"
                        v-model="signIn.password">
              </el-input>
            </el-form-item>
            <el-form-item prop="code">
              <el-input size="large"
                        prefix-icon="iconfont icon-verification"
                        placeholder="验证码"
                        style="width: 158px"
                        v-model="signIn.code">
              </el-input>
              <div class="verification">
                <img :src="codeUrl" @click="getCode" />
              </div>
            </el-form-item>
            <el-row class="fe-flex-between">
              <el-button size="medium" type="success" @click="submitForm('signIn')">登录</el-button>
              <el-button size="medium" type="success" plain @click="resetForm('signIn')">重置</el-button>
            </el-row>
            <p class="message">没有注册？<span @click="formShow = !formShow">去注册</span></p>
          </el-form>
        </div>
        <!--        注册-->
        <div class="form" v-else key="SignUP">
          <p style="text-align: left"><span class="login-title">风物社区</span></p>
          <el-form status-icon ref="signUp" :rules="SignUpRules" :model="signUp">
            <el-form-item prop="username">
              <el-input size="large"
                        prefix-icon="iconfont icon-user"
                        placeholder="用户名"
                        v-model="signUp.username">
              </el-input>
            </el-form-item>
            <el-form-item prop="nickName">
              <el-input size="large"
                        prefix-icon="iconfont icon-nickname"
                        placeholder="昵称"
                        v-model="signUp.nickName">
              </el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input size="large"
                        type="password"
                        prefix-icon="iconfont icon-password"
                        placeholder="密码"
                        auto-complete="off"
                        v-model="signUp.password">
              </el-input>
            </el-form-item>
            <el-form-item prop="checkPass">
              <el-input size="large"
                        type="password"
                        prefix-icon="iconfont icon-password"
                        placeholder="确认密码"
                        auto-complete="off"
                        v-model="signUp.checkPass">
              </el-input>
            </el-form-item>
            <el-form-item prop="code">
              <el-input size="large"
                        prefix-icon="iconfont icon-verification"
                        placeholder="验证码"
                        style="width: 158px"
                        v-model="signUp.code">
              </el-input>
              <div class="verification">
                <img :src="codeUrl" @click="getCode" />
              </div>
            </el-form-item>
            <el-row class="fe-flex-between">
              <el-button size="medium" type="success" @click="submitForm('signUp')">注册</el-button>
              <el-button size="medium" type="success" plain @click="resetForm('signUp')">重置</el-button>
            </el-row>
            <p class="message">已经注册？<span @click="formShow = !formShow">去登录</span></p>
          </el-form>
        </div>
      </transition>

    </div>
  </div>
</template>

<script>

import Cookies from "js-cookie";
import {getCodeImg, login} from "@/api/login";
import { register } from "@/api/system/register";
import {decrypt, encrypt} from "@/utils/jsencrypt";
import {setToken} from "@/utils/auth";

export default {
  data() {
    let validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.signUp.checkPass !== '') {
          this.$refs.signUp.validateField('checkPass');
        }
        callback();
      }
    };
    let validatePass2 = (rule, value, callback) => {
      if (!value) {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.signUp.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      // 显示表单
      formShow: true,
      // 登录
      signIn: {
        username: '',
        password: '',
        code: "",
        uuid: ""
      },
      // 注册
      signUp: {
        username: '',
        password: '',
        checkPass: '',
        nickName: '',
        code: '',
        uuid: ""
      },
      // 验证码
      codeUrl: "",
      // 登录校验
      SignInRules: {
        username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
        password: [{required: true, message: '请输入密码', trigger: 'blur'}],
        code: [{required: true, message: '请输入验证码', trigger: 'blur'}]
      },
      // 注册校验
      SignUpRules: {
        username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
        nickName: [{required: true, message: '请输入昵称', trigger: 'blur'}],
        password: [{validator: validatePass, trigger: 'blur'}],
        checkPass: [{validator: validatePass2, trigger: 'blur'}],
        code: [{required: true, message: '请输入验证码', trigger: 'blur'}]
      }
    }
  },
  created() {
    this.$store.dispatch("LogOut").then(() => {
      this.getCode();
      this.getCookie();
    }).catch(() => {
      this.getCode();
    });
  },
  mounted() {
    // this.autoView()
  },
  methods: {
    // 提交表单
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // alert('submit!');
          if (formName === 'signIn') {
            this.getSignIn()
          }else {
            this.postSignUp()
          }
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    // 重置表单
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.$store.dispatch("LogOut");
      this.getCode()
    },
    //登录
    getSignIn() {
      if (this.signIn) {
        Cookies.set("username", this.signIn.username, {expires: 7});
        Cookies.set("password", encrypt(this.signIn.password), {expires: 7});
      } else {
        Cookies.remove("username");
        Cookies.remove("password");
      }
      this.$store.dispatch("Login", this.signIn).then(() => {
        this.$router.push({name: 'homeIndex'})
        console.log(1)
      }).catch(() => {
        this.getCode();
      });

    },
    // 注册
    postSignUp() {
      const username = this.signUp.username.trim()
      const password = this.signUp.password
      const nickName = this.signUp.nickName
      const code = this.signUp.code
      const uuid = this.signUp.uuid
      return new Promise((resolve, reject) => {
        register(username, password,nickName, code, uuid).then(res => {
          this.$message({
            message: '注册成功',
            type: 'success'
          });
          this.resetForm()
        }).catch(error => {
          reject(error)
        })
      })
    },

    getCode() {
      getCodeImg().then(res => {
        this.codeUrl = "data:image/gif;base64," + res.img;
        this.signIn.uuid = res.uuid;
        this.signUp.uuid = res.uuid;
      });
    },
    getCookie() {
      if(Cookies.get("username")){
        const username = Cookies.get("username");
        const password = Cookies.get("password");
        this.signIn = {
          username: username === undefined ? this.loginForm.username : username,
          password: password === undefined ? this.loginForm.password : decrypt(password),
        };
      }
    },
  }

}
</script>

<style lang="scss" scoped>
@import url("https://fonts.googleapis.com/css?family=Roboto:300");
@import "src/styles/modules/login";

</style>
