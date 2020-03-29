import Vue from 'vue'
import { Button, Form, FormItem, Input,
  Message, Container, Header,Aside,Main,
  Menu,Submenu,MenuItem
} from 'element-ui' //额外导入form和input相关的组件
//导入Message消息提示,与form input不同,他需要进行全局挂载

//如果偷懒全局加载的话
// import Element from 'element-ui'

Vue.use(Button);    //导入后不要忘记需要使用!
Vue.use(Form);
Vue.use(FormItem);
Vue.use(Input);
Vue.use(Container);
Vue.use(Header);
Vue.use(Aside);
Vue.use(Main);
Vue.use(Menu);
Vue.use(Submenu);
Vue.use(MenuItem);

Vue.prototype.$message = Message;   //将Message挂载到全局的Vue上