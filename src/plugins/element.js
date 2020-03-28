import Vue from 'vue'
import { Button, Form, FormItem, Input } from 'element-ui' //额外导入form和input相关的组件
import { Message } from 'element-ui'        //导入Message消息提示,与form input不同,他需要进行全局挂在

Vue.use(Button);
Vue.use(Form);  //导入后需要使用!
Vue.use(FormItem);
Vue.use(Input);

Vue.prototype.$message = Message;   //将Message挂载到全局的Vue上
