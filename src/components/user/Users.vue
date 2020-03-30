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


      <!-- 搜索与添加区 -->
      <!-- eleUI的栅格系统 一共24格-->
      <!-- gutter:2个栅格之间的间隔,单位可能是px,如果扩张会减少左由2个栅格的宽度; 注意,也需要用:gutter,数值不是string-->
      <el-row :gutter="20">

        <el-col :span="8">
          <el-input placeholder="请输入内容">
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>

        <el-col :span="4">
          <el-button type="primary">添加用户</el-button>
        </el-col>
      </el-row>

      <!-- 表格区 -->
      <!-- 参考: https://element.eleme.cn/2.0/#/zh-CN/component/table -->
      <!-- :data:指定表格中的数据源-->
      <el-table :data="userList" border :stripe="true">
        <!-- 通过设置type = index 来添加索引(默认效果) -->
        <!-- 通过lable来指定表头名,prop指定字段的数据-->
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column label="姓名"prop="userName"></el-table-column>
        <el-table-column label="手机"prop="mobile"></el-table-column>
        <el-table-column label="邮箱"prop="email"></el-table-column>
<!--        <el-table-column label="创建时间"prop="createTime"></el-table-column>-->
        <el-table-column label="权限"prop="roleName"></el-table-column>

        <!-- 无法直接显示布尔值,需要借助作用域插槽slot-scope来获取--> <!-- 据说新版是v-slot-->
        <el-table-column label="状态">
          <template slot-scope="scope">
            <el-switch
                v-model="scope.row.mgState"
                active-color="#409BFF"
                inactive-color="#373744">
            </el-switch>
          </template>
        </el-table-column>


        <el-table-column label="操作" width="250px">  <!-- width强行设定宽度 -->
          <!-- 不知道为啥这里也用了scope -->
          <template slot-scope="scope">

            <!-- toolTip 鼠标提示文字,也需要导入! -->
            <!-- :enterable="false" 鼠标移入后提示框自动消失-->
            <!-- 参考: https://element.eleme.cn/2.0/#/zh-CN/component/tooltip -->
            <el-tooltip effect="light" content="修改" placement="top" :enterable="false">
              <el-button type="primary" icon="el-icon-edit" size="mini"></el-button>
            </el-tooltip>

            <el-tooltip effect="light" content="删除" placement="top" :enterable="false">
              <el-button type="danger" icon="el-icon-delete" size="mini"></el-button>
            </el-tooltip>

            <el-tooltip effect="light" content="设置用户" placement="top" :enterable="false">
              <el-button type="info" icon="el-icon-setting" size="mini"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页区 -->
      <div class="block">
        <!-- @size-change: 切换分页显示的条数,需要自己定义方法-->
        <!-- @@current-change: 切换当前显示页-->
        <!-- :current-page: 当前页数 -->
        <!-- :page-sizes: 各个页面显示条数,数组-->
        <!-- :page-size: 当前每页显示的条数-->
        <!-- layout:可以选分页中展示哪个属性 -->
        <!-- :total: 共计所有数据-->
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="queryInfo.currentPage"
            :page-sizes="[1, 2, 5, 10]"
            :page-size="queryInfo.currentPage"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>
      </div>

    </el-card>

  </div>
</template>

<script>
  export default {
    name: "users",
    data() {
      return {
        queryInfo: {
          query: '',
          currentPage: 1,
          pageSize: 2,
        },
        userList: [],
        total: 0,
      }
    },
    methods: {
      //获取用户列表
      async getUserList() {
        const {data:res} = await this.axios.get('users',{params: this.queryInfo});
        //发出去的请求: http://murasakichigo.xyz:9090/my-vue-shop-project/users?query=&pagenum=1&pagesize=2 可见axios会自动拼接参数
        console.log(res);   //测试用
        
        if (res.meta.status !== 200) return this.$message.error('数据获取失败');
        //将获取到的数据添加至data; 良好习惯,事先在data中设定好的参数结构再赋值,而不是等数据来了后再设置进data
        this.userList = res.data.users;
        this.total = res.data.total;
      },

      // 分页相关
      // 控制分页尺寸
      handleSizeChange(newSize) {
        // console.log(newSize);
        //设定当前页显示的条数后重新去请求服务器数据
        this.queryInfo.pageSize = newSize;
        this.getUserList();
      },

      // 显示当前页面
      handleCurrentChange(newPage) {
        console.log(newPage);
        this.queryInfo.currentPage = newPage;
        this.getUserList();
      }
    },
    created() {
      this.getUserList();
    }
  }
</script>

<style scoped>

  .el-table {
    margin-top: 20px;
  }

  .el-pagination {
    margin-top: 15px;
  }


</style>