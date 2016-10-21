<#include "/layout/tableDataLayout.ftl">
<@tabledata>

<ul>
	<#list zfDetailList as detail>
	<li class="clearfix">
		<p class="pull-left font-size-14 col-xs-10" style="padding-bottom: 10px;" id="question${detail_index}">${detail.question}</p>
		<p class="pull-right font-size-14 padding-right col-xs-2" style="padding-bottom: 10px;" id="answer${detail_index}"><#if detail.result==0>否<#elseif detail.result==1>是</#if></p>
	</li>
	</#list>
</ul>

</@tabledata>