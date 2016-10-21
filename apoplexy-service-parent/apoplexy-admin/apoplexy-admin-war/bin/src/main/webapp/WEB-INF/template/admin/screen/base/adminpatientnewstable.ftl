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
             <#list patienNewsList as patientnews>
                 <tr>
                    <td id="detailNo${patientnews_index}"  class="none">${patientnews.id}</td>
                    <td id="content${patientnews_index}"   class="none">${patientnews.content}</td>
                     <td id="newsType${patientnews_index}"   class="none">${patientnews.type}</td>
                    <td id="title${patientnews_index}">${patientnews.title}</td>
                     
                    <td id="smallLogo${patientnews_index}">${patientnews.smallLogo}</td>
                    <td id="subTitle${patientnews_index}">${patientnews.subTitle}</td>
                    <td id="source${patientnews_index}">${patientnews.source}</td>
                    <td id="type${patientnews_index}">
                        <#if patientnews.type==0>
                                                                                                                         图文
                        <#elseif patientnews.type==1>
                            	           视频
                         <#elseif patientnews.type==2>     
                           			PDF
                        </#if>
                       
                    </td>
                     <td id="createTime${patientnews_index}"><#if patientnews.createTime??><@p.out value="${patientnews.createTime?string('yyyy-MM-dd')}" /></#if></td>
                     <td>详细内容</td>
                    <td>
                        <a href="javascript:;" class="hospital-modify" onclick="modfiypatientnews(${patientnews_index})"><i class="glyphicon glyphicon-edit"></i>修改</a>
                        <a href="javascript:;" class="hospital-delete" onclick="deletepatientnews(${patientnews_index})"><i class="glyphicon glyphicon-trash"></i>删除</a>
                    </td>
                </tr>
             </#list>
         </tbody>
     </table>
</@tabledata>