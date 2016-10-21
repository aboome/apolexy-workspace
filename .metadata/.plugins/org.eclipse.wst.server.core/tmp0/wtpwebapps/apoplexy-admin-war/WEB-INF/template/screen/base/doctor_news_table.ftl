<#include "/layout/tableDataLayout.ftl">
<@tabledata>
<table class="table table-hover table-bordered table-striped table-condensed">
    <thead>
    <tr class="thead">
        <th>标题</th>
        <th>摘要</th>
        <th>来源</th>
        <th>内容类型</th>
        <th>资源类型</th>
        <th>发布时间</th>
        <th>缩略图</th>
        <th>详细内容</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <tr><!-- 该行仅为控制隔行变色 --></tr>
        <#list doctorNewsList as doctornews>
        <tr>
            <td id="detailNo${doctornews_index}" class="none">${doctornews.id}</td>
            <td id="type${doctornews_index}" class="none">${doctornews.type}</td>
            <td id="smallLogo${doctornews_index}" class="none">${doctornews.smallLogo}</td>
            <td id="doctorNewsType${doctornews_index}" class="none">${doctornews.newType}</td>
            <td id="contentType${doctornews_index}" class="none">${doctornews.contentType}</td>
            <td id="title${doctornews_index}">${doctornews.title}</td>
            <td id="subTitle${doctornews_index}">${doctornews.subTitle}</td>
            <td id="source${doctornews_index}">${doctornews.source}</td>
            <td>
                <#if doctornews.newType==1>
                    学术知识
                <#elseif doctornews.newType==2>
                    新闻资讯
                </#if>
            </td>
            <td>
                <#if doctornews.type==1>
                    图片
                <#elseif doctornews.type==2>
                    视频
                <#elseif doctornews.type==3>
                    PDF
                </#if>
            </td>
            <td id="createTime${doctornews_index}"><#if doctornews.createTime??><@p.out value="${doctornews.createTime?string('yyyy-MM-dd HH:mm:ss')}" /></#if></td>
            <td>
                <a href="javascript:;" class="view click-view" onclick="viewImage(${doctornews_index})"><i
                        class="fa fa-image"></i>点击查看</a>
            </td>
            <td>
                <a href="javascript:;" class="view click-view" onclick="viewDetail(${doctornews_index})"><i
                        class="fa fa-eye"></i>点击查看</a>
            </td>
            <td>
                <a href="javascript:;" class="hospital-modify" onclick="modifyDoctorNews(${doctornews_index})"><i
                        class="glyphicon glyphicon-edit"></i>修改</a>
                <a href="javascript:;" class="hospital-delete" onclick="deletedoctornews(${doctornews_index})"><i
                        class="glyphicon glyphicon-trash"></i>删除</a>
            </td>
        </tr>
        </#list>
    </tbody>
</table>
</@tabledata>