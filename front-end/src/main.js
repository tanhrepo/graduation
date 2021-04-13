import Vue from "vue"
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './styles/index.scss'
import './assets/iconfont/iconfont.css'
import './assets/iconfont/iconfont.js'
import 'animate.css';
import App from "./App.vue"
import router from "./router"
import store from "./store"
import Viewer from "v-viewer"// 图片预览
import "viewerjs/dist/viewer.css"// 图片预览
import './permission' // permission control



Vue.use(Viewer)
Vue.use(ElementUI, { size: 'small' })
Vue.config.productionTip = false

Viewer.setDefaults({
  defaultOptions: {
    zIndex: 9999
  },
  Options: {
    "inline": true,
    "button": true,
    "navbar": true,
    "title": true,
    "toolbar": true,
    "tooltip": true,
    "movable": true,
    "zoomable": true,
    "rotatable": true,
    "scalable": true,
    "transition": true,
    "fullscreen": true,
    "keyboard": true,
    "url": "data-source"
  }
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')