<template>
  <div class="fe-page">
    <div class="fe-scroll-y fe-ptd-30">
      <div class="fe-page-container fe-flex-between">
        <div class="fe-container-left fe-shadow add-article">
          <p class="fe-font-lg fe-mb-md">新帖子：</p>
          <div class="selector-topic"><i class="iconfont icon-topic fe-mr-sm"></i>选择话题</div>
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" class="demo-ruleForm">
            <el-form-item prop="articleTitle">
              <el-input
                  placeholder="请输入标题..."
                  v-model="ruleForm.articleTitle"></el-input>
            </el-form-item>
            <el-form-item prop="articleContent">
              <el-input
                  type="textarea"
                  placeholder="请输入内容..."
                  :autosize="{ minRows: 6, maxRows: 26}"
                  v-model="ruleForm.articleContent"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
              <el-button @click="resetForm()">重置</el-button>
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
import {mapState} from "vuex";
import {addArticle} from "@/api/system/article";

export default {
  name: "AddArticle",
  data() {
    return {
      ruleForm: {
        articleTitle:'',
        articleContent: ''
      },
      rules: {
        articleTitle:[{required: true, message: '请输入标题', trigger: 'blur' }],
        articleContent: [
          { required: true, message: '请输入内容', trigger: 'blur' }
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
  computed:{
    ...mapState(["user"]),
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // alert('submit!');
          console.log(1)
          console.log(this.ruleForm.articleContent)
          const param = {
            articleContent : this.ruleForm.articleContent,
            imgs: [],
            articleLable: "",
            articleTitle: this.ruleForm.articleTitle,
            vedios: [],
            createUser: this.user.userInfo.userName
          }
          console.log("param",param)
          return new Promise((resolve, reject) => {
            addArticle(param).then(res => {
              this.resetForm()
            }).catch(error => {
              reject(error)
            })
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm() {
      this.$refs['ruleForm'].resetFields();
      console.log(this.user)
    }
  }
}
</script>

<style lang="scss" scoped>
.add-article{
  background-color: #FFFFFF;
  padding: 16px 12px;

  .el-form-item{
    margin-bottom: 14px;
  }
}

.selector-topic{
  padding: 12px;
  font-size: 14px;
  background-color: #37C1D3;
  border-radius: 5px;
  margin-bottom: 14px;
  cursor: pointer;
  color: #FFFFFF;
}
</style>