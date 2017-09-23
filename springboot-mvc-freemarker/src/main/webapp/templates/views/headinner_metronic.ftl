<#-- 国产浏览器优先使用webkit内核 -->
<meta name="renderer" content="webkit">
<#-- dev模式后面要走配置文件 -->
<#assign isDev = true>
<#assign projectName="wedata">
<#assign staticServer="http://localhost:9091"/>
<#assign assets=staticServer+"/lib/metronic4.5.2/assets">
<#-- GOPS定制JS -->
<script>
    var $GC = {
        "gopsServer": "${portalgopsServer}",
        "staticServer": "${staticServer}",
        "imageServer": "${orderRequestImgServer}",
        "version": "${version}",
        "debug": ${isDev!"false"}
    };
    var $GF = function () {
        var queue = [], 
            push = queue.push;
        queue.push = function (fn) {
            if(queue.complete === true){
                setTimeout(fn, 0);
            }
            else{
                push.call(queue, fn);
            }
        };
        queue.complete = false;
        return queue;
    }();
</script>

<#-- 为了便于维护多个项目，所有的metronic公共css、js都在对应的js文件中配置并引入 -->
<script src="${assets}/loadcss.js"></script>
<script>
    loadcss.project = "${projectName}";
    loadcss.version = $GC.version;
    loadcss.load();
</script>


<#-- GOPS 定制CSS -->
<script>
    var cssdir = $GC.staticServer + "/${projectName}/css/";
    var css = [
        cssdir + "all.content.css",
        "${assets}/layouts/layout/css/themes/darkblue.css"
    ];
    loadcss.load(css);
</script>

<#-- livereload -->
<#if isDev>
    <script src="http://127.0.0.1:35729/livereload.js" defer="defer" async="async"></script>
</#if>

<#-- 初始化less编译 -->
<script>
    var less = {
        async: false, 
        onReady: false  // 页面加载完成自动开始编译，会查找外链less样式，若找不到会出bug
    };
</script>
<script src="${assets}/extends/less.js"></script>
<script>
    // 暴露全局变量，供fragment调用
    var compileLess = function () {
        [].forEach.call(document.querySelectorAll("style[type=less]"), function (sheet) {
            less.render(sheet.innerHTML).then(function (result) {
                sheet.setAttribute("type", "text/css");
                sheet.innerHTML = result.css;
            }, function (err) { alert("less 解析错误，具体信息查看控制台"); });
        });
    };
    addEventListener("load", compileLess);
</script>



