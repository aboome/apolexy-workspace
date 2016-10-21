<#include "/layout/tableDataLayout.ftl">
<div class="discusslist">
    <table class="table table-hover table-bordered table-striped my-table-layer">
        <thead>
        <tr class="thead">
            <th>类型</th>
            <th>评论医生</th>
            <th>评论内容</th>
            <th>评论时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr><!-- 该行仅为控制隔行变色 --></tr>
        <#list commentList as comment>
        <tr>
            <td>评论</td>
            <td class="none" id="commentId${comment_index}">${comment.discussId}</td>
            <td id="doctorName${comment_index}">${comment.doctorName}</td>
            <td id="discussContent${comment_index}">${comment.discussContent}</td>
            <td id="discussTime${comment_index}"><#if comment.discussTime??><@p.out value="${comment.discussTime?string('yyyy-MM-dd HH:mm:ss')}" /></#if></td>
            <td>
                <a href="javascript:;" class="hospital-delete" onclick="deleteComment(${comment_index})">
                	<i class="glyphicon glyphicon-trash"></i> 删除
                </a>
            </td>
        </tr>
        <#list comment.childDiscussList as childComment>
        <tr>
            <td>回复</td>
            <td class="none" id="childCommentId${childComment_index}">${childComment.discussId}</td>
            <td id="doctorName_childCommentId${childComment_index}">${childComment.fromDoctor}</td>
            <td id="discussContent_childCommentId${childComment_index}">${childComment.discussContent}</td>
            <td id="discussTime_childCommentId${childComment_index}"><#if childComment.discussTime??><@p.out value="${childComment.discussTime?string('yyyy-MM-dd HH:mm:ss')}" /></#if></td>
            <td>
                <a href="javascript:;" class="hospital-delete" onclick="deleteChildComment(${childComment_index})">
                	<i class="glyphicon glyphicon-trash"></i> 删除
                </a>
            </td>
        </tr>
        </#list>
        </#list>
        </tbody>
    </table>
</div>
