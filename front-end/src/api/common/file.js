// 获取文章列表
import request from "@/utils/request";

export function putFile() {
  return request({
    url: '/common/upload',
    method: 'post'
  })
}