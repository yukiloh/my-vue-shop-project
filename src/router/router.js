import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'

Vue.use(VueRouter);

const routes = [
  {
    path: '/login',
    component: Login
  },
  // {
  //   path: '/',
  //   redirect: '/login' //也可以直接进行重定向而不是导航到到组件
  // },
];

const router = new VueRouter({
  routes
});

export default router
