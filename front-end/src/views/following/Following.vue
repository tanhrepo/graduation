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
import {getArticleList} from "@/api/system/article";
import { getFollowList } from "@/api/system/operation"
import { getIdUserInfo } from "@/api/login"
import {mapState} from "vuex";
import {MsgSort} from  "@/utils/mapTree.js"

export default {
name: "Following",
  components: {BackTop, TextItem},
  data() {
    return {
      itemData: [

      ]
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
      let data = {
        followId:this.user.userInfo.userId,
      }
      return new Promise((resolve, reject) => {
        getFollowList(data).then(res => {
          console.log("关注列表",res.rows)
          for(let i = 0,j = res.rows.length;i<j;i++){
            this.getUserInfo(res.rows[i].userId)
          }
        }).catch(error => {
          reject(error)
        })
      })
    },
    getUserInfo(id){
      return new Promise((resolve, reject) => {
        getIdUserInfo(id).then(res => {
          console.log("关注用户",res.data)
          this.getArticle(res.data.userName)
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 文章数据获取
    getArticle(userName){
      let data = {
        createUser:userName,
      }
      return new Promise((resolve, reject) => {
        getArticleList(data).then(res => {
          console.log(res)
          this.itemData.push.apply(this.itemData,res.rows)
          console.log("this.itemData",this.itemData)
          this.itemData = MsgSort(this.itemData,'createTime')
          console.log("new",this.itemData)
        }).catch(error => {
          reject(error)
        })
      })
    }
  }
}
</script>

<style scoped>

</style>