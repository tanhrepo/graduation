<template>
  <div class="wind fe-flex-column">
    <div class="fe-no-shrink">
      <Navbar></Navbar>
    </div>
    <router-view/>
  </div>
</template>

<script>
import Navbar from '@/views/components/Navbar'

export default {
  name: 'Main',
  components: {
    Navbar,
  },
}
</script>

