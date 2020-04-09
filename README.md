# my-vue-shop-project

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).


====================================================================
#### 参考视频地址
https://www.bilibili.com/video/BV1EE411B7SU

#### 合并分支
git checkout 主分支

git merge 需要合并的副分支

#### icon参考
icon.scss 文件


#### 如需修改webpack的设置
https://cli.vuejs.org/zh/config/#vue-config-js

在根目录创建```vue.config.js``


#### 划分生产环境和开发环境,另外关于 chunk-vendors.xxx.js
build完后会生成./dist文件夹,内容都在里面了

所有第三方依赖项都会打包至chunk-vendors.xxx.js中,导致该js文件过大,不便于页面的加载
可以通过配置```externals```不让编译时打包所设置的节点

在```根目录/public/index.html```下可以设置头部资源,js和css等静态资源应设置在此

```/public```下,favicon也是在这里设置的

对于elementUI直接在prod.js中注释掉```import './plugins/element.js'```,然后在头部引入相关资源即可

#### 关于懒加载
需要引入插件```@babel/plugin-syntax-dynamic-import```
通过将不同的资源分割为不同的包,然后按需加载,具体实现略


#### 通过express来运行项目
启动
```node ./app.js```
