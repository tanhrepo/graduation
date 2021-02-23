import Vue from 'vue'
import Router from "vue-router";

Vue.use(Router)
const routes = [
  {
    path: '/',
    name: 'dashboardIndex',
    component: () => import( '@/views/index'),
    meta: {
      title: '专网概览'
    }
  },
  {
    name:'page1',
    path:'/page1',
    component:() => import( '@/views/page1'),
  },
  {
    name:'page2',
    path:'/page2',
    component:() => import( '@/views/page2'),
  },
]
const router = new Router({
  routes,
  mode:"history"
})
export default router