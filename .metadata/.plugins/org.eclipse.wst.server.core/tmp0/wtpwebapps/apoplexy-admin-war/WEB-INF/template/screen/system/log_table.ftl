<#include "/layout/tableDataLayout.ftl">
<@tabledata>
<table class="table table-hover table-bordered table-striped table-condensed">
    <thead>
    <tr class="thead">
        <th>模块名称</th>
        <th>操作描述</th>
        <th>操作用户</th>
        <th>操作时间</th>
    </tr>
    </thead>
    <tbody>
    <tr><!-- 该行仅为控制隔行变色 --></tr>
        <#list logList as log>
            <tr>
                <td class="none" id="id${idea_index}">${log.id}</td>
            <#--<td id="model${log_indexl}">${log.model}</td>-->
                <td id="modelName${log_index}">${log.modelName}</td>
            <#--<td id="operateEnum${log_index}">${log.operateEnum}</td>-->
                <td id="operateDesc${log_index}">${log.operateDesc}</td>
            <#--<td id="operateUserId${log_index}">${log.operateUserId}</td>-->
                <td id="operateUserName${log_index}">${log.operateUserName}</td>
                <td id="operateTime${log_index}"><#if log.operateTime??><@p.out value="${log.operateTime?string('yyyy-MM-dd HH:mm:ss')}" /></#if></td>
            <#--<td>-->
            <#--<a href="javascript:;" class="hospital-delete" onclick="deleteLog(${log_index})"><i-->
            <#--class="glyphicon glyphicon-trash"></i>删除</a>-->
            <#--</td>-->
            </tr>
        </#list>
    </tbody>
</table>
</@tabledata>