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
      <el-aside :width="asideWidth">

        <!-- 折叠功能 -->
        <div class="toggle-button" @click="toggleCollapse">{{toggleIcon}}</div>

        <!-- 侧边栏菜单区;添加了新的元素同样需要重新引入 -->

        <!-- unique-opened:每次只打开1个菜单; 加上:让值为布尔值,不加则是string,如果只填写unique-opened也表示打开该选项 -->
        <!-- collapse:是否开启折叠,具体的布尔值绑定到data中方便toggleCollapse进行切换-->
        <!-- collapse-transition: 是否开启动画效果,比较丑就关了 -->
        <!-- 参考: https://element.eleme.cn/2.0/#/zh-CN/component/menu Menu Attribute中的collapse属性-->
        <!-- router: 开启跳转,会跳转至index所对应的地址上(只对二级菜单有效)-->
        <!-- 修改了index后原本的自动高亮消失,需要设置default-active来指定哪个菜单高亮;  可以从$route.path来获取当前页面的地址-->
        <!-- active-text-color: 选中时的高亮颜色-->
        <el-menu
            background-color="#373744"
            text-color="#fff"
            :unique-opened="true"
            :collapse="isCollapse"
            :collapse-transition="false"
            :router="true"
            :default-active="$route.path"
            active-text-color="#409BFF"
        >

          <!-- 一级菜单-->
          <!-- 菜单也是通过后台获取的数据; 根据获取的数据来遍历-->
          <!-- :index 动态数据绑定--> <!-- index必须唯一,eleUI是同故宫index来控制哪个菜单被打开/折叠 -->
          <!--index的值必须是string,但是接收的int,所以加个''强转-->
          <el-submenu :index="item.id+''" v-for="item in menuList" :key="item.id">

            <!-- 一级菜单模板区(显示区)-->
            <template slot="title">
              <!-- 图标 -->
              <!-- 一级图标通过读取预设的iconObj中的key:value来决定-->
              <i :class="iconObj[item.id]"></i>
              <!-- 文本 -->
              <span>{{item.authName}}</span>
            </template>

            <!-- 二级菜单,添加后使得一级菜单可以弹出二级菜单 -->
            <!-- 二级菜单的index需要router跳转,所以设为path的值-->
            <el-menu-item :index="'/' + subItem.path" v-for="subItem in item.children" :key="subItem.id">
              <!-- 二级菜单的内容与一级菜单的模板去一样;  如果需要再添加一级则再template标签后再添加el-menu-item即可-->
              <template slot="title">
                <!-- 二级图标都是menu -->
                <i class="el-icon-menu"></i>
                <span>{{subItem.authName}}</span>
              </template>
            </el-menu-item>

          </el-submenu>
        </el-menu>
      </el-aside>


      <!--右侧主体-->
      <el-container>
        <el-main>
          <router-view></router-view>
        </el-main>
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
        //每个icon对应的图标
        iconObj: {
          '101': 'el-icon-user',
          '201': 'el-icon-s-check',
          '301': 'el-icon-goods',
          '401': 'el-icon-s-order',
          '501': 'el-icon-data-analysis',
        },
        //是否折叠的控制器
        isCollapse: false,
        toggleIcon: '<<<',
        asideWidth: "200px"
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
        // console.log(res); //测试用

        //如果数据返回失败则调用eleUI的错误message显示信息
        if (res.meta.status !== 200) return this.$message.error(res.meta.msg);

        //如果数据返回成功,则将获取到的res赋值到data中
        this.menuList = res.data;
        // console.log(this.menuList);    //检查用
      },

      //折叠菜单栏
      toggleCollapse() {
        this.isCollapse = !this.isCollapse;

        if (this.isCollapse === true) {
          this.asideWidth = "64px";
          this.toggleIcon = ">>";
        }else {
          this.asideWidth = "200px";
          this.toggleIcon = "<<<";
        }
      },
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

    .el-menu {
      border-right: none;
    }

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


  //让一级选项的文字和icon间隔增大;  eleUI最后标签都会转化为class所以可以用.el-xxx来选择
  .el-submenu span{
    margin-left: 5px;
  }

  //为折叠栏添加样式
  .toggle-button {
    font-size: 20px;
    line-height: 30px;
    color: #b6b6b6;
    text-align: center;
    letter-spacing: -0.2em;  //字间距
  }

</style>
