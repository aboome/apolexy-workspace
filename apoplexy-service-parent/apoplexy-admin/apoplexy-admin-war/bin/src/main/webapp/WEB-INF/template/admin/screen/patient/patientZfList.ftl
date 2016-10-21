<#include "/admin/layout/baseLayout.ftl">
<#macro html_title>查询中风筛查列表</#macro>
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
        <div id="patientZfListtable" class="hospital-table">    
        </div>
    </div>
    <!-- 查看详情弹出层 -->
    <div id="detailLayer" style="display:none" class="zf-modal">
        <form class="form-horizontal" role="form">
            <label class="control-label zf-name-label">基本信息</label>
            <div class="form-group">
                <label class="control-label zf-name-label">用户名</label>
                <label id="detailUserName"></label>
            </div>
            <div class="form-group">
                <label class="control-label zf-name-label">年龄</label>
                <label id="detailAge"></label>
            </div>
            <div class="form-group">
                <label class="control-label zf-name-label">性别</label>
                <label id="detailSex"></label>
            </div>
            <div class="form-group">
                <label class="control-label zf-name-label">记录时间</label>
                <label id="detailCreateTime"></label>
            </div>
            <label class="control-label zf-name-label">中风急救Fast表</label>
            <div id="zfDetailTable" class="hospital-table"></div>
        </form>
    </div>
</div>
</#macro>
<#macro html_foot>
    <@p.js src="patient/patientZfList.js"/>	
    <@p.js src="third/My97DatePicker/WdatePicker.js"/>
</#macro>