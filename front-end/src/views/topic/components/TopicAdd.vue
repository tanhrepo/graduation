<template>
  <div class="fe-page">
    <div class="fe-scroll-y fe-ptd-30">
      <div class="fe-page-container fe-flex-between">
        <div class="fe-container-left fe-shadow add-topic">
          <p class="fe-font-lg fe-mb-md">新话题：</p>
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" class="demo-ruleForm">
            <el-form-item prop="lableName">
              <el-input
                  placeholder="请输入话题标题..."
                  v-model="ruleForm.lableName"></el-input>
            </el-form-item>
            <el-form-item prop="lableDescription">
              <el-input
                  type="textarea"
                  placeholder="请输入话题描述..."
                  :autosize="{ minRows: 6, maxRows: 26}"
                  v-model="ruleForm.lableDescription"></el-input>
            </el-form-item>
            <el-form-item>
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
                <el-button class="upload-btn"
                           slot="trigger"
                           icon="iconfont icon-image fe-mr-sm"
                           size="small"
                           type="primary">添加话题背景图片
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
import {putFile} from "@/api/common/file";
import {addLabel, getLabelList} from "@/api/system/label";

export default {
  name: "TopicAdd",
  data() {
    return {
      ruleForm: {
        lableName: '',
        lableDescription: '',
        lableImg: null,
        createBy: '',
      },
      rules: {
        lableName: [{required: true, message: '请输入话题标题', trigger: 'blur'}],
        lableDescription: [
          {required: true, message: '请输入话题描述内容', trigger: 'blur'}
        ]
      },
      fileListImg: [],
      itemData:[]
    }
  },
  computed: {
    ...mapState(["user"]),
  },
  mounted() {
    this.getData()
    this.ruleForm.createBy = this.user.userInfo.userName
  },
  methods: {
    // 数据获取
    getData() {
      return new Promise((resolve, reject) => {
        getLabelList().then(res => {
          this.itemData = res.rows
          console.log(this.itemData)
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 重置表单
    resetForm() {
      this.$refs["ruleForm"].resetFields();
      this.ruleForm = {
        lableName: '',
        lableDescription: '',
        lableImg: null,
        createBy: this.user.userInfo.userName
      }
      this.fileListImg = []
      this.fileListVideo = []
      this.urlListImg = 0
      this.urlListVideo = 0
      this.getData()
    },
    // 提交表单，发布文章
    submitForm() {
      this.$refs["ruleForm"].validate((valid) => {
        if (valid) {
          this.checkCode()
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    // 校验话题是否存在
    checkCode(){
      let data = this.itemData,
          arr = [];
      if (data.length > 0) {
        for (let item of data) {
          arr.push(item.lableName);
        }
        console.log(arr);
        if(arr.indexOf(this.ruleForm.lableName) === -1){
          this.submitUpload()
        }else {
          this.$message({
            message: '当前话题已存在，请重新输入！',
            type: 'warning'
          });
        }
      }
    },
    // 上传表单按钮
    submitUpload() {
      this.$refs.uploadImg.submit();
      if(!this.fileListImg.length){
        this.$message({
          message: '请上传背景图...',
          type: 'warning'
        });
      }
    },
    // 图片上传
    async uploadFile(item) {
      let formData = new FormData()
      formData.append('file', item.file)
      formData.append('group', 'system')
      await putFile(formData).then(res => {
        console.log("res", res)
        this.ruleForm.lableImg = res.url
        this.postForm()
      }).catch(error => {
        return error
      })
    },

    // 表单提交
    postForm() {
      const param = this.ruleForm
      return new Promise((resolve, reject) => {
        addLabel(param).then(res => {
          this.$message({
            message: '新增话题成功！',
            type: 'success'
          });
        }).catch(error => {
          reject(error)
        })
      })
    },
    handleChange(file, fileList) {
      this.fileListImg = fileList.slice(-1);
    },
  }
}
</script>

<style lang="scss" scoped>
.add-topic {
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

  .upload-demo {
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

::v-deep .el-button--primary {
  background-color: #37C1D3;
  border-color: #37C1D3;

  &:hover {
    background-color: #78DFE8;
    border-color: #78DFE8;
  }

}
</style>