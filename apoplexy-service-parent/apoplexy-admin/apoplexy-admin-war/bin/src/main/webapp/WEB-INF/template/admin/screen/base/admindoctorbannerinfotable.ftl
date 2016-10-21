<#include "/admin/layout/tableDataLayout.ftl">
<@tabledata>
    <table class="table table-hover table-bordered table-striped table-condensed">
    
         <thead>
            <tr class="thead">

              <td colspan="4">医生端主页推荐位</td>
  
            </tr>
         </thead>
         
        <thead>
            <tr class="thead">
                     <th>标题</th>
                    <th>图片</th>
                    <th>链接</th>
                     <th>操作</th>
            </tr>
         </thead>
         <tbody>
             <tr><!-- 该行仅为控制隔行变色 --></tr>
             <#list bannerInfoDoctorList as bannerpantient>
                 <tr>
                    <td id="detailNo${bannerpantient_index}" class="none">${bannerpantient.id}</td>
                    <td id="title${bannerpantient_index}">${bannerpantient.title}</td>
                    <td id="imageId${bannerpantient_index}">${bannerpantient.imageId}</td>
                    <td id="url${bannerpantient_index}">${bannerpantient.url}</td>
         

                    <td>
                        <a href="javascript:;" class="hospital-modify" onclick="modfiydoctornews(${bannerpantient_index})"><i class="glyphicon glyphicon-edit"></i>修改</a>
                        <a href="javascript:;" class="hospital-delete" onclick="deletedoctortnews(${bannerpantient_index})"><i class="glyphicon glyphicon-trash"></i>删除</a>
                    </td>
                </tr>
             </#list>
         </tbody>
     </table>
     
     

</@tabledata>