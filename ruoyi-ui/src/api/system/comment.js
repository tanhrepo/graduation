/**
 * @author tanhong
 * @date 2021-05-06 06:36
 */
import request from '@/utils/request'
// 查询菜单列表
export function listComment(query) {
  return request({
    url: '/system/comment/list',
    method: 'get',
    params: query
  })
}
