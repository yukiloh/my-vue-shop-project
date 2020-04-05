<template>
  <div>

    <!-- 复制自roles 因为不满意他权限显示的方式 -->

    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
      <el-breadcrumb-item>角色管理</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 卡片视图区 -->
    <el-card class="box-card">
      <el-button type="warning" @click="changeView" class="changeViewButton">切换视图</el-button>


      <!-- 表格区 -->
      <!-- @expand-change:  点击展开数据后的回调函数"-->
      <el-table :data="rolesList" border :stripe="true" @expand-change="onExpand">
        <!-- expand: 点击后可以展开本条数据-->
        <el-table-column type="expand" >
          <template slot-scope="scope">

            <!-- 使用栅格系统-->
            <!--c1:class1,i1:index1-->
            <!-- class:动态的为该标签进行绑定!,让class称为一个数组,用三元判断是否是第一个元素,是则添加border top-->
            <el-row v-for="(c1,i1) in scope.row.children" :class="['border-bottom',i1 === 0 ? 'border-top' :'']">

              <!--第一列-->
              <el-col :span="8" >
                <el-tag type="info" >{{c1.authName}}</el-tag>
                <i class="el-icon-caret-right"></i>
              </el-col>

              <!--第二+三列-->
              <el-col :span="16" >
                <!-- 或者: i2 === 0 ? 插入top:不插top   代码更精简-->
                <el-row v-for="(c2,i2) in c1.children" :class="[i2 !== c1.children.length-1 ? 'border-bottom' :'']">
                  <el-col :span="8" >
                    <el-tag >{{c2.authName}}</el-tag>
                    <i class="el-icon-caret-right"></i>
                  </el-col>
                  <!-- 二级标签不需要每行一个直接铺满tag就好了 -->
                  <el-col :span="8" >
                    <el-tag type="warning" v-for="c3 in c2.children">{{c3.authName}}</el-tag>
                  </el-col>
                </el-row>
              </el-col >
            </el-row>

          </template>
        </el-table-column>
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

      onExpand() {
        console.log('?');
      },

      changeView() {
        this.$router.push('/roles');
      }


    },
    created() {
      this.getRoleList();
    }
  }
</script>

<style scoped lang="less">
  .changeViewButton {
    margin-bottom: 20px;
  }
  .el-tag {
    margin: 7px;
  }

  .border-bottom {
    border-bottom: #dddddd 1px solid;
  }

  .border-top {
    border-top: #dddddd 1px solid;
  }

</style>