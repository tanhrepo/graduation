<template>
  <div>
    <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b">
      <el-menu-item index="page1">base-page1</el-menu-item>
      <el-menu-item index="page2">base-page2</el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "menu1",
  data() {
    return {
      activeIndex: 'page1',
    }
  },
  methods:{
    handleSelect(key) {
      if (key !== this.activeIndex) {
        this.activeIndex = key
        this.$router.push({ name: key })
      }
    }
  }
}
</script>

<style scoped>

</style>