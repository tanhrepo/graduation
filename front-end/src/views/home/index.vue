<!-- 首页(推荐) -->
<template>
  <div class="fe-page">
    <div id="homeIndex" class="fe-scroll-y fe-ptd-30">
      <BackTop :ID='"homeIndex"'></BackTop>
      <div class="fe-page-container fe-flex-between">
        <div class="fe-container-left" style="background-color: #FFFFFF">
          <div v-for="item in itemData" :key="item.articleId">
            <TextItem :item-data="item"></TextItem>
          </div>
        </div>
        <div class="fe-container-right" style="background-color: #FFFFFF"></div>
      </div>
    </div>
  </div>
</template>

<script>
import TextItem from "@/views/components/TextItem";
import BackTop from "@/views/components/BackTop";
import {getArticleList} from "@/api/system/article";
export default {
  components: {BackTop, TextItem},
  data(){
    return{
      itemData:[
        {
          articleId:1,
          username:'zhang',
          userImg:require("@/assets/images/user/user_11.jpg"),
          nickName:'张三',
          articleTitle:'这是张三的签名',
          articleContent:'路由守卫 (导航守卫)及其具体使用. 导航守卫其实也是路由守卫，\n也可以是路由拦截，我们可以通过路由拦截，来判断用户是否登录，该页面用户是否有权限浏览，需要结合meta来实现. 所谓的路由守卫可以简单的理解为一座房子的门口的保安，想要进入这个房子就必须通过保安的检查，要告诉路由守卫你从哪里来？. 总不能随便陌生人就给放进去？',
          imgs:[require("@/assets/images/item/item_01.png"),require("@/assets/images/item/item_02.png"),require("@/assets/images/item/item_03.png"),require("@/assets/images/item/item_04.png"),require("@/assets/images/item/item_05.png"),require("@/assets/images/item/item_05.png"),require("@/assets/images/item/item_01.png"),require("@/assets/images/item/item_02.png"),require("@/assets/images/item/item_03.png"),require("@/assets/images/item/item_04.png"),],
          articleViewCount:0,
          user:{
            avatar:''
          }
        },
      ]
    }
  },
  created() {
    this.getData()
  },
  methods:{
    // 数据获取
    getData(){
      return new Promise((resolve, reject) => {
        getArticleList().then(res => {
          console.log(res)
          this.itemData = res.rows
        }).catch(error => {
          reject(error)
        })
      })
    }
  }
}
</script>

<style>
</style>