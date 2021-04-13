module.exports = {
  [process.env.VUE_APP_BASE_API]: {
    target: `http://localhost:8080`,
    changeOrigin: true,
    pathRewrite: {
      ['^' + process.env.VUE_APP_BASE_API]: ''
    },
    // logLevel: 'debug', // 需要websocket 开启
  }
}