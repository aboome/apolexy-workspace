<#include "/admin/layout/tableDataLayout.ftl">
<@tabledata>
    <table class="table table-hover table-bordered table-striped table-condensed">
        <thead>
            <tr class="thead">
                    <th>客户端</th>
                    <th>版本号</th>
                    <th>版本升级地址</th>
                    <th>操作</th>
            </tr>
         </thead>
         <tbody>
             <tr><!-- 该行仅为控制隔行变色 --></tr>
             <#list versionList as version>
                 <tr>
                    <td class="none" id="id${version_index}">${version.id}</td>
                    <td id="modelName${version_index}">${version.modelName}</td>
                    <td id="version${version_index}">${version.version}</td>
                    <td id="upgradeUrl${version_index}">${version.upgradeUrl}</td>
                    <td>
                        <a href="javascript:;" class="hospital-modify" onclick="modifyVersion(${version_index})"><i class="glyphicon glyphicon-edit"></i>修改</a>
                    </td>
                </tr>
             </#list>
         </tbody>
     </table>
</@tabledata>