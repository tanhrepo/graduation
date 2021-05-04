<template>
  <div class="topic-item" @click="jump(ItemData.lableId)">
    <img v-show="ItemData.lableImg" :src="ItemData.lableImg" alt="">
    <h2># {{ ItemData.lableName }}</h2>
    <p>{{ ItemData.lableDescription }}</p>
  </div>
</template>

<script>
export default {
  name: "TopicItem",
  props: ['ItemData'],
  methods:{
    jump(id){
      this.$router.push({
        name:"topicDetail",
        query:{
          id,
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.topic-item{
  width: 100%;
  margin: 0 0 16px;
  background-color: #F1F3F4;
  border-radius: 6px;
  padding: 12px;
  color: #1A4A67;
  overflow: hidden;
  break-inside: avoid;
  cursor: pointer;

  &:hover{
    transition: 0.3s;
    transform: translateY(3px);
    box-shadow:  6px 6px 12px #d7d7d7,
    -6px -6px 12px #e9e9e9;
  }

  p{
    color: #2D3E53;
    white-space: pre-wrap;
    font-size: 14px;
    line-height: 22px;
    margin-top: 10px;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 3;
  }

  img{
    width: 100%;
    margin-bottom: 10px;
  }
}
</style>