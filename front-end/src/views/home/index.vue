<!-- 首页(推荐) -->
<template>
  <div class="fe-page fe-scroll-y" style="background-color: #169bfa">首页
    <svg class="icon fe-font-lg"  aria-hidden="true">
      <use xlink:href="#icon-wind"></use>
    </svg>
    <span class="iconfont icon-wind" style="color: #169bfa"></span>
  </div>
</template>

<script>
import { getRouters } from '@/api/menu'
export default {
  data(){
    return{

    }
  },
  created() {
    // this.GenerateRoutes({})
  },
  methods:{
    // 路由
    GenerateRoutes() {
      getRouters().then(res => {
        console.log(res)
      })
    }
  }
}
</script>

<style>
</style>