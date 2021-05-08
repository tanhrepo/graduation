<!--  关注页-->
<template>
  <div class="fe-page">
    <div id="Following" class="fe-scroll-y fe-ptd-30">
      <BackTop :ID='"Following"'></BackTop>
      <div class="fe-page-container fe-flex-between">
        <div class="fe-container-left" style="background-color: #FFFFFF">
          <div v-for="(item,index) in itemData" :key="index">
            <TextItem :item-data="item"></TextItem>
          </div>
        </div>
        <div class="fe-container-right">
          <div class="fe-fixed-right">
            <img src="~@/assets/images/article/Working_desk.svg" width="100%" alt="">
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import BackTop from "@/views/components/BackTop";
import TextItem from "@/views/components/TextItem";
import { getFollowArticleList } from "@/api/system/operation"
import {mapState} from "vuex";

export default {
name: "Following",
  components: {BackTop, TextItem},
  data() {
    return {
      itemData: []
    }
  },
  created() {
    this.getData()
  },
  computed:{
    ...mapState(["user"]),
  },
  methods: {
    // 数据获取
    getData() {
      return new Promise((resolve, reject) => {
        getFollowArticleList(this.user.userInfo.userId).then(res => {
          console.log("关注列表",res.rows)
          this.itemData = res.rows
        }).catch(error => {
          reject(error)
        })
      })
    },
  }
}
</script>

<style scoped>

</style>