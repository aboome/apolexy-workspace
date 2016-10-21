<#include "/layout/baseLayout.ftl">
<#macro html_title>下载量统计管理</#macro>
<#macro html_body>

<div class="hospital-wrap">
    <div class="search-term">
        <form class="form-horizontal" role="form">

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
		               	<button type="button" class="btn btn-block search" id="search">查询</button>
		            </div>
		            <div class="col-xs-1">
		                <button type="button" class="btn btn-block clear" id="clear">清空</button>
		            </div>
	        	</div>
            </div>

        </form>
    </div>
	<div style="margin: 60px 21px;" class="clearfix">
	    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
	    <div id="doctorDownload"  class="zf-charts col-xs-5"></div>
	
	    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
	    <div id="patientDownload"  class="zf-charts col-xs-5"></div>
	    
	    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
	    <div id="doctorRegister"  class="zf-charts col-xs-5"></div>
	    
	    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
	    <div id="patientRegister"  class="zf-charts col-xs-5"></div>
    </div>
</div>
</#macro>
<#macro html_foot>
    <@p.js src="statics/download_statistics.js"/>
    <@p.js src="third/My97DatePicker/WdatePicker.js"/>
    <@p.js src="third/echarts.min.js"/>
</#macro>