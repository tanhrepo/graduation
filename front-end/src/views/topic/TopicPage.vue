<!--话题页-->
<template>
  <div class="fe-page">
    <div id="VideoPage" class="fe-scroll-y fe-ptd-30">
      <BackTop :ID='"VideoPage"'></BackTop>
      <div class="fe-page-container fe-flex-between">
        <div class="fe-container-left topic-container" style="background-color: #FFFFFF">
          <div v-for="item in itemData" :key="item.articleId">
            <TopicItem :ItemData="item"></TopicItem>
          </div>
        </div>
        <div class="fe-container-right">
          <img src="~@/assets/images/article/growth.svg" width="100%" alt="">
          <div class="fe-flex-between h-auto fe-font-lg refresh-q">
            <span>推荐话题</span>
            <span @click="refresh" class="refresh"><i class="iconfont icon-refresh"></i>换一批</span>
          </div>
          <div v-for="(item,index) in itemData.slice(start,stop)" class="top" :class="topicRight[index]">
            <p># {{ item.lableName }}</p>
            <img :src="img[index]" alt="">
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import BackTop from "@/views/components/BackTop";
import {getLabelList} from "@/api/system/label";
import TopicItem from "@/views/topic/components/TopicItem";

const color = ['#B7D3EC', '#FFB84F', '#F4E0BD', '#C1E4DE']
export default {
  name: "TopicPage",
  components: {TopicItem, BackTop},
  data() {
    return {
      itemData: [],
      topicRight: ["top1", "top2", "top3", "top4"],
      img: [
        require("@/assets/images/article/purple2.svg"),

        require("@/assets/images/article/purple1.svg"),
        require("@/assets/images/article/purple3.svg"),
        require("@/assets/images/article/purple4.svg"),
      ],
      start:0,
      stop:4,
    }
  },
  created() {
    this.getData()
  },
  methods: {
    // 数据获取
    getData() {
      return new Promise((resolve, reject) => {
        getLabelList().then(res => {
          console.log(res)
          this.itemData = res.rows
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 推荐刷新
    refresh(){
      let i = 4
      this.start += i
      this.stop += i
      if (this.stop > this.itemData.length) {
        this.start = 0
        this.stop = 4
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.topic-container {
  columns: 3;
  column-gap: 16px;
}

.top {
  position: relative;
  padding: 12px 0 12px 24px;
  border-radius: 6px;
  margin-top: 12px;
  cursor: pointer;

  &:hover{
    transition: 0.3s;
    transform: translateY(3px);
    box-shadow:  6px 6px 12px #d7d7d7,
    -6px -6px 12px #e9e9e9;
  }

  img {
    position: absolute;
    right: -1px;
    height: 64px;
    top: -18px;
  }

  p {
    font-size: 18px;
    font-weight: 600;
    line-height: 22px;
    width: 160px;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 1;

  }

}

.top1 {
  background-color: #B7D3EC;
  color: #5E81B9;
}

.top2 {
  background-color: #FFB84F;
  color: #FFFFFF;
}

.top3 {
  background-color: #F4E0BD;
  color: #BF903B;
}

.top4 {
  background-color: #C1E4DE;
  color: #518D85;
}
.refresh-q{
  color:#999999;
  margin-top: 14px;
  .refresh{
    display: flex;
    align-items: center;
    cursor: pointer;
    i{
      font-size: 14px;
      margin-right: 4px;
    }
  }
}

</style>