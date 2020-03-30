<template>
  <!-- 用户列表页面 -->
  <div>

    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>活动管理</el-breadcrumb-item>
      <el-breadcrumb-item>活动列表</el-breadcrumb-item>
    </el-breadcrumb>



    <!-- 卡片视图区 -->
    <el-card class="box-card">


      <!-- eleUI的栅格系统 一共24格-->
      <!-- gutter:2个栅格之间的间隔,单位可能是px,如果扩张会减少左由2个栅格的宽度-->
      <el-row gutter="20">

        <el-col :span="8">
          <el-input placeholder="请输入内容">
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>


        <el-col :span="4">
          <el-button type="primary">添加用户</el-button>

        </el-col>
      </el-row>

    </el-card>

  </div>
</template>

<script>
  export default {
    name: "users"
  }
</script>

<style scoped>

</style>