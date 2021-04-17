<template>
  <div class="right-comment">
    <div id='Comment' class="page-up" @click="comment()">
      <i class="iconfont icon-message"></i>
    </div>
    <div id='Like' class="page-up" @click="comment()">
      <i class="iconfont icon-like"></i>
    </div>
    <div id='Star' class="page-up" @click="comment()">
      <i class="iconfont icon-star"></i>
    </div>
    <div id='Share' class="page-up" @click="comment()">
      <i class="iconfont icon-share"></i>
    </div>
  </div>
</template>

<script>
export default {
  name: "Comment",

  methods: {
    comment() {
      this.$parent.commentDialog()
    }
  }
}
</script>

<style lang="scss" scoped>
.right-comment{
  position: fixed;
  left: 150px;
  top: 30%;

  .page-up{
    background-color: #409eff;
    width: 40px;
    height: 40px;
    border-radius: 20px;
    cursor: pointer;
    transition: .3s;
    box-shadow: 0 3px 6px rgba(0, 0, 0, .5);
    opacity: .5;
    z-index: 100;
    margin-bottom: 12px;
    i{
      color: #fff;
      display: block;
      line-height: 40px;
      text-align: center;
      font-size: 18px;
    }
    p{
      display: none;
      text-align: center;
      color: #fff;
    }
    &:hover{
      opacity: 1;
    }
  }
}
</style>