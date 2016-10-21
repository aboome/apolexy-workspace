<#include "/admin/layout/tableDataLayout.ftl">
<@tabledata>
    <table class="table table-hover table-bordered table-striped table-condensed">
    
         <thead>
            <tr class="thead">

              <td colspan="3">医生端开机动画</td>
  
            </tr>
         </thead>
         
        <thead>
            <tr class="thead">
                     <th>图片</th>
                    <th>详情</th>
                    <th>操作</th>
            </tr>
         </thead>
         <tbody>
             <tr><!-- 该行仅为控制隔行变色 --></tr>
             <#list landingDoctorList as landingDoctor>
                 <tr>
                    <td id="detailNo${landingDoctor_index}"  class="none">${landingDoctor.id}</td>
                    <td id="imageId${landingDoctor_index}">${landingDoctor.imageId}</td>
                    <td>查看详情</td>
         

                    <td>
                        <a href="javascript:;" class="hospital-modify" onclick="modfiydoctornews(${landingDoctor_index}><i class="glyphicon glyphicon-edit"></i>修改</a>
                        <a href="javascript:;" class="hospital-delete" onclick="deletedoctort(${landingDoctor_index})"><i class="glyphicon glyphicon-trash"></i>删除</a>
                    </td>
                </tr>
             </#list>
         </tbody>
     </table>
     
     

</@tabledata>