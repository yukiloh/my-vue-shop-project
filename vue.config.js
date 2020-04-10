

//vue-cli的自定义配置方案
module.exports = {

  // publicPath: './', // 定义根目录,打包时需要设置


  //分离开发模式和生产模式;  使用链式编程;
  chainWebpack: config => {

    //生产模式
    config.when(process.env.NODE_ENV === 'production',config=>{

      config.entry('app').clear().add('./src/main-prod.js');

      //不对以下依赖打包;原理:当发现有import 以下文件时,会直接从头部文件查找
      //key指引入的包名,value指实际使用的变量名,如果有-则key需要包裹''
      //搞不定,会使页面空白

      config.set('externals',{

      });


    });

    //开发模式  先开发模式调试externals是否可用再换到生产模式
    config.when(process.env.NODE_ENV === 'development',config=>{
      config.entry('app').clear().add('./src/main-dev.js');

      config.set('externals',{
        vue: 'Vue',
        moment: 'moment',
        echarts: 'echarts',
        'element-ui': 'Element',    //element比较特殊,因为使用了按需加载,
      });
    });
  },



};