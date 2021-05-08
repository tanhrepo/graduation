/**
 * @author tanhong
 * @date 2021-05-06 07:01
 */
import request from '@/utils/request'
// 查询操作
export function listOperation(query) {
  return request({
    url: '/system/operation/list',
    method: 'get',
    params: query
  })
}
