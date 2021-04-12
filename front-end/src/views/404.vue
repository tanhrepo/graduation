<template>
  <div class="wind fe-flex-column">
    <div class="fe-no-shrink nav">
      <Navbar></Navbar>
      <!-- <Breadcrumb></Breadcrumb> -->
    </div>
    <div class="fe-page fe-flex-center">
      <div class="fe-view">
        <div class="page-error">
          <img src="~@/assets/images/404.png" width="400" alt="404" />
          <div class="content">
            <h1>404</h1>
            <div class="mb-24 desc">抱歉，您访问的页面不存在</div>
            <el-button @click="$router.push('/')" type="primary">返回首页</el-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Navbar from '@/views/components/Navbar'
// import Breadcrumb from '@/views/components/Breadcrumb'

export default {
  components: {
    Navbar,
    // Breadcrumb
  },
}
</script>
<style lang="scss" scoped>
.page-error {
  height: 100%;
  display: -ms-flexbox;
  display: flex;
  -ms-flex-align: center;
  align-items: center;
  -ms-flex-pack: center;
  justify-content: center;
  background-color: #fff;
  border-radius: 4px;
  .content {
    margin-left: 16px;
    h1 {
      height: 120px;
      font-size: 80px;
      margin-bottom: 0;
      font-weight: 500;
    }
    .desc {
      color: rgba(123, 147, 167, 0.8);
      font-size: 14px;
    }
    .el-button--small {
      font-size: 14px;
    }
  }
}
</style>
