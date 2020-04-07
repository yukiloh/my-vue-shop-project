<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
      <el-breadcrumb-item>商品列表</el-breadcrumb-item>
    </el-breadcrumb>


    <!-- 卡片区-->

    <el-card class="box-card">

      <!--分2行-->

      <!--搜索input,添加商品btn-->

      <!--商品列表form-->


    </el-card>
  </div>
</template>

<script>

  export default {
    name: 'Goods',
  }

</script>



<style scoped lang="less">

</style>