import request from '@/utils/request'

// 登录方法
export function login(username, password, code, uuid) {
  const data = {
    username,
    password,
    code,
    uuid
  }
  return request({
    url: '/login',
    method: 'post',
    data: data
  })
}

// 获取用户详细信息
export function getInfo(data) {
  return request({
    url: '/getInfo',
    method: 'get',
    params:data
  })
}
// 用户名用户详情
export function getUserNameInfo(data) {
  return request({
    url: '/system/user/:'+data,
    method: 'get',
  })
}
// 退出方法
export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  })
}

// 获取验证码
export function getCodeImg() {
  return request({
    url: '/captchaImage',
    method: 'get'
  })
}