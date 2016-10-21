<#include "/admin/layout/baseLayout.ftl">
<#macro html_title>查询体征数据列表</#macro>
<#macro html_body>
<div class="hospital-wrap">
    <div class="table-content">
        <div id="versionListtable" class="hospital-table">
            
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
     <!-- 修改弹出层 -->
    <div id="modifyLayer" style="display:none" class="zf-modal">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="modifyVersion" class="control-label zf-name-label">版本号</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="modifyVersion"
                           placeholder="请输入版本号">
                </div>
            </div>
        </form>
    </div>
</div>
</#macro>
<#macro html_foot>
    <@p.js src="system/version.js"/>	
</#macro>