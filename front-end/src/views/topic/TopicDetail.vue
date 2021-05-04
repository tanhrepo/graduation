<template>
  <div class="fe-page">
    <div id="TopicDetail" class="fe-scroll-y fe-ptd-30">
      <BackTop :ID='"TopicDetail"'></BackTop>
      <div class="fe-page-container fe-flex-between">
        <div class="fe-container-left" style="background-color: #FFFFFF">
          <div style="padding: 12px">
            <div class="topic-header">
              <img :src="labelData.lableImg" alt="">
              <div class="wrap-mask">
                <span class="topic-title">{{ labelData.lableName }}</span>
                <p class="description" @click="dialogDescription = true">{{ labelData.lableDescription }}</p>
                <el-dialog
                    title="话题描述："
                    :visible.sync="dialogDescription"
                    width="30%">
                  <p class="dialogDescription">{{ labelData.lableDescription }}</p>
                </el-dialog>
              </div>
            </div>
          </div>

          <div v-for="item in itemData" :key="item.articleId">
            <TextItem :item-data="item"></TextItem>
          </div>
        </div>
        <div class="fe-container-right">
          <img src="~@/assets/images/article/topic_page.svg" width="100%" alt="">
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import TextItem from "@/views/components/TextItem";
import BackTop from "@/views/components/BackTop";
import {getArticleList} from "@/api/system/article";
import {getLabelItem} from "@/api/system/label";

export default {
  name: "TopicDetail",
  components: {BackTop, TextItem},
  data() {
    return {
      labelData: {},
      itemData: [
        {
          articleId: 1,
          username: 'zhang',
          userImg: require("@/assets/images/user/user_11.jpg"),
          nickName: '张三',
          articleTitle: '这是张三的签名',
          articleContent: '路由守卫 (导航守卫)及其具体使用. 导航守卫其实也是路由守卫，\n也可以是路由拦截，我们可以通过路由拦截，来判断用户是否登录，该页面用户是否有权限浏览，需要结合meta来实现. 所谓的路由守卫可以简单的理解为一座房子的门口的保安，想要进入这个房子就必须通过保安的检查，要告诉路由守卫你从哪里来？. 总不能随便陌生人就给放进去？',
          imgs: [require("@/assets/images/item/item_01.png"), require("@/assets/images/item/item_02.png"), require("@/assets/images/item/item_03.png"), require("@/assets/images/item/item_04.png"), require("@/assets/images/item/item_05.png"), require("@/assets/images/item/item_05.png"), require("@/assets/images/item/item_01.png"), require("@/assets/images/item/item_02.png"), require("@/assets/images/item/item_03.png"), require("@/assets/images/item/item_04.png"),],
          articleViewCount: 0,
          user: {
            avatar: ''
          }
        },
      ],
      dialogDescription:false,
      dialogNotice:false
    }
  },
  created() {
    this.getData()
  },
  methods: {
    // 数据获取
    getData() {
      let params = this.$route.query.id;
      return new Promise((resolve, reject) => {
        getLabelItem(params).then(res => {
          console.log(res)
          this.labelData = res.data
          this.getDataList(res.data.lableName)
        }).catch(error => {
          reject(error)
        })
      })
    },
    getDataList(LabelName) {
      let params = {articleLable: LabelName}
      return new Promise((resolve, reject) => {
        getArticleList(params).then(res => {
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

<style lang="scss" scoped>
.topic-header {
  width: 100%;
  height: 280px;
  overflow: hidden;
  border-radius: 12px;
  position: relative;

  img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }

  .wrap-mask{
    position: absolute;
    width: 100%;
    height: 100%;
    top: 0;
    background: rgba(0,0,0,.4);
    padding: 40px ;
    color: #FFFFFF;
    .topic-title{
      font-size: 34px;
      line-height: 34px;
      display: inline-block;
      margin-bottom: 20px;
    }
    .description{
      font-size: 16px;
      line-height: 25px;
      height: 75px;
      white-space: pre-line;
      overflow: hidden;
      text-overflow: ellipsis;
      display: -webkit-box;
      -webkit-box-orient: vertical;
      -webkit-line-clamp: 3;
    }
    .dialogDescription{
      font-size: 16px;
      line-height: 25px;
      white-space: pre-line;
    }
  }
}
</style>