<template>
  <div id="container" class="chart"></div>
</template>

<script>
import Highcharts from "highcharts";
import highchartsMore from 'highcharts/highcharts-more'

highchartsMore(Highcharts);

export default {
  name: "packedCharts",
  mounted() {
    this.chartInit()
  },
  methods: {
    chartInit() {
      Highcharts.chart('container', {
        chart: {
          type: 'packedbubble',
          height: '100%'
        },
        title: {
          text: '热门话题各用户发帖数'
        },
        tooltip: {
          useHTML: true,
          pointFormat: '<b>{point.name}:</b> {point.y}个帖子'
        },
        plotOptions: {
          packedbubble: {
            minSize: '30%',
            maxSize: '120%',
            zMin: 0,
            zMax: 1000,
            layoutAlgorithm: {
              splitSeries: false,
              gravitationalConstant: 0.02
            },
            dataLabels: {
              enabled: true,
              format: '{point.name}',
              filter: {
                property: 'y',
                operator: '>',
                value: 250
              },
              style: {
                color: 'black',
                textOutline: 'none',
                fontWeight: 'normal'
              }
            }
          }
        },
        series: [{
          name: '后端',
          data: [{
            name: '张三',
            value: 767.1
          }, {
            name: '空间',
            value: 20.7
          },
            {
              name: "还能",
              value: 97.2
            },
            {
              name: "的风格不",
              value: 111.7
            },
            {
              name: "岁的法国",
              value: 158.1
            },
            {
              name: "的方式",
              value: 241.6
            },
            {
              name: "大法官",
              value: 249.1
            },
            {
              name: "手感",
              value: 298.1
            },
            {
              name: "分蛋糕",
              value: 323.7
            },
            {
              name: "的手法",
              value: 78.3
            },
            {
              name: "的萨",
              value: 415.4
            }, {
              name: "发",
              value: 353.2
            }, {
              name: "放",
              value: 337.6
            },
            {
              name: "高度",
              value: 71.1
            },
            {
              name: "是",
              value: 69.8
            },
            {
              name: "电饭锅",
              value: 67.7
            },
            {
              name: "大地飞歌",
              value: 59.3
            },
            {
              name: "更多",
              value: 54.8
            },
            {
              name: "电饭锅",
              value: 51.2
            },
            {
              name: "分",
              value: 48.3
            },
            {
              name: "得分",
              value: 44.4
            },
            {
              name: "奋斗过",
              value: 44.3
            },
            {
              name: "犯嘀咕",
              value: 43.7
            },
            {
              name: "对方",
              value: 40.2
            },
            {
              name: "反对",
              value: 40
            },
            {
              name: "巅峰",
              value: 34.7
            },
            {
              name: "反对",
              value: 34.6
            },
            {
              name: "Croatia",
              value: 20.7
            },
            {
              name: "奋斗过",
              value: 19.4
            },
            {
              name: "Slovenia",
              value: 16.7
            },
            {
              name: "Lithuania",
              value: 12.3
            },
            {
              name: "Luxembourg",
              value: 10.4
            },
            {
              name: "Macedonia",
              value: 9.5
            },
            {
              name: "Moldova",
              value: 7.8
            },
            {
              name: "Latvia",
              value: 7.5
            },
            {
              name: "Cyprus",
              value: 7.2
            }]
        }, {
          name: '前端',
          data: [{
            name: "Senegal",
            value: 8.2
          },
            {
              name: "Cameroon",
              value: 9.2
            },
            {
              name: "敌法",
              value: 13.1
            },
            {
              name: "Ghana",
              value: 14.1
            },
            {
              name: "双方的跟",
              value: 14.1
            },
            {
              name: "Sudan",
              value: 17.3
            },
            {
              name: "Tunisia",
              value: 24.3
            },
            {
              name: "Angola",
              value: 25
            },
            {
              name: "Libya",
              value: 50.6
            },
            {
              name: "覆盖",
              value: 7.3
            },
            {
              name: "法国",
              value: 60.7
            },
            {
              name: "法官的",
              value: 8.9
            },
            {
              name: "就会",
              value: 9.1
            },
            {
              name: "Nigeria",
              value: 93.9
            },
            {
              name: "规范化",
              value: 392.7
            }, {
              name: "Egypt",
              value: 225.1
            }, {
              name: "Algeria",
              value: 141.5
            }]
        }, {
          name: 'Android',
          data: [{
            name: "Australia",
            value: 409.4
          },
            {
              name: "New Zealand",
              value: 34.1
            },
            {
              name: "Papua New Guinea",
              value: 7.1
            }]
        }, {
          name: 'iOS',
          data: [{
            name: "Costa Rica",
            value: 7.6
          },
            {
              name: "Honduras",
              value: 8.4
            },
            {
              name: "Jamaica",
              value: 8.3
            },
            {
              name: "Panama",
              value: 10.2
            },
            {
              name: "Guatemala",
              value: 12
            },
            {
              name: "Dominican Republic",
              value: 23.4
            },
            {
              name: "Cuba",
              value: 30.2
            },
            {
              name: "USA",
              value: 5334.5
            }, {
              name: "Canada",
              value: 566
            }, {
              name: "Mexico",
              value: 456.3
            }]
        }, {
          name: '人工智能',
          data: [{
            name: "El 发布",
            value: 7.2
          },
            {
              name: "Uruguay",
              value: 8.1
            },
            {
              name: "Bolivia",
              value: 17.8
            },
            {
              name: "Trinidad and Tobago",
              value: 34
            },
            {
              name: "Ecuador",
              value: 43
            },
            {
              name: "你好",
              value: 78.6
            },
            {
              name: "Peru",
              value: 52
            },
            {
              name: "Colombia",
              value: 74.1
            },
            {
              name: "Brazil",
              value: 501.1
            }, {
              name: "Argentina",
              value: 199
            },
            {
              name: "Venezuela",
              value: 195.2
            }]
        }, {
          name: '开发工具',
          data: [{
            name: "Nepal",
            value: 6.5
          },
            {
              name: "Georgia",
              value: 6.5
            },
            {
              name: "Brunei Darussalam",
              value: 7.4
            },
            {
              name: "Kyrgyzstan",
              value: 7.4
            },
            {
              name: "Afghanistan",
              value: 7.9
            },
            {
              name: "Myanmar",
              value: 9.1
            },
            {
              name: "Mongolia",
              value: 14.7
            },
            {
              name: "Sri Lanka",
              value: 16.6
            },
            {
              name: "Bahrain",
              value: 20.5
            },
            {
              name: "Yemen",
              value: 22.6
            },
            {
              name: "Jordan",
              value: 22.3
            },
            {
              name: "Lebanon",
              value: 21.1
            },
            {
              name: "Azerbaijan",
              value: 31.7
            },
            {
              name: "Singapore",
              value: 47.8
            },
            {
              name: "Hong Kong",
              value: 49.9
            },
            {
              name: "Syria",
              value: 52.7
            },
            {
              name: "DPR Korea",
              value: 59.9
            },
            {
              name: "Israel",
              value: 64.8
            },
            {
              name: "Turkmenistan",
              value: 70.6
            },
            {
              name: "Oman",
              value: 74.3
            },
            {
              name: "Qatar",
              value: 88.8
            },
            {
              name: "Philippines",
              value: 96.9
            },
            {
              name: "Kuwait",
              value: 98.6
            },
            {
              name: "Uzbekistan",
              value: 122.6
            },
            {
              name: "Iraq",
              value: 139.9
            },
            {
              name: "弄个",
              value: 158.1
            },
            {
              name: "那个",
              value: 190.2
            },
            {
              name: "的vds",
              value: 201.1
            },
            {
              name: "Malaysia",
              value: 227.5
            },
            {
              name: "上的分布",
              value: 236.2
            },
            {
              name: "scc",
              value: 272
            },
            {
              name: "Taiwan",
              value: 276.7
            },
            {
              name: "Indonesia",
              value: 453
            },
            {
              name: "Saudi Arabia",
              value: 494.8
            },
            {
              name: "Japan",
              value: 1278.9
            },
            {
              name: "国防部",
              value: 10540.8
            },
            {
              name: "不放过",
              value: 2341.9
            },
            {
              name: "标过",
              value: 1766.4
            },
            {
              name: "规范",
              value: 618.2
            },
            {
              name: "如",
              value: 610.1
            }]
        }]
      });
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
