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
  // 推荐
  {
    path: '/dashboard',
    name: 'dashboard',
    component: Main,
    redirect: '/dashboard/index',
    meta: {
      title: '推荐',
      breadcrumb: false,
      hideSubMenu: true,
      single: true // 无子菜单
    },
    children: [
      {
        path: 'index',
        name: 'index',
        component: () => import(/* webpackChunkName: "dashboard" */ '@/views/home/index'),
        meta: {
          title: '推荐',
          index: 0
        },
      }
    ]
  },
  // 关注页
  {
    path: '/following',
    name: 'following',
    component: Main,
    redirect: '/following/index',
    meta: {
      title: '关注',
      breadcrumb: false,
      hideSubMenu: true,
      single: true // 无子菜单
    },
    children: [
      {
        path: 'index',
        name: 'followingIndex',
        component: () => import(/* webpackChunkName: "dashboard" */ '@/views/following/Following'),
        meta: {
          title: '关注',
          index: 1
        },
      }
    ]
  },
  // 图文页
  {
    path: '/imageText',
    name: 'imageText',
    component: Main,
    redirect: '/imageText/index',
    meta: {
      title: '图文',
      breadcrumb: false,
      hideSubMenu: true,
      single: true // 无子菜单
    },
    children: [
      {
        path: 'index',
        name: 'imageTextIndex',
        component: () => import(/* webpackChunkName: "dashboard" */ '@/views/imageText/ImageText'),
        meta: {
          title: '图文',
          index: 2
        },
      }
    ]
  },
  // 视频页
  {
    path: '/video',
    name: 'video',
    component: Main,
    redirect: '/video/index',
    meta: {
      title: '视频',
      breadcrumb: false,
      hideSubMenu: true,
      single: true // 无子菜单
    },
    children: [
      {
        path: 'index',
        name: 'videoIndex',
        component: () => import(/* webpackChunkName: "dashboard" */ '@/views/video/VideoPage'),
        meta: {
          title: '视频',
          index: 3
        },
      }
    ]
  },
  // 话题页
  {
    path: '/topic',
    name: 'topic',
    component: Main,
    redirect: '/topic/index',
    meta: {
      title: '话题',
      breadcrumb: false,
      hideSubMenu: true,
      single: true // 无子菜单
    },
    children: [
      {
        path: 'index',
        name: 'topicIndex',
        component: () => import(/* webpackChunkName: "dashboard" */ '@/views/topic/TopicPage'),
        meta: {
          title: '话题',
          index: 4
        },
      }
    ]
  },
  // 消息
  {
    path: '/message',
    name: 'message',
    component: Main,
    redirect: '/message/index',
    meta: {
      title: '消息',
      breadcrumb: false,
      hideSubMenu: true,
      single: true // 无子菜单
    },
    children: [
      {
        path: 'index',
        name: 'messageIndex',
        component: () => import(/* webpackChunkName: "dashboard" */ '@/views/topic/TopicPage'),
        meta: {
          title: '消息',
          index: 5
        },
      }
    ]
  },
  // 我的
  {
    path: '/mine',
    name: 'mine',
    component: Main,
    redirect: '/mine/index',
    meta: {
      title: '我的',
      breadcrumb: false,
      hideSubMenu: true,
      single: true // 无子菜单
    },
    children: [
      {
        path: 'index',
        name: 'mineIndex',
        component: () => import(/* webpackChunkName: "dashboard" */ '@/views/mine/MinePage'),
        meta: {
          title: '我的',
          index: 6
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
