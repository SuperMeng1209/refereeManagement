import App from './App'

// #ifndef VUE3
import Vue from 'vue'
Vue.config.productionTip = false
App.mpType = 'app'
// main.js
import uView from '@/uni_modules/uview-ui'
Vue.use(uView)
const app = new Vue({
    ...App
})
app.$mount()
// #endif
//小程序不允许使用127.0.0.1 mac:ifconfig  window:ipconfig
Vue.prototype.$serviceUrl = '192.168.43.237'
// #ifdef VUE3
import { createSSRApp } from 'vue'
export function createApp() {
  const app = createSSRApp(App)
  return {
    app
  }
}
// #endif