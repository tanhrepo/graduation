// 获取文章列表
import request from "@/utils/request";

export function getArticleList() {
  return request({
    url: '/system/article/list',
    method: 'get'
  })
}

// 新增文章
export function addArticle(data) {
  return request({
    url: '/system/article',
    method: 'post',
    data: data
  })
}

// 文章详情
export function getArticleItem(data) {
  return request({
    url: '/system/article/'+data,
    method: 'get',
  })
}

// 评论详情
export function getCommentList(data) {
  return request({
    url: '/system/comment/list',
    method: 'get',
    params: data
  })
}

// 新增评论
export function postComment(data) {
  return request({
    url: '/system/comment',
    method: 'post',
    data: data
  })
}