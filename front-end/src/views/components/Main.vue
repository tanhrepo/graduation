<template>
  <div class="retina fe-flex-column">
    <div class="fe-no-shrink">
      <Navbar></Navbar>
      <!--      <Breadcrumb></Breadcrumb>-->
    </div>
    <router-view/>
  </div>
</template>

<script>
import Navbar from '@/views/components/Navbar'
// import Breadcrumb from '@/views/components/Breadcrumb'

export default {
  name: 'Main',
  components: {
    Navbar,
    // Breadcrumb
  },
}
</script>
