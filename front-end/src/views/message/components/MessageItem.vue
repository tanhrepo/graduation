<!--消息详情-->
<template>
  <div class="message-item" @click="jump(ItemData.articleId)">
    <div class="avatar">
      <img :src="'http://localhost:8080'+ ItemData.createUser.avatar" alt="">
    </div>
    <div class="detail fe-flex-between">
      <div>
        <div class="detail-user">
          <p>@ {{ ItemData.createUser.nickName }}</p>
          <span>{{ ItemData.createTime }}</span>
        </div>
        <p class="detail-content">
          <span v-if="ItemData.parentId" class="blue-item">回复：</span>
          <span v-else class="blue-item">评论文章：</span>
          {{ ItemData.content }}
        </p>
      </div>
      <img v-if="ItemData.imgs" :src="ItemData.imgs[0]" alt="">
    </div>
  </div>
</template>

<script>
export default {
  name: "MessageItem",
  props: ['ItemData'],
  methods:{
    jump(id){
      this.$router.push({
        name:'detail',
        query:{
          id,
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.message-item {
  display: flex;
  padding: 20px 20px 0;
  margin-top: -1px;
  border-radius: 12px;

  &:hover{
    transition: 0.5s background-color;
    background-color: #EBEBEB;
    cursor: pointer;
  }

  .avatar {
    width: 40px;
    margin-right: 12px;

    img {
      width: 40px;
      height: 40px;
      border-radius: 50%;
    }
  }

  .detail {
    box-shadow: inset 0 -1px 0 0 #EBEBEB;
    flex: 1;
    .detail-user{
      height: 40px;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      p{
        font-size: 14px;
      }
      span{
        color: #B8B8B8;
      }
    }
    .detail-content{
      font-size: 14px;
      color: #2d3e53;
      line-height: 25px;
      white-space: pre-line;
      overflow: hidden;
      text-overflow: ellipsis;
      display: -webkit-box;
      -webkit-box-orient: vertical;
      -webkit-line-clamp: 2;
      margin: 10px 0 20px;
    }
    img{
      width: 76px;
      height: 76px;
      object-fit: cover;
      border-radius: 6px;
    }
  }
  .blue-item{
    color: #169bfa;
  }
}
</style>