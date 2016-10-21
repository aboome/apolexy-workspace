<#include "/layout/tableDataLayout.ftl">
<@tabledata>
<table class="table table-hover table-bordered table-striped table-condensed">
    <thead>
    <tr class="thead">
        <th>用户名</th>
        <th>年龄</th>
        <th>性别</th>
        <th>区域</th>
        <th>发病时间</th>
        <th>记录时间</th>
        <th>Fast量表信息</th>
    </tr>
    </thead>
    <tbody>
    <tr><!-- 该行仅为控制隔行变色 --></tr>
        <#list ZFEmerList as zf>
        <tr>
            <td class="none" id="id${zf_index}">${zf.id}</td>
            <td id="userName${zf_index}">${zf.userName}</td>
            <td id="age${zf_index}">${zf.age}</td>
            <td id="sex${zf_index}"><#if zf.sex==0>男<#elseif zf.sex==1>女</#if></td>
            <td id="area${zf_index}"><#if zf.area??>${zf.area}<#else>未知</#if></td>
            <td id="onsetTime${zf_index}"><#if zf.onsetTime??><@p.out value="${zf.onsetTime?string('yyyy-MM-dd HH:mm:ss')}" /></#if></td>
            <td id="createTime${zf_index}"><#if zf.createTime??><@p.out value="${zf.createTime?string('yyyy-MM-dd HH:mm:ss')}" /></#if></td>
            <td>
                <a href="javascript:;" class="hospital-modify" onclick="viewDetail(${zf_index})"><i
                    class="fa fa-eye"></i>点击查看详情</a>
            </td>
        </tr>
        </#list>
    </tbody>
</table>
</@tabledata>