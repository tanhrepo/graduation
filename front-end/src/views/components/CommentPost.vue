<template>
  <el-dialog title="评论：" :visible.sync="dialogVisible">
    <el-form :rules="rules" :model="addForm" ref="addForm">
      <el-form-item prop="content">
        <el-input
            type="textarea"
            placeholder="写下你的评论..."
            :autosize="{ minRows: 6, maxRows: 26}"
            v-model="addForm.content">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-upload
            ref="uploadImg"
            accept=".bmp, .gif, .jpg, .jpeg, .png"
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
                     type="primary">添加图片
          </el-button>
        </el-upload>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="resetForm">取 消</el-button>
      <el-button type="primary" @click="submitForm()">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import {putFile} from "@/api/common/file";
import {postComment} from "@/api/system/article";

export default {
  name: "CommentPost",
  props:['articleId','createBy','parentId','answerUser'],
  data() {
    return {
      dialogVisible: false,
      rules: {
        content: [
          {required: true, message: '请输入内容', trigger: 'blur'}
        ]
      },
      addForm: {
        content: '',
        imgs: [],
        articleId: this.articleId,
        createBy: this.createBy,
        parentId: this.parentId,
        answerUser: this.answerUser,
      },
      fileListImg: [],
      urlListImg: 0,
      postImg: false,
    }
  },
  mounted() {
    console.log("this.articleId",this.articleId)
    console.log("this.createBy",this.createBy)
    console.log("this.parentId",this.parentId)
    console.log("this.answerUser",this.answerUser)
    // this.addForm.articleId = this.articleId
  },
  methods:{
    // 重置表单
    resetForm() {
      this.$refs["addForm"].resetFields();
      this.addForm = {
        content: '',
        imgs: [],
        articleId: this.articleId,
        createBy: this.createBy,
        parentId: this.parentId,
        answerUser: this.answerUser,
      }
      this.fileListImg = []
      this.urlListImg = 0
      this.postImg = false
      this.dialogVisible = false
    },
    // 提交表单，发布文章
    submitForm() {
      this.$refs["addForm"].validate((valid) => {
        if (valid) {
          this.submitUpload()
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    // 上传表单按钮
    submitUpload() {
      this.$refs.uploadImg.submit();
      if (!this.fileListImg.length) {
        console.log("直接上传")
        this.postForm()
      }
    },
    // 图片上传
    async uploadFile(item) {
      let formData = new FormData()
      formData.append('file', item.file)
      formData.append('group', 'system')
      await putFile(formData).then(res => {
        console.log("res", res)
        this.addForm.imgs.push(res.url)
        console.log()
        let i = 1
        this.urlListImg += i
        if (this.urlListImg === this.fileListImg.length) {
          this.postImg = true
          this.postForm()
        }
      }).catch(error => {
        return error
      })
    },

    // 评论表单提交
    postForm() {
      const param = this.addForm
      console.log(param)
      return new Promise((resolve, reject) => {
        postComment(param).then(res => {
          this.$message({
            message: '发布成功！',
            type: 'success'
          });
          this.resetForm()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 文件上传数据新增变化
    handleChange(file, fileList) {
      this.fileListImg = fileList.slice(-12);
    },

    // 评论弹窗
    commentDialog(parentId,answerUser) {
      this.dialogVisible = !this.dialogVisible
      this.addForm.articleId = this.articleId
      this.addForm.createBy = this.createBy
      this.addForm.parentId = parentId
      this.addForm.answerUser = answerUser
      console.log("this.addForm.articleId",this.addForm.articleId)
      console.log("this.addForm.createBy",this.addForm.createBy)
      console.log("this.addForm.parentId",this.addForm.parentId)
      console.log("this.addForm.answerUser",this.addForm.answerUser)

    },
  }
}
</script>

<style lang="scss" scoped>
//弹窗
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

::v-deep .el-button--primary {
  background-color: #37C1D3;
  border-color: #37C1D3;

  &:hover {
    background-color: #78DFE8;
    border-color: #78DFE8;
  }

}

.el-form-item {
  margin-bottom: 14px;
}
</style>