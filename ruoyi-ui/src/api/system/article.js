/**
 * @author tanhong
 * @date 2021-05-06 06:07
 */
import request from '@/utils/request'
// 查询菜单列表
export function listArticle(query) {
  return request({
    url: '/system/article/list',
    method: 'get',
    params: query
  })
}
