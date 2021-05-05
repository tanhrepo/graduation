<template>
  <div class="fe-page">
    <div id="MinePage" class="fe-scroll-y fe-ptd-30">
      <div class="fe-page-container">
        <el-row :gutter="20">
          <el-col :span="16">
            <el-card>
              <div slot="header" class="clearfix">
                <span>基本资料</span>
              </div>
              <el-tabs v-model="activeTab">
                <el-tab-pane label="基本资料" name="userinfo">
                  <userInfo :user="user"/>
                </el-tab-pane>
                <el-tab-pane label="修改密码" name="resetPwd">
                  <resetPwd :user="user"/>
                </el-tab-pane>
              </el-tabs>
            </el-card>
          </el-col>
          <el-col :span="8">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>个人信息</span>
              </div>
              <div>
                <div style="text-align: center">
                  <userAvatar :user="user"/>
                </div>
                <ul class="list-groups">
                  <li>
                    <span class="fe-flex-center"><i class="iconfont icon-user fe-mr-sm"></i>用户名称:</span>
                    <span>{{ user.userName }}</span>
                  </li>
                  <li>
                    <span class="fe-flex-center"><i class="iconfont icon-phone fe-mr-sm"></i>手机号码:</span>
                    <span>{{ user.phonenumber }}</span>
                  </li>
                  <li>
                    <span class="fe-flex-center"><i class="iconfont icon-email fe-mr-sm"></i>用户邮箱:</span>
                    <span>{{ user.email }}</span>
                  </li>
                  <li>
                    <span class="fe-flex-center"><i class="iconfont icon-nickname fe-mr-sm"></i>所属角色:</span>
                    <span>{{ roleGroup }}</span>
                  </li>
                  <li>
                    <span class="fe-flex-center"><i class="iconfont icon-date fe-mr-sm"></i>注册日期:</span>
                    <span>{{ user.createTime }}</span>
                  </li>
                </ul>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script>
import userAvatar from "./userAvatar";
import userInfo from "./userInfo";
import resetPwd from "./resetPwd";
import {getUserProfile} from "@/api/system/user";

export default {
  name: "Profile",
  components: {userAvatar, userInfo, resetPwd},
  data() {
    return {
      user: {},
      roleGroup: {},
      postGroup: {},
      activeTab: "userinfo"
    };
  },
  created() {
    this.getUser();
  },
  methods: {
    getUser() {
      getUserProfile().then(response => {
        this.user = response.data;
        this.roleGroup = response.roleGroup;
        this.postGroup = response.postGroup;
      });
    }
  }
};
</script>

<style scoped lang="scss">
.sys-user {
  width: 1000px;
  margin: 0 auto;
}

.clearfix {
  &:after {
    visibility: hidden;
    display: block;
    font-size: 0;
    content: " ";
    clear: both;
    height: 0;
  }
}

.list-groups {
  margin: 40px 0 20px;
  li {
    display: flex;
    justify-content: space-between;
    font-size: 14px;
    color: #2d3e53;
    line-height: 30px;
  }
}
</style>
