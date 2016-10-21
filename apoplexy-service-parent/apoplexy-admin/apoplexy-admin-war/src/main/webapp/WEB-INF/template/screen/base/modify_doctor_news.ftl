<#include "/layout/baseLayout.ftl">
<#macro html_title>医生端讯息资料管理</#macro>
<#macro html_body>
<div id="modifyLayer" class="search-term add-news">
    <span id="newsId" class="none">${newsId}</span>
    
    <form class="form-horizontal" role="form" id="modifyDoctorNewsForm">

        <div class="form-group">
        	<div class="col-xs-6">
	            <label for="modifyDoctorNewsTitle" class="control-label hospital-name-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;标题</label>
	            <div class="hospital-name">
	                <input type="text" class="form-control hospital-name-input" id="modifyDoctorNewsTitle" placeholder="请输入标题" name="modifyDoctorNewsTitle">
	            </div>
            </div>
        	<div class="col-xs-6">
	            <label for="modifyDoctorSmallLogo" class="control-label hospital-name-label">&nbsp;&nbsp;&nbsp;缩略图</label>
	            <div class="hospital-name">
	                <input type="file" name="modifyDoctorSmallLogo" id="modifyDoctorSmallLogo" class="form-control hospital-name-input"/>
	            </div>
	        </div>
        </div>

        

        <div class="form-group">
        	<div class="col-xs-6">
	            <label for="modifyDoctorNewsSource" class="control-label hospital-name-label">资讯来源</label>
	            <div class="hospital-name">
	                <input type="text" class="form-control hospital-name-input" id="modifyDoctorNewsSource" placeholder="请输入资讯来源" name="modifyDoctorNewsSource">
	            </div>
	        </div>
        	<div class="col-xs-6">
	            <label for="modifyDoctorType" class="control-label hospital-name-label">内容分类</label>
	            <div class="hospital-name">
	                <select id="modifyDoctorType" class="form-control my_select" name="modifyDoctorType">
	                    <option value="">请选择内容分类</option>
	                    <option value="1">图文</option>
	                    <option value="2">视频</option>
	                    <option value="3">PDF</option>
	                </select>
	            </div>
	        </div>
        </div>

        <div class="form-group">
        	<div class="col-xs-6">
	            <label for="modifyDoctorNewsType" class="control-label hospital-name-label">资讯分类</label>
	            <div class="hospital-name">
	                <select id="modifyDoctorNewsType" class="form-control my_select" name="modifyDoctorNewsType">
	                    <option value="">请选择资讯分类</option>
	                    <option value="1">学术知识</option>
	                    <option value="2">新闻资讯</option>
	                </select>
	            </div>
	        </div>
        	<div class="col-xs-6">
	            <label for="modifyDoctorSrc" class="control-label hospital-name-label">资源分类</label>
	            <div class="hospital-name">
	                <select id="modifyDoctorSrc" class="form-control my_select" name="modifyDoctorSrc">
	                    <option value="">请选择资源分类</option>
	                    <option value="0">链接</option>
	                    <option value="1" selected>内容</option>
	                </select>
	            </div>
	        </div>
        </div>
        
		<div class="form-group">
			<div class="col-xs-12">
	            <label for="modifyDoctorSubTitle" class="control-label hospital-name-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;摘要</label>
	            <div class="hospital-name" style="width: 80.8%;">
	                <input type="text" class="form-control" id="modifyDoctorSubTitle" placeholder="请输入摘要" name="modifyDoctorSubTitle">
	            </div>
	        </div>
        </div>
    </form>
    

    <h5 class="h5 h5Info">资讯内容</h5>
    <div class="plainInfo" id="plainInfoText">
        <script id="newsContent" type="text/plain" style="width=100%;min-height:200px;"></script>
    </div>

    <div id="plainInfoUrl" class="none">
        <div class="form-group col-xs-12">
            <input type="text" class="form-control" id="modifyDoctorNewsUrl" placeholder="请输入资讯内容链接">
        </div>
    </div>

	<div class="btnInfo">
	    <button type="button" class="btn add" id="save" onclick="saveDoctorNews()"><i class="glyphicon glyphicon-save"></i> 保存</button>
	    <button type="button" class="btn add" id="cancel" onclick="cancelSave()"><i class="glyphicon glyphicon-repeat"></i> 取消</button>
	</div>
</div>
</#macro>
<#macro html_foot>
    <@p.js src="base/modify_doctor_news.js"/>
    <@p.js src="third/My97DatePicker/WdatePicker.js"/>
    <@p.js src="third/ajaxfileupload.js"/>
    <@p.js src="third/uedit/ueditor.config.js"/>
    <@p.js src="third/uedit/ueditor.all.min.js"/>
    <@p.js src="third/uedit/lang/zh-cn/zh-cn.js"/>
</#macro>