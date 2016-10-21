<#include "/admin/layout/baseLayout.ftl">
<#macro html_title>AST病例管理</#macro>
<#macro html_body>
<div class="hospital-wrap">
    <div class="search-term">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <div class="hospital-name">
                    <input type="text" class="form-control hospital-name-input" id="doctor-name"
                           placeholder="请输入医生名称">
                </div>
                <div class="hospital-name">
                    <input type="text" class="form-control hospital-name-input" id="hospital-name"
                           placeholder="请输入医院名称">
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
                    <button type="button" class="btn search" id="search">查询</button>
                    <button type="button" class="btn clear" id="clear">清空</button>
                </div>
            </div>
        </form>
    </div>
    <div class="table-content">
        

        <div id="casemanagetable" class="hospital-table">
            
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
        
    </div>
     <!-- 弹出层 -->
    <div id="deleteLayer" style="display:none">
        <ul class="input-group">
            <label>您确定删除此条AST管理信息吗？</label>
        </ul>
    </div>
    
    <#--详情弹出层-->
    <div id="detailLayer" style="display:none" class="zf-modal">

    </div>
</div>
</#macro>
<#macro html_foot>
    <@p.js src="doctor/casemanage.js"/>	
    <@p.js src="third/My97DatePicker/WdatePicker.js"/>
</#macro>