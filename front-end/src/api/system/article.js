// 获取文章列表
import request from "@/utils/request";

export function getArticleList() {
  return request({
    url: '/system/article/list',
    method: 'get'
  })
}

export function register(data) {
  return request({
    url: '/system/article',
    method: 'post',
    data: data
  })
}