<#include "/admin/layout/tableDataLayout.ftl">
<@tabledata>
    <table class="table table-hover table-bordered table-striped table-condensed">
    
         <thead>
            <tr class="thead">

              <td colspan="4">患者端端主页推荐位</td>
  
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
             <#list bannerInfoPatientList as bannerDoctor>
                 <tr>
                    <td id="patientDetailNo${bannerDoctor_index}" class="none">${bannerDoctor.id}</td>
                    <td id="title${bannerDoctor_index}">${bannerDoctor.title}</td>
                    <td id="imageId${bannerDoctor_index}">${bannerDoctor.imageId}</td>
                    <td id="url${bannerDoctor_index}">${bannerDoctor.url}</td>
         

                    <td>
                        <a href="javascript:;" class="hospital-modify" onclick="modfiypatientnews(${bannerDoctor_index})"><i class="glyphicon glyphicon-edit"></i>修改</a>
                        <a href="javascript:;" class="hospital-delete" onclick="deletepatientnews(${bannerDoctor_index})"><i class="glyphicon glyphicon-trash"></i>删除</a>
                    </td>
                </tr>
             </#list>
         </tbody>
     </table>
     
     

</@tabledata>