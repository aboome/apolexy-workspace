<#include "/admin/common/baselib.ftl">
<#--讨论回复列表弹出层-->
	<div class="discusslist">
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
             <#list replyList as reply>
                 <tr>
                    <td class="none" id="replyId${reply_index}">${reply.id}</td>
                    <td id="replyNameTo${reply_index}">${reply.replyNameTo}</td>
                    <td id="hospitalNameTo${reply_index}">${reply.hospitalNameTo}</td>
                    <td id="createTime${reply_index}"><#if reply.createTime??><@p.out value="${reply.createTime?string('yyyy-MM-dd HH:mm:ss')}" /></#if></td>
                    <td id="content${reply_index}">${reply.content}</td>
                    <td id="replyNameFrom${reply_index}">${reply.replyNameFrom}</td>
                    <td id="hospitalNameFrom${reply_index}">${reply.hospitalNameFrom}</td>
                    <td>
                        <a href="javascript:;" class="hospital-delete"onclick="deleteDiscussReview(${reply_index})"><i class="glyphicon glyphicon-trash"></i>删除</a>
                    </td>
                </tr>
             </#list>
         </tbody>
     </table>
	</div>
