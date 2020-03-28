import Vue from 'vue'
import { Button, Form, FormItem, Input, Message} from 'element-ui' //额外导入form和input相关的组件
//导入Message消息提示,与form input不同,他需要进行全局挂载

Vue.use(Button);
Vue.use(Form);  //导入后需要使用!
Vue.use(FormItem);
Vue.use(Input);

Vue.prototype.$message = Message;   //将Message挂载到全局的Vue上