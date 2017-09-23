<#assign projectName="wedata">
<#assign assets="http://localhost:9091/lib/metronic4.5.2/assets">
<#assign staticServer="http://localhost:9091"/>
<#-- 为了便于维护多个项目，所有的metronic公共css、js都在对应的js文件中配置并引入 -->
<script src="${assets}/script.js"></script>
<script src="${assets}/dependencies.js"></script>
<script>
	dependencies.project = "${projectName}";
	dependencies.version = $GC.version;
	dependencies.onload = function () {
		var jsdir = $GC.staticServer + "/${projectName}/" + ($GC.debug?"jsdev":"js");
		var scripts = [jsdir + "/common.js"];

		// TODO
		scripts.push('${assets}/global/plugins/echarts/echarts.js');
		scripts.push('${staticServer}/${projectName}/jsdev/charts-echarts.js');
		scripts.push('${staticServer}/common/jsdev/plugins/jquery.iframe.upload.js');

		$script(dependencies.suffix(scripts), function () {

			// 加载页面JS模块
			var moduleName = $("#g-cfg").data("module");
			if(moduleName) {
				$script(jsdir + "/" + moduleName + ".js?_=" + $GC.version, function(){
					// 执行页面JS片段
					while($GF.length){
						$GF.shift()();
					}
					$GF.complete = true;
				});
			} else {
				// 执行页面JS片段
				while($GF.length){
					$GF.shift()();
				}
				$GF.complete = true;
			}
		});
	};
	dependencies.load();
</script>