const path = require('path')
const webpack = require('webpack')
const resolve = dir => {
  return path.join(__dirname, dir)
}
const DevServerProxy = require('./dev.proxy.js')
const port = process.env.port || process.env.npm_config_port || 8013 // 端口

module.exports = {

  devServer: {
    open: true,
    port: port,
    proxy: DevServerProxy,
    overlay: {
      warnings: false,
      errors: true
    }
  },
  publicPath: process.env.NODE_ENV === 'production' ? process.env.BASE_URL : '/',
  lintOnSave: true,
  productionSourceMap: false,
  css: {
    sourceMap: false,
    loaderOptions: {
      sass: {
        prependData: `@import "./src/styles/_variables.scss";`,
      },
    },
  },
  configureWebpack: {
    resolve: {
      alias: {
        '@': resolve('src')
      },
      extensions: ['.js', '.vue', '.scss', '.css', '.json']
    },
    plugins: [new webpack.ContextReplacementPlugin(/moment[/\\]locale$/, /zh-cn/)]
  },
  chainWebpack: config => {
    config.plugin('html').tap(args => {
      args[0].title = '风物社区'
      return args
    })
    config.optimization.splitChunks({
      cacheGroups: {
        elementUI: {
          name: 'element',
          priority: 20,
          test: /[\\/]node_modules[\\/]_?element-ui(.*)/,
          chunks: 'all'
        },
        highcharts: {
          name: 'highcharts',
          priority: 20,
          test: /[\\/]node_modules[\\/]_?highcharts(.*)/,
          chunks: 'all'
        },
      }
    })
  }
}
