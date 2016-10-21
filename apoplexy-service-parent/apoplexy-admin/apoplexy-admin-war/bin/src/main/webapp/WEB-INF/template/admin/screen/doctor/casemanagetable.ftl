<#include "/admin/layout/tableDataLayout.ftl">
<@tabledata>
    <table class="table table-hover table-bordered table-striped table-condensed">
        <thead>
            <tr class="thead">
                    <th>医生姓名</th>
                    <th>所在医院</th>
                    <th>发布时间</th>
                    <th>病例</th>
                    <th>阅读</th>
                    <th>回复</th>
                    <th>赞</th>
                    <th>收藏</th>
                    <th>操作</th>
            </tr>
         </thead>
         <tbody>
             <tr><!-- 该行仅为控制隔行变色 --></tr>
             <#list astList as ast>
                 <tr>
                    <td class="none" id="manageId${ast_index}">${ast.id}</td>
                    <td id="doctorName${ast_index}">${ast.doctorName}</td>
                    <td id="hospital${ast_index}">${ast.hospital}</td>
                    <td id="createTime${ast_index}"><#if ast.createTime??><@p.out value="${ast.createTime?string('yyyy-MM-dd HH:mm:ss')}" /></#if></td>
                    <td>
                    	<a href="javascript:;" class="hospital-modify"onclick="manageDetail(${ast_index})"><i class="glyphicon glyphicon-edit"></i>点击查看</a>
                    </td>
                    <td id="readCount${ast_index}">${ast.readCount}</td> 
                    <td id="replyCount${cases_index}"><a href="javascript:;" class="hospital-delete" onclick="astReviewList(${ast_index})">${ast.commentCount}</a></td> 
                    <td id="likeCount${ast_index}">${ast.likeCount}</td> 
                    <td id="collectionCount${ast_index}">${ast.collectionCount}</td> 
                    <td>
                        <a href="javascript:;" class="hospital-delete" onclick="deleteManage(${ast_index})"><i class="glyphicon glyphicon-trash"></i>删除</a>
                    </td>
                </tr>
             </#list>
         </tbody>
     </table>
</@tabledata>