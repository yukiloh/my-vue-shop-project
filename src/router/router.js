import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Home from '../components/Home.vue'
import Welcome from '../components/Welcome.vue'
import Users from '../components/user/Users.vue'
import Goods from '../components/goods/Goods.vue'
import Categories from '../components/goods/categories.vue'
import CateParams from '../components/goods/cateParams.vue'
import Perm from '../components/perms/Perm.vue'
import Orders from '../components/orders/Orders.vue'
import Stats from '../components/stats/Stats.vue'
import Roles from "../components/perms/Roles";
import Roles2 from "../components/perms/Roles2";

Vue.use(VueRouter);

const routes = [

  {path: '/login',component: Login},
  {path: '/home',component: Home,

    //重定向,当访问了home后直接重定向到/home/welcome
    redirect: '/welcome',
    // home路径下的子路由
    children: [
      {path: '/welcome',component: Welcome},
      {path: '/users',component: Users},
      {path: '/goods',component: Goods},
      {path: '/roles',component: Roles},
      {path: '/orders',component: Orders},
      {path: '/perm',component: Perm},
      {path: '/stats',component: Stats},
      {path: '/roles2',component: Roles2},
      {path: '/cateParams',component: CateParams},
      {path: '/categories',component: Categories},
    ]},
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
