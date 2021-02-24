let userInfo = localStorage.userInfo || sessionStorage.userInfo ? localStorage.userInfo || sessionStorage.userInfo : ''
if (userInfo) userInfo = JSON.parse(userInfo)
// todo 后续补充
// let userInfo = {
//   userCode: 登录账号：手机号
//   userType:【省管理员，省用户，市管理员，市用户，超级用户-只有一个账号且该账户不可呗修改】进行操作权限判断
//   cityName:'四川'
// }

const state = {
  userInfo: {
    userName: userInfo.userName,
    userCode: userInfo.userCode,
    userType: userInfo.userType,
    cityName: userInfo.cityName
  },
}

const mutations = {
  setUserInfo (state, userInfo) {
    state.userInfo = userInfo
  },
}

export default {
  namespaced: true,
  state,
  mutations
}
