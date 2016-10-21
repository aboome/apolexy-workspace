<#include "/admin/layout/tableDataLayout.ftl">
<@tabledata>
    <table class="table table-hover table-bordered table-striped table-condensed">
        <thead>
            <tr class="thead">
                    <th>医生姓名</th>
                     <th>所属医院</th>
                    <th>所属科室</th>
                     <th>职称</th>
                     <th>工作岗位</th>
                      <th>有效期</th>
                    <th>操作</th>
            </tr>
         </thead>
         <tbody>
             <tr><!-- 该行仅为控制隔行变色 --></tr>
             <#list doctorList as doctor>
                 <tr>
                    <td id="detailNo${doctor_index}" class="none">${doctor.id}</td>
                    <td id="doctorSex${doctor_index}" class="none">${doctor.sex}</td>
                    <td id="doctorPhone${doctor_index}" class="none">${doctor.phone}</td>
                    <td id="doctorEmail${doctor_index}" class="none">${doctor.email}</td>
                      
                    <td id="doctorName${doctor_index}">${doctor.doctorName}</td>
                    <td id="hospital${doctor_index}">${doctor.hospital}</td>
                    <td id="department${doctor_index}">${doctor.department}</td>
                    <td id="title${doctor_index}">${doctor.title}</td>
                    <td id="job${doctor_index}">${doctor.job}</td>
                      <td id="effectiveTime${doctor_index}"><#if doctor.effectiveTime??><@p.out value="${doctor.effectiveTime?string('yyyy-MM-dd')}" /></#if></td>
                      
                    <td>
                        <a href="javascript:;" class="hospital-modify" onclick="modfiyDoctor(${doctor_index})"><i class="glyphicon glyphicon-edit"></i>修改</a>
                        <a href="javascript:;" class="hospital-delete" onclick="deleteDoctor(${doctor_index})"><i  class="glyphicon glyphicon-trash"></i>删除</a>
                    </td>
                </tr>
             </#list>
         </tbody>
     </table>
</@tabledata>