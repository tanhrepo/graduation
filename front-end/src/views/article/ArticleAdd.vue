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
              <el-upload
                  ref="uploadImg"
                  accept=".bmp, .gif, .jpg, .jpeg, .png"
                  class="upload-demo"
                  action=""
                  :disabled="showImg"
                  v-show="!showImg"
                  :http-request="uploadFile"
                  :on-change="handleChange"
                  :on-remove="handleRemoveImg"
                  :file-list="fileListImg"
                  :auto-upload="false"
                  list-type="picture">
                <el-button class="upload-btn"
                           slot="trigger"
                           :disabled="showImg"
                           icon="iconfont icon-image fe-mr-sm"
                           size="small"
                           type="primary">添加图片
                </el-button>
              </el-upload>
              <el-upload
                  ref="uploadVideo"
                  class="upload-demo"
                  action=""
                  :disabled="showVideo"
                  v-show="!showVideo"
                  accept=".mp4,.avi,.wmv,.mpeg,.m4v,.mov,.asf,.flv,.f4v,.rmvb,.rm,.3gp,.vob"
                  :http-request="uploadVideo"
                  :on-change="handleChangeVideo"
                  :on-remove="handleRemoveVideo"
                  :file-list="fileListVideo"
                  :auto-upload="false">
                <el-button class="upload-btn"
                           slot="trigger" :disabled="showVideo"
                           icon="iconfont icon-video fe-mr-sm"
                           size="small"
                           type="primary">添加视频
                </el-button>
              </el-upload>
            </el-form-item>
            <el-form-item style="text-align: center;margin-top: 20px">
              <el-button type="primary" @click="submitForm()">立即发布</el-button>
              <el-button @click="resetForm()">重置表单</el-button>
            </el-form-item>
          </el-form>

        </div>
        <div class="fe-container-right " style="background-color: #FFFFFF">
          <img src="~@/assets/images/article/edit.svg" width="100%" alt="">

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {mapState} from "vuex";
import {addArticle} from "@/api/system/article";
import {putFile} from "@/api/common/file";

export default {
  name: "ArticleAdd",
  data() {
    return {
      showImg: false,
      showVideo: false,
      fileListImg: [],
      fileListVideo: [],
      fileData: '',
      ruleForm: {
        articleTitle: '',
        articleContent: '',
        articleLable: '',
        imgs: [],
        vedios: [],
        articleVediourls: "",
        createUser: '',
      },
      rules: {
        articleTitle: [{required: true, message: '请输入标题', trigger: 'blur'}],
        articleContent: [
          {required: true, message: '请输入内容', trigger: 'blur'}
        ]
      },
      urlListImg: 0,
      urlListVideo: 0,
      postImg: false,
      postVideo: false
    }
  },
  computed: {
    ...mapState(["user"]),
  },
  mounted() {
    this.ruleForm.createUser = this.user.userInfo.userName
  },
  methods: {
    // 提交表单，发布文章
    submitForm() {
      this.$refs["ruleForm"].validate((valid) => {
        if (valid) {
          this.submitUpload()
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    postForm() {
      const param = this.ruleForm
      return new Promise((resolve, reject) => {
        addArticle(param).then(res => {
          this.$message({
            message: '发布成功！',
            type: 'success'
          });
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 重置表单
    resetForm() {
      this.$refs["ruleForm"].resetFields();
      this.ruleForm = {
        articleTitle: '',
        articleContent: '',
        articleLable: '',
        imgs: [],
        vedios: [],
        articleVediourls: "",
        createUser: this.user.userInfo.userName
      }
      this.fileListImg = []
      this.fileListVideo = []
      this.urlListImg = 0
      this.urlListVideo = 0
      this.postImg = false
      this.postVideo = false
      this.showImg = false
      this.showVideo = false
    },
    // 图片上传
    async uploadFile(item) {
      let formData = new FormData()
      formData.append('file', item.file)
      formData.append('group', 'system')
      await putFile(formData).then(res => {
        console.log("res", res)
        this.ruleForm.imgs.push(res.url)
        console.log()
        let i = 1
        this.urlListImg += i
        if (this.urlListImg === this.fileListImg.length) {
          this.postImg = true
          this.fileUploadList()
        }
      }).catch(error => {
        return error
      })
    },
    // 视频上传
    async uploadVideo(item) {
      let formData = new FormData()
      formData.append('file', item.file)
      formData.append('group', 'system')
      await putFile(formData).then(res => {
        console.log("res", res)
        this.ruleForm.articleVediourls = res.url
        this.postVideo = true
        this.fileUploadList()
      }).catch(error => {
        return error
      })
    },
    // 上传文件
    submitUpload() {

      this.$refs.uploadImg.submit();
      this.$refs.uploadVideo.submit();
      if (!this.fileListImg.length && !this.fileListVideo.length) {
        console.log("直接上传")
      } else if (this.fileListImg.length && !this.fileListVideo.length) {
        this.postVideo = true
        console.log("没有视频")
      } else if (!this.fileListImg.length && this.fileListVideo.length) {
        this.postImg = true
        console.log("没有图片")
      }
    },
    // 有图片的时候
    fileUploadList() {
      console.log(this.postImg, this.postVideo)
      if (this.postImg && this.postVideo) {
        console.log("文件上传")
        console.log(this.ruleForm)
        this.postForm()
      } else if (!this.postImg && this.postVideo) {
        console.log("图片还没有上传")
      } else if (this.postImg && this.postVideo) {
        console.log("视频还没有上传")
      }
    },
    handleChange(file, fileList) {
      this.fileListImg = fileList.slice(-12);
      if (this.fileListImg.length) {
        this.showVideo = true
      } else {
        this.showVideo = false
      }
    },
    handleRemoveImg(file, fileList) {
      if (fileList.length) {
        this.showVideo = true
      } else {
        this.showVideo = false
      }
    },
    handleRemoveVideo(file, fileList) {
      if (fileList.length) {
        this.showImg = true
      } else {
        this.showImg = false
      }
    },
    handleChangeVideo(file, fileList) {
      this.fileListVideo = fileList.slice(-1);
      if (this.fileListVideo.length) {
        this.showImg = true
      } else {
        this.showImg = false
      }
    },
    handleProgress() {

    }
  }


}
</script>

<style lang="scss" scoped>
.add-article {
  background-color: #FFFFFF;
  padding: 16px 12px;

  .el-form-item {
    margin-bottom: 14px;
  }


  ::v-deep .el-upload-list__item-thumbnail {
    object-fit: cover;
    border-radius: 6px;
  }

  ::v-deep .el-upload {
    width: 100%;
  }
  .upload-demo{
    margin-bottom: 12px;
  }
  .upload-btn {
    width: 100%;
    text-align: left;
    display: flex;
    align-items: center;
    padding: 12px;
    border-radius: 6px;
    font-size: 14px;
  }
}

.selector-topic {
  padding: 12px;
  font-size: 14px;
  background-color: #37C1D3;
  border-radius: 6px;
  margin-bottom: 14px;
  cursor: pointer;
  color: #FFFFFF;

  &:hover {
    background-color: #78DFE8;
  }
}

::v-deep .el-button--primary {
  background-color: #37C1D3;
  border-color: #37C1D3;

  &:hover {
    background-color: #78DFE8;
    border-color: #78DFE8;
  }

}
</style>