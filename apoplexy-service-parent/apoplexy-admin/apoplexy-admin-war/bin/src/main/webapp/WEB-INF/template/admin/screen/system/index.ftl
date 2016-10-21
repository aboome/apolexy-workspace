<#include "/admin/layout/baseLayout.ftl">
<#macro html_title>首页</#macro>
<#macro html_body>
<div class="content-index clearfix">
        <div class="appCount-wrap">
            <h3 class="count-title">APP下载数据</h3>
            <div class="appCount" id="appCount">

            </div>
        </div>
        <div class="obs-data-wrap">
            <h3 class="count-title">患者端数据</h3>
            <div class="obs-data" id="obs-data">

            </div>
        </div>

</div>
</#macro>
<#macro html_foot>
    <@p.js src="third/echarts.min.js"/>
    <@p.js src="system/index.js"/>	
</#macro>