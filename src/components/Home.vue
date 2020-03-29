<template>

  <!-- 后台管理的主页面,分为上banner,左menu,右body,右下foot的布局-->
  <!-- 可以直接从elementUI的 Container 中找到合适的布局  -->
  <!-- 插入el标签后依然需要区element.js中导入相关组件-->

  <el-container class="home-container">

    <!-- 头部-->
    <el-header>
      <!--左侧用一个div包裹logo和文字-->
      <div>
        <img src="../assets/img/logo.png" height="50" width="50"/>
        <span>后台管理系统</span>
      </div>




      <el-button type="info" @click="logout">退出</el-button>
    </el-header>

    <!-- 主体 -->
    <el-container>

      <!--侧边栏-->
      <el-aside>

        <!-- 侧边栏菜单区;添加了新的元素同样需要重新引入 -->
        <el-menu
            width="200px"
            background-color="#373744"
            text-color="#fff"
            active-text-color="#ffd04b">

          <!-- 一级菜单-->
          <el-submenu index="1" >

            <!-- 一级菜单模板区(显示区)-->
            <template slot="title">
              <!-- 图标 -->
              <i class="el-icon-location"></i>
              <!-- 文本 -->
              <span>导航一</span>
            </template>

            <!-- 二级菜单,添加后使得一级菜单可以弹出二级菜单 -->
            <el-menu-item index="1-2">
              <!-- 二级菜单的内容与一级菜单的模板去一样;  如果需要再添加一级则再template标签后再添加el-menu-item即可-->
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>导航一</span>
              </template>
            </el-menu-item>

          </el-submenu>
        </el-menu>


        Aside
      </el-aside>


      <!--右侧主体-->
      <el-container>
        <el-main>Main</el-main>
      </el-container>

    </el-container>
  </el-container>
</template>

<script>
  export default {

    //vue在页面创建期间调用的函数
    created() {
      //左侧的按钮也是远程调用获取的数据,所以需要在页面刚创建的时候调用
      this.getMenuList();

    },
    name: "Home",

    data() {
      return {
        //左侧菜单的数据
        menuList: [],
      }
    },

    methods: {
      //退出功能,清空sessionStorage
      logout() {
        window.sessionStorage.clear();
        this.$router.push('login');
        this.$message.success('退出成功！');
      },

      //获取左侧菜单; 因为是axios的异步调用所以async+await;
      async getMenuList() {
        const {data:res} = await this.axios.get('menu');    //错误点:解构赋值,将返回数据中的data,赋值为res!
        console.log(res); //测试用

        //如果数据返回失败则调用eleUI的错误message显示信息
        if (res.meta.status != 200) return this.$message.error(res.meta.msg);

        //如果数据返回成功,则将获取到的res赋值到data中
        this.menuList = res.data;

      }
    }

  }
</script>

<style scoped lang="less">

  //可以直接用el标签来获取
  .el-header {
    background: #373744;
    display: flex;    //开启弹性布局,下面2个都需要开启flex
    justify-content: space-between;   //让每个元素所占的宽度相同,并且向两边对齐
    align-items: center;    //让元素上下居中对齐 参考: https://www.runoob.com/cssref/css3-pr-align-items.html

    padding-left: 2px;    //ele自带的头部间距有点大所以修改边距
    padding-top: 2px;

    color: #eeeeee;
    font-size: 20px;

    //el-header的子元素选择器,可以不用再写 .el-header div{...}
    > div {
      display: flex;    //里面的div依然还是要再开一次flex
      align-items: center;
      > span {
        margin-left: 15px;
      }
    }

    > img {
      border-radius: 50%;
    }
  }


  .el-aside {
    background: #373744;
  }

  .el-main {
    background: #eaedf1;
  }

  /*通过F12可以看到div#app下存在标签 section data-v-8dc7cce2="" class="el-container...*/
  /*需要让该标签高度设为100%才能让home页面撑满元素;*/
  /*没有选择用container的原因可能是考虑会冲突*/
  .home-container {
    height: 100%;
  }


</style>
