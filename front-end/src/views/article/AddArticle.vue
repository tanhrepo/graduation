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
              <div class="upload-con">
                <el-upload
                    ref="uploadImg"
                    accept=".bmp, .gif, .jpg, .jpeg, .png"
                    class="upload-demo"
                    action=""
                    :http-request="uploadFile"
                    :on-change="handleChange"
                    :file-list="fileListImg"
                    :auto-upload="false"
                    list-type="picture">
                  <el-button slot="trigger" icon="iconfont icon-image" size="small" type="primary"></el-button>
                </el-upload>
                <el-upload
                    ref="uploadVideo"
                    class="upload-demo"
                    action=""
                    accept=".mp4,.avi,.wmv,.mpeg,.m4v,.mov,.asf,.flv,.f4v,.rmvb,.rm,.3gp,.vob"
                    :http-request="uploadVideo"
                    :on-change="handleChangeVideo"
                    :file-list="fileListVideo"
                    :auto-upload="false">
                  <el-button slot="trigger" icon="iconfont icon-video" size="small" type="primary"></el-button>
                </el-upload>
              </div>
            </el-form-item>
            <el-form-item style="text-align: center;margin-top: 20px">
              <el-button type="primary" @click="submitForm()">立即发布</el-button>
              <el-button @click="resetForm()">重置表单</el-button>
            </el-form-item>
          </el-form>

        </div>
        <div class="fe-container-right " style="background-color: #FFFFFF">
          <img src="~@/assets/images/edit/edit.svg" width="100%" alt="">

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
  name: "AddArticle",
  data() {
    return {
      fileListImg: [],
      fileListVideo: [],
      fileData: '',
      ruleForm: {
        articleTitle: '',
        articleContent: '',
        articleLable: '',
        imgs: [],
        vedios: [],
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
        createUser: this.user.userInfo.userName
      }
      this.fileListImg = []
      this.fileListVideo = []
    },
    // 图片上传
    async uploadFile(item) {
      let formData = new FormData()
      formData.append('file', item.file)
      formData.append('group', 'system')
      await putFile(formData).then(res => {
        console.log("res", res)
        this.ruleForm.imgs.push(res.url)
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
        this.ruleForm.vedios.push(res.url)
        let i = 1
        this.urlListVideo += i
        if (this.urlListVideo === this.fileListVideo.length) {
          this.postVideo = true
          this.fileUploadList()
        }
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
      }else if(this.fileListImg.length && !this.fileListVideo.length){
        this.postVideo = true
        console.log("没有视频")
      }else if(!this.fileListImg.length && this.fileListVideo.length){
        this.postImg = true
        console.log("没有图片")
      }
    },
    // 有图片的时候
    fileUploadList() {
      console.log(this.postImg , this.postVideo)
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
      this.fileListImg = fileList.slice(-3);
    },
    handleChangeVideo(file, fileList) {
      this.fileListVideo = fileList.slice(-3);
    },
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

  .upload-con {
    display: flex;
    justify-content: space-between;

    .upload-demo {
      width: 49%;
      &:nth-child(1){
        text-align: right;
      }
    }

  }
}

.selector-topic {
  padding: 12px;
  font-size: 14px;
  background-color: #37C1D3;
  border-radius: 5px;
  margin-bottom: 14px;
  cursor: pointer;
  color: #FFFFFF;

  &:hover{
    background-color:#78DFE8 ;
  }
}

::v-deep .el-button--primary{
  background-color: #37C1D3;
  border-color: #37C1D3;

  &:hover{
    background-color: #78DFE8;
    border-color: #78DFE8;
  }

}
</style>