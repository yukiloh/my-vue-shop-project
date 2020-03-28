import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Home from '../components/Home.vue'

Vue.use(VueRouter);

const routes = [

  {path: '/login',component: Login},
  {path: '/home',component: Home},
  {path: '/',redirect: '/login' },  //也可以直接进行重定向而不是导航到到组件

];

const router = new VueRouter({
  routes
});

//导航首位,类似于拦截器
//to: 将要访问的路径   from: 来自哪里    next:放行函数;
router.beforeEach((to,from,next) => {
  if (to.path === '/login') next();    //login的页面不进行拦截    错误点:路径没有加/
  const token = window.sessionStorage.getItem('token');
  if (!token) return next('/login');    //如果没有token则强制跳转login
  next();
});

export default router
