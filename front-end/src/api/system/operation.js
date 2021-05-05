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

// 关注用户
export function UserFollow(data) {
  return request({
    url: '/system/praise/likeArticle',
    method: 'get',
    params: data
  })
}
