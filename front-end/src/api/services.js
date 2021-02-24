/**
 * @file 本文件为服务调用URL常量配置实现
 * @author OPRD TEAM.
 * @example <caption>配置示例</caption>
 * // 配置登录和注销服务地址
 * export default {
 *   LOGIN: '/foo/login',
 *   LOGOUT: '/foo/logout'
 * }
 */

export default {
  login: { // 登录页
    getCode: '/sysAuth/getVerificationCode', // 获取验证码
    login: '/sysAuth/login', // 登录
    loginOut: '/sysAuth/logout', // 登出
  }
}