/**
 * @file 本文件为服务调用对象配置实现
 * @author OPRD TEAM.
 * * 配置项目说明:
 * commonConfig -- 默认服务调用对象配置
 */

var baseURL = ''
if (process.env.NODE_ENV === 'development') { // 开发环境
  // baseURL = 'https://api.5m.work/mock/29/'
  baseURL = 'http://10.21.10.39:10010/5gopp-application/'
} else if (process.env.NODE_ENV === 'production') { // 测试或生产环境
  baseURL = '/5gopp-application/'
}

/**
 * 服务调用对象配置
 * @type {Object}
 */
const conf = {
  commonConfig: {
    baseURL: baseURL
  }
}

export default conf
