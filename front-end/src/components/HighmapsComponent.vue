<template>
  <div class="highcharts-container" ref="geoMap"></div>
</template>

<script>
import Highcharts from 'highcharts'
import HighchartsMore from 'highcharts/highcharts-more'
import Highmaps from 'highcharts/modules/map'
// import HighchartsDrilldown from 'highcharts/modules/drilldown'
HighchartsMore(Highcharts)
// HighchartsDrilldown(Highcharts)
Highmaps(Highcharts)

export default {
  name: 'HighmapsComponent',
  props: ['options'],
  data () {
    return {
      chart: null,
      resizeTimer: null
    }
  },
  watch: {
    // ! 只监听数据变化,对于构造过程中options逐一监听性能开销过大,所以父组件必须使用v-if="showMap"等options除series之前均构造完成 
    'options.series': {
      deep: true,
      immediate: true,
      handler: function () {
        if (this.chart) {
          this.chart.series[0].setData(this.options.series[0].data)
          let max = this.options.colorAxis.max
          // 避免使用全量update
          this.chart.update(({ colorAxis: { max } }))
        }
      }
    }
  },
  mounted () {
    // ! 父组件必须构造好options之后再渲染，即必须在父组件使用v-if
    this.initMap()
    window.addEventListener('resize', this.resize)
    this.$once('hook:beforeDestroy', () => {
      window.removeEventListener('resize', this.resize)
    })
  },
  methods: {
    initMap () {
      this.chart = new Highcharts.Map(this.$el, this.options)
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
