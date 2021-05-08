<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="12" style="padding-left: 20px">
        <h2>风物社区管理系统</h2>
        <pre style="font-size: 16px">
            本文设计的网络社区系统是以内容为核心的系统。该系统使用了基于用户行为
          的协同过滤算法进行内容推荐，并用 Highcharts 对用户的行为数据进行可视
          化。
            系统通过前后端分离的开发模式，基于 MVVM 模型，MySQL，Axios 和
          Vue 等技术栈进行设计。分别从用户端和管理端两个方面设计功能模块，其中用
          户端包含推荐、图文、视频、关注、话题、消息、个人中心等模块，管理端则由
          用户管理、角色管理、文章管理、话题管理、菜单管理、系统监控等模块构成。
        </pre>
      </el-col>

      <el-col :sm="24" :lg="12" style="padding-left: 50px">
        <el-row>
          <el-col :span="12">
            <h2>技术选型</h2>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <ul class="list-ul">
              <li>Vue</li>
              <li>Vuex</li>
              <li>HighCharts</li>
              <li>Element-ui</li>
              <li>Axios</li>
              <li>Sass</li>
              <li>...</li>
            </ul>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-divider />
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="12" :lg="10">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>话题热度：</span>
          </div>
          <div class="body">
            <pie-charts></pie-charts>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="14">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>贴子热度：</span>
          </div>
          <div class="body">
            <packed-charts></packed-charts>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import PieCharts from "@/views/wCharts/pieCharts";
import PackedCharts from "@/views/wCharts/packedCharts";
export default {
  name: "index",
  components: {PackedCharts, PieCharts},
  data() {
    return {
      // 版本号
      version: "3.3.0",
    };
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    },
  },
};
</script>

<style scoped lang="scss">
.list-ul{
  li{
    font-size: 16px;
    line-height: 22px;
  }
}
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

