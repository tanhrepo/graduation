import Main from '@/views/components/Main'
// import Layout from '@/components/Layout'
const routes = [
  {
    path: '/',
    redirect: '/dashboard/index',
    component: Main,
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/login'),
  },
  {
    path: '/dashboard',
    name: 'dashboard',
    component: Main,
    redirect: '/dashboard/index',
    meta: {
      title: '首页',
      breadcrumb: false,
      hideSubMenu: true,
      single: true // 无子菜单
    },
    children: [
      {
        path: 'index',
        name: 'dashboardIndex',
        component: () => import(/* webpackChunkName: "dashboard" */ '@/views/home/index'),
        meta: {
          title: '首页',
        },
      }
    ]
  },
  {
    path: '*',
    component: () => import('@/views/404.vue')
  }
]

export default routes
