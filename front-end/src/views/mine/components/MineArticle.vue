<template>
<div>
  <div v-for="item in itemData" :key="item.articleId">
    <TextItem :item-data="item"></TextItem>
  </div>
</div>
</template>

<script>
import TextItem from "@/views/components/TextItem";
import {getArticleList} from "@/api/system/article";
export default {
  name: "MineArticle",
  props:["userInfo"],
  watch: {
    'userInfo': {
      deep: true,
      handler (val) {
        console.log("val",val)
        this.getData()
      }
    }
  },
  components: { TextItem },
  data(){
    return{
      itemData:[]
    }
  },
  methods: {
    // 数据获取
    getData() {
      let data = {
        createUser:this.userInfo.userName
      }
      return new Promise((resolve, reject) => {
        getArticleList(data).then(res => {
          console.log(res)
          this.itemData = res.rows
        }).catch(error => {
          reject(error)
        })
      })
    }
  },

}
</script>

<style scoped>

</style>