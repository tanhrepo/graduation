<template>
  <div class="highcharts-container" style="width:100%!important"></div>
</template>

<script>
import Highcharts from 'highcharts'
import HighchartsMore from 'highcharts/highcharts-more'
import HighchartsVariablepie from 'highcharts/modules/variable-pie'
import Highstock from 'highcharts/modules/stock'
import HighAnnotations from 'highcharts/modules/annotations'
HighchartsMore(Highcharts)
HighchartsVariablepie(Highcharts)
Highstock(Highcharts)
HighAnnotations(Highcharts)
Highcharts.setOptions({
  global: {
    useUTC: false
  }
})
export default {
  name: 'HighchartsComponent',
  props: ['options'],
  data () {
    return {
      chart: null,
      resizeTimer: null
    }
  },
  mounted () {
    this.initChart()
    window.addEventListener('resize', this.resize)
    this.$once('hook:beforeDestroy', () => {
      window.removeEventListener('resize', this.resize)
    })
  },
  methods: {
    initChart () {
      this.chart = new Highcharts.Chart(this.$el, this.options)
    },
    resize () {
      if (this.resizeTimer) { clearTimeout(this.resizeTimer); this.resizeTimer = null }
      this.resizeTimer = setTimeout(() => {
        this.chart.reflow()
      }, 220)
    }
  },
}
</script>
