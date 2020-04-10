
//导入express
const express = require('express');

//调用express
const app = express();

//使用,指定静态资源目录
app.use(express.static('../dist'));


app.listen(80,()=>{
    console.log('running at http://localhost ')
});