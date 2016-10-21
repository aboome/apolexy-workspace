<#include "/layout/baseLayout.ftl">
<#macro html_title>查询意见反馈列表</#macro>
<#macro html_body>
<div class="hospital-wrap" style="padding: 15px 21px;">
    
    <div class="table-content" style="padding-top: 0;">
        
        <div id="ideaListtable" class="hospital-table">
            
        </div>
    </div>
    
</div>
</#macro>
     <div class="zf-modal" id="deleteLayer" style="display:none">
        <p class="deleteInfo modal-body">您确定要删除该条记录吗？</p>
    </div>
    
<#macro html_foot>
    <@p.js src="system/idea.js"/>	
</#macro>