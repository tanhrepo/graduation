import Vue from "vue"
import ElementUI from 'element-ui'
import './styles/index.scss'
import './assets/iconfont/iconfont.css'
import App from "./App.vue"
import router from "./router"
import store from "./store"

Vue.use(ElementUI, { size: 'small' })
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')