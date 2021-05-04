<template>
  <div class="comment-item fe-flex">
    <img class="avatar-comment" :src="'http://localhost:8080' + item.createUser.avatar" alt="">
    <div class="comment-right fe-flex-column">
      <!--                  头部-->
      <div class="right-top fe-flex-between">
        <div class="top-left">
          <span>{{ item.createUser.nickName }}</span>
          <span>{{ item.createTime }}</span>
        </div>
        <!-- 评论右边控件-->

        <div class="top-right">
          <span @click="reply(item)" class="reply fe-align-center"><i class="iconfont icon-message"> 回复</i></span>
          <span class="grey-bar"></span>
          <i class="iconfont icon-like"/>
          <span>{{ item.praiseCount - item.trampleCount }}</span>
          <i class="iconfont icon-step"/>
        </div>

      </div>
      <!--                  内容-->
      <p class="right-content" @click="isHidden = !isHidden" :class="{'right-content-hidden':isHidden}">
        <span v-if="item.ansUser">
            <span style="color:#907e7e;margin: 0 4px 0 0">回复</span>
            <span class="fe-url" style="color: #169bfa">
              {{ item.ansUser.nickName }}：
            </span>
          </span>
        {{ item.content }}
      </p>
      <!--                  图片-->
      <div v-if="item.imgs">
        <viewer class="detail-img">
          <div v-for="(Img,index) in item.imgs" :key="index" class="detail-img-item">
            <img :src="Img" alt="">
          </div>
        </viewer>
      </div>
      <!--                  评论-->
      <div v-if="item.children" class="comment-item-sub">
        <p class="comment-sub-p">
          <span class="fe-url" style="color: #169bfa">{{ item.children[0].createUser.nickName }}</span>
          <span v-if="item.children[0].ansUser">
            <span style="color:#907e7e;margin: 0 4px">回复</span>
            <span class="fe-url" style="color: #169bfa">
              {{ item.children[0].ansUser.nickName }}
            </span>
          </span>
          ：{{ item.children[0].content }}
        </p>
        <p v-if="item.children[1]" class="comment-sub-p">
          <span class="fe-url" style="color: #169bfa">{{ item.children[1].createUser.nickName }}</span>
          <span v-if="item.children[1].ansUser === item.children[1].parentId">
                        <span style="color:#907e7e;margin: 0 4px">回复</span>
                        <span class="fe-url" style="color: #169bfa">
                          {{ item.children[1].ansUser.nickName }}
                        </span>
                      </span>
          ：{{ item.children[1].content }}
        </p>
        <span @click="drawerSub(item.children)" class="comment-sub-more">查看更多{{ item.children.length }}条评论</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CommentSub",
  props: ["item"],
  data() {
    return {
      isHidden:true
    }
  },
  methods: {
    drawerSub(data) {
      this.$parent.drawerSub(data)
    },
    reply(item){
      console.log(item)
      if (item.parentId){
        this.$parent.$parent.commentDialog(item,2)
      }else {
        this.$parent.commentDialog(item,1)
      }
    }
  }
}
</script>

<style lang="scss" scoped>
//评论
.comment-item {
  width: 100%;
  height: auto;
  padding: 20px 0 0;

  .avatar-comment {
    object-fit: cover;
    width: 40px;
    height: 40px;
    border-radius: 50%;
    margin-right: 12px;
  }

  .comment-right {
    flex: 1;
    padding-bottom: 20px;
    border-bottom: 1px solid #eeeeee;


    .right-top {
      height: 40px;
      width: 100%;
      margin-bottom: 12px;

      .top-left {
        height: 100%;
        display: flex;
        flex-direction: column;
        justify-content: space-between;

        span:nth-child(1) {
          font-size: 15px;
          color: #2d3e53;
        }

        span:nth-child(2) {
          font-size: 12px;
          color: #B8B8B8;
          margin-right: 12px;
        }
      }

      .top-right {
        height: 100%;
        display: flex;
        align-items: center;

        .reply {
          display: inline-block;
          padding: 0 12px;
          font-size: 14px;
          border-radius: 5px;
          line-height: 18px;
          border: 1px solid #eeeeee;
          margin: 0;

          &:hover {
            border-color: #9ACAFB;
            background-color: #9ACAFB;
            cursor: pointer;

            i {
              color: #FFFFFF;
            }
          }

          i {
            font-size: 14px;
            color: #969696;
          }
        }

        span {
          margin: 0 12px;
          font-size: 14px;
        }

        i:hover {
          cursor: pointer;
          color: #169bfa;
        }
      }
    }

    .right-content {
      font-size: 14px;
      color: #2d3e53;
      line-height: 22px;
      white-space: pre-line;

    }

    .right-content-hidden {
      overflow: hidden;
      text-overflow: ellipsis;
      display: -webkit-box;
      -webkit-box-orient: vertical;
      -webkit-line-clamp: 3;
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
</style>