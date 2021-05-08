<template>
  <div class="fe-page">
    <div id="MinePage" class="fe-scroll-y fe-ptd-30">
      <BackTop :ID='"MinePage"'></BackTop>
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
              <el-button @click="jumpUser">编辑个人资料</el-button>
            </div>
            <img class="user-title-bg" src="~@/assets/images/article/space_user.svg" alt="">
          </div>
          <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
            <el-tab-pane label="帖子" name="first">
              <MineArticle ref="mineArticle" :userInfo="userInfo"></MineArticle>
            </el-tab-pane>
            <el-tab-pane label="关注" name="second">
              <MineFollow ref="mineFollow"></MineFollow>
            </el-tab-pane>
            <el-tab-pane label="评论" name="third">
              <MineComment ref="mineComment" :userInfo="userInfo"></MineComment>
            </el-tab-pane>
            <el-tab-pane label="收藏" name="fourth">
              <MineStar ref="mineStar" :userInfo="userInfo"></MineStar>
            </el-tab-pane>
<!--            <el-tab-pane label="点赞" name="fifth">-->
<!--              <MinePraise ref="minePraise" :userInfo="userInfo"></MinePraise>-->
<!--            </el-tab-pane>-->
          </el-tabs>
        </div>
        <div class="fe-container-right">
          <div class="fe-fixed-right">
            <img src="~@/assets/images/article/mine.svg" width="100%" alt="">
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {mapState} from "vuex";
import BackTop from "@/views/components/BackTop";
import MineArticle from "@/views/mine/components/MineArticle";
import MineComment from "@/views/mine/components/MineComment";
import MineFollow from "@/views/mine/components/MineFollow";
import MinePraise from "@/views/mine/components/MinePraise";
import MineStar from "@/views/mine/components/MineStar";

export default {
  name: "MinePage",
  components: {MineStar, MinePraise, MineFollow, MineComment, MineArticle, BackTop},
  data(){
    return{
      userInfo:{},
      activeName:'first',
    }
  },
  computed: {
    ...mapState(["user"]),
  },
  created() {
    this.userInfo = this.user.userInfo
  },
  mounted() {
    this.getData()
  },
  methods:{
    getData(){
      this.$refs.mineArticle.getData()
      this.$refs.mineComment.getData()
      this.$refs.mineFollow.getData()
      // this.$refs.minePraise.getData()
      this.$refs.mineStar.getData()
    },
    handleClick(tab, event) {
      console.log(tab, event);
    },
    jumpUser(){
      this.$router.push({name:'userItem'})
    }
  }
}
</script>

<style lang="scss" scoped>
@import "src/styles/modules/user";
</style>