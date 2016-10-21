<#include "/layout/tableDataLayout.ftl">
<@tabledata>
<table class="table table-hover table-bordered table-striped table-condensed">
    <thead>
    <tr class="thead">
        <th>菜单名称</th>
        <th>菜单级别</th>
        <th>父菜单名称</th>
        <th>菜单URL</th>
        <th>排序</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <tr><!-- 该行仅为控制隔行变色 --></tr>
        <#list menuList as menuList>
        <tr>
            <td id="id${menuList_index}" class="none">${menuList.id}</td>
            <td id="parentId${menuList_index}" class="none">${menuList.parentId}</td>
            <td id="menuName${menuList_index}">${menuList.menuName}</td>
            <td id="menuLevel${menuList_index}">${menuList.menuLevel}</td>
            <td id="parentMenuName${menuList_index}">${menuList.parentMenuName}</td>
            <td id="menuUrl${menuList_index}">${menuList.menuUrl}</td>
            <td id="sort${menuList_index}">${menuList.sort}</td>
            <td>
                <a href="javascript:;" class="hospital-modify" onclick="modifyMenu(${menuList_index})"><i
                        class="glyphicon glyphicon-edit"></i>修改</a>
                <a href="javascript:;" class="hospital-delete" onclick="deleteMenu(${menuList_index})"><i
                        class="glyphicon glyphicon-trash"></i>删除</a>
            </td>
        </tr>
        </#list>
    </tbody>
</table>
</@tabledata>