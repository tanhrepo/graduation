// 获取话题列表
import request from "@/utils/request";

export function getLabelList(data) {
  return request({
    url: '/system/lable/list',
    method: 'get',
    params: data
  })
}



// 新增话题
export function addLabel(data) {
  return request({
    url: '/system/lable',
    method: 'post',
    data: data
  })
}

// 修改话题
export function changeLabel(data) {
  return request({
    url: '/system/lable',
    method: 'put',
    data: data
  })
}

// 话题详情
export function getLabelItem(data) {
  return request({
    url: '/system/lable/'+data,
    method: 'get',
  })
}

// 删除话题
export function deleteLabelItem(data) {
  return request({
    url: '/system/lable/'+data,
    method: 'delete',
  })
}