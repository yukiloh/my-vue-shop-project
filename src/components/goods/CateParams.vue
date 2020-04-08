<template>
  <div>
    <!-- 每个三级分类的具体参数,其实应该叫商品参数 -->
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
      <el-breadcrumb-item>分类参数</el-breadcrumb-item>
    </el-breadcrumb>


    <el-card class="box-card">
      <!--警告提示的文字-->
      <el-alert
          title="只允许选择三级分类"
          type="warning"
          show-icon
          :closable="false"
      >
      </el-alert>


      <el-row class="cate_row">
        <el-col>
          <span>商品： </span>
          <!-- 默认只允许选中三级分类，如果希望可选一二级则需要添加:props="{ checkStrictly: true }"-->
          <!-- 数据源最好不出现没有三级的分类,容易bug-->
          <el-cascader
              v-model="selectedCateId"
              :options="selectedCateList"
              placeholder="只允许选择三级分类"
              @change="showSelected"
              :props="{ expandTrigger: 'hover' }"
              clearable
          ></el-cascader>
        </el-col>
      </el-row>

      <!-- 因为不想做每个属性下的标签,意思意思做个标签演示 -->
      <!-- 通过遍历数组dynamicTags来显示tag-->
      <!-- disable-transitions:禁用渐变动画,是=禁用-->
      <el-tag
          v-for="tag in dynamicTags"
          :key="tag"
          closable
          :disable-transitions="true"
          @close="handleClose(tag)">
        {{tag}}
      </el-tag>

      <!-- 通过v-if-else 来实现按钮和文本框的显示/隐藏 -->
      <!-- v-if:通过设置inputVisible来控制input和button是否可见-->
      <!-- v-model: 将要输入的值绑定到此变量-->
      <!-- @keyup.enter.native: 当按下enter触发事件;blur也是,也就是说当失焦后就会触发提交input-->
      <el-input
          class="input-new-tag"
          v-if="inputVisible"
          v-model="inputValue"
          ref="saveTagInput"
          size="small"
          @keyup.enter.native="handleInputConfirm"
          @blur="handleInputConfirm"
      >
      </el-input>
      <!-- showInput: -->
      <el-button v-else class="button-new-tag" size="small" @click="showInput">+ New Tag</el-button>
      <!-- 在form中可能会出现2行的new tag都变成input的现象,因为公用同一个inputValue,可以使用scope.row.input-->

      <!--标签页-->
      <!--省略了每个数据下的+new tag功能-->
      <!-- v-model绑定显示哪个label -->
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <!-- 通用属性页面 -->
        <el-tab-pane label="通用属性" name="common">
          <el-button class="addPram" type="primary" @click="addCatePram" :disabled="isBtnDisable">添加参数</el-button>
          <el-table :data="commonList" border :stripe="true">
              <el-table-column type="index" label="#"></el-table-column>
              <el-table-column prop="key" label="属性名称" width="250px"></el-table-column>
              <el-table-column prop="value" label="参数"></el-table-column>
                <el-table-column label="操作">
                  <template slot-scope="scope">
                    <el-button type="primary" @click="editCateParam(scope.row.id)">编辑</el-button>
                    <el-button type="danger" @click="delCateParam(scope.row.id)" >删除</el-button>
                  </template>
              </el-table-column>
          </el-table>
        </el-tab-pane>

        <!-- 自定义属性页面 -->
        <el-tab-pane label="自定义属性" name="customer" >
          <el-button class="addPram" type="primary" @click="addCatePram" :disabled="isBtnDisable">添加参数</el-button>
          <el-table :data="customerList" border :stripe="true">
              <el-table-column type="index" label="#"></el-table-column>
              <el-table-column prop="key" label="属性名称" width="250px"></el-table-column>
              <el-table-column prop="value" label="参数"></el-table-column>
              <el-table-column label="操作">
                <template slot-scope="scope">
                  <el-button type="primary" @click="editCateParam(scope.row.id)">编辑</el-button>
                  <el-button type="danger" @click="delCateParam(scope.row.id)" >删除</el-button>
                </template>
              </el-table-column>
          </el-table>
        </el-tab-pane>

        <!-- 添加参数 -->
        <el-dialog
            title="添加参数"
            :visible.sync="addParamDialogVisible"
            width="30%"
            @close="clearDialog"
        >

          <el-form :model="addParamForm"  label-width="80px" ref="addCateParamFormRef">
            <el-form-item label="属性名称" prop="propName" required>
              <el-input  v-model="addParamForm.propName"></el-input>
            </el-form-item>

            <el-form-item label="属性参数" prop="param" required>
              <el-input v-model="addParamForm.param"></el-input>
            </el-form-item>

          </el-form>

          <!--对话框的底部区域,包含了2个按钮-->
          <span slot="footer" class="dialog-footer" >
              <!-- 点击取消后会控制addDialogVisible等于false -->
              <el-button @click="clearDialog">重 置</el-button>
              <el-button type="primary" @click="submitCateParam">确 定</el-button>
            </span>
        </el-dialog>
      </el-tabs>

    </el-card>




  </div>
