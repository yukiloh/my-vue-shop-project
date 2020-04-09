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
          <!-- @tab-click: 当点击标签页时触发,可以用来控制页面的切换-->
          <el-tabs tab-position="left" v-model="activeStep"
                   :before-leave="beforeTabLeave"
                   @tab-click="tabClick"
          >

            <!--一共5个tab-pane,挑着做..-->
            <!-- name: 设定当前页面的名称-->
            <el-tab-pane label="基本信息" name="0" >
              <el-form-item label="商品名称" prop="goodsName" >
                <el-input  v-model="goodsForm.goodsName" placeholder="必须输入商品名称后才可进入下一阶段"></el-input>
              </el-form-item>

              <el-form-item label="商品价格(元)" >
                <el-input type="number" v-model="goodsForm.goodsPrice" ></el-input>
              </el-form-item>
              <!-- 还有个级联,不做了..-->
            </el-tab-pane>

            <!-- 原教程使用复选框checkbox,省略-->
            <el-tab-pane label="商品参数" name="1">商品参数</el-tab-pane>

            <!-- 通过请求api获取商品属性,通过form表单展示,略-->
            <el-tab-pane label="商品属性" name="2">商品属性</el-tab-pane>

            <!-- 使用upload组件 -->
            <el-tab-pane label="商品图片" name="3">


              <!-- action:所上传的api地址 -->
              <!-- :on-preview:预览时的事件 -->
              <!-- :on-remove:删除时的事件 -->
              <!-- headers:可以设置upload请求的头部(不如需要设置token) -->
              <!-- :on-success:一般服务器会返回status和图片的uri地址; 同样也有on-error-->
              <el-upload
                  class="upload-demo"
                  action="https://jsonplaceholder.typicode.com/posts/"
                  :on-preview="handlePreview"
                  :on-remove="handleRemove"
                  :file-list="fileList"
                  list-type="picture"
                  :on-success="handleSuccess"
              >
                <el-button size="small" type="primary">点击上传</el-button>
                <!--↓提示文字-->
                <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
              </el-upload>
            </el-tab-pane>

            <!-- 可以使用中文的markdown编辑器,最后内容文本绑定到v-model的一个属性中-->
            <!-- 参考:https://github.com/hinesboy/mavonEditor-->
            <el-tab-pane label="商品内容" name="4">
              商品内容
              这里应该有个markdown编辑器的
              <!-- 最终提交表单 -->
              <el-button type="primary" @click="submitGoods">提交</el-button>
            </el-tab-pane>
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
        goodsForm: {goodsName: '',goodsPrice: '',pictures:[]},
        goodsRules: {
          //对于用户名和密码可以使用默认的校验规则
          goodsName: [{required: true,message:'用户名?',trigger:'blur'},{min: 2,max:12,message:'长度 2 ~ 12',trigger:'blur'}],
        },
        fileList: [],
        //参考用:  //[{name:'',url: ''}]

      }
    },

    methods: {
      //当离开标签页时触发,自带2个参数,旧页面&新页面
      beforeTabLeave(oldTab, newTab) {
        let myCheck = false;
        //由表单验证来校验check,是否放行
        this.$refs.goodsFormRef.validate(valid => {
          if (valid) myCheck = true;
          else this.$message.error('需要先填写商品名称');
          return valid
        });
        return myCheck    //当返回false时阻止页面离开
      },

      //当点击标签页时触发
      tabClick() {
        //console.log(this.activeStep); //会显示当前的页码
        this.$message.success('假装获取了数据页:'+this.activeStep);
      },

      //删除图片的事件
      handleRemove(file, fileList) {
        //移除图片需要获取图片的临时地址(从file中),然后在表单的pics中也移除该元素
        //console.log(file, fileList);
      },
      //预览图片事件
      handlePreview(file) {
        //console.log(file);
        //可以做一个额外的dialog来展示已上传的图片,省略
      },

      //图片上传成功后
      handleSuccess(response,file,fileList) {
        // 可以通过response来获取服务器上传后返回的数据
        // 然后将新图片的uri添加到goodsForm中的pictures列表中
        // console.log(response);
        // console.log(file);
        // console.log(fileList);
      },

      submitGoods() {
        this.$refs.goodsFormRef.validate(valid => {
          if (valid) {
            this.$message.success('假装提交成功了');
            // 进行axios提交
            // this.axios.post('addGoods',this.goodsForm);

            // 进行页面跳转
            this.$router.push('/goods');
          }
          else this.$message.error('有项目未填写');
        });
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

  .el-upload {
    width: 300px;
  }

</style>

