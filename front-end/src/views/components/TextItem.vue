<template>
  <div class="text-item fe-bd-b">
    <div class="text-item-title">
      <div class="title-container">
        <img class="fe-url" @click="jumpDetail('userPage',ItemData.user.userId)" v-if="ItemData.user.avatar" :src="'http://localhost:8080' + ItemData.user.avatar" alt="">
        <div class="flex-column-between title-text">
          <!--          标题-->
          <span @click="jumpDetail('detail',ItemData.articleId)" class="fe-url">{{ ItemData.articleTitle }}</span>
          <!--          昵称-->
          <span @click="jumpDetail('userPage',ItemData.user.userId)" class="fe-url">{{ ItemData.user.nickName }}</span>
        </div>
      </div>

    </div>
    <!--    文章内容-->
    <p class="text-item-content" @click="isHidden = !isHidden" :class="{'text-item-content-hidden':isHidden}">
      {{ ItemData.articleContent }}
    </p>
    <!--    图片-->
    <div class="item-img" v-if="!ItemData.articleVediourls">
      <viewer class="item-img">
        <div v-for="(item,index) in ItemData.imgs" :key="index" class="item-img-item">
          <img :src="item" alt="">
        </div>
      </viewer>
    </div>
    <!--    视频-->
    <div v-if="ItemData.articleVediourls" class="item-video">
      <video-player class="video-player vjs-custom-skin"
                    ref="videoPlayer"
                    :playsinline="true"
                    :options="playerOptions"
      >
      </video-player>
    </div>
    <!--    控件-->
    <div class="fe-flex-between item-control">
      <span><i class="iconfont icon-share"/><span>{{ ItemData.articleTransmitCount }}</span></span>
      <span><i class="iconfont icon-star"/><span>{{ ItemData.articleCollectCount }}</span></span>
      <span><i class="iconfont icon-message" @click="jumpDetail('detail',ItemData.articleId)" /><span>{{ ItemData.articleCommentCount }}</span></span>
      <span><i class="iconfont icon-like"/><span>{{ ItemData.praiseCount - ItemData.articleTrampleCount }}</span><i class="iconfont icon-step"/></span>
    </div>
  </div>
</template>

<script>
import 'video.js/dist/video-js.css'
import 'vue-video-player/src/custom-theme.css'
import {videoPlayer} from 'vue-video-player'

export default {
  name: "TextItem",
  props: ['ItemData'],
  components: {
    videoPlayer
  },
  data() {
    return {
      isHidden: true,
      playerOptions: {
        playbackRates: [0.7, 1.0, 1.5, 2.0], // 播放速度
        autoplay: false, // 加载完成后是否自动播放
        muted: false, // 是否静音。
        loop: false, // 是否循环播放
        preload: 'auto', // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto 浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
        language: 'zh-CN',
        aspectRatio: '16:9', // 宽高比例
        fluid: true, // 当 true 时，Video.js player 将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
        sources: [{
          type: '', // 这里的种类支持很多种：基本视频格式、直播、流媒体等，具体可以参看 git 网址项目
          src: '' // url 地址
        }],
        poster: '', // 你的封面地址
        notSupportedMessage: '无法播放', // 允许覆盖 Video.js 无法播放媒体源时显示的默认信息。
        controlBar: true // 为 false 时不显示默认的控制按钮
      },
    }
  },
  mounted() {
    this.playerOptions['sources'][0]['src'] = this.ItemData.articleVediourls
  },
  methods: {
    jumpDetail(name,id){
      this.$router.push({
        name,
        query:{
          id,
        }
      })
    },
    praise(){

    }
  },

}
</script>

<style lang="scss" scoped>
.text-item {
  display: flex;
  flex-direction: column;
  padding: 12px 12px 12px;

  .text-item-title {
    height: 40px;
    align-items: center;
    margin-bottom: 12px;

    .title-container {
      display: flex;
      align-items: center;

      img {
        width: 40px;
        height: 40px;
        border-radius: 50%;
        margin-right: 12px;
      }

      .title-text {
        height: 38px;

        span:nth-child(1) {
          font-size: 14px;
          color: #696A6E;
        }

        span:nth-child(2) {
          font-size: 12px;
          color: #B8B8B8;
        }
      }
    }

  }

  .text-item-content {
    font-size: 14px;
    color: #2d3e53;
    line-height: 25px;
    white-space: pre-line;
  }

  .text-item-content-hidden {
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
  }

  .item-img {
    display: flex;
    flex-wrap: wrap;

    .item-img-item {
      width: 100px;
      height: 100px;
      margin: 12px 12px 0 0;
      border-radius: 5px;
      overflow: hidden;
      &:nth-child(6),
      &:nth-child(12){
        margin-right: 0;
      }
      img {
        width: 100%;
        height: 100%;
        object-fit: cover;
      }
    }
  }

  .item-control {
    margin-top: 12px;
    font-size: 16px;

    span {
      display: flex;
      align-items: center;

      i:hover {
        color: #169bfa;
      }

      span {
        display: inline-block;
        width: 40px;
        text-align: center;
        margin: 0 12px;
      }
    }
  }

  .item-video {
    border-radius: 6px;
    overflow: hidden;
    margin-top: 12px;
  }
}
</style>