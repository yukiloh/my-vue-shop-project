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

        <!-- 搜索的本质就是向后台发起一次带query关键词的查询,因此将其绑定到queryInfo中的query即可; 具体效果后台不想做了... -->
        <!-- clearable: 在input中添加一个可清空当前内容的icon-->
        <!-- @clear: 当点击清空input后所触发的回调函数 -->
        <el-col :span="8">
          <el-input
              placeholder="查询内容..."
              v-model="queryInfo.query"
              clearable
              @clear="getUserList">
            <el-button slot="append" icon="el-icon-search" @click="getUserList"></el-button>
          </el-input>
        </el-col>

        <el-col :span="4">
          <!-- 点击添加用户后会弹出一个对话框,此时需要使用eleUI的Dialog对话框组件-->
          <!-- 设置添加用户的点击事件,会使addDialogVisible 为 true-->
          <el-button type="primary" @click="addDialogVisible = true">添加用户</el-button>

          <!-- 添加用户的对话框 -->
          <!-- title:对话框的title-->
          <!-- :visible.sync:控制显示/隐藏对话框-->
          <!-- :before-close: 对话框关闭前会调用此函数(未使用) -->
          <el-dialog
              title="添加用户"
              :visible.sync="addDialogVisible"
              width="30%">

            <!--对话框的内容主体,通过form录入需要添加的用户信息-->
            <!--创建ele表单的步骤: 先创建form,绑定数据主体(addUserForm),绑定规则主体(addUserFormRules)-->
            <!--然后依据需要创建每条input-->
            <el-form :model="addUserForm" :rules="addUserFormRules" ref="addUserFormRef" label-width="70px">
              <el-form-item label="用户名" prop="username">
                <el-input  v-model="addUserForm.username"></el-input>
              </el-form-item>

              <el-form-item label="密码" prop="password">
                <el-input type="password" v-model="addUserForm.password"></el-input>
              </el-form-item>

              <el-form-item label="邮箱" prop="email">
                <el-input type="email" v-model="addUserForm.email"></el-input>
              </el-form-item>

              <el-form-item label="手机" prop="mobile">
                <el-input type="number" v-model="addUserForm.mobile"></el-input>
              </el-form-item>
            </el-form>

            <!--对话框的底部区域,包含了2个按钮-->
            <span slot="footer" class="dialog-footer" >
              <!-- 点击取消后会控制addDialogVisible等于false -->
              <el-button @click="clearDialog">重 置</el-button>
              <el-button type="primary" @click="addUser">确 定</el-button>
            </span>
          </el-dialog>
        </el-col>
      </el-row>

      <!-- 表格区 -->
      <!-- 参考: https://element.eleme.cn/2.0/#/zh-CN/component/table -->
      <!-- :data:指定表格中的数据源-->
      <el-table :data="userList" border :stripe="true">
        <!-- 通过设置type = index 来添加索引(默认效果) -->
        <!-- 通过label来指定表头名,prop指定字段的数据-->
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column label="姓名"prop="userName"></el-table-column>
        <el-table-column label="手机"prop="mobile"></el-table-column>
        <el-table-column label="邮箱"prop="email"></el-table-column>
<!--        <el-table-column label="创建时间"prop="createTime"></el-table-column>-->
        <el-table-column label="权限"prop="roleName"></el-table-column>

        <!-- 无法直接显示布尔值,需要借助作用域插槽slot-scope来获取--> <!-- 据说新版是v-slot-->
        <!-- @change:eleUI自带的事件,当开关状态发生变化时可以执行回调函数; 需要额外传入当前开关的状态! -->
        <el-table-column label="状态">
          <template slot-scope="scope">
            <el-switch
                @change="userStateChanged(scope.row.mgState)"
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
        <!-- @current-change: 切换当前显示页-->
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
            :page-size="queryInfo.pageSize"
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
      //自定义规则
      //3个参数:规则,待校验的值,回调函数
      let checkEmail = (rule,value,callback)=>{
        //通过正则来验证邮箱
        const regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
        //调用正则的test
        if (regEmail.test(value)) {
          return callback();  //当参数为空会执行true的回调函数
        }
        return callback(new Error('这是邮箱?'));  //如果有误则需要传入一个Error的函数,注意大小写,指定了只能用Error这个函数!

      };


      let checkMobile = (rule,value,callback)=>{
        //验证手机号
        const regEmail = /^(0|86|17951)?(13[0-9]|15[012356789]|17[3678]|18[0-9]|14[57])[0-9]{8}$/;
        if (regEmail.test(value)) {
          return callback();
        }
        return callback(new Error('这是手机?'));

      };


      return {
        //由axios发起的所需要查询的数据信息
        queryInfo: {
          query: '',
          currentPage: 1,
          pageSize: 2,
        },
        userList: [],
        total: 0,
        addDialogVisible: false,  //控制对话框的显示/隐藏
        //添加用户表单的数据
        addUserForm: {
          username: '',
          password: '',
          email: '',
          mobile: '',
        },
        //添加用户表单的规则
        addUserFormRules: {
          //对于用户名和密码可以使用默认的校验规则
          username: [{required: true,message:'用户名?',trigger:'blur'},{min: 5,max:12,message:'用户名长度 5 ~ 12',trigger:'blur'}],
          password: [{required: true,message:'密码?',trigger:'blur'},{min: 6,max:12,message:'密码长度 6 ~ 12',trigger:'blur'}],
          //对于邮箱则需要使用自定义的校验规则,validator: xxx
          //参考 https://element.eleme.cn/2.0/#/zh-CN/component/form 自定义校验规则
          email: [{required: true,message:'邮箱??',trigger:'blur'},{validator: checkEmail,trigger:'blur'}],
          mobile: [{required: true,message:'手机??',trigger:'blur'},{validator: checkMobile,trigger:'blur'}],
        },

      }
    },
    methods: {
      //获取用户列表
      async getUserList() {
        const {data:res} = await this.axios.get('users',{params: this.queryInfo});
        //发出去的请求: http://murasakichigo.xyz:9090/my-vue-shop-project/users?query=&pagenum=1&pagesize=2 可见axios会自动拼接参数
        // console.log(res);   //测试用
        
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
        // console.log(newPage);  // 测试用
        this.queryInfo.currentPage = newPage;
        this.getUserList();
      },

      //改变用户状态
      userStateChanged(mgState) {
        // console.log(mgState);  //测试用
        //可以在此发起一个向后台的请求,来改变当前用户的状态
        //具体省略

        //注意,需要判断是否向后台传输成功,如果失败则需要将userinfo.mgState切换至false并提示用户!
      },

      //清空对话框
      clearDialog() {
        this.$refs.addUserFormRef.resetFields();
      },

      //添加用户后进行预验证
      addUser() {
        //通过validate来对表单进行验证
        this.$refs.addUserFormRef.validate(async valid =>{
          // console.log(valid);  //测试用,true false
          if (!valid) return; //不通过则直接驳回
          //如果通过了可以发起网络校验;  参数1:路径名,参数2:数据
          let {data: result} = await this.axios.post('addUser',this.addUserForm);

          if (result.meta.status !== 201) {
            this.$message.error('添加失败！');
            return ;

          }else {
            this.$message.success('添加成功！');
            //清空并关闭窗口,重新刷新用户列表
            this.clearDialog();
            this.addDialogVisible = false;
            this.getUserList();
          }
        });
      },

    },
    created() {
      this.getUserList();
    }
  }
</script>

<style lang="less" scoped>

  //表格
  .el-table {
    margin-top: 20px;
  }

  //分页栏
  .el-pagination {
    margin-top: 15px;
  }


</style>