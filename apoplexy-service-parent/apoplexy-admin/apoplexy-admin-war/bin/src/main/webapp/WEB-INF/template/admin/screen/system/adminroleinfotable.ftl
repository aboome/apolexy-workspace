<#include "/admin/layout/tableDataLayout.ftl">
<@tabledata>
<table class="table table-hover table-bordered table-striped table-condensed">
    <thead>
    <tr class="thead">
        <th>角色名称名称</th>
        <th>角色菜单信息</th>
        <th>备注</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <tr><!-- 该行仅为控制隔行变色 --></tr>
        <#list AdminRoleInfoList as roleInfo>
        <tr>
            <td id="detailNo${roleInfo_index}" class="none">${roleInfo.id}</td>
            <td id="roleName${roleInfo_index}">${roleInfo.roleName}</td>
            <td id="desc${roleInfo_index}">${roleInfo.description}</td>
            <td><a href="javascript:;" class="view click-view" onclick="RoleMenuInfo(${roleInfo_index})"><i class="glyphicon glyphicon-edit"></i>修改角色菜单</a></td>
            <td>
                <a href="javascript:;" class="hospital-modify" onclick="modifyRoleInfo(${roleInfo_index})"><i
                        class="glyphicon glyphicon-edit"></i>修改</a>
                <a href="javascript:;" class="hospital-delete" onclick="deleteRoleInfo(${roleInfo_index})"><i
                        class="glyphicon glyphicon-trash"></i>删除</a>
            </td>
        </tr>
        </#list>
    </tbody>
</table>
</@tabledata>