//vue-cli3全局配置：https://cli.vuejs.org/zh/config/#%E5%85%A8%E5%B1%80-cli-%E9%85%8D%E7%BD%AE
module.exports = {
    //部署应用时的基本 URL
    baseUrl: './',

    //当运行 vue-cli-service build 时生成的生产环境构建文件的目录
    outputDir: 'dist',

    //放置生成的静态资源 (js、css、img、fonts) 的 (相对于 outputDir 的) 目录。
    // assetsDir: '',

    //指定生成的 index.html 的输出路径 (相对于 outputDir)。
    indexPath: 'index.html',

    //默认情况下，生成的静态资源在它们的文件名中包含了 hash 以便更好的控制缓存。然而，这也要求 index 的 HTML 是被 Vue CLI 自动生成的。如果你无法使用 Vue CLI 生成的 index HTML，你可以通过将这个选项设为 false 来关闭文件名哈希。
    filenameHashing: true,

    //多页
    pages: undefined,

    //是否在开发环境下通过 eslint-loader 在每次保存时 lint 代码。这个值会在 @vue/cli-plugin-eslint 被安装之后生效。
    lintOnSave: true,

    //是否使用包含运行时编译器的 Vue 构建版本。设置为 true 后你就可以在 Vue 组件中使用 template 选项了，但是这会让你的应用额外增加 10kb 左右。
    runtimeCompiler: true,

    //默认情况下 babel-loader 会忽略所有 node_modules 中的文件。如果你想要通过 Babel 显式转译一个依赖，可以在这个选项中列出来。
    transpileDependencies: [],

    //如果你不需要生产环境的 source map，可以将其设置为 false 以加速生产环境构建。
    productionSourceMap: true,

    //设置生成的 HTML 中 <link rel="stylesheet"> 和 <script> 标签的 crossorigin 属性。
    //需要注意的是该选项仅影响由 html-webpack-plugin 在构建时注入的标签 - 直接写在模版 (public/index.html) 中的标签不受影响。
    //更多细节：https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_settings_attributes
    crossorigin: undefined,
    
    //在生成的 HTML 中的 <link rel="stylesheet"> 和 <script> 标签上启用 Subresource Integrity (SRI)。如果你构建后的文件是部署在 CDN 上的，启用该选项可以提供额外的安全性。
    //需要注意的是该选项仅影响由 html-webpack-plugin 在构建时注入的标签 - 直接写在模版 (public/index.html) 中的标签不受影响。
    //另外，当启用 SRI 时，preload resource hints 会被禁用，因为 Chrome 的一个 bug(https://bugs.chromium.org/p/chromium/issues/detail?id=677022) 会导致文件被下载两次。
    integrity: false,

    //
    css: {
        //默认情况下，只有 *.module.[ext] 结尾的文件才会被视作 CSS Modules 模块。设置为 true 后你就可以去掉文件名中的 .module 并将所有的 *.(css|scss|sass|less|styl(us)?) 文件视为 CSS Modules 模块。
        // modules: true,
        
        // 是否使用css分离插件 ExtractTextPlugin
        extract: false,

        // 开启 CSS source maps?
        sourceMap: false,

        // css预设器配置项
        loaderOptions: {},

    },

    //所有 webpack-dev-server 的选项都支持。注意：
    //有些值像 host、port 和 https 可能会被命令行参数覆写。
    //有些值像 publicPath 和 historyApiFallback 不应该被修改，因为它们需要和开发服务器的 baseUrl 同步以保障正常的工作
    devServer: {
        port: 4200,
        https: false,
        //如果你的前端应用和后端 API 服务器没有运行在同一个主机上，你需要在开发环境下将 API 请求代理到 API 服务器。这个问题可以通过 vue.config.js 中的 devServer.proxy 选项来配置。
        //https://github.com/chimurai/http-proxy-middleware#proxycontext-config
        // proxy: 'http://localhost:9000'
    },

    //是否为 Babel 或 TypeScript 使用 thread-loader。该选项在系统的 CPU 有多于一个内核时自动启用，仅作用于生产构建。
    parallel: require('os').cpus().length > 1,

    //向 PWA(https://github.com/vuejs/vue-cli/tree/dev/packages/%40vue/cli-plugin-pwa) 插件传递选项。
    pwa: {},

    //这是一个不进行任何 schema 验证的对象，因此它可以用来传递任何第三方插件选项。
    pluginOptions: {},

    //是一个函数，会接收一个基于 webpack-chain 的 ChainableConfig 实例。允许对内部的 webpack 配置进行更细粒度的修改。
    // chainWebpack: () => {}
    
    //如果这个值是一个对象，则会通过 webpack-merge 合并到最终的配置中。
    //如果这个值是一个函数，则会接收被解析的配置作为参数。该函数及可以修改配置并不返回任何东西，也可以返回一个被克隆或合并过的配置版本。
    // configureWebpack: { }
  }