<template>
  <div>
    <!-- 因为是第二个页面所以可以复制黏贴一下 -->

    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
      <el-breadcrumb-item>权限列表</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 卡片视图区 -->
    <el-card class="box-card">
      <!-- 表格区 -->
      <!-- 参考: https://element.eleme.cn/2.0/#/zh-CN/component/table -->
      <!-- :data:指定表格中的数据源-->
      <!-- stripe: 隔行变色显示-->
      <el-table :data="permList" border :stripe="true">
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column label="权限名称" prop="authName"></el-table-column>
        <el-table-column label="路径" prop="path"></el-table-column>
        <!-- 需要使用美化标签Tag-->
        <!-- 参考: -->
        <el-table-column label="访问级别" prop="level">
          <template slot-scope="scope" l>
            <el-tag v-if="scope.row.level === '0'" type="info">一级</el-tag>
            <el-tag v-if="scope.row.level === '1'" >二级</el-tag>
            <el-tag v-if="scope.row.level === '2'" type="warning">三级</el-tag>
          </template>

        </el-table-column>
      </el-table>

    </el-card>


  </div>
</template>

<script>
  export default {
    name: "Perm",
    data() {
      return {
        permList: [],

      }
    },
    methods: {
      async getPermList() {
        const {data: res} = await this.axios.get('perm');
        console.log(res);

        if (res.meta.status !== 200) return this.$message.error('数据获取失败');
        //将获取到的数据添加至data; 良好习惯,事先在data中设定好的参数结构再赋值,而不是等数据来了后再设置进data
        this.permList = res.data.perms;
      },


    },
    created() {
      this.getPermList();
    }
  }
</script>

<style scoped lang="less">

</style>