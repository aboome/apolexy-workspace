<#include "/layout/tableDataLayout.ftl">
<@tabledata>
<table class="table table-hover table-bordered table-striped table-condensed">
    
    <#list landingPageList as landingPage>
        <#if landingPage_index == 0><h3 class="table-caption"><#if landingPage.owner==1>医生端开机动画<#elseif landingPage.owner==2>患者端开机动画</#if></h3></#if>
    </#list>
    
    <thead>
    <tr class="thead">
        <th>图片</th>
        <th>排序</th>
        <th>更新时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <tr><!-- 该行仅为控制隔行变色 --></tr>
        <#list landingPageList as landingPage>
        <tr>
            <td id="detailNo${landingPage.owner}${landingPage_index}" class="none">${landingPage.id}</td>
            <td id="imageId${landingPage.owner}${landingPage_index}" class="none">${landingPage.imageId}</td>
            <td id="owner${landingPage.owner}${landingPage_index}" class="none">${landingPage.owner}</td>
            <td>
                <a href="javascript:;" class="view click-view" onclick="viewLandingPage(${landingPage.owner}${landingPage_index})"><i class="glyphicon glyphicon-picture"></i>点击查看</a>
            </td>
            <td id="sort${landingPage.owner}${landingPage_index}">${landingPage.sort}</td>
            <td id="lastUpdateTime${landingPage.owner}${landingPage_index}"><#if landingPage.lastUpdateTime??><@p.out value="${landingPage.lastUpdateTime?string('yyyy-MM-dd HH:mm:ss')}" /></#if></td>
            <td>
                <a href="javascript:;" class="hospital-modify" onclick="modifyLandingPage(${landingPage.owner}${landingPage_index})"><i class=" glyphicon glyphicon-edit"></i>修改</a>
                <a href="javascript:;" class="hospital-delete" onclick="deleteLandingPage(${landingPage.owner}${landingPage_index})"><i class="glyphicon glyphicon-trash"></i>删除</a>
            </td>
        </tr>
        </#list>
    </tbody>
</table>
</@tabledata>