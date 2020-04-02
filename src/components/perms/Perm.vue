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
            <el-tooltip effect="light" content="设置用户" placement="top" :enterable="false">
              <el-button type="info" icon="el-icon-setting" size="mini" disabled></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <!--修改用户的对话框-->
      <el-dialog
          title="修改用户"
          :visible.sync="editDialogVisible"
          width="30%">

        <!-- close:该表单关闭时调用的函数-->
        <el-form :model="editUserForm" :rules="editUserFormRules"
                 ref="editUserFormRef" label-width="70px" @close="clearEditDialog">
          <el-form-item label="用户名">
            <el-input  v-model="editUserForm.username" disabled></el-input>
          </el-form-item>

          <el-form-item label="邮箱" prop="email">
            <el-input type="email" v-model="editUserForm.email"></el-input>
          </el-form-item>

          <el-form-item label="手机" prop="mobile">
            <el-input type="number" v-model="editUserForm.mobile"></el-input>
          </el-form-item>
        </el-form>

        <span slot="footer" class="dialog-footer" >
              <el-button @click="clearEditDialog">清 空</el-button>
              <el-button type="primary" @click="editUserSubmit">确 定</el-button>
            </span>
      </el-dialog>

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
    name: "Perm"
  }
</script>

<style scoped lang="less">

</style>