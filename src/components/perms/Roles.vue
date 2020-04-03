<template>
  <div>

    <!-- 因为是第二个页面所以可以复制黏贴一下 -->

    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
      <el-breadcrumb-item>角色管理</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 卡片视图区 -->
    <el-card class="box-card">
      <!-- 表格区 -->
      <!-- @expand-change:  点击展开数据后的回调函数"-->
      <el-table :data="rolesList" border :stripe="true" @expand-change="testttt">
        <!-- expand: 点击后可以展开本条数据-->
        <el-table-column type="expand" ></el-table-column>
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column label="角色" prop="authName"></el-table-column>
        <el-table-column label="描述" prop="desc"></el-table-column>
        <!-- 需要使用美化标签Tag-->
        <!-- 参考: https://element.eleme.cn/2.0/#/zh-CN/component/tag-->
        <el-table-column label="操作" prop="level">
          <template slot-scope="scope">

            <el-tooltip effect="light" content="修改" placement="top" :enterable="false" >
              <!-- scope.row可以获取到改行所对应的数据! -->
              <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditUserDialog(scope.row)"></el-button>
            </el-tooltip>

            <!-- 执行删除从操作时需要弹出警告框,此时需要用MessageBox-->
            <el-tooltip effect="light" content="删除" placement="top" :enterable="false">
              <!-- @click:通过id来删除user -->
              <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeUserById(scope.row.id)"></el-button>
            </el-tooltip>

            <!-- 此项没做! -->
            <el-tooltip effect="light" content="分配权限" placement="top" :enterable="false">
              <el-button type="warning" icon="el-icon-setting" size="mini"></el-button>
            </el-tooltip>
          </template>

        </el-table-column>
      </el-table>

    </el-card>


  </div>
</template>

<script>
  export default {
    name: "Roles",

    data() {
      return {
        rolesList: [],

      }
    },
    methods: {
      async getRoleList() {
        const {data: res} = await this.axios.get('roles');
        // console.log(res);  //测试用

        if (res.meta.status !== 200) return this.$message.error('数据获取失败');
        //将获取到的数据添加至data; 良好习惯,事先在data中设定好的参数结构再赋值,而不是等数据来了后再设置进data
        this.rolesList = res.data;
      },

      testttt() {
        console.log(2);
      }


    },
    created() {
      this.getRoleList();
    }
  }
</script>

<style scoped lang="less">

</style>