<#include "/layout/tableDataLayout.ftl">
<@tabledata>
    <table class="table table-hover table-bordered table-striped table-condensed">
        <#list bannerInfoList as bannerInfo>
            <#if bannerInfo_index == 0><h3 class="table-caption"><#if bannerInfo.owner==1>医生端主页推荐位<#elseif bannerInfo.owner==2>患者端主页推荐位</#if></h3></#if>
        </#list>
        <thead>
            <tr class="thead">
               <th>标题</th>
               <th>图片</th>
               <th>链接</th>
               <th>排序</th>
               <th>操作</th>
            </tr>
         </thead>
         <tbody>
             <tr><!-- 该行仅为控制隔行变色 --></tr>
             <#list bannerInfoList as bannerInfo>
                 <tr>
                    <td id="detailNo${bannerInfo.owner}${bannerInfo_index}" class="none">${bannerInfo.id}</td>
                    <td id="imageId${bannerInfo.owner}${bannerInfo_index}" class="none">${bannerInfo.imageId}</td>
                     <td id="owner${bannerInfo.owner}${bannerInfo_index}" class="none">${bannerInfo.owner}</td>
                    <td id="title${bannerInfo.owner}${bannerInfo_index}">${bannerInfo.title}</td>
                    <td>
                    <a href="javascript:;" class="view click-view" onclick="viewBannerInfo(${bannerInfo.owner}${bannerInfo_index})"><i class="glyphicon glyphicon-picture"></i>点击查看</a>
                    </td>
                    <td id="url${bannerInfo.owner}${bannerInfo_index}">${bannerInfo.url}</td>
                    <td id="bannerSort${bannerInfo.owner}${bannerInfo_index}">${bannerInfo.sort}</td>
                    <td>
                        <a href="javascript:;" class="hospital-modify" onclick="modifyBanner(${bannerInfo.owner}${bannerInfo_index})"><i class="glyphicon glyphicon-edit"></i>修改</a>
                        <a href="javascript:;" class="hospital-delete" onclick="deleteBanner(${bannerInfo.owner}${bannerInfo_index})"><i class="glyphicon glyphicon-trash"></i>删除</a>
                    </td>
                </tr>
             </#list>
         </tbody>
     </table>
     
     

</@tabledata>