<#include "/layout/tableDataLayout.ftl">
<@tabledata>
<table class="table table-hover table-bordered table-striped table-condensed">
    <thead>
    <tr class="thead">
        <th>姓名</th>
        <th>性别</th>
        <th>医院</th>
        <th>科室</th>
        <th>手机号码</th>
        <th>职称</th>
        <th>岗位</th>
        <th>有效期</th>
        <th>头像</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <tr><!-- 该行仅为控制隔行变色 --></tr>
        <#list doctorList as doctor>
        <tr>
            <td id="detailNo${doctor_index}" class="none">${doctor.id}</td>
            <td id="doctorSex${doctor_index}" class="none">${doctor.sex}</td>
            <td id="doctorEmail${doctor_index}" class="none">${doctor.email}</td>
            <td id="avatar${doctor_index}" class="none">${doctor.avatar}</td>
            <td id="hospitalId${doctor_index}" class="none">${doctor.hospitalId}</td>
            <td id="doctorName${doctor_index}">${doctor.doctorName}</td>
            <td id="doctorSexName${doctor_index}">
                <#if doctor.sex==0>
                    女
                <#elseif doctor.sex==1>
                    男
                </#if>
            </td>
            <td id="hospital${doctor_index}">${doctor.hospital}</td>
            <td id="department${doctor_index}">${doctor.department}</td>
            <td id="doctorPhone${doctor_index}">${doctor.phone}</td>
            <td id="title${doctor_index}">${doctor.title}</td>
            <td id="job${doctor_index}">${doctor.job}</td>
            <td id="effectiveTime${doctor_index}"><#if doctor.effectiveTime??><@p.out value="${doctor.effectiveTime?string('yyyy-MM-dd')}" /></#if></td>
            <td>
                <a href="javascript:;" class="view click-view" onclick="viewAvatar(${doctor_index})"><i
                        class="glyphicon glyphicon-picture"></i>点击查看</a>
            </td>
            <td>
                <a href="javascript:;" class="hospital-modify" onclick="modifyDoctor(${doctor_index})"><i
                        class="glyphicon glyphicon-edit"></i>修改</a>
                <a href="javascript:;" class="hospital-delete" onclick="deleteDoctor(${doctor_index})"><i
                        class="glyphicon glyphicon-trash"></i>删除</a>
            </td>
        </tr>
        </#list>
    </tbody>
</table>
</@tabledata>