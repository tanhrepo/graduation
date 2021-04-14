<template>
  <div class="text-item fe-bd-b">
    <div class="text-item-title fe-flex-between">
      <div class="title-container">
        <img :src="ItemData.userImg" alt="">
        <div class="flex-column-between title-text">
          <span>{{ ItemData.articleTitle }}</span>
          <span>{{ ItemData.nickName }}</span>
        </div>
      </div>
      <p><el-button type="primary" plain size="mini">关注</el-button></p>
    </div>
    <p class="text-item-content" @click="isHidden = !isHidden" :class="{'text-item-content-hidden':isHidden}">
      {{ ItemData.articleContent }}
    </p>
    <div class="item-img" v-if="ItemData.imgs.length">
      <viewer class="item-img">
        <div v-for="(item,index) in ItemData.imgs" :key="index" class="item-img-item" >
          <img :src="item" alt="">
        </div>
      </viewer>
    </div>
    <div class="fe-flex-between item-control">
      <span><i class="iconfont icon-share" /><span>2443</span></span>
      <span><i class="iconfont icon-star" /><span>2443</span></span>
      <span><i class="iconfont icon-message" /><span>2443</span></span>
      <span><i class="iconfont icon-like" /><span>2443</span><i class="iconfont icon-step" /></span>
    </div>

  </div>
</template>

<script>
export default {
  name: "TextItem",
  props: ['ItemData'],
  data(){
    return{
      isHidden:true
    }
  },
  mounted() {
    console.log(1)
    console.log(   this.ItemData)
  }
}
</script>

<style lang="scss" scoped>
.text-item {
  display: flex;
  flex-direction: column;
  padding: 12px 12px 20px;

  .text-item-title{
    height: 40px;
    align-items: center;
    margin-bottom: 12px;

    .title-container{
      display: flex;
      align-items: center;
      img{
        width: 40px;
        height: 40px;
        border-radius: 50%;
        margin-right: 12px;
      }

      .title-text{
        height: 38px;

        span:nth-child(1){
          font-size: 14px;
          color: #696A6E;
        }

        span:nth-child(2){
          font-size: 12px;
          color: #B8B8B8;
        }
      }
    }

    .el-button{
      border-radius: 12px;
    }
  }

  .text-item-content{
    font-size: 14px;
    color: #2d3e53;
    line-height: 25px;
    white-space: pre-line;
  }
  .text-item-content-hidden{
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
  }

  .item-img{
    display: flex;
    flex-wrap: wrap;
    .item-img-item{
      width: 100px;
      height: 100px;
      margin: 12px 12px 0 0;
      border-radius: 5px;
      overflow: hidden;

      img{
        width: 100%;
        height: 100%;
        object-fit:cover;
      }
    }
  }

  .item-control{
    margin-top: 12px;
    font-size: 16px;
    span{
      display: flex;
      align-items: center;
      span{
        margin: 0 12px;
      }
    }
  }
}
</style>