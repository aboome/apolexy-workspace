<#include "/layout/tableDataLayout.ftl">
<@tabledata>
<table class="table table-hover table-bordered table-striped table-condensed">
    <thead>
    <tr class="thead">
        <th>医生姓名</th>
        <th>所在医院</th>
        <th>发布时间</th>
        <th>病例类型</th>
        <th>主诉</th>
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
        <#list casesList as cases>
        <tr>
            <td class="none" id="discussId${cases_index}">${cases.id}</td>
            <td id="doctorName${cases_index}">${cases.doctorName}</td>
            <td id="hospital${cases_index}">${cases.hospital}</td>
            <td id="createTime${cases_index}"><#if cases.createTime??><@p.out value="${cases.createTime?string('yyyy-MM-dd HH:mm:ss')}" /></#if></td>
            <td id="type${cases_index}">${cases.type}</td>
            <td id="mainDesc${cases_index}">${cases.mainDesc}</td>
            <td>
                <a href="javascript:;" class="view click-view" onclick="discussDetail(${cases_index})"><i
                        class="glyphicon glyphicon-edit"></i>点击查看</a>
            </td>
            <td id="readCount${cases_index}">${cases.readCount}</td>
            <td id="replyCount${cases_index}"><a href="javascript:;" class="view replyCount"
                                                 onclick="discussReviewList(${cases_index})">${cases.replyCount}</a>
            </td>
            <td id="likeCount${cases_index}">${cases.likeCount}</td>
            <td id="collectionCount${cases_index}">${cases.collectionCount}</td>
            <td>
                <a href="javascript:;" class="hospital-delete" onclick="deleteDiscuss(${cases_index})"><i
                        class="glyphicon glyphicon-trash"></i>删除</a>
            </td>
        </tr>
        </#list>
    </tbody>
</table>
</@tabledata>