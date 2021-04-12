<template>
  <div class="fe-page">
    <div class="fe-scroll-y fe-ptd-30">
      <div class="fe-page-container fe-flex-between">
        <div class="fe-container-left fe-shadow add-article">
          <p class="fe-font-lg fe-mb-md">新帖子：</p>
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" class="demo-ruleForm">
            <el-form-item prop="articleContent">
              <el-input
                  type="textarea"
                  :autosize="{ minRows: 6, maxRows: 26}"
                  v-model="ruleForm.articleContent"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
              <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
          </el-form>
        </div>
        <div class="fe-container-right " style="background-color: #FFFFFF">
          <img src="~@/assets/images/edit/edit.svg" width="100%"  alt="">
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "AddArticle",
  data() {
    return {
      ruleForm: {
        articleTitle:'',
        articleContent: ''
      },
      rules: {
        articleContent: [
          { required: true, message: '请输入发帖内容', trigger: 'blur' }
        ]
      },
    // {
    //   "articleCollectCount": 0,收藏数
    //     "articleCommentCount": 0,评论条数
    //     "articleContent": "string",内容
    //     "articleImgurls": "string",图片
    //     "articleLable": "string",标签，话题，分类
    //     "articleTitle": "string",标题
    //     "articleTrampleCount": 0,踩
    //     "articleTransmitCount": 0,
    //     "articleVediourls": "string",
    //     "articleViewCount": 0, 浏览量
    //     "createBy": "string",
    //     "createUser": "string", 作者
    //     "params": {},
    //   "remark": "string",
    //     "searchValue": "string",
    //     "updateBy": "string"
    // }
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // alert('submit!');
          console.log(1)
          console.log(this.ruleForm.articleContent)
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style lang="scss" scoped>
.add-article{
  background-color: #FFFFFF;
  padding: 16px 12px;
}
</style>