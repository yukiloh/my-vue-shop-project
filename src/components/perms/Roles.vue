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

      <el-button type="warning" @click="changeView" class="changeViewButton" >切换视图</el-button>

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
              <!-- span: 栅格所占用的尺寸-->
              <el-col :span="8" >
                <el-tag type="info" closable @close="removeTagById(c1.id)">{{c1.authName}}</el-tag>
                <i class="el-icon-caret-right"></i>
              </el-col>

              <!--第二+三列-->
              <el-col :span="16" >
                <!-- 或者: i2 === 0 ? 插入top:不插top   代码更精简-->
                <el-row v-for="(c2,i2) in c1.children" :class="[i2 !== c1.children.length-1 ? 'border-bottom' :'']">
                  <!-- 这里因为新起了一个row,所以2个col的全尺寸还是24,并非16-->
                  <el-col :span="8" >
                    <el-tag closable @close="removeTagById(c2.id)">{{c2.authName}}</el-tag>
                    <i class="el-icon-caret-right"></i>
                  </el-col>
                  <!-- 二级标签不需要每行一个直接铺满tag就好了 -->
                  <el-col :span="16" >
                    <el-tag type="warning" v-for="c3 in c2.children" closable @close="removeTagById(c3.id)">{{c3.authName}}</el-tag>
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
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="" disabled>编辑角色</el-button>
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="" disabled>删除角色</el-button>

            <!-- 通过dialog来显示树状结构 -->
            <el-button type="warning" icon="el-icon-setting" size="mini" @click="showPermsDialog(scope.row)">分配权限</el-button>
          </template>

        </el-table-column>
      </el-table>

      <!--修改用户的对话框 别写在template中会出现重影-->
      <!-- :close:dialog关闭时需要充值defKeys数组 -->
      <el-dialog
          title="分配权限"
          :visible.sync="permsDialog"
          width="30%"
          :close="resetDefCheckedKey"
      >

        <!-- 树形结构-->
        <!--data:数据源  props:指定树形节点的属性-->
        <!--show-checkbox:可勾选节点-->
        <!--node-key:指定选中节点的值-->
        <!--default-expand-all:指定打开所有节点-->
        <!--default-checked-keys:默认勾选的数组-->
        <el-tree
            :data="allPermList"
            :props="permProps"
            show-checkbox
            node-key="id"
            default-expand-all
            :default-checked-keys="defCheckedKey"
            ref="treeRef"

        ></el-tree>

        <span slot="footer" class="dialog-footer" >
          <el-button @click="permsDialog=false">取 消</el-button>

          <!-- 获取选中和半选中的rid数组,提交给后台 -->
          <el-button type="primary" @click="submitPerm">确 定</el-button>
        </span>
      </el-dialog>

    </el-card>


  </div>
</template>

<script>
  export default {
    name: "Roles",

    data() {
      return {
        rolesList: [],
        allPermList: [],
        permsDialog: false,
        permProps: {
          label: 'authName',    //指定节点名称
          children: 'children'  //指定子节点的集合
        },
        defCheckedKey:[], //默认选中的节点的数组

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


      removeTagById(id) {
        //和删除用户那里一样的,复制
        this.$confirm('你确定?', '删除权限', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'   //消息文字左边的icon
        }).then((confirm) => {
          // console.log(confirm); //测试用,如果点击了确定会打印confirm
          this.$message({
            type: 'success',
            message: '已成功删除权限id:'+id
            //模拟向后台发起请求,根据id删除对应权限
            //发起后服务器会发送完整的权限数据,将此数据赋值至data,而不是再次getRoleList,因为会导致页面刷新
          });
        }).catch((cancel) => {
          // console.log(cancel);  //测试用,如果点击了取消会打印cancel
          this.$message({
            type: 'info',
            message: '已取消'
          });
        });
      },

      async getPermList(){
        const {data: res} = await this.axios.get('allPerm');
        // console.log(res);  //测试用
        if (res.meta.status !== 200) return this.$message.error('数据获取失败');
        this.allPermList = res.data[0].children;
      },


      showPermsDialog(role){
        console.log(role);
        //当点击显示对话框时,获取当前角色的权限列表的id,赋值至defCheckedKey,默认勾选该数组
        this.getNodeList(role, this.defCheckedKey);

        //需要在打开dialog之前将属性节点设置所要勾选的节点!!
        this.permsDialog = true;

      },

      resetDefCheckedKey(){
        this.defCheckedKey = [];
      },

      //获取所有三级子节点的id
      getNodeList(node,arr) {
        //如果没有子节点,则表示这是三级节点,将该节点的id加到arr中
        if (!node.children) return arr.push(node.id);

        //如果存在子节点,则继续递归
        node.children.forEach(item => this.getNodeList(item, arr))
      },

      submitPerm(){
        const keys = [
          ...this.$refs.treeRef.getCheckedKeys(),
          ...this.$refs.treeRef.getHalfCheckedKeys()
        ];

        this.$message.success('更新了:'+keys);
        //省略向服务器提交数据步骤

        this.getRoleList();       //更新数据
        this.permsDialog = false; //关闭dialog

      },

      onExpand() {
        // console.log('?');
      },

      changeView() {
        this.$router.push('/roles2');
      }


    },
    created() {
      this.getRoleList();
      this.getPermList();
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