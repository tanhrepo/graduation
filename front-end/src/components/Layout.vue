<template>
  <div class="fe-page">
    <keep-alive>
      <router-view v-if="!$route.meta.noCache" :key="$route.fullPath" />
    </keep-alive>
    <router-view v-if="$route.meta.noCache" />
  </div>
</template>

<script>
export default {
  name: 'Layout'
}
</script>
