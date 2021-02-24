<template>
  <div class="fe-breadcrumb fe-align-center">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item
        v-for="item in breadcrumbList"
        :key="item.path"
        @click.native="gotoPage(item.name)"
      >{{ item.meta.title }}</el-breadcrumb-item>
    </el-breadcrumb>
  </div>
</template>

<script>
export default {
  name: 'Breadcrumb',
  data () {
    return {
      breadcrumbList: []
    }
  },
  watch: {
    $route () {
      this.$nextTick(() => {
        this.getBreadcrumb()
      })
    }
  },
  created () {
    this.getBreadcrumb()
  },
  methods: {
    getBreadcrumb () {
      let matched = this.$route.matched
      const breadcrumbList = matched.filter(
        item =>
          item.meta &&
          item.meta.title &&
          item.meta.breadcrumb !== false
      )
      const last = matched[matched.length - 1]
      if (last && last.meta.showParent) {
        const parentMeta = last.meta.parent
        breadcrumbList.splice(breadcrumbList.length - 1, 0, parentMeta)
      }
      this.breadcrumbList = [...breadcrumbList]
    },
    gotoPage (name) {
      this.$router.push({ name })
    }
  }
}
</script>

<style lang="scss">
.fe-breadcrumb {
  position: relative;
  z-index: 1000;
  padding: 0 24px;
  height: 40px;
  background: #fff;
  .el-breadcrumb {
    .el-breadcrumb__item {
      cursor: pointer;
      color: $color-title;
    }
    .el-breadcrumb__item:last-child {
      pointer-events: none;
      .el-breadcrumb__inner {
        color: $--color-primary;
      }
    }
  }
}
</style>
