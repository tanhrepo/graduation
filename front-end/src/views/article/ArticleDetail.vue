<template>
  <div class="fe-page">
    <div class="fe-scroll-y fe-ptd-30">
      <div class="fe-page-container fe-flex-between ">
        <div class="fe-container-left fe-shadow article-detail">
          <h1>{{ detail.articleTitle }}</h1>
          <div class="detail-title">
            <img src="~@/assets/images/item/item_01.png" alt="">
            <div class="detail-text flex-column-between">
              <!--          昵称-->
              <span class="fe-url">{{ detail.nickName }}</span>
              <!--          信息-->
              <span>
                <span>{{ detail.createTime }}</span>
                <span>字数: {{ wordCount }}</span>
                <span>阅读: {{ detail.articleViewCount }}</span>
              </span>
            </div>
          </div>
          <p class="detail-content">{{ detail.articleContent }}</p>
          <!--    图片-->
          <div v-if="!detail.articleVediourls">
            <viewer class="detail-img">
              <div v-for="(item,index) in detail.imgs" :key="index" class="detail-img-item">
                <img :src="item" alt="">
              </div>
            </viewer>
          </div>
          <!--    视频-->
          <div v-if="detail.articleVediourls" class="detail-video">
            <video-player class="video-player vjs-custom-skin"
                          ref="videoPlayer"
                          :playsinline="true"
                          :options="playerOptions"
            >
            </video-player>
          </div>
          <!--    控件-->
          <div class="fe-flex-between detail-control">
            <span><i class="iconfont icon-share"/><span>{{ detail.articleTransmitCount }}</span></span>
            <span><i class="iconfont icon-star"/><span>{{ detail.articleCollectCount }}</span></span>
            <span><i class="iconfont icon-message"/><span>{{ detail.articleCommentCount }}</span></span>
            <span><i class="iconfont icon-like"/><span>2345</span><i class="iconfont icon-step"/></span>
          </div>
        </div>
        <div class="fe-container-right " style="background-color: #FFFFFF">
          <img src="~@/assets/images/article/detail.svg" width="100%" alt="">
        </div>
      </div>
      <footer class="w-100" style="position: relative">
        <div class="detail-footer"></div>
      </footer>
    </div>

  </div>
</template>

<script>
import 'video.js/dist/video-js.css'
import 'vue-video-player/src/custom-theme.css'
import {videoPlayer} from 'vue-video-player'

export default {
  name: "ArticleDetail",
  components: {
    videoPlayer
  },
  data() {
    return {
      detail: {
        nickName: '张三',
        createTime: '2020-12-10',
        articleTitle: '这是一个淡淡的标题',
        articleContent: '一只公鸡，由于不愿参加一场斗鸡比赛，用身上绑着的刀，刺中其主人，最终导致对方因失血过多死亡。\n' +
            '\n' +
            '这起有些匪夷所思的事件，于2月23日发生在印度特伦甘纳邦的贾格尔地区。\n' +
            '\n' +
            '这只公鸡主人给它起名叫‘’拉贾"。由于主人萨蒂什带它到郊区参加一场斗鸡比赛。萨蒂什在公鸡的腿上绑了一把3英寸（约7.6厘米）的刀子，然后将其送入赛场。\n' +
            '\n' +
            '但这只公鸡却不愿意参加比赛，在它试图逃离时，主人萨蒂什抓住了它，但公鸡腿上的刀刃刚好刺穿了萨蒂什的腹股沟。在被送往医院的途中，萨蒂什因失血过多身亡。\n' +
            '\n' +
            '自案发以来，这只公鸡一直被当地警察局拘留。未来它将现身法庭，作为一起有15人参加非法斗鸡的起诉证据的一部分。当地警察表示，警方正在寻找参与此次组织非法斗鸡的另外15人。根据印度法律，这些参与者将为过失导致萨蒂什的死亡承担部分责任，他们还可能被指控过失杀人，非法赌博和举办斗鸡比赛等罪名。\n',
        imgs: [require('@/assets/images/item/item_01.png'), require('@/assets/images/item/item_02.png'), require('@/assets/images/item/item_03.png'), require('@/assets/images/item/item_04.png'), require('@/assets/images/item/item_05.png'), require('@/assets/images/item/item_01.png'), require('@/assets/images/item/item_02.png'), require('@/assets/images/item/item_03.png'), require('@/assets/images/item/item_04.png'), require('@/assets/images/item/item_05.png'),],
        articleVediourls: '',
        articleCommentCount: 6545,
        articleCollectCount: 2345,
        articleTransmitCount: 2345,
        articleTrampleCount: 564,
        articleViewCount: 3456,
      },
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
  computed: {
    wordCount() {
      let sLen = 0;
      try {
        //先将回车换行符做特殊处理
        let str = this.detail.articleContent.replace(/(\r\n+|\s+|　+)/g, "囖龘");
        //处理英文字符数字，连续字母、数字、英文符号视为一个单词
        str = str.replace(/[\x00-\xff]/g, "m");
        //合并字符m，连续字母、数字、英文符号视为一个单词
        str = str.replace(/m+/g, "*");
        //去掉回车换行符
        str = str.replace(/囖龘+/g, "");
        //返回字数
        sLen = str.length;
      } catch (e) {

      }
      return sLen;
    }
  },
  mounted() {
    console.log(this.$route.query.id)
  },
  methods: {}
}
</script>

<style lang="scss" scoped>
.article-detail {
  background-color: #FFFFFF;
  padding: 20px;

  //标题
  .detail-title {
    margin-top: 12px;
    display: flex;
    align-items: center;

    img {
      object-fit: cover;
      width: 40px;
      height: 40px;
      border-radius: 50%;
      margin-right: 12px;
    }

    .detail-text {
      height: 38px;

      span:nth-child(1) {
        font-size: 14px;
        color: #696A6E;
      }

      span:nth-child(2) {
        span {
          font-size: 12px;
          color: #B8B8B8;
          margin-right: 12px;
        }
      }
    }
  }

  //内容
  .detail-content {
    font-size: 14px;
    color: #2d3e53;
    line-height: 26px;
    white-space: pre-line;
    margin-top: 40px;
  }

  //图片
  .detail-img {
    display: flex;
    flex-wrap: wrap;

    .detail-img-item {
      width: 100px;
      height: 100px;
      margin: 12px 12px 0 0;
      border-radius: 5px;
      overflow: hidden;

      &:nth-child(6),
      &:nth-child(12) {
        margin-right: 0;
      }

      img {
        width: 100%;
        height: 100%;
        object-fit: cover;
      }
    }
  }

  //视频
  .detail-video {
    border-radius: 6px;
    overflow: hidden;
    margin-top: 12px;
  }

  //控件
  .detail-control {
    height: auto;
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

}
//页脚评论
.detail-footer{
  left: 0;
  right: 0;
  bottom: 0;
  height: 56px;
  background-color: #169bfa;
  position: fixed;

  div:nth-child(1){
    width: 1000px;
    margin: 0 auto;
    height: 100%;
    display: flex;
    align-items: center;
    background-color: #30B08F;
  }
}

</style>