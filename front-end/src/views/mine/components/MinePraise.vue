<template>
  <div>
    <div v-for="(item,index) in itemData" :key="index">
      <PraiseItem :itemData="item"></PraiseItem>
    </div>
  </div>
</template>

<script>
import {getPraiseList} from "@/api/system/operation";
import {getArticleItem} from "@/api/system/article";
import PraiseItem from "@/views/mine/components/PraiseItem";

export default {
  name: "MinePraise",
  props: ["userInfo"],
  components:{PraiseItem},
  watch: {
    'userInfo': {
      deep: true,
      handler(val) {
        console.log("val", val)
        this.getData()
      }
    }
  },
  data(){
    return{
      itemData:[]
    }
  },
  methods: {
    // 数据获取
    getData() {
      let data = {
        userId:this.userInfo.userId,
      }
      console.log("i大家阿斯顿你看见反对爱上对方骄傲的是客服阿三酱豆腐开始",data)
      return new Promise((resolve, reject) => {
        getPraiseList(data).then(res => {
          console.log("点赞",res)
          // this.itemData = res.rows
          for(let i = 0,j = res.rows.length;i<j;i++){
            this.articleItem(res.rows[i].entityId)
          }
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 基础数据调用，文章详情
    articleItem(entityId) {
      return new Promise((resolve, reject) => {
        getArticleItem(entityId).then(res => {
          console.log('文章详情detail', res.data)
          this.itemData.push(res.data)
          console.log("isdfgnkdjfgnfdkjsgndklj",this.itemData)
        }).catch(error => {
          reject(error)
        })
      })
    },
  },
}
</script>

<style scoped>

</style>