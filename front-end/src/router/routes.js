import Main from '@/views/components/Main'
import Layout from '@/components/Layout'
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
      single: true, // 无子菜单
    },
    children: [
      {
        path: 'home',
        name: 'home',
        component: Layout,
        meta: {
          title: '推荐',
        },
        children:[
          {
            path: '/dashboard/index',
            name: 'homeIndex',
            component: () => import(/* webpackChunkName: "dashboard" */ '@/views/home/index'),
            meta: {
              title: '推荐',
              index: 0
            },
          }
        ]
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
      },
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
        component: () => import(/* webpackChunkName: "dashboard" */ '@/views/message/MessagePage'),
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
  },
  // 新建文章
  {
    path: '/system',
    name: 'system',
    component: Main,
    meta: {
      title: '编辑',
      breadcrumb: false,
      hideSubMenu: true,
      single: true, // 无子菜单
      isHidden:true,
    },
    children: [
      {
        path: 'add',
        name: 'add',
        component: () => import(/* webpackChunkName: "dashboard" */ '@/views/article/ArticleAdd'),
        meta: {
          title: '新建文章',
          index: 1000
        },
      },
      {
        path: 'addTopic',
        name: 'addTopic',
        component: () => import(/* webpackChunkName: "dashboard" */ '@/views/topic/components/TopicAdd'),
        meta: {
          title: '新建话题',
          index: 999
        },
      },
      {
        path: 'detail',
        name: 'detail',
        component: () => import(/* webpackChunkName: "dashboard" */ '@/views/article/ArticleDetail'),
        meta: {
          title: '文章详情',
          index: 998
        },
      },
      {
        path: 'topicDetail',
        name: 'topicDetail',
        component: () => import(/* webpackChunkName: "dashboard" */ '@/views/topic/TopicDetail'),
        meta: {
          title: '话题详情',
          index: 990
        },
      },
      {
        path: 'userPage',
        name: 'userPage',
        component: () => import(/* webpackChunkName: "dashboard" */ '@/views/mine/UserPage'),
        meta: {
          title: '个人主页',
          index: 991
        },
      },
      {
        path: 'userItem',
        name: 'userItem',
        component: () => import(/* webpackChunkName: "dashboard" */ '@/views/mine/user/index'),
        meta: {
          title: '个人主页',
          index: 992
        },
      }
    ]
  },
]

export default routes
