<#include "/admin/common/baselib.ftl">
<#--ast回复列表弹出层-->

    <table class="table table-hover table-bordered table-striped table-condensed">
        <thead>
            <tr class="thead">
            <th>评论者姓名</th>
            <th>评论者所在医院</th>
            <th>评论时间</th>
            <th>评论内容</th>
            <th>回复者姓名</th>
            <th>回复者所在医院</th>
            <th>操作</th>
            </tr>
         </thead>
         <tbody>
             <tr><!-- 该行仅为控制隔行变色 --></tr>
             <#list astReplyList as astReply>
                 <tr>
                    <td class="none" id="astReplyId${astReply_index}">${astReply.id}</td>
                    <td id="toDoctorName${astReply_index}">${astReply.toDoctorName}</td>
                    <td id="toHospital${astReply_index}">${astReply.toHospital}</td>
                    <td id="createTime${astReply_index}"><#if astReply.createTime??><@p.out value="${astReply.createTime?string('yyyy-MM-dd HH:mm:ss')}" /></#if></td>
                    <td id="comment${astReply_index}">${astReply.comment}</td>
                    <td id="doctorName${astReply_index}">${astReply.doctorName}</td>
                    <td id="hospital${astReply_index}">${astReply.hospital}</td>
                    <td>
                        <a href="javascript:;" class="hospital-delete"onclick="deleteAstReview(${astReply_index})"><i class="glyphicon glyphicon-trash"></i>删除</a>
                    </td>
                </tr>
             </#list>
         </tbody>
     </table>

