<template>
  <div class="praise-item">
    <img @click="jumpDetail('userPage',createUser.userId)" class="avatar" :src="'http://localhost:8080' + createUser.avatar" alt="">
    <div class="fe-flex center">

      <div class="flex-column-between praise-left">
        <span @click="jumpDetail('detail',itemData.articleId)" class="fe-url">{{ itemData.articleTitle }}</span>
        <p>{{ createUser.nickName }}</p>
      </div>
      <p class="container">{{ itemData.articleContent }}</p>
      <!--    图片-->
      <div class="item-img" v-if="!itemData.imgs">
        <viewer class="item-img">
          <div v-for="(item,index) in itemData.imgs" :key="index" class="item-img-item">
            <img :src="item" alt="">123
          </div>
        </viewer>
      </div>
    </div>
  </div>
</template>

<script>
import {getUserNameInfo} from "@/api/login";

export default {
  name: "PraiseItem",
  props: {
    itemData: {
      type: Object,
      default: null
    }
  },
  data() {
    return {
      createUser: {}
    }
  },
  mounted() {
    this.getUserInfo()
  },
  methods: {
    // 作者信息
    getUserInfo() {
      return new Promise((resolve, reject) => {
        getUserNameInfo(this.itemData.createUser).then(res => {
          console.log('文章用户', res.data)
          this.createUser = res.data
        }).catch(error => {
          reject(error)
        })
      })
    },
    jumpDetail(name,id){
      this.$router.push({
        name,
        query:{
          id,
        }
      })
    },
  }
}
</script>

<style lang="scss" scoped>
.praise-item {
  padding: 20px;
  border-bottom: 1px solid #EBEBEB;
  display: flex;
  justify-content: space-between;

  .avatar {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    margin-right: 12px;
  }

  .center {
    flex: 1;
    flex-direction: column;

    .container {
      padding-top: 12px;
      font-size: 14px;
      color: #2d3e53;
      line-height: 25px;
      white-space: pre-line;
      overflow: hidden;
      text-overflow: ellipsis;
      display: -webkit-box;
      -webkit-box-orient: vertical;
      -webkit-line-clamp: 3;
    }
  }

  .praise-left {
    height: 40px;

    span {
      font-size: 14px;
    }
  }

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
</style>