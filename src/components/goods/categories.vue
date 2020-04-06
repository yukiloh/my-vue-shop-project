<template>
  <div>


    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
      <el-breadcrumb-item>角色管理</el-breadcrumb-item>
    </el-breadcrumb>


    <!-- 卡片视图区 -->
    <el-card class="box-card">
      <el-table
          :data="tableData"
          style="width: 100%;margin-bottom: 20px;"
          row-key="id"
          border
          default-expand-all
          :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
        <el-table-column
            prop="date"
            label="日期"
            sortable
            width="180">
        </el-table-column>
        <el-table-column
            prop="name"
            label="姓名"
            sortable
            width="180">
        </el-table-column>
        <el-table-column
            prop="address"
            label="地址">
        </el-table-column>
      </el-table>



    </el-card>


  </div>
  
</template>

<script>
  export default {
    name: "categories"
  }
</script>

<style scoped lang="less">

</style>