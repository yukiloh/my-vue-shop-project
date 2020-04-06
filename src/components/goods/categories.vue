<template>
  <div>


    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
      <el-breadcrumb-item>商品分类</el-breadcrumb-item>
    </el-breadcrumb>


    <!-- 卡片视图区 -->
    <el-card class="box-card">
      <el-button type="primary" @click="openCateDialog" class="addCategories">添加分类</el-button>

      <!--添加分类的对话框-->
      <el-dialog
          @close="closeCateDialog"
          title="添加商品分类"
          :visible.sync="addCateDialogVisible"
          width="35%">

        <!--表单-->
        <el-form :model="addCateForm"  label-width="100px">

          <el-form-item label="分类名称" prop="username" required>
            <el-input  v-model="addCateForm.username"></el-input>
          </el-form-item>


          <el-form-item label="父级分类">
            <!-- 级联选择器 -->
            <!-- v-model: 指定选中的值,数组,对应数据源种的value,指定后可以产生默认选择的效果-->
            <!-- :options: 数据源-->
            <el-cascader
              v-model="selectedCateId"
              :options="selectedCateList"
            ></el-cascader>
          </el-form-item>


        </el-form>

        <!--对话框的底部区域,包含了2个按钮-->
        <span slot="footer" class="dialog-footer" >
          <!-- 点击取消后会控制addDialogVisible等于false -->
          <el-button @click="closeCateDialog">取 消</el-button>
          <el-button type="primary" @click="submitCateDialog">确 定</el-button>
        </span>
      </el-dialog>


      <!-- 直接使用eleUI的表格树形结构,会默认去找每个元素中是否包含children属性,
            也可以通过tree-props: {hasChildren: 'hasChildren', children: 'children' }来指定-->
      <el-table
          :data="categoriesList"
          row-key="cate_id"
          border
          >
        <el-table-column
            prop="cate_id"
            label="商品编号"
            width="120">
        </el-table-column>
        <el-table-column
            prop="cate_name"
            label="名称"
            width="250"
            >
        </el-table-column>
        <el-table-column
            prop="cate_level"
            label="层级"
            width="120">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.cate_level === 0" type="info">一级</el-tag>
            <el-tag v-if="scope.row.cate_level === 1" >二级</el-tag>
            <el-tag v-if="scope.row.cate_level === 2" type="warning">三级</el-tag>

          </template>
        </el-table-column>
        <el-table-column
            prop="cate_deleted"
            label="是否已删除"
            width="100">
          <template slot-scope="scope">
            <span> {{scope.row.cate_deleted === true ? '是':'否'}}</span>
          </template>
        </el-table-column>
        <el-table-column
            label="操作"
            >
          <el-button type="info" @click="" class="">操作</el-button>
          <el-button type="primary" @click="" class="">操作</el-button>
          <el-button type="warning" @click="" class="">操作</el-button>

        </el-table-column>

      </el-table>

      <!-- 分页区 --><!--没做-->
      <div class="block">
        <el-pagination
            @size-change=""
            @current-change=""
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
    name: "categories",
    data() {
      return {
        queryInfo: {
          currentPage: 1,
          pageSize: 10,
        },
        total: 3,
        categoriesList: [],

        addCateDialogVisible: false,
        addCateForm: {
          username: '',
        },
        selectedCateId: ['zhinan1','shejiyuanze1','yizhi1'],

        //懒得写后台了
        selectedCateList: [
          {
          value: 'zhinan1',
          label: '指南',
          children: [{
            value: 'shejiyuanze1',
            label: '设计原则',
            children: [{
              value: 'yizhi1',
              label: '一致'
            }, {
              value: 'fankui',
              label: '反馈'
            }, {
              value: 'xiaolv',
              label: '效率'
            }, {
              value: 'kekong',
              label: '可控'
            }]
          }, {
            value: 'daohang',
            label: '导航',
            children: [{
              value: 'cexiangdaohang',
              label: '侧向导航'
            }, {
              value: 'dingbudaohang',
              label: '顶部导航'
            }]
          }]
        },
          {
            value: 'zhinan1',
            label: '指南',
            children: [{
              value: 'shejiyuanze1',
              label: '设计原则',
              children: [{
                value: 'yizhi1',
                label: '一致'
              }, {
                value: 'fankui',
                label: '反馈'
              }, {
                value: 'xiaolv',
                label: '效率'
              }, {
                value: 'kekong',
                label: '可控'
              }]
            }, {
              value: 'daohang',
              label: '导航',
              children: [{
                value: 'cexiangdaohang',
                label: '侧向导航'
              }, {
                value: 'dingbudaohang',
                label: '顶部导航'
              }]
            }]
          }
        ]
      }
    },

    methods: {
      async getCategories() {
        const {data:res} = await this.axios.get('categories',{params: this.queryInfo});
        if (res.meta.status !== 200) return this.$message.error('错误,'+res.meta.msg);
        this.categoriesList = res.data.children;
      },
      openCateDialog() {
        this.addCateDialogVisible = true;
      },
      closeCateDialog() {
        this.addCateDialogVisible = false;
        this.addCateForm.username = '';
        this.selectedCateId = [];
      },
      submitCateDialog() {
        //假装提交数据
        //↓获取了级联标签+需要提交的分类名
        let result = this.selectedCateId+this.addCateForm.username;
        this.$message.success('添加商品分类成功: '+ result);
        this.addCateDialogVisible = false;
        this.addCateForm.username = '';
        this.selectedCateId = [];
      },

    },

    created() {
      this.getCategories();
    }
  }
</script>

<style scoped lang="less">

  .addCategories {
    margin-bottom: 15px;
  }

  .el-cascader {
    width: 100%;
  }

  .el-form-item {
    margin-left: -10px;
  }

  .el-cascader-menu {
    height: 300px;
  }
</style>