<#include "/admin/layout/tableDataLayout.ftl">
<@tabledata>
    <table class="table table-hover table-bordered table-striped table-condensed">
        <thead>
            <tr class="thead">
                    <th>医院名称</th>
                     <th>医院简介</th>
                    <th>医院地址</th>
                     <th>医院图片</th>
                    <th>操作</th>
            </tr>
         </thead>
         <tbody>
             <tr><!-- 该行仅为控制隔行变色 --></tr>
             <#list hospitalList as hospital>
                 <tr>
                    <td id="detailNo${hospital_index}"  class="none">${hospital.id}</td>
                    <td id="parentHospitalName${hospital_index}"  class="none">${hospital.parentHospitalName}</td>
                    <td id="lon${hospital_index}"  class="none">${hospital.lon}</td>
                    <td id="lat${hospital_index}"  class="none">${hospital.lat}</td>
                    <td id="level${hospital_index}"  class="none">${hospital.level}</td>
                    <td id="areaCode${hospital_index}"  class="none">${hospital.areaCode}</td>
                    <td id="hospitalName${hospital_index}">${hospital.hospitalName}</td>
                    <td id="hospitalDesc${hospital_index}">${hospital.hospitalDesc}</td>
                    <td id="hospitalAddr${hospital_index}">${hospital.hospitalAddr}</td>
                    <td id="imageId${hospital_index}">${hospital.imageId}</td>
                    <td>
                        <a href="javascript:;" class="hospital-modify" onclick="modfiyHospital(${hospital_index})"><i class="glyphicon glyphicon-edit"></i>修改</a>
                        <a href="javascript:;" class="hospital-delete" onclick="deleteHospital(${hospital_index})"><i class="glyphicon glyphicon-trash"></i>删除</a>
                    </td>
                </tr>
             </#list>
         </tbody>
     </table>
</@tabledata>