</template>

<script>
  export default {
    name: "cateParams",
    data() {
      return {
        selectedCateId: [],
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
        ],
        addParamDialogVisible: false,
        addParamForm: {propName: '',param: ''},
        activeName: 'common',
        commonList: [],
        commonListHidden: [{id:1,key:'通用参数名称1',value:"参数1"},{id:2,key:'通用参数名称2',value:"参数2"},{id:3,key:'通用参数名称3',value:"参数3"}],
        customerList: [],
        customerListHidden: [{id:11,key:'自定义参数名称1',value:"参数1"},{id:12,key:'自定义参数名称2',value:"参数2"},{id:13,key:'自定义参数名称3',value:"参数3"}],

        //标签相关
        dynamicTags: ['标签一', '标签二', '标签三'],
        inputVisible: false,
        inputValue: '',


      }
    },
    methods: {
      //标签发生变化时
      handleClick(tab, event) {
        //console.log('tab',tab.label); //tab会获取被点击的tab-pane标签
        //console.log('event',event);   //event可能会获取鼠标点击的事件
        this.getCateParam();
      },

      //级联分类被选中后
      showSelected() {
        //当数据清空后需要将下方数据也清空
        if (this.selectedCateId.length === 0) {
          this.commonList = [];
          this.customerList = [];
          return;
        }
        this.getCateParam();

      },

      //获取分类的通用/自定义属性;  当级联或者标签页被选中后都会进行获取属性操作
      getCateParam() {
        if (this.selectedCateId.length === 0) return;
        this.$message.success('模拟向后台发起请求！'+this.activeName, this.selectedCateId);
        //模拟获取数据,当发起请求后将hidden的数据复制到list中
        this.commonList = this.commonListHidden;
        this.customerList = this.customerListHidden;
      },

      editCateParam(key) {
        this.$message.warning('模拟修改分类参数！'+key);
        //新起一个dialog,根据给与的key发起请求,修改参数


      },

      delCateParam(key) {
        this.$message.error('模拟删除分类参数！'+key);
        //弹出警告框,根据给与的key发起请求,选择是否删除
      },

      //添加参数
      addCatePram() {
        this.addParamDialogVisible = true;

      },

      //提交修改的参数
      submitCateParam() {
        //先验证表单,正确才能提交
        this.$refs.addCateParamFormRef.validate(
          (valid) => {
            if (valid) {
              //如果是common的标签则提交给common,反之则customer
              if (this.activeName === 'common') {
                this.$message.success('添加通用属性:' + this.addParamForm.propName+this.addParamForm.param);
              } else {
                this.$message.success('添加自定义属性:' + this.addParamForm.propName+this.addParamForm.param);
              }
              this.addParamDialogVisible = false;
            }
          }
        );
      },

      //清空对话框
      clearDialog() {
        this.addParamForm = {};
        //利用表单下的方法来清空
        this.$refs.addCateParamFormRef.resetFields();
      },



      //标签相关
      handleClose(tag) {
        console.log(tag);
        //切割数组,参数:切第几个(通过indexOf(tag)来获取),切几个
        this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
      },

      showInput() {
        this.inputVisible = true;
        //$nextTick:在下次 DOM 更新循环结束之后执行延迟回调
        //参考:https://zhuanlan.zhihu.com/p/26724001
        this.$nextTick(_ => {
          //如果直接点击是获取不到saveTagInput的,因为dom还没刷新
          this.$refs.saveTagInput.$refs.input.focus();
        });
      },

      handleInputConfirm() {
        //添加当前input的值到数组,让input消失,清空input内容
        let inputValue = this.inputValue;
        if (inputValue) {
          //push:添加数组
          this.dynamicTags.push(inputValue);
        }
        this.inputVisible = false;
        this.inputValue = '';
      }



    },

    //通过computed来定义会发送动态变化的btn
    //computed:当对象发生变化时,进行计算
    computed: {
      isBtnDisable() {
        return this.selectedCateId.length !== 3;

      }
    }
  }
</script>

<style scoped lang="less">
  .cate_row {
    margin: 15px 0;

  }

  .addPram  {
      margin-bottom: 15px;
  }


  //标签相关
  .el-tag + .el-tag {
    margin-left: 10px;
  }
  .button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
  }
  .input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
  }

</style>