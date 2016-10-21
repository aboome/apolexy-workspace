<#include "/admin/layout/tableDataLayout.ftl">
<@tabledata>
    <table class="table table-hover table-bordered table-striped table-condensed">
        <thead>
            <tr class="thead">
                    <th>医生姓名</th>
                    <th>所在医院</th>
                    <th>发布时间</th>
                    <th>严重程度</th>
                    <th>转诊标题</th>
                    <th>转诊描述</th>
                    <th>转诊病例</th>
                    <th>阅读</th>
                    <th>接单医生的数量</th>
                    <th>转诊状态</th>
                    <th>操作</th>
            </tr>
         </thead>
         <tbody>
             <tr><!-- 该行仅为控制隔行变色 --></tr>
             <#list referralList as referral>
                 <tr>
                    <td class="none" id="referralId${referral_index}">${referral.id}</td>
                    <td id="doctorName${referral_index}">${referral.doctorName}</td>
                    <td id="hospital${referral_index}">${referral.hospital}</td>
                    <td id="createTime${referral_index}"><#if referral.createTime??><@p.out value="${referral.createTime?string('yyyy-MM-dd HH:mm:ss')}" /></#if></td>
                    <td id="type${referral_index}">${referral.type}</td>
                    <td id="title${referral_index}">${referral.title}</td>                         
                    <td id="mainDesc${referral_index}">${referral.mainDesc}</td> 
                    <td>
                    	<a href="javascript:;" class="hospital-modify" onclick="referralDetail(${referral_index})"><i class="glyphicon glyphicon-edit"></i>点击查看</a>
                    </td>
                    <td id="readCount${referral_index}">${referral.readCount}</td> 
                    <td id="receiveCount${referral_index}"><a href="javascript:;" class="hospital-delete">${referral.receiveCount}</a></td> 
                    <td id="status${referral_index}">
                    	<#if referral.status==0>
                                  	  接诊中                                                           
                        <#elseif referral.status==1>
                                                                                            已转诊
                        </#if> 
                     <br/>
                     <#if referral.status==0>
                     <a href="javascript:;" class="hospital-modify" onclick="doctorList(${referral_index})">
                     <i class="glyphicon glyphicon-edit"></i>查看接诊医生</a>                                                         
                     <#elseif referral.status==1>
                     <a href="javascript:;" class="hospital-modify" onclick="doctorEvaluate(${referral_index})">
                     <i class="glyphicon glyphicon-edit"></i>查看接诊评价</a>
                     </#if> 
                     
                    </td> 
                    
                    <td>
                        <a href="javascript:;" class="hospital-delete" onclick="deleteReferral(${referral_index})"><i class="glyphicon glyphicon-trash"></i>删除</a>
                    </td>
                </tr>
             </#list>
         </tbody>
     </table>
</@tabledata>