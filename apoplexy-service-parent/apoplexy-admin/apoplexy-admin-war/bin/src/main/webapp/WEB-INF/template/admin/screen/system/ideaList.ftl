<#include "/admin/layout/baseLayout.ftl">
<#macro html_title>查询意见反馈列表</#macro>
<#macro html_body>
<div class="hospital-wrap">
    
    <div class="table-content">
        
        <div id="ideaListtable" class="hospital-table">
            
        </div>
    </div>
    <div class="footer">
        <div class="page-term form-horizontal">
            <label for="size" class="control-label size-label">每页显示：</label>
            <div class="page-size">
                <select class="form-control input-sm" id="size">
                    <option value="10">10</option>
                    <option value="20">20</option>
                    <option value="30">30</option>
                    <option value="40">40</option>
                    <option value="50">50</option>
                </select>
            </div>
            <label for="size" class="control-label size-label">条</label>
            <div class="page-count">
                全部记录共：<span class="count-num">550</span>条
            </div>
        </div>
        <div class="page-content">
            <ul class="pagination">
                <li><a href="#">&laquo;</a></li>
                <li class="active"><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li><a href="#">&raquo;</a></li>
            </ul>
        </div>
    </div>
</div>
</#macro>
     <div class="zf-modal" id="deleteLayer" style="display:none">
        <p class="deleteInfo">您确定要删除该条记录吗？</p>
    </div>
    
<#macro html_foot>
    <@p.js src="system/idea.js"/>	
</#macro>