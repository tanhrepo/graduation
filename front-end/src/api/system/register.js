// 注册
import request from "@/utils/request";

export function register(username, password,nickName, code, uuid) {
  const data = {
    username,
    password,
    nickName,
    code,
    uuid
  }
  return request({
    url: '/system/register',
    method: 'post',
    data: data
  })
}