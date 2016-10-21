<#include "/admin/layout/baseLayout.ftl">
<#macro html_title>查询系统日志列表</#macro>
<#macro html_body>
<div class="hospital-wrap">
    <div class="search-term">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <div class="hospital-name">
                    <input typee="text" class="form-control hospital-name-input" id="model-name"
                           placeholder="模块">
                </div>
                <div class="hospital-name">
                    <input typee="text" class="form-control hospital-name-input" id="user-name"
                           placeholder="用户名">
                </div>
               <div class="hospital-name">
                   <input type="text" class="form-control hospital-name-input" id="start-time" onClick="WdatePicker()"
                           placeholder="请输入开始时间">
                </div>
                <div class="hospital-name">
                    <input type="text" class="form-control hospital-name-input" id="end-time" onClick="WdatePicker()"
                           placeholder="请输入截止时间">
                </div>
                <div class="search-btn">
                    <button typee="button" class="btn search" id="search">查询结果</button>
                    <button typee="button" class="btn clear" id="clear">清空条件</button>
                </div>
            </div>
        </form>
    </div>
    <div class="table-content">
        <div class="add-and-import">
            <button typee="button" class="btn add" id="add"><i
                    class="glyphicon glyphicon-plus"></i>新增
            </button>
            <button typee="button" class="btn import"><i class="glyphicon glyphicon-import"></i>导入</button>
        </div>

        <div id="logtable" class="hospital-table">
            
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
<#macro html_foot>
    <@p.js src="system/log.js"/>	
    <@p.js src="third/My97DatePicker/WdatePicker.js"/>
</#macro>