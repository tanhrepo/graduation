<template>
  <div class="wind fe-flex-column">
    <div class="fe-no-shrink nav">
      <Navbar></Navbar>
    </div>
    <div style="height: calc(100% - 60px)">
      <transition :name="transitionName" mode="out-in">
        <keep-alive>
          <router-view v-if="!$route.meta.noCache" :key="$route.fullPath"/>
        </keep-alive>
        <router-view v-if="$route.meta.noCache"/>
      </transition>
    </div>
  </div>
</template>

<script>
import Navbar from '@/views/components/Navbar'

export default {
  name: 'Main',
  components: {
    Navbar,
  },
  data() {
    return {
      transitionName: ''
    }
  },
  watch: {
    $route(to, from) {
      //如果to索引大于from索引,判断为前进状态,反之则为后退状态
      if (to.meta.index > from.meta.index) {
        //设置动画名称
        this.transitionName = 'slide-left';
      } else {
        this.transitionName = 'slide-right';
      }
    }
  }
}
</script>
<style lang="scss" scoped>
.slide-right-enter-active,
.slide-right-leave-active,
.slide-left-enter-active,
.slide-left-leave-active {
  transition: all 0.5s ease;
}

.slide-left-leave-to,
.slide-right-enter {
  opacity: 0;
  transform: translateX(-100px);
}

.slide-left-enter,
.slide-right-leave-to {
  opacity: 0;
  transform: translateX(100px);
}

.slide-left-leave,
.slide-right-leave,
.slide-left-enter-to,
.slide-right-enter-to {
  opacity: 1;
  transform: translateX(0);
}

</style>
