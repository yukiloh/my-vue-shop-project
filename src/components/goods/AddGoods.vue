<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
      <el-breadcrumb-item>添加商品</el-breadcrumb-item>
    </el-breadcrumb>



    <!-- 横条信息 -->
    <!-- 进度条 -->
    <!-- 纵向标签页/-->

    <!-- 卡片区-->
    <el-card class="box-card">
      <!-- 提示信息-->
      <el-row >
        <el-alert
            title="商品提示信息"
            type="info"
            center
            :closable="false"
            show-icon>
        </el-alert>
      </el-row>

      <!-- 进度条 -->
      <el-row>
        <!-- activeStep-0: el-tab-pane需要的参数是string,active则是number,所以此处进行隐式转换 -->
        <el-steps class="step" :space="1300" :active="activeStep-0" finish-status="success" align-center>
          <el-step title="基本信息"></el-step>
          <el-step title="商品参数"></el-step>
          <el-step title="商品属性"></el-step>
          <el-step title="商品图片"></el-step>
          <el-step title="商品内容"></el-step>
          <el-step title="完成"></el-step>
        </el-steps>

      </el-row>

      <!-- 标签页 -->
      <el-row>
        <!--tab-pane只能被tabs包裹,所以form表单要写在外面-->
        <!--label-position="top": 指定标签名的位置,默认是右-->
        <el-form label-position="top" label-width="0px" class="login_form" :model="goodsForm" :rules="goodsRules" ref="goodsFormRef">

          <!-- tab-position="left":让标签居左-->
          <!-- v-model: 通过此参数来控制显示哪个页面-->
          <!-- :before-leave: 自带事件,当离开本标签页时-->
          <el-tabs tab-position="left" v-model="activeStep" :before-leave="beforeTabLeave">
            <!-- name: 设定当前页面的名称-->
            <el-tab-pane label="基本信息" name="0" >
              <el-form-item label="商品名称" prop="goodsName" >
                <el-input  v-model="goodsForm.goodsName" ></el-input>
              </el-form-item>

              <el-form-item label="商品价格" >
                <el-input type="number" v-model="goodsForm.goodsPrice" ></el-input>
              </el-form-item>
              <!-- 还有个级联,不做了..-->
            </el-tab-pane>
            <el-tab-pane label="商品参数" name="1">商品参数</el-tab-pane>
            <el-tab-pane label="商品属性" name="2">商品属性</el-tab-pane>
            <el-tab-pane label="商品图片" name="3">商品图片</el-tab-pane>
            <el-tab-pane label="商品内容" name="4">商品内容</el-tab-pane>
          </el-tabs>

        </el-form>


      </el-row>
    </el-card>
  </div>
</template>

<script>
  export default {
    name: "addGoods",

    data() {
      return {
        activeStep: '0',  //el-tab-pane需要string,number也可以
        goodsForm: {goodsName: '',goodsPrice: '',},
        goodsRules: {
          //对于用户名和密码可以使用默认的校验规则
          goodsName: [{required: true,message:'用户名?',trigger:'blur'},{min: 2,max:12,message:'长度 5 ~ 12',trigger:'blur'}],
        },

      }
    },

    methods: {
      //当离开标签页时触发,自带2个参数,旧页面&新页面
      beforeTabLeave(oldTab, newTab) {
        if (this.goodsForm.goodsName === '' || this.goodsForm.goodsName === null) {
          this.$message.error('需要先填写商品 名称');
          return false; //返回false会阻止标签页进行跳转
        }

      },
    },

    created() {

    }
  }
</script>


<style lang="less" scoped>
  .el-row {
    margin-bottom: 15px;
  }


</style>

