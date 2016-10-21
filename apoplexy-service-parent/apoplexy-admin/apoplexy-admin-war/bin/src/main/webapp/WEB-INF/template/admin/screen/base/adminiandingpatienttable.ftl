  <#include "/admin/layout/tableDataLayout.ftl">
<@tabledata>
        <table class="table table-hover table-bordered table-striped table-condensed">
        
                 <thead>
            <tr class="thead">

              <td colspan="3">患者端开机动画</td>
  
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
             <#list landingPagepatientList as patientList>
                 <tr>
                    <td id="pantientDetailNo${patientList_index}" class="none">${patientList.id}</td>
                    <td id="imageId${patientList_index}">${patientList.imageId}</td>
                    <td>查看详情</td>
 

                    <td>
                        <a href="javascript:;" class="hospital-modify" onclick="modfiypatient(${patientList_index}><i class="glyphicon glyphicon-edit"></i>修改</a>
                        <a href="javascript:;" class="hospital-delete" onclick="deletepatient(${patientList_index})"><i class="glyphicon glyphicon-trash"></i>删除</a>
                    </td>
                </tr>
             </#list>
         </tbody>
     </table>
   </@tabledata>  