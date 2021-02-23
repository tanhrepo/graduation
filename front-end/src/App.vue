<template>
  <div id="app">
    <menu1></menu1>
    <div>
      <h3> git测试</h3>
      <router-view/>
    </div>
  </div>
</template>

<script>
import menu1 from "@/components/menu1";

export default {
  name: 'App',
  components: {
    menu1
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
</style>
