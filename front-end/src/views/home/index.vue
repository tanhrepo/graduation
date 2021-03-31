<!-- 首页 -->
<template>
  <div class="">首页
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
    GenerateRoutes({ commit }) {

      return new Promise(resolve => {
        // 向后端请求路由数据
        getRouters().then(res => {
          console.log(res)
        })
      })
    }
  }
}
</script>

<style>
</style>