<#include "/layout/tableDataLayout.ftl">
<#--接诊医生列表弹出层-->

    <table class="table table-bordered my-table-layer">
        <thead>
	         <tr>
	             <th>医生姓名</th>
	             <th>所在医院</th>
	             <th>发布时间</th>
	         </tr>
         </thead>
         <tbody>
             <tr><!-- 该行仅为控制隔行变色 --></tr>
             <#list receivedoctorList as doctorList>
                 <tr>
                    <td id="doctorName${doctorList_index}">${doctorList.doctorName}</td>
                    <td id="hospitalName${doctorList_index}">${doctorList.hospitalName}</td>
                    <td id="createTime${doctorList_index}"><#if doctorList.createTime??><@p.out value="${doctorList.createTime?string('yyyy-MM-dd HH:mm:ss')}" /></#if></td>
                </tr>
             </#list>
         </tbody>
     </table>

