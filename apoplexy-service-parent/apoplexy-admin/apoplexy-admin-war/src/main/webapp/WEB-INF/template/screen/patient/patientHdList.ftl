<#include "/layout/baseLayout.ftl">
<#macro html_title>查询体征数据列表</#macro>
<#macro html_body>
<div class="hospital-wrap">
    <div class="search-term">
    	<form class="form-horizontal" role="form">
        	<div class="form-group">
                <label for="patient-name" class="control-label hospital-name-label">患者名称:</label>
                <div class="hospital-name">
                    <input type="text" class="form-control hospital-name-input" id="patient-name" placeholder="请输入患者名称">
                </div>
             
                <label for="area-name" class="control-label hospital-name-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;区域:</label>
                <div class="hospital-name">
                    <input type="text" class="form-control hospital-name-input" id="area-name" placeholder="请输入区域">
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
          <div id="patientHdListtable" class="hospital-table"></div>
            
        </div>
</div>
</#macro>
<#macro html_foot>
    <@p.js src="patient/patientHdList.js"/>	
    <@p.js src="third/My97DatePicker/WdatePicker.js"/>
</#macro>