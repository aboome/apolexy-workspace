<#include "/admin/layout/tableDataLayout.ftl">
<@tabledata>
<table class="table table-hover table-bordered table-striped table-condensed">
    <thead>
    <tr class="thead">
        <th>问题</th>
        <th>回答</th>
    </tr>
    </thead>
    <tbody>
    <tr><!-- 该行仅为控制隔行变色 --></tr>
        <#list zfDetailList as detail>
        <tr>
            <td id="question${detail_index}">${detail.question}</td>
            <td id="answer${detail_index}"><#if detail.result==0>否<#elseif detail.result==1>是</#if></td>
        </tr>
        </#list>
    </tbody>
</table>
</@tabledata>