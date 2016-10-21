<#include "/admin/layout/baseLayout.ftl">
<#macro html_title>查询体征数据列表</#macro>
<#macro html_body>
<div class="hospital-wrap">
    <div class="search-term">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <div class="hospital-name">
                    <input type="text" class="form-control hospital-name-input" id="patient-name"
                           placeholder="患者名称">
                </div>
                <div class="hospital-name">
                    <input type="text" class="form-control hospital-name-input" id="area-name"
                           placeholder="区域">
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
                    <button type="button" class="btn search" id="search">查询结果</button>
                    <button type="button" class="btn clear" id="clear">清空条件</button>
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