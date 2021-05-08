<template>
<div class="follow-item">
  <div class="fe-flex">
    <img @click="jumpDetail('userPage',itemData.userId)" :src="'http://localhost:8080' + itemData.avatar" alt="">
    <div class="flex-column-between">
      <span class="fe-url">{{ itemData.nickName }}</span>
      <p>{{ itemData.createTime }}</p>
    </div>
  </div>

  <P class="fe-flex-center">
    <el-button type="primary" v-if="!followView" @click="follow(itemData.userId)" plain size="mini">关注</el-button>
    <el-button type="primary" v-else disabled plain size="mini">已关注</el-button>
  </P>
</div>
</template>

<script>
import {getFollowList, UserFollow} from "@/api/system/operation";
import {mapState} from "vuex";

export default {
  name: "FollowItem",
  props:["itemData"],
  data(){
    return{
      followView:null
    }
  },
  computed:{
    ...mapState(["user"]),
  },
  mounted() {
    this.getFollowData(this.itemData.userId)
  },
  methods:{
    jumpDetail(name,id){
      this.$router.push({
        name,
        query:{
          id,
        }
      })
    },
    follow(userId){
      let data = {
        createBy:this.user.userInfo.userName,
        followId:this.user.userInfo.userId,
        userId:userId
      }
      return new Promise((resolve, reject) => {
        UserFollow(data).then(res => {
          this.getFollowData(userId)
        }).catch(error => {
          reject(error)
        })
      })
    },
    getFollowData(userId) {
      let data = {
        followId: this.user.userInfo.userId,
        userId:userId
      }
      return new Promise((resolve, reject) => {
        getFollowList(data).then(res => {
          this.followView = res.total
        }).catch(error => {
          reject(error)
        })
      })
    },
  }
}
</script>

<style lang="scss" scoped>
.follow-item{
  display: flex;
  padding: 12px;
  justify-content: space-between;
  border-bottom: 1px solid #EBEBEB;


  &:hover{
    background-color: #EBEBEB;
    border-radius: 12px;
    transition: 0.4s background-color;
  }
  img{
    height: 40px;
    width: 40px;
    border-radius: 50%;
    margin-right: 12px;
  }
  span{
    font-size: 14px;
  }
}
</style>