<template>
  <div class="fe-page">
    <div id="articleDetail" class="fe-scroll-y fe-ptd-30">
      <Comment></Comment>
      <BackTop :ID='"articleDetail"'></BackTop>
      <div class="fe-page-container fe-flex-between ">
        <div class="fe-container-left ">
          <!--文章-->
          <div class="fe-shadow article-detail">
            <h1>{{ detail.articleTitle }}</h1>
            <div class="detail-title">
              <div class="fe-flex">
                <img @click="jumpDetail('userPage',createUser.userId)" :src="'http://localhost:8080'+ createUser.avatar"
                     alt="">
                <div class="detail-text flex-column-between">
                  <!--          昵称-->
                  <span @click="jumpDetail('userPage',createUser.userId)" class="fe-url"> {{
                      createUser.nickName || "张三"
                    }}</span>
                  <!--          信息-->
                  <span>
                  <span>{{ detail.createTime }}</span>
                  <span>字数: {{ wordCount }}</span>
                  <span>阅读: {{ detail.articleViewCount }}</span>
                </span>
                </div>
              </div>
              <p>
                <el-button type="primary" v-if="!followView" @click="follow(createUser.userId)" plain size="mini">关注
                </el-button>
                <el-button type="primary" v-else disabled plain size="mini">已关注</el-button>
              </p>
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
              <span><i class="iconfont icon-star"
                       @click="collect(detail.articleId)"/><span>{{ detail.articleCollectCount }}</span></span>
              <span><i class="iconfont icon-message"
                       @click="commentDialog"/><span>{{ detail.articleCommentCount }}</span></span>
              <span><i class="iconfont icon-like"
                       @click="praiseArticle(detail.articleId,createUser.userId)"/><span>{{detail.praiseCount - detail.articleTrampleCount}}</span>
                <i @click="trampleArticle(detail.articleId,createUser.userId)" class="iconfont icon-step"/></span>
            </div>
          </div>

          <CommentPost
              ref="CommentPost"
              :articleId="this.$route.query.id"
              :createBy="this.user.userInfo.userName"
              :parentId="parentId"
              :answerUser="answerUser"
          >

          </CommentPost>

          <!--评论-->
          <div class=" fe-shadow comment-main">
            <!--            评论的头部选择-->
            <div class="comment-top fe-flex-between">
              <div class="fe-align-center">
                <span class="blue-bar"></span>
                <span>全部评论</span><span>{{ comment.length }}</span>
              </div>
              <div>
                <el-dropdown @command="handleCommand" :hide-on-click="false">
                  <span class="el-dropdown-link">
                    {{ SortOption[sort] }}<i class="el-icon-arrow-down el-icon--right"></i>
                  </span>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command='0'>按热度排序</el-dropdown-item>
                    <el-dropdown-item command='1'>按时间正序</el-dropdown-item>
                    <el-dropdown-item command='2'>按时间倒序</el-dropdown-item>
                    <el-dropdown-item command='3'>只看作者</el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
              </div>
            </div>
            <!--            评论详情-->
            <ul>
              <li v-for="item in newTree" :key="item.id">
                <CommentSub :item="item"></CommentSub>
              </li>
            </ul>
          </div>

          <!--          // el-drawer组件-->
          <el-drawer
              title="更多评论"
              :visible.sync="drawer"
              size="80%"
              :modal="false"
              @closed="newTreeSub = []"
              direction="btt">
            <ul v-if="newTreeSub.length">
              <li v-for="item in newTreeSub" :key="item.id" class="comment-item fe-flex">
                <CommentSub :item="item"></CommentSub>
              </li>
            </ul>
          </el-drawer>
        </div>

        <div class="fe-container-right " style="background-color: #FFFFFF">
          <img src="~@/assets/images/article/detail.svg" width="100%" alt="">
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import 'video.js/dist/video-js.css'
import 'vue-video-player/src/custom-theme.css'
import {videoPlayer} from 'vue-video-player'
import Comment from "@/views/components/Comment";
import BackTop from "@/views/components/BackTop";
import {getCommentList, getArticleItem, getArticleCollect} from "@/api/system/article";
import {mapState} from "vuex";
import {buildTree} from "@/utils/mapTree"
import CommentSub from "@/views/components/CommentSub";
import CommentPost from "@/views/components/CommentPost";
import {getUserNameInfo} from "@/api/login";
import {getFollowList, UserFollow, ArticlePraise,unArticlePraise,ArticleTrample} from "@/api/system/operation"

