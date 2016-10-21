<#include "/admin/layout/tableDataLayout.ftl">
<@tabledata>
    <table class="table table-hover table-bordered table-striped table-condensed">
        <thead>
            <tr class="thead">
                    <th>用户名</th>
                    <th>性别</th>
                    <th>记录时间</th>
                    <th>高压</th>
                    <th>低压</th>
                    <th>血糖</th>
                    <th>血脂</th>
                   
            </tr>
         </thead>
         <tbody>
             <tr><!-- 该行仅为控制隔行变色 --></tr>
             <#list hdList as hd>
                 <tr>
                    <td class="none" id="id${hd_index}">${hd.id}</td>
                    <td id="userName${hd_index}">${hd.userName}</td>
                    <td id="sex${hd_index}">
                        <#if hd.sex==0>
                                                                                                 男
                        <#elseif hd.sex==1>
                                                                                                    女
                        </#if>                                                           
                    </td>
                    <td id="createTime${hd_index}"><#if hd.createTime??><@p.out value="${hd.createTime?string('yyyy-MM-dd HH:mm:ss')}" /></#if></td>
                    <td id="highPressure${hd_index}">${hd.highPressure}</td>
                    <td id="lowPressure${hd_index}">${hd.lowPressure}</td>
                    <td id="bloodSugar${hd_index}">${hd.bloodSugar}</td>
                    <td id="bloodFat${hd_index}">${hd.bloodFat}</td>
                    
                </tr>
             </#list>
         </tbody>
     </table>
</@tabledata>