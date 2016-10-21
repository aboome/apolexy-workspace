<#include "/admin/layout/tableDataLayout.ftl">
<@tabledata>
<table class="table table-hover table-bordered table-striped table-condensed">
    <thead>
    <tr class="thead">
        <th>用户名</th>
        <th>年龄</th>
        <th>性别</th>
        <th>区域</th>
        <th>填写时间</th>
        <th>高危指数</th>
        <th>Fast量表信息</th>
    </tr>
    </thead>
    <tbody>
    <tr><!-- 该行仅为控制隔行变色 --></tr>
        <#list hrList as hr>
        <tr>
            <td class="none" id="id${hr_index}">${hr.id}</td>
            <td id="userName${hr_index}">${hr.userName}</td>
            <td id="age${hr_index}">${hr.age}</td>
            <td id="sex${hr_index}"><#if hr.sex==0>男<#elseif hr.sex==1>女</#if></td>
            <td id="area${hr_index}">${hr.area}</td>
            <td id="createTime${hd_index}"><#if hr.createTime??><@p.out value="${hr.createTime?string('yyyy-MM-dd HH:mm:ss')}" /></#if></td>
            <td id="incidence${hr_index}">${hr.incidence}</td>
            <td><a href="javascript:;" class="hospital-modify" onclick="viewDetail(${hr_index})"><i
                    class="glyphicon glyphicon-edit"></i>点击查看详情</a></td>
        </tr>
        </#list>
    </tbody>
</table>
</@tabledata>