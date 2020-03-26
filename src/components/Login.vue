
<!-- .vue为单文件组件,主要包含结构(template标签),行为(script)和样式(style) -->
<template>
  <div class="login_container">

    <div class="login_box">

      <!-- 头像区 -->
      <div class="avatar_box">
        <img src = "../assets/img/avatar.png" alt = "">
      </div>


      <!-- 表单区,这里使用了elementUI,因为当时创建项目时时按需加载,所以需要到plugins中配置加载项!-->
      <el-form label-width="0px" class="login_form"> <!-- label-width:给label预留空间,哪怕没有label如果预留了空间也会生效 -->
        <!-- 用户名 -->
        <el-form-item>
          <el-input></el-input>
        </el-form-item>

        <!-- 密码 -->
        <el-form-item>
          <el-input></el-input>
        </el-form-item>


        <el-form-item class="btns">
          <el-button type="primary">登陆</el-button>
          <el-button type="info">重置</el-button>
        </el-form-item>

      </el-form>

    </div>







  </div>
</template>

<script>

export default {}
</script>


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


  .login_form {
    position: absolute;
    bottom: 0;
    width: 100%;
    padding: 0 20px;
    box-sizing: border-box;   /*告诉浏览器如何区计算当前元素的宽和高;border-box让计算时包含边框和内边距*/
                              /*具体参考 https://www.jianshu.com/p/9c7487f4e89a*/
  }


  .btns {
    display: flex;    /*弹性布局*/
    justify-content: flex-end;  /*让他居右*/
  }
</style>

