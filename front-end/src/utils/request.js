import axios from 'axios'
import { Message } from 'element-ui'
import { getToken } from '@/utils/auth'
import Config from '@/settings'
// import apiConfig from '@/api/axios.config.js'
// import qs from 'qs'

const service = axios.create({
  // axios中请求配置有baseURL选项，表示请求URL公共部分
  baseURL: process.env.VUE_APP_BASE_API,
  timeout: Config.timeout
})

// request拦截器
service.interceptors.request.use(
  config => {
    if (getToken()) {
      config.headers['Authorization'] = getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
    }
    // if (config.method === 'post') {
    //   config.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'
    //   config.data = qs.stringify(config.data)
    // }
    return config
  },
  error => {
    Promise.reject(error)
  }
)

// response 拦截器
service.interceptors.response.use(
  response => {
    const res = response.data
    return Promise.resolve(res)
    // if (res.success || res.FLAG === 'SUCCESS') {
    //   return Promise.resolve(res)
    // } else {
    //   Message({
    //     message: res.msg || res.MESSAGE || 'Error',
    //     type: 'error',
    //     duration: 3000
    //   })
    //   return Promise.reject(res)
    // }
  },
  error => {
    let code = 0
    try {
      code = error.response.data.status
    } catch (e) {
      if (error.toString().indexOf('Error: timeout') !== -1) {
        Message({
          message: '网络请求超时',
          type: 'error',
          duration: 3000
        })
        return Promise.reject(error)
      }
    }
    if (code) {
      const errorMsg = error.response.data.message
      if (errorMsg !== undefined) {
        Message({
          message: errorMsg,
          type: 'error',
          duration: 3000
        })
      }
    } else {
      Message({
        message: "接口请求失败",
        type: 'error',
        duration: 3000
      })
    }
    return Promise.reject(error)
  }
)

export default service
