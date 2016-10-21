<#include "/layout/tableDataLayout.ftl">
<@tabledata>
    <table class="table table-hover table-bordered table-striped table-condensed">
        <thead>
            <tr class="thead">
                    <th>反馈人</th>
                    <th>反馈内容</th>
                    <th>反馈时间</th>
                    <th>操作</th>
            </tr>
         </thead>
         <tbody>
             <tr><!-- 该行仅为控制隔行变色 --></tr>
              <#list comReplyList as idea>
                 <tr>
                    <td class="none" id="id${idea_index}">${idea.id}</td>
                    <td id="userName${idea_index}">${idea.userName}</td>
                    <td id="idea${idea_index}">${idea.idea}</td>
                    <td id="createTime${idea_index}"><#if idea.createTime??><@p.out value="${idea.createTime?string('yyyy-MM-dd HH:mm:ss')}" /></#if></td>
                    <td>
                        <a href="javascript:;" class="hospital-delete" onclick="deleteIdea(${idea_index})"><i class="glyphicon glyphicon-trash"></i>删除</a>
                    </td>
                </tr>
             </#list>
         </tbody>
     </table>
</@tabledata>