const state = {
  isExpired: true
}

const mutations = {
  setIsExpired (state, isExpired) {
    state.isExpired = isExpired
  }
}

export default {
  namespaced: true,
  state,
  mutations
}
