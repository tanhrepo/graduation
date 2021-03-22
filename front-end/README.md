# anshop



## Project setup

```
npm install
```

### Compiles and hot-reloads for development

```
npm run serve || npm run dev
```

### Compiles and minifies for production

```
npm run build
```

### Lints and fixes files

```
npm run lint
```

### 编码规范

- @vue/cli 4.x node>= v10.x
- 推荐：编辑器 vscode ,插件 Vetur Prettier Eslint GitLens Rainbow-Brackets better-comments
  (建议 vscode 编辑器一个窗口打开一个工程：保证单个项目的配置文件生效)
- 对于.vscode 配置 仅在 master 分支作为第一次工程 init 展示，后续各团队自行在分支处理，添
  加 .gitignore 或者删除均可，`请注意.gitignore中打包dist目录名与outputDir的一致性`
- 遵循 OPRD 前端工程规范,可参考[https://cn.vuejs.org/v2/style-guide/]
- 该工程图片命名统一使用\_连接
- element-UI 全量引入,尺寸 small,遵循 UI 规范,复用公共样式
- el-menu 组件仅允许作为大量页面菜单使用（因为样式已被特定处理）
- `components` 文件夹下组件统一大写开头,在模板中引用也需大写开头(为的是和 UI 组件本身连字
  符使用方式做区分)
- 所有模块使用路由内滚动 fe-scroll-y,示例参考 temp.vue
- 所有新增后台接口模块请记得同时配置 config.x.json -API 和 dev.proxy.js
- vuex 使用 module 模式
- 页面跳转无特殊情况下全部使用 name 模式，避免使用 route.query 进行参数传递
- 各团队打包配置请自行添加 build 命令进行环境区分
