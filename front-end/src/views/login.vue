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
            <el-form-item prop="verification">
              <el-input size="large"
                        prefix-icon="iconfont icon-verification"
                        placeholder="验证码"
                        style="width: 67%"
                        v-model="signIn.verification">
              </el-input>
              <div class="verification">
                <!--              <img :src="codeUrl" @click="getCode" class="login-code-img"/>-->
              </div>
            </el-form-item>
            <el-row class="fe-flex-between">
              <el-button size="medium" type="success" @click="submitForm('signIn')">登录</el-button>
              <el-button size="medium" @click="resetForm('signIn')">重置</el-button>
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
            <el-form-item prop="nickname">
              <el-input size="large"
                        prefix-icon="iconfont icon-nickname"
                        placeholder="昵称"
                        v-model="signUp.nickname">
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
            <el-form-item prop="verification">
              <el-input size="large"
                        prefix-icon="iconfont icon-verification"
                        placeholder="验证码"
                        style="width: 67%"
                        v-model="signUp.verification">
              </el-input>
              <div class="verification">
                <!--              <img :src="codeUrl" @click="getCode" class="login-code-img"/>-->
              </div>
            </el-form-item>
            <el-row class="fe-flex-between">
              <el-button size="medium" type="success" @click="submitForm('signUp')">注册</el-button>
              <el-button size="medium" @click="resetForm('signUp')">重置</el-button>
            </el-row>
            <p class="message">已经注册？<span @click="formShow = !formShow">去登录</span></p>
          </el-form>
        </div>
      </transition>

    </div>
  </div>
</template>

<script>


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
        verification: '',
      },
      // 注册
      signUp: {
        username: '',
        password: '',
        checkPass:'',
        nickname: '',
        verification: '',
      },

      // 登录校验
      SignInRules:{
        username:[{ required: true, message: '请输入用户名', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
        verification:[{ required: true, message: '请输入验证码', trigger: 'blur' }]
      },
      // 注册校验
      SignUpRules: {
        username:[{ required: true, message: '请输入用户名', trigger: 'blur' }],
        nickname:[{ required: true, message: '请输入昵称', trigger: 'blur' }],
        password: [{ validator: validatePass, trigger: 'blur' }],
        checkPass: [{ validator: validatePass2, trigger: 'blur' }],
        verification:[{ required: true, message: '请输入验证码', trigger: 'blur' }]
      }
    }
  },
  created() {

  },
  mounted() {
    // this.autoView()
  },
  methods: {
    // 注册
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    // 重置表单
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  }

}
</script>

<style lang="scss" scoped>
@import url("https://fonts.googleapis.com/css?family=Roboto:300");
@import "src/styles/modules/login";

</style>
