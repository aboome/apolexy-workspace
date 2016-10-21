<#include "/layout/baseLayout.ftl">
<#macro html_title>查询中风筛查列表</#macro>
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
        <div id="patientZfListtable" class="hospital-table">    
        </div>
    </div>
    <!-- 查看详情弹出层 -->
    <div id="detailLayer" style="display:none" class="padding-zf">
    	<div class="zf-top">
    		<h6 class="detail-layer-bg">基本信息</h6>
    		<ul>
				<li class="clearfix">
					<p class="pull-left font-size-14 col-xs-10">用户名</p>
					<p id="detailUserName" class="pull-right font-size-14 padding-right col-xs-2"></p>
				</li>
				<li class="clearfix">
					<p class="pull-left font-size-14 col-xs-10">年龄</p>
					<p id="detailAge" class="pull-right font-size-14 padding-right col-xs-2"></p>
				</li>
				<li class="clearfix">
					<p class="pull-left font-size-14 col-xs-10">性别</p>
					<p id="detailSex" class="pull-right font-size-14 padding-right col-xs-2"></p>
				</li>
			</ul>	
    	</div>
    	
    	<div class="zf-top clearfix">
    		<h6 class="detail-layer-bg">记录时间</h6>
    		<p id="detailCreateTime" class="font-size-14 col-xs-10"></p>
    	</div>
    	
    	<div class="zf-top">
    		<h6 class="detail-layer-bg">中风急救Fast表</h6>
    		<p id="zfDetailTable" class="font-size-14"></p>
    	</div>
    
    </div>
</div>
</#macro>
<#macro html_foot>
    <@p.js src="patient/patientZfList.js"/>	
    <@p.js src="third/My97DatePicker/WdatePicker.js"/>
</#macro>