// 操作
import request from "@/utils/request";

// 点赞文章
export function ArticlePraise(data) {
  return request({
    url: '/system/praise/likeArticle',
    method: 'get',
    params: data
  })
}

// 取消点赞文章
export function unArticlePraise(data) {
  return request({
    url: '/system/praise/unlikeArticle',
    method: 'get',
    params: data
  })
}
// 踩文章
export function ArticleTrample(data) {
  return request({
    url: '/system/trample/trampleArticle',
    method: 'get',
    params: data
  })
}
// 点赞评论
export function CommentPraise(data) {
  return request({
    url: '/system/praise/likeComment',
    method: 'get',
    params: data
  })
}

// 踩评论
export function CommentTrample(data) {
  return request({
    url: '/system/trample/trampleComment',
    method: 'get',
    params: data
  })
}

// 取消点赞评论
export function unCommentPraise(data) {
  return request({
    url: '/system/praise/unlikeComment',
    method: 'get',
    params: data
  })
}
// 关注用户
export function UserFollow(data) {
  return request({
    url: '/system/follows',
    method: 'post',
    data: data
  })
}
// 关注用户
export function getFollowList(data) {
  return request({
    url: '/system/follows/list',
    method: 'get',
    params: data
  })
}

// 收藏列表
export function getOperationList(data) {
  return request({
    url: '/system/operation/list',
    method: 'get',
    params: data
  })
}

// 点赞列表
export function getPraiseList(data) {
  return request({
    url: '/system/praise/userArticleLikes',
    method: 'get',
    params: data
  })
}

// 关注者文章

// 文章详情
export function getFollowArticleList(data) {
  return request({
    url: '/system/article/watchList/'+data,
    method: 'get',
  })
}