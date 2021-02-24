<template>
  <div>
    <template v-for="item of menulist">
      <el-submenu v-if="item.children  && !item.meta.hideSubMenu" :key="item.path" :index="item.path">
        <template slot="title">{{ item.meta.title }}</template>
        <!--递归调用-->
        <menu-list :menulist="item.children"></menu-list>
      </el-submenu>
      <el-menu-item v-else :key="item.name" :index="item.name">{{ item.meta.title }}</el-menu-item>
    </template>
  </div>
</template>

<script>
export default {
  name: 'MenuList',
  props: {
    menulist: {
      type: Array,
      required: true,
      default: () => []
    }
  }
}
</script>
