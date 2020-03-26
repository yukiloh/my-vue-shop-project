import Vue from 'vue'
import App from './App.vue'
import router from './router/router'
import './plugins/element.js'

import './assets/css/global.css'    //需要额外引入css文件

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
