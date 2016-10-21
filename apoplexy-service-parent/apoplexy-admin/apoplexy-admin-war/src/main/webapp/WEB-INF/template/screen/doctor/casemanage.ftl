<#include "/layout/baseLayout.ftl">
<#macro html_title>AST病例管理</#macro>
<#macro html_body>
<div class="hospital-wrap">
    <div class="search-term">
    	<form class="form-horizontal" role="form">
        	<div class="form-group">
                <label for="doctor-name" class="control-label hospital-name-label">医生名称:</label>
                <div class="hospital-name">
                    <input type="text" class="form-control hospital-name-input" id="doctor-name"
                           placeholder="请输入医生名称">
                </div>
             
                <label for="hospital-name" class="control-label hospital-name-label">医院名称:</label>
                <div class="hospital-name">
                    <input type="text" class="form-control hospital-name-input" id="hospital-name"
                           placeholder="请输入医院名称">
                </div>
                
                <div class="search-btn row">
	                <div class="col-xs-1">
	                	<button type="button" class="btn btn-block search" id="search">查询</button>
	                </div>
            	</div>
             </div>
             <div class="form-group">
				<label class="control-label hospital-name-label" for="start-time">开始时间:</label>
				<div class="hospital-name">
					<input class="Wdate form-control" style="border: 1px solid #ddd;" id="start-time" type="text" onClick="WdatePicker()" placeholder="请选择开始时间">
				</div>
			 
				<label class="control-label hospital-name-label" for="end-time">截止时间:</label>
				<div class="hospital-name">
					<input class="Wdate form-control" style="border: 1px solid #ddd;" id="end-time" type="text" onClick="WdatePicker()" placeholder="请输入截止时间">
				</div>
				
				<div class="search-btn row">
	                <div class="col-xs-1">
	                    <button type="button" class="btn btn-block clear" id="clear">清空</button>
	                </div>
	            </div>
			 </div>
        </form>
  
    </div>
    <div class="table-content">
        

        <div id="casemanagetable" class="hospital-table">
            
        </div>
    </div>
    
     <!-- 弹出层 -->
    <div id="deleteLayer" style="display:none" class="zf-modal">
        <ul class="deleteInfo modal-body">
            	您确定删除此条AST管理信息吗？
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