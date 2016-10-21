<#include "/admin/layout/tableDataLayout.ftl">
<@tabledata>
    <table class="table table-hover table-bordered table-striped table-condensed">
    

    
        <thead>
            <tr class="thead">
                    <th>标题</th>
                    <th>缩略图</th>
                     <th>摘要</th>
                     <th>来源</th>
                     <th>分类</th>
                     <th>发布时间</th>
                     <th>详细内容</th>
                    <th>操作</th>
                    
            </tr>
         </thead>
         <tbody>
             <tr><!-- 该行仅为控制隔行变色 --></tr>
             <#list doctorNewsList as doctornews>
                 <tr>
                   <td id="detailNo${doctornews_index}" class="none">${doctornews.id}</td>
                   <td id="content${doctornews_index}" class="none">${doctornews.content}</td>
                    <td id="type${doctornews_index}" class="none">${doctornews.type}</td>
                    <td id="doctorNewsType${doctornews_index}" class = "none">${doctornews.newType}</td>
                   <td id="title${doctornews_index}" >${doctornews.title}</td>
                   <td id="smallLogo${doctornews_index}" >${doctornews.smallLogo}</td>
                   <td id="subTitle${doctornews_index}">${doctornews.subTitle}</td>
                   <td id="source${doctornews_index}">${doctornews.source}</td>
                    <td id="newType">
                     <#if doctornews.newType==1>
                                 	   学术知识
                        <#elseif doctornews.newType==2>
                            	             新闻资讯
                     
                        </#if>
                    </td>

                   <td id="createTime${doctornews_index}"><#if doctornews.createTime??><@p.out value="${doctornews.createTime?string('yyyy-MM-dd')}" /></#if></td>
                   <td>详细内容</td>
                    <td>
                        <a href="javascript:;" class="hospital-modify" onclick="modfiydoctornews(${doctornews_index})"><i class="glyphicon glyphicon-edit"></i>修改</a>
                        <a href="javascript:;" class="hospital-delete" onclick="deletedoctornews(${doctornews_index})"><i class="glyphicon glyphicon-trash"></i>删除</a>
                    </td>
                </tr>
             </#list>
         </tbody>
     </table>
</@tabledata>