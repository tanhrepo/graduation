<template>
  <div id="pieCharts" class="chart"></div>
</template>

<script>
import Highcharts from "highcharts";

export default {
  name: "pieCharts",
  mounted() {
    this.chartInit()
  },
  methods: {
    chartInit() {
      Highcharts.chart("pieCharts", {
        chart: {
          plotBackgroundColor: null,
          plotBorderWidth: null,
          plotShadow: false,
          type: 'pie'
        },
        title: {
          text: '前几名话题份额比例'
        },
        tooltip: {
          pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
          pie: {
            allowPointSelect: true,
            cursor: 'pointer',
            dataLabels: {
              enabled: false
            },
            showInLegend: true
          }
        },
        series: [{
          name: '比例',
          colorByPoint: true,
          data: [{
            name: '后端',
            y: 41.41,
            sliced: true,
            selected: true
          }, {
            name: '前端',
            y: 31.84
          }, {
            name: 'Android',
            y: 10.85
          }, {
            name: 'iOS',
            y: 4.67
          }, {
            name: '人工智能',
            y: 4.18
          }, {
            name: '开发工具',
            y: 7.05
          }]
        }]
      })
    }
  }
}
</script>

<style lang="scss" scoped>
::v-deep text.highcharts-credits {
  display: none;
  .chart-con {
    padding: 50px;
  }
}
</style>
