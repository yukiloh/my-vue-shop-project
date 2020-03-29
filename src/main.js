import Vue from 'vue'
import App from './App.vue'
import router from './router/router'
import './plugins/element.js'

import './assets/css/global.css'    //需要额外引入css文件

import axios from 'axios'         //引入axios包
import VueAxios from 'vue-axios'; //vue-axios的插件包,让vue.use可以使用axios



Vue.config.productionTip = false;

//================================================
//axios相关
// Vue.prototype.$http = axios;    //将axios挂在至vue的$http下,使用时: this.$http.post(...);  容易和其他项目起冲突,不使用;
Vue.use(VueAxios,axios);          //使用vue-axios来弥补↑的问题,使用时Vue.axios.get(...)

//可以设定axios的基础路径(api),例如localhost/api/,之后调用时可以省略前缀使用login来发起请求;  注意,可能不能省略/
// axios.defaults.baseURL = 'http://localhost:9090/my-vue-shop-project/';    //本地
axios.defaults.baseURL = 'http://murasakichigo.xyz:9090/my-vue-shop-project/';      //远程gcp

//axios的拦截器,对发起的异步进行预处理(检查token)
axios.interceptors.request.use( config => {
  //在axios发送前可以获取到这个config; 里面包含了本次需要发送的请求数据
  //console.log('axios config',config);

  //从请求数据中,为heads添加Authorization属性  (自定义的head)
  config.headers.Authorization = window.sessionStorage.getItem('token');
  //添加了后可以看到本次请求头中会初夏 Authorization: null ,服务器端则可以对该请求头进行校验,例如,如果没有该头则直接驳回发送401

  return config;  //必须返回config!
});


//================================================


new Vue({
  router,
  render: h => h(App)
}).$mount('#app');
