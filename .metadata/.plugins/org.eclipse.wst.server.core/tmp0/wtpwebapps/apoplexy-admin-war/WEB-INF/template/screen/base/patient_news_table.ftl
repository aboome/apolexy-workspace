<#include "/layout/tableDataLayout.ftl">
<@tabledata>
<table class="table table-hover table-bordered table-striped table-condensed">
    <thead>
    <tr class="thead">
        <th>标题</th>
        <th>摘要</th>
        <th>来源</th>
        <th>资源分类</th>
        <th>发布时间</th>
        <th>缩略图</th>
        <th>详细内容</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <tr><!-- 该行仅为控制隔行变色 --></tr>
        <#list patientNewsList as patientNews>
        <tr>
            <td id="detailNo${patientNews_index}" class="none">${patientNews.id}</td>
            <td id="newsType${patientNews_index}" class="none">${patientNews.type}</td>
            <td id="smallLogo${patientNews_index}" class="none">${patientNews.smallLogo}</td>
            <td id="contentType${patientNews_index}" class="none">${patientNews.contentType}</td>
            <td id="title${patientNews_index}">${patientNews.title}</td>
            <td id="subTitle${patientNews_index}">${patientNews.subTitle}</td>
            <td id="source${patientNews_index}">${patientNews.source}</td>
            <td id="type${patientNews_index}">
                <#if patientNews.type==1>
                    图文
                <#elseif patientNews.type==2>
                    视频
                <#elseif patientNews.type==3>
                    PDF
                </#if>
            </td>
            <td id="createTime${patientNews_index}"><#if patientNews.createTime??><@p.out value="${patientNews.createTime?string('yyyy-MM-dd HH:mm:ss')}" /></#if></td>
            <td>
                <a href="javascript:;" class="view click-view" onclick="viewImage(${patientNews_index})"><i
                        class="fa fa-image"></i>点击查看</a>
            </td>
            <td>
                <a href="javascript:;" class="view click-view" onclick="viewDetail(${patientNews_index})"><i
                        class="fa fa-eye"></i>点击查看</a>
            </td>
            <td>
                <a href="javascript:;" class="hospital-modify" onclick="modifyPatientNews(${patientNews_index})"><i
                        class="glyphicon glyphicon-edit"></i>修改</a>
                <a href="javascript:;" class="hospital-delete" onclick="deletePatientNews(${patientNews_index})"><i
                        class="glyphicon glyphicon-trash"></i>删除</a>
            </td>
        </tr>
        </#list>
    </tbody>
</table>
</@tabledata>