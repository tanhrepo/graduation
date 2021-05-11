<template>
  <div class="fe-page">
    <div id="UserPage" class="fe-scroll-y fe-ptd-30">
      <BackTop :ID='"UserPage"'></BackTop>
      <div class="fe-page-container fe-flex-between">
        <div class="fe-container-left" style="background-color: #FFFFFF">
          <div class="fe-shadow user-title">
            <div class="fe-flex-between">
              <img :src="'http://localhost:8080' + userInfo.avatar" alt="">
              <div class="userInfo">
                <span>{{ userInfo.nickName }}</span>
                <p><i class="iconfont icon-article fe-mr-sm"></i>优质作者挖掘机 | 系统开发</p>
                <p><i class="iconfont icon-user-card fe-mr-sm"></i> 推荐优质作者，传播优质内容</p>
              </div>
            </div>
            <div style="z-index: 100;">
              <el-button type="primary" v-if="!followView" @click="follow(userInfo.userId)" plain size="mini">+点击关注
              </el-button>
              <el-button type="primary" v-else disabled plain size="mini">已关注</el-button>
            </div>
            <img class="user-title-bg" src="~@/assets/images/article/user_space.svg" alt="">
          </div>
          <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
            <el-tab-pane label="帖子" name="first">
              <MineArticle :userInfo="userInfo"></MineArticle>
            </el-tab-pane>
            <el-tab-pane label="评论" name="third">
              <MineComment :userInfo="userInfo"></MineComment>
            </el-tab-pane>
            <el-tab-pane label="收藏" name="fourth">
              <MineStar ref="mineStar" :userInfo="userInfo"></MineStar>
            </el-tab-pane>
          </el-tabs>
        </div>
        <div class="fe-container-right">
          <div class="fe-fixed-right">
            <img src="~@/assets/images/article/space_detail.svg" width="100%" alt="">
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MineComment from "@/views/mine/components/MineComment";
import MineArticle from "@/views/mine/components/MineArticle";
import BackTop from "@/views/components/BackTop";
import {getIdUserInfo} from "@/api/login";
import MineStar from "@/views/mine/components/MineStar";
import {getFollowList, UserFollow} from "@/api/system/operation";
import {mapState} from "vuex";

export default {
  name: "UserPage",
  components: {MineStar, MineComment, MineArticle, BackTop},
  data(){
    return{
      userInfo:{},
      activeName:'first',
      followView: null,

    }
  },
  created() {
    this.getData();

  },
  computed:{
    ...mapState(["user"]),
  },
  methods: {
    // 数据获取
    getData() {
      let data = this.$route.query.id
      return new Promise((resolve, reject) => {
        getIdUserInfo(data).then(res => {
          this.userInfo = res.data
          console.log("userInfo",this.userInfo)
          this.getFollowData(this.$route.query.id)
        }).catch(error => {
          reject(error)
        })
      })
    },
    handleClick(tab, event) {
      console.log(tab, event);
    },
    // 是否关注
    getFollowData(userId) {
      let data = {
        followId: this.user.userInfo.userId,
        userId: userId
      }
      return new Promise((resolve, reject) => {
        getFollowList(data).then(res => {
          console.log("关注没有", res)
          this.followView = res.total
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 关注
    follow(userId) {
      let data = {
        createBy: this.user.userInfo.userName,
        followId: this.user.userInfo.userId,
        userId: userId
      }
      return new Promise((resolve, reject) => {
        UserFollow(data).then(res => {
          console.log('关注用户', res.data)
          this.getData();
        }).catch(error => {
          reject(error)
        })
      })
    },
  }

}
</script>

<style lang="scss" scoped>
@import "src/styles/modules/user";
</style>