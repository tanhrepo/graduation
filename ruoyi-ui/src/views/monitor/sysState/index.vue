<template>
  <div class="chart-level">
    <div class="chart-con">
      <div :id="domId" class="chart"></div>
      <div id="container"></div>
    </div>
  </div>
</template>

<script>
import Highcharts from 'highcharts'

export default {
  name: 'ChartLevel',
  data () {
    return {
      domId: 'chartLevel'
    }
  },
  mounted () {
    this.chartInit()
    this.chartInitNew()
  },
  methods: {
    chartInit () {
      Highcharts.chart(this.domId, {
        chart: {
          type: 'spline'
        },
        title: {
          text: '社区系统用户访问和操作数量'
        },
        xAxis: {
          type: 'datetime',
          labels: {
            overflow: 'justify'
          }
        },
        yAxis: {
          title: {
            text: '数量（个）'
          },
          min: 0,
          minorGridLineWidth: 0,
          gridLineWidth: 0,
          alternateGridColor: null,
          plotBands: [{ // Light air
            from: 0.3,
            to: 1.5,
            color: 'rgba(68, 170, 213, 0.1)',
            label: {
              text: '',
              style: {
                color: '#606060'
              }
            }
          }, { // Light breeze
            from: 1.5,
            to: 3.3,
            color: 'rgba(0, 0, 0, 0)',
            label: {
              text: '',
              style: {
                color: '#606060'
              }
            }
          }, { // Gentle breeze
            from: 3.3,
            to: 5.5,
            color: 'rgba(68, 170, 213, 0.1)',
            label: {
              text: '',
              style: {
                color: '#606060'
              }
            }
          }, { // Moderate breeze
            from: 5.5,
            to: 8,
            color: 'rgba(0, 0, 0, 0)',
            label: {
              text: '',
              style: {
                color: '#606060'
              }
            }
          }, { // Fresh breeze
            from: 8,
            to: 11,
            color: 'rgba(68, 170, 213, 0.1)',
            label: {
              text: '',
              style: {
                color: '#606060'
              }
            }
          }, { // Strong breeze
            from: 11,
            to: 14,
            color: 'rgba(0, 0, 0, 0)',
            label: {
              text: '',
              style: {
                color: '#606060'
              }
            }
          }, { // High wind
            from: 14,
            to: 15,
            color: 'rgba(68, 170, 213, 0.1)',
            label: {
              text: '',
              style: {
                color: '#606060'
              }
            }
          }]
        },
        tooltip: {
          valueSuffix: ' 次数'
        },
        plotOptions: {
          spline: {
            lineWidth: 4,
            states: {
              hover: {
                lineWidth: 5
              }
            },
            marker: {
              enabled: false
            },
            pointInterval: 3600000, // one hour
            pointStart: Date.UTC(2009, 9, 6, 0, 0, 0)
          }
        },
        series: [{
          name: '访问',
          data: [4.3, 5.1, 4.3, 5.2, 5.4, 4.7, 3.5, 4.1, 5.6, 7.4, 6.9, 7.1,
            7.9, 7.9, 7.5, 6.7, 7.7, 7.7, 7.4, 7.0, 7.1, 5.8, 5.9, 7.4,
            8.2, 8.5, 9.4, 8.1, 10.9, 10.4, 10.9, 12.4, 12.1, 9.5, 7.5,
            7.1, 7.5, 8.1, 6.8, 3.4, 6.1, 7.9, 2.8, 2.9, 1.3, 4.4, 4.2,
            3.0, 3.0]
        }, {
          name: '操作',
          data: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.1, 0.0, 0.3, 0.0,
            0.0, 0.4, 0.0, 0.1, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
            0.0, 0.6, 1.2, 1.7, 0.7, 2.9, 4.1, 2.6, 3.7, 3.9, 1.7, 2.3,
            3.0, 3.3, 4.8, 2.0, 1.8, 3.0, 1.2, 2.0, 0.9, 0.4, 0.3, 0.5, 0.4]
        }],
        navigation: {
          menuItemStyle: {
            fontSize: '10px'
          }
        }
      }, (c) => { // 图表初始化完毕后的回调函数
        // 环形图圆心

      })
    },
    chartInitNew(){
      Highcharts.chart('container', {
        chart: {
          type: 'areaspline'
        },
        title: {
          text: '不同类型的帖子访问情况'
        },
        legend: {
          layout: 'vertical',
          align: 'left',
          verticalAlign: 'top',
          x: 150,
          y: 100,
          floating: true,
          borderWidth: 1,
          backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
        },
        xAxis: {
          categories: [
            '周一','周二','周三','周四','周五','周六','周日'
          ],
          plotBands: [{ // 标识出周末
            from: 4.5,
            to: 6.5,
            color: 'rgba(68, 170, 213, .2)'
          }]
        },
        yAxis: {
          title: {
            text: '人数 单位'
          }
        },
        tooltip: {
          shared: true,
          valueSuffix: ' 单位'
        },
        plotOptions: {
          areaspline: {
            fillOpacity: 0.5
          }
        },
        series: [{
          name: '图文',
          data: [3, 4, 3, 5, 4, 10, 12]
        }, {
          name: '视频',
          data: [1, 3, 4, 3, 3, 5, 4]
        }]
      });
    }
  }
}

</script>

<style lang="scss" scoped>
::v-deep text.highcharts-credits{
  display: none;


  .chart-con{
    padding: 50px;
  }
}
</style>
