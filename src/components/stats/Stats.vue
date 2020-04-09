<template>
  <div>
    统计
    可以使用echarts展示报表

    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 600px;height:400px;"></div>


  </div>
</template>

<script>
  import echarts from 'echarts';

  export default {
    name: "Stats",

    //dom渲染完后
    mounted() {

      //echarts的参考: https://echarts.apache.org/zh/tutorial.html#5%20%E5%88%86%E9%92%9F%E4%B8%8A%E6%89%8B%20ECharts
      // 基于准备好的dom，初始化echarts实例
      let myChart = echarts.init(document.getElementById('main'));

      // 指定图表的配置项和数据; 最好不要设置为空;可以在获取到数据后使用lodash的_.merge来合并数据源
      let option = {
        title: {
          text: 'ECharts 入门示例'
        },
        tooltip: {},
        legend: {
          data:['销量']
        },
        xAxis: {
          data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
        },
        yAxis: {},
        series: [{
          name: '销量',
          type: 'line', //bar
          data: [5, 20, 36, 10, 10, 20]
        }]
      };

      // 使用刚指定的配置项和数据显示图表。
      // 也可以设置其他数据源来展示不同的图表
      myChart.setOption(option);

    }
  }
</script>

<style scoped>

</style>