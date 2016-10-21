<#include "/layout/baseLayout.ftl">
<#macro html_title>医院数据统计管理</#macro>
<#macro html_body>

<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main"  style="width: 800px;height:600px;margin:70px auto;"></div>

</#macro>
<#macro html_foot>
    <@p.js src="statics/hospital_statistics.js"/>
    <@p.js src="third/echarts.min.js"/>
</#macro>