export default {
  name: "ArticleDetail",
  components: {
    CommentPost,
    CommentSub,
    BackTop,
    Comment,
    videoPlayer
  },
  data() {
    return {
      parentId: null,
      answerUser: null,
      createUser: {
        avatar: "/profile/avatar/2021/04/19/6988e1c5-ba19-4de5-9092-700db4a7bee6.jpeg",
        nickName: "",
        userName: "admin",
        userId: 2
      },
      detail: {
        createUser: "",
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
      comment: [
        {
          id: 1,
          createUser: {
            avatar: "/profile/upload/2021/04/20/a75f8fe1-1b29-45f5-923a-932b7fc59d98.png",
            nickName: "李四",
            userName: "admin",
            userId: 2
          },

          createTime: "2021-04-19 23:54:58",
          content: "奥i毒发你打开就发你肯定就爱上了\n案件那就拉的看法呢\ni拿到结果你就骄傲的发你觉得爱哭的房间\n酷酷酷",
          imgs: ["http://localhost:8080/profile/upload/2021/04/20/90ad9aa0-5d83-43ad-8e24-81b52719b5d6.png", "http://localhost:8080/profile/upload/2021/04/20/a75f8fe1-1b29-45f5-923a-932b7fc59d98.png"],
          parentId: null,
          answerUser: 1,
          praiseCount: 646,
          trampleCount: 21,
        },
        {
          id: 2,
          createUser: {
            avatar: "/profile/upload/2021/04/20/fb56fba8-b143-433d-8d3f-2d0d0ad671ee.png",
            nickName: "李四",
            userName: "admin",
            userId: 1
          },
          createTime: "2021-04-19 23:54:58",
          content: "奥毒发你打开就发你肯定就爱上了\n案件那就拉的看法呢\ni拿到结果你就骄傲的发你觉得爱哭的房酷酷酷",
          imgs: ["http://localhost:8080/profile/upload/2021/04/20/fb56fba8-b143-433d-8d3f-2d0d0ad671ee.png"],
          parentId: null,
          answerUser: 1,
          praiseCount: 646,
          trampleCount: 21,
        },
        {
          id: 3,
          createUser: {
            avatar: "/profile/avatar/2021/04/19/6988e1c5-ba19-4de5-9092-700db4a7bee6.jpeg",
            nickName: "李四",
            userName: "admin",
            userId: 1
          },
          createTime: "2021-04-19 23:54:58",
          content: "奥i毒发你打开就发你肯定就爱上了\n案件那就拉的看法呢\ni拿到结果你就骄傲的发你觉得爱哭的房间\n酷酷酷",
          imgs: [],
          parentId: null,
          answerUser: null,
          praiseCount: 646,
          trampleCount: 21,
        },
        {
          id: 4,
          createUser: {
            avatar: "/profile/avatar/2021/04/19/6988e1c5-ba19-4de5-9092-700db4a7bee6.jpeg",
            nickName: "李四",
            userName: "admin",
            userId: 1
          },
          createTime: "2021-04-19 23:54:58",
          content: "奥i毒发你打开就发你肯定就爱上了\n案件那就拉的看法呢\ni拿到结果你就骄傲的发你觉得爱哭的房间\n酷酷酷",
          imgs: [
            "http://localhost:8080/profile/upload/2021/04/20/7cb49ca0-5e8b-4eba-a50f-14860ad4c6fd.png",
            "http://localhost:8080/profile/upload/2021/04/19/a774eff0-7a97-45ee-8863-38a96fc4cec1.jpg",
            "http://localhost:8080/profile/upload/2021/04/20/33fdd237-aaef-4c8b-976f-70ab8a46bb07.png"
          ],
          parentId: null,
          answerUser: null,
          praiseCount: 646,
          trampleCount: 21,
        },
        {
          id: 5,
          createUser: {
            avatar: "/profile/upload/2021/04/20/05513d56-e630-49b3-af91-bcb7a34f610c.png",
            nickName: "李四",
            userName: "admin",
            userId: 1
          },
          createTime: "2021-04-19 23:54:58",
          content: "奥i毒发你打开就发你肯定就爱上了\n案件那就拉的看法呢\ni拿到结果你就骄傲的发你觉得爱哭的房间\n酷酷酷",
          imgs: ["http://localhost:8080/profile/upload/2021/04/20/05513d56-e630-49b3-af91-bcb7a34f610c.png", "http://localhost:8080/profile/upload/2021/04/19/a774eff0-7a97-45ee-8863-38a96fc4cec1.jpg"],
          parentId: 1,
          answerUser: null,
          praiseCount: 646,
          trampleCount: 21,
        },
        {
          id: 6,
          createUser: {
            avatar: "/profile/avatar/2021/04/19/6988e1c5-ba19-4de5-9092-700db4a7bee6.jpeg",
            nickName: "李四",
            userName: "admin",
            userId: 2
          },
          createTime: "2021-04-19 23:54:58",
          content: "奥i毒发你打开就发你肯定就爱上了\n案件那就拉的看法呢\ni拿到结果你就骄傲的发你觉得爱哭的房间\n酷酷酷",
          imgs: ["http://localhost:8080/profile/upload/2021/04/20/05513d56-e630-49b3-af91-bcb7a34f610c.png", "http://localhost:8080/profile/upload/2021/04/19/a774eff0-7a97-45ee-8863-38a96fc4cec1.jpg"],
          parentId: 4,
          answerUser: null,
          praiseCount: 646,
          trampleCount: 21,
        },
        {
          id: 7,
          createUser: {
            avatar: "/profile/avatar/2021/04/19/6988e1c5-ba19-4de5-9092-700db4a7bee6.jpeg",
            nickName: "李四",
            userName: "admin",
            userId: 2
          },
          createTime: "2021-04-19 23:54:58",
          content: "奥i毒发你打开就发你肯定就爱上了\n案件那就拉的看法呢\ni拿到结果你就骄傲的发你觉得爱哭的房间\n酷酷酷",
          imgs: ["http://localhost:8080/profile/upload/2021/04/20/05513d56-e630-49b3-af91-bcb7a34f610c.png", "http://localhost:8080/profile/upload/2021/04/19/a774eff0-7a97-45ee-8863-38a96fc4cec1.jpg"],
          parentId: 4,
          answerUser: null,
          praiseCount: 646,
          trampleCount: 21,
        },
        {
          id: 8,
          createUser: {
            avatar: "/profile/avatar/2021/04/19/6988e1c5-ba19-4de5-9092-700db4a7bee6.jpeg",
            nickName: "李四",
            userName: "admin",
            userId: 1
          },
          createTime: "2021-04-19 23:54:58",
          content: "奥i毒发你打开就发你肯定就爱上了\n案件那就拉的看法呢\ni拿到结果你就骄傲的发你觉得爱哭的房间\n酷酷酷",
          imgs: ["http://localhost:8080/profile/upload/2021/04/20/7a86a1ef-dfa9-4997-a7b5-27c8820bb7e7.png", "http://localhost:8080/profile/upload/2021/04/19/a774eff0-7a97-45ee-8863-38a96fc4cec1.jpg"],
          parentId: 4,
          answerUser: null,
          praiseCount: 646,
          trampleCount: 21,
        },
        {
          id: 9,
          createUser: {
            avatar: "/profile/avatar/2021/04/19/6988e1c5-ba19-4de5-9092-700db4a7bee6.jpeg",
            nickName: "李四",
            userName: "admin",
            userId: 1
          },
          createTime: "2021-04-19 23:54:58",
          content: "奥i毒发你打开就发你肯定就爱上了\n案件那就拉的看法呢\ni拿到结果你就骄傲的发你觉得爱哭的房间\n酷酷酷",
          imgs: ["http://localhost:8080/profile/upload/2021/04/20/7a86a1ef-dfa9-4997-a7b5-27c8820bb7e7.png",
            "http://localhost:8080/profile/upload/2021/04/20/9657b311-dc0a-4a6e-8acf-27310ccd0472.png"],
          parentId: 3,
          answerUser: null,
          praiseCount: 646,
          trampleCount: 21,
        },
        {
          id: 10,
          createUser: {
            avatar: "/profile/avatar/2021/04/19/6988e1c5-ba19-4de5-9092-700db4a7bee6.jpeg",
            nickName: "李四",
            userName: "admin",
            userId: 1
          },
          ansUser: {
            avatar: "/profile/avatar/2021/04/19/6988e1c5-ba19-4de5-9092-700db4a7bee6.jpeg",
            nickName: "李四",
            userName: "admin"
          },
          createTime: "2021-04-19 23:54:58",
          content: "奥i毒发你打开就发你肯定就爱上了\n案件那就拉的看法呢\ni拿到结果你就骄傲的发你觉得爱哭的房间\n酷酷酷",
          imgs: ["http://localhost:8080/profile/upload/2021/04/20/7a86a1ef-dfa9-4997-a7b5-27c8820bb7e7.png",
            "http://localhost:8080/profile/upload/2021/04/20/e62a29a6-f90e-4ead-8846-972a1ac20812.png"],
          parentId: 1,
          answerUser: null,
          praiseCount: 646,
          trampleCount: 21,
        },
        {
          id: 11,
          createUser: {
            avatar: "/profile/avatar/2021/04/19/6988e1c5-ba19-4de5-9092-700db4a7bee6.jpeg",
            nickName: "李四",
            userName: "admin",
            userId: 2
          },
          ansUser: {
            avatar: "/profile/avatar/2021/04/19/6988e1c5-ba19-4de5-9092-700db4a7bee6.jpeg",
            nickName: "李四",
            userName: "admin"
          },
          createTime: "2021-04-19 23:54:58",
          content: "奥i毒发你打开就发你肯定就爱上了\n案件那就拉的看法呢\ni拿到结果你就骄傲的发你觉得爱哭的房间\n酷酷酷",
          imgs: ["http://localhost:8080/profile/upload/2021/04/20/fe95a95d-c127-464b-b7de-d1c91a6b2ef2.png",
            "http://localhost:8080/profile/upload/2021/04/20/1c25b628-f0e8-4273-8df3-ce6d87920709.png"],
          parentId: 3,
          answerUser: null,
          praiseCount: 646,
          trampleCount: 21,
        },
        {
          id: 12,
          createUser: {
            avatar: "/profile/avatar/2021/04/19/6988e1c5-ba19-4de5-9092-700db4a7bee6.jpeg",
            nickName: "李四",
            userName: "admin",
            userId: 1
          },
          createTime: "2021-04-19 23:54:58",
          content: "奥i毒发你打开就发你肯定就爱上了\n案件那就拉的看法呢\ni拿到结果你就骄傲的发你觉得爱哭的房间\n酷酷酷",
          imgs: ["http://localhost:8080/profile/upload/2021/04/20/1c25b628-f0e8-4273-8df3-ce6d87920709.png",
            "http://localhost:8080/profile/upload/2021/04/20/e62a29a6-f90e-4ead-8846-972a1ac20812.png"],
          parentId: 1,
          answerUser: null,
          praiseCount: 646,
          trampleCount: 21,
        },

      ],
      // comment:[],
      newTree: [],
      newTreeSub: [],
      SortOption: ['按热度排序', '按时间正序', '按时间倒序', '只看作者'],
      sort: 0,
      isHidden: true,
      drawer: false,
      followView: null,
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
    },
    ...mapState(["user"]),
  },
  mounted() {
    this.getData()
    // this.addForm.articleId = this.$route.query.id
    // this.addForm.createBy = this.user.userInfo.userName

  },
  methods: {
    // 基础数据调用，文章详情
    getData() {
      return new Promise((resolve, reject) => {
        getArticleItem(this.$route.query.id).then(res => {
          console.log('文章详情detail', res.data)
          this.detail = res.data
          this.playerOptions['sources'][0]['src'] = res.data.articleVediourls
          this.getUserInfo(this.detail.createUser)
        }).catch(error => {
          reject(error)
        })
      })
    },

    follow(userId) {
      let data = {
        createBy: this.user.userInfo.userName,
        followId: this.user.userInfo.userId,
        userId: userId
      }
      return new Promise((resolve, reject) => {
        UserFollow(data).then(res => {
          console.log('关注用户', res.data)
        }).catch(error => {
          reject(error)
        })
      })
    },
    jumpDetail(name, id) {
      this.$router.push({
        name,
        query: {
          id,
        }
      })
    },
    // 作者信息
    getUserInfo(userName) {
      return new Promise((resolve, reject) => {
        getUserNameInfo(userName).then(res => {
          console.log('文章用户', res.data)
          this.createUser = res.data
          this.getCommentData()
          this.getFollowData(this.createUser.userId)
        }).catch(error => {
          reject(error)
        })
      })
    },
    getFollowData(userId) {
      let data = {
        followId: this.user.userInfo.userId,
        userId: userId
      }
      return new Promise((resolve, reject) => {
        getFollowList(data).then(res => {
          console.log("关注没有", res)
          this.followView = res.total
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 查询文章对应评论列表
    getCommentData() {
      let data = {
        articleId: this.$route.query.id
      }
      console.log("data", data)
      return new Promise((resolve, reject) => {
        getCommentList(data).then(res => {
          console.log('文章评论列表', res)
          this.comment = res.rows
          this.newTree = buildTree(this.comment)
          this.newTree.reverse()
          console.log(this.newTree)
          console.log(typeof (this.newTree))
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 选择排序条件
    handleCommand(command) {
      console.log(command)
      this.sort = command
    },
    // 收藏文章
    collect(id) {
      let data = {
        articleId: id,
        userId: this.user.userInfo.userId,
      }
      return new Promise((resolve, reject) => {
        getArticleCollect(data).then(res => {
          console.log('文章收藏', res)
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 评论弹窗
    commentDialog(item, index) {
      console.log("item", item)
      if (index === 2) {
        this.$refs.CommentPost.commentDialog(item.parentId, item.createUser.userId)
      } else if (index === 1) {
        this.$refs.CommentPost.commentDialog(item.id, item.createUser.userId)
      } else {
        this.$refs.CommentPost.commentDialog(null, this.createUser.userId)
      }

      // this.answerUser = item.answerUser
      // this.parentId = item.parentId
    },
    // 点赞文章
    praiseArticle(articleId, likedUserId) {
      let data = {
        articleId: articleId,
        likedUserId: likedUserId,
        userId: this.user.userInfo.userId,
      }
      return new Promise((resolve, reject) => {
        ArticlePraise(data).then(res => {
          console.log('文章点赞', res)
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 取消文章点赞
    unPraiseArticle(articleId, likedUserId) {
      let data = {
        articleId: articleId,
        likedUserId: likedUserId,
        userId: this.user.userInfo.userId,
      }
      return new Promise((resolve, reject) => {
        unArticlePraise(data).then(res => {
          console.log('文章点赞', res)
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 踩文章
    trampleArticle(articleId, likedUserId){
      let data = {
        articleId: articleId,
        likedUserId: likedUserId,
        userId: this.user.userInfo.userId,
      }
      return new Promise((resolve, reject) => {
        ArticleTrample(data).then(res => {
          console.log('文章踩', res)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 更多评论
    drawerSub(data) {
      console.log(data)
      this.newTreeSub = data.reverse()
      this.drawer = true
    },

  }
}
</script>

<style lang="scss" scoped>
.article-detail {
  background-color: #FFFFFF;
  padding: 32px;
  border-radius: 6px;

  //标题
  .detail-title {
    margin-top: 12px;
    display: flex;
    align-items: center;
    justify-content: space-between;

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


  //视频
  .detail-video {
    border-radius: 6px;
    overflow: hidden;
    margin-top: 12px;
  }

  //控件
  .detail-control {
    height: auto;
    margin-top: 26px;
    font-size: 16px;

    span {
      display: flex;
      align-items: center;

      i {
        font-size: 18px;

        &:hover {
          color: #169bfa;
        }
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

.blue-bar {
  display: inline-block;
  width: 4px;
  height: 20px;
  background: #9ACAFB;
  margin-right: 12px;
}


//评论
.comment-main {
  margin-top: 20px;
  background-color: #FFFFFF;
  width: 100%;
  padding: 32px;
  height: auto;
  border-radius: 6px;

  .comment-top {
    height: 40px;
    width: 100%;
    align-items: center;
    font-size: 18px;

    span {
      line-height: 20px;

      &:nth-child(2) {
        margin-right: 12px;
      }

      &:nth-child(3) {
        display: inline-block;
        padding: 0 4px;
        font-size: 14px;
        border-radius: 5px;
        color: #969696;
        border: 1px solid #eeeeee;
      }
    }
  }

  .comment-item-sub {
    background-color: #F5F5F7;
    border-radius: 6px;
    padding: 14px;
    margin-top: 12px;

    .comment-sub-p {
      font-size: 14px;
      color: #2d3e53;
      line-height: 26px;
      white-space: pre-line;
      overflow: hidden;
      text-overflow: ellipsis;
      display: -webkit-box;
      -webkit-box-orient: vertical;
      -webkit-line-clamp: 2;
    }

    .comment-sub-more {
      font-size: 14px;
      color: #169bfa;
      line-height: 26px;
      cursor: pointer;
    }
  }

  .el-dropdown-link {
    display: inline-block;
    padding: 0 10px;
    font-size: 14px;
    border-radius: 5px;
    border: 1px solid #eeeeee;
    line-height: 20px;
  }
}

//评论
.comment-item {
  width: 100%;
  height: auto;
  padding: 20px 0 0;

}

//图片
.detail-img {

  display: flex;
  flex-wrap: wrap;

  .detail-img-item {
    width: 96px;
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

.grey-bar {
  display: inline-block;
  width: 1px;
  height: 20px;
  background: #E0E0E0;
  margin-right: 12px;
}

::v-deep
.el-drawer__container {
  transform: translateX(-158px);

  .el-drawer {
    height: 70%;
    width: 700px;
    margin: 0 auto;
  }

  .el-drawer__header {
    padding: 20px 32px;
    margin-bottom: 0;
    font-size: 16px;
  }

  .el-drawer__body {
    padding: 0 32px 20px;
    overflow-y: auto;

    &::-webkit-scrollbar {
      display: none;
    }
  }
}

</style>