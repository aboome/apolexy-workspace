<#include "/layout/baseLayout.ftl">
<#macro html_title>开机动画管理</#macro>
<#macro html_body>
<div class="hospital-wrap" style="padding: 15px 21px;">
    <div class="table-content" style="padding-top: 0;">
        <div class="add-and-import">
            <button type="button" class="btn add" id="add" onclick="addLandingPage()"><i
                    class="glyphicon glyphicon-plus"></i>新增
            </button>
        </div>

        <div id="doctorLandingPageTable" class="hospital-table"></div>

        <div id="patientLandingPageTable" class="hospital-table"></div>
    </div>
    <!-- 删除弹出层 -->
    <div class="zf-modal" id="deleteLayer" style="display:none">
        <p class="deleteInfo modal-body">您确定删除此条记录吗？</p>
    </div>

    <div id="addLayer" style="display:none" class="zf-modal">
    	<form class="form-horizontal" role="form" id="landingpageInfo">
            <div class="form-group">
                <label for="landingPageFilePath" class="control-label col-xs-3">图片</label>
                <div class="col-xs-9">
                	<input type="file" name="landingPageFilePath" id="landingPageFilePath"  class="form-control" name="landingPageFilePath"/>
                </div>
            </div>
            
            <div class="form-group">
                <label for="addSort" class="control-label col-xs-3">排序</label>
                <div class="col-xs-9">
                	<input type="text" class="form-control" id="addSort" placeholder="请输入排序" name="addSort">
                </div>
            </div>
            
            <div class="form-group">
                <label for="addOwner" class="control-label col-xs-3">客户端类型</label>
                <div class="col-xs-9">
	                <select class="form-control" id="addOwner" name="addOwner">
	                    <option value="">请选择客户端类型</option>
	                    <option value="1">医生端</option>
	                    <option value="2">患者端</option>
	                </select>
                </div>
            </div>
    	</form>
    </div>

    <!-- 弹出层 -->
    <div id="detailLayer" style="display:none" class="zf-modal">
    	<div class="modal-body">
        	<img id="landingPageDetail" src=""/>
        </div>
    </div>


</div>
</#macro>
<#macro html_foot>
    <@p.js src="base/landingpage.js"/>
    <@p.js src="third/ajaxfileupload.js"/>
</#macro>