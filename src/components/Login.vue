
<!-- .vue为单文件组件,主要包含结构(template标签),行为(script)和样式(style) -->
<!-- 结构区域 -->
<template>
  <div class="login_container">

    <div class="login_box">

      <!-- 头像区 -->
      <div class="avatar_box">
        <img src = "../assets/img/avatar.png" alt = "">
      </div>


      <!-- 表单区,这里使用了elementUI,因为当时创建项目时时按需加载,所以需要到plugins中配置加载项!-->
      <!-- label-width:给label预留空间,哪怕没有label如果预留了空间也会生效 -->
      <!-- :model : v-bind:的简写,定义一个绑定元素的属性;另外需要在script(行为区域)定义data--><!--去掉:model也可以进行数据绑定....-->
      <!-- :rules: 为表单的每项添加规则-->
      <el-form label-width="0px" class="login_form" :model="loginForm" :rules="loginRules" ref="loginFormRef">
        <!-- 用户名 -->
        <!-- prop: 对应rules的规则名称 -->
        <el-form-item prop="username">
          <!-- prefix-icon:前置图标,可以用<i slot>来实现输入栏中的图标-->
          <!-- 如果需要导入第三方的图标库,需要在main.js中import '.../xxx.css' 再插入到prefix-icon中-->
          <!-- v-model: 将数据双向绑定到input中-->
          <el-input prefix-icon="el-icon-user-solid" v-model="loginForm.username"></el-input>
        </el-form-item>

        <!-- 密码 -->
        <el-form-item prop="password">
          <el-input prefix-icon="el-icon-lock" v-model="loginForm.password" type="password"></el-input>
        </el-form-item>


        <el-form-item class="btns">
          <!-- 登陆前需要进行表单验证 -->
          <el-button type="primary" @click="login">登陆</el-button>
          <!-- eleUI自带对表单重置的方法 resetFields ,需要先获取表单的实例对象 el-form -->
          <el-button type="info" @click="resetLoginForm">重置</el-button>
        </el-form-item>

      </el-form>

    </div>

  </div>
</template>






<!-- 行为区域 -->
<script>

export default {

  //定义数据
  data() {
    return {
      //登陆表单的数据
      loginForm: {
        username: '',
        password: '',
      },
      //表单验证规则
      loginRules: {
        //验证用户名的合法性
        username: [
          {require: true,message: '需要用户名',trigger: blur}, //参数解释:必选,提示信息,当失去焦点时触发
          {min: 6,max: 10,message: '长度在 6 到 10 个字符',trigger: 'blur'}  //最小,最大,提示信息,当失焦
        ],
        //密码的合法性
        password: [
          {require: true,message: '需要密码',trigger: blur},
          {min: 6,max: 10,message: '长度在 6 到 10 个字符',trigger: 'blur'}
        ],
      }
    }
  },


  //定义方法
  methods: {
    //重置表单
    resetLoginForm() {
      // console.log(this);   //this是当前组件,而$refs是他的一个属性,由此找到目标表单
      // resetFields: eleUI提供的重置表单的方法;  注意如果data里面有值还是会重置回data中的值的
      // 关于表单相关: https://element.eleme.cn/2.0/#/zh-CN/component/form    表单相关的方法也在其中
      this.$refs.loginFormRef.resetFields();

    },
    //登陆功能,需要先进行表单验证
    login() {
      //和重置表单一样,需要先获取表单对象(loginFormRef)来进行后续的操作
      this.$refs.loginFormRef.validate( async valid => {
        // console.log(valid); //true/false,判断条件依据 ↑的loginRules决定,满足则true
        if (!valid) return; //不满足则直接返回

        //通过axios发起登陆请求;  需要在main.js中进行配置!  需要明白此处的this是Vue!!否则需要导入Vue
        //因为axios返回的是一个promise,所以可以用async和await来接收!
        //async需要修饰在紧挨着await的方法上,而这里的方法是是箭头函数,加在↑!
        //通过解构赋值,将返回的data直接赋值为result
        let {data: result} = await this.axios.post('login',this.loginForm);
        console.log(result);
        
        if (result.meta.status !== 200) return console.log('login failed!');
        else console.log('login success!');
      })

    }
  }
}
</script>



<!-- 样式区域 -->
<!--使用less语法;   scoped表示样式只在当前组件内生效,一般必选-->
<!-- 注意,使用less需要额外安装less和less-loader-->
<style lang="less" scoped>
  /*背景*/
  .login_container {
    background-color: #2b4b6b;
    height: 100%;
  }

  /*登陆的盒子*/
  .login_box {
    width: 450px;
    height: 300px;
    background-color: #ffffff;
    border-radius: 5%;  /*圆角边框,用px效果太差*/
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%,-50%);  /*向x,y移动自身长宽的-50%距离,配合left top的50%正好让div处于中间*/
  }


  /*头像框*/
  .avatar_box {
    border-radius: 50%;   /*形成圆角*/
    border: #eeeeee 1px solid;
    height: 130px;
    width: 130px;
    padding: 10px;   /*让图片和外边框形成间距*/
    box-shadow: 0 0 10px #dddddd;   /*设置图形阴影,具体可以看 https://my.oschina.net/u/3887849/blog/1841581 */
    position: absolute;

    /*让头像外框居中*/
    left: 50%;
    transform: translate(-50%,-50%);

    background: #eeeeee;

    img {
      width: 100%;
      height: 100%;
      border-radius: 50%;   /*图片也要加圆角*/
      background-color: #eeeeee;
    }
  }


  /*表单输入区*/
  .login_form {
    position: absolute;
    bottom: 0;
    width: 100%;
    padding: 0 20px;
    box-sizing: border-box;   /*告诉浏览器如何区计算当前元素的宽和高;border-box让计算时包含边框和内边距*/
                              /*具体参考 https://www.jianshu.com/p/9c7487f4e89a*/
  }


  /*登陆和重置按钮*/
  .btns {
    display: flex;    /*弹性布局*/
    justify-content: flex-end;  /*让他居右*/
  }
</style>

