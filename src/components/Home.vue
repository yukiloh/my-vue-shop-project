<template>

  <!-- 后台管理的主页面,分为上banner,左menu,右body,右下foot的布局-->
  <!-- 可以直接从elementUI的 Container 中找到合适的布局  -->
  <!-- 插入el标签后依然需要区element.js中导入相关组件-->

  <el-container class="home-container">

    <!-- 头部-->
    <el-header>
      Header
      <el-button type="info" @click="logout">退出</el-button>
    </el-header>

    <!-- 主体 -->
    <el-container>

      <!--侧边栏-->
      <el-aside width="200px">Aside</el-aside>

      <!--右侧主体-->
      <el-container>
        <el-main>Main</el-main>
      </el-container>
      
    </el-container>
  </el-container>
</template>

<script>
  export default {
    name: "Home",
    methods: {
      //退出功能,清空sessionStorage
      logout() {
        window.sessionStorage.clear();
        this.$router.push('login');
        this.$message.success('退出成功！');
      }
    }

  }
</script>

<style scoped lang="less">
  /*可以直接用el标签来获取*/
  .el-header {
    background: #373744;
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
