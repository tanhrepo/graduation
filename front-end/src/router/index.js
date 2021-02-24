import Vue from 'vue'
import VueRouter from 'vue-router'
import routes from './routes.js'
import { getToken } from '@/utils/auth'

// 解决由于router版本问题导致的重定向失败
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (location, onResolve, onReject) {
  if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject)
  return originalPush.call(this, location).catch(err => err)
}

Vue.use(VueRouter)

const router = new VueRouter({
  routes,
  mode: 'history'
})

router.beforeEach((to, from, next) => {
  let token = getToken()
  if (to.path === '/login') {
    next()
  } else if (!token) {
    next({
      path: '/login',
      replace: true
    })
  } else {
    next()
  }
})

export default router
