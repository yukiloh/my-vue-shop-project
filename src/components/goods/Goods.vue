<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
      <el-breadcrumb-item>商品列表</el-breadcrumb-item>
    </el-breadcrumb>


    <!-- 卡片区-->
    <!--分2行-->
    <!--搜索input,添加商品btn-->
    <!--商品列表form-->
    <el-card class="box-card">
      <el-row :gutter="20">

        <el-col :span="8">
          <el-input
              placeholder="查询内容..."
              v-model="queryInfo.keyword"
              clearable
              @clear="">
            <!-- slot="append" 指定输入框后置的内容-->
            <el-button slot="append" icon="el-icon-search" @click="searchGoodsKeyword"></el-button>
          </el-input>

        </el-col>

        <el-col :span="8">
          <el-button type="primary" @click="routeToAddGoods">添加商品</el-button>
        </el-col>
      </el-row>

      <el-row>
        <el-table :data="goodsList" border :stripe="true">

          <el-table-column type="index" label="#"></el-table-column>
          <el-table-column label="商品名称" prop="auth_name"></el-table-column>
          <el-table-column label="价格" prop="price" width="80px"></el-table-column>
          <el-table-column label="重量" prop="mass" width="80px"></el-table-column>
          <el-table-column label="创建时间" prop="created" width="300px"></el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button type="primary" @click="">修改</el-button>
              <el-button type="danger" @click="">删除</el-button>
            </template>
          </el-table-column>
        </el-table>


      </el-row>

      <!--分页区--><!-- 没做-->
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
    name: 'Goods',

    data() {
      return {
        queryInfo: {
          keyword: '',
          currentPage: 1,
          pageSize: 10,
        },
        goodsList: [
          {auth_name: '取暖器',price: 25099,mass: 0,created: ''},
          {auth_name: '冷风机',price: 23099,mass: 0,created: ''},
        ],
      }
    },

    methods: {
      searchGoodsKeyword() {
        //是空的话就不逼逼了
        if (this.queryInfo.keyword === '' || this.queryInfo.keyword === null) return;
        //模拟搜索功能
        this.$message.success('搜索: '+this.queryInfo.keyword);

      },

      getGoodsList() {
        //price以分位单位,进行除100计算
        for (let i = 0; i < this.goodsList.length; i++) {
          let goods = this.goodsList[i];
          goods.price /= 100;
          //通过moment直接获取当前时间; 也可以$moment中传入unix来获取目标时间
          goods.created = this.$moment().format("YYYY-MM-DD HH:mm:ss");

        }
      },

      routeToAddGoods() {
        this.$router.push('addGoods');
      }

    },


    created() {
      this.getGoodsList();
    }


  }

</script>



<style scoped lang="less">

  .el-table {
    margin-top: 15px;
  }
  .el-pagination {
    margin-top: 15px;
  }

</style>