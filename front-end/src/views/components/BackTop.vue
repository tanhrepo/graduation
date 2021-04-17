<template>
  <div class="right-bottom">
    <div id='Back' class="page-up" @click="backPage()" v-if="this.$route.name === 'detail'">
      <i class="iconfont icon-add"></i>
    </div>
    <div id='Add' class="page-up" @click="addArticle()" >
      <i class="iconfont icon-add"></i>
    </div>
    <div id='refresh' class="page-up" @click="refresh()" >
      <i class="iconfont icon-refresh"></i>
    </div>
    <div id='backTop' class="page-up" @click="goAnchor()" >
      <i class="iconfont icon-back-top"></i>
    </div>
  </div>
</template>

<script>

export default {
  name: 'BackTop',
  props: ['ID'],
  data(){
    return{
      ViewMenu:[],
      orderMenu:[]
    }
  },
  mounted() {

  },
  methods:{
    goAnchor() {

      if (this.orderMenu.indexOf(this.$route.name) === -1){

        let dom = document.getElementById(this.ID)
        let timer = setInterval(() => {
          let iSpeed = Math.floor(-dom.scrollTop / 5)
          dom.scrollTop = dom.scrollTop + iSpeed
          if (dom.scrollTop === 0) {
            clearInterval(timer)
          }
        }, 16)
      }else {

        let timer = setInterval(() => {
          let iSpeed = Math.floor(-document.documentElement.scrollTop / 5)
          document.documentElement.scrollTop += iSpeed
          if (!document.documentElement.scrollTop) {
            clearInterval(timer)
          }
        }, 16)
      }
    },
    addArticle(){
      this.$router.push({name:'add'})
    },
    refresh(){
      this.$parent.getData();
    },
    backPage(){
      this.$router.go(-1)
    }
  },

}
</script>

<style scoped lang="scss">
.right-bottom{
  position: fixed;
  right: 150px;
  bottom: 30px;

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