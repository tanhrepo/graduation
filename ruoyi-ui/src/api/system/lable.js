/**
 * @author tanhong
 * @date 2021-05-06 06:34
 */
import request from '@/utils/request'
// 查询话题
export function listLabel(query) {
  return request({
    url: '/system/lable/list',
    method: 'get',
    params: query
  })
}
