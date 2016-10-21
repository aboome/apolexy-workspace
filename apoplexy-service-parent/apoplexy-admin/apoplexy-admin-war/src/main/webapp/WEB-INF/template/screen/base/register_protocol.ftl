<#include "/layout/baseLayout.ftl">
<#macro html_title>注册协议管理</#macro>
<#macro html_body>
<div class="regProtocolCon">

	<ul id="zfRegProtocolTab" class="nav nav-tabs">
		<li class="active">
			<a href="#doctor" data-toggle="tab">
				医生端注册协议
			</a>
		</li>
		<li>
			<a href="#patient" data-toggle="tab">
				患者端注册协议
			</a>
		</li>
	</ul>
	
	<div id="zfRegProtocolTabContent" class="tab-content">
		<div class="regprotocol tab-pane fade in active" id="doctor">
			<div class="regprotocol-title">
				<p class="time">修改时间：<span class="modify-time" id="doctorLastUpdateTime"></span></p>
                <a href="javascript:;" onclick="saveDoctorProtocol()" class="edit"><i
                        class="glyphicon glyphicon-floppy-save"></i> 保存</a>
			</div>
			<div class="regprotocol-content" id="doctor-regprotocol-content">
                <script id="doctorProtocolContent" type="text/plain" style="height:100px;"></script>
            </div>
		</div>
		<div class="regprotocol tab-pane fade" id="patient">
			<div class="regprotocol-title">
				<p class="time">修改时间：<span class="modify-time" id="patientLastUpdateTime"></span></p>
                <a href="javascript:;" onclick="savePatientProtocol()" class="edit"><i
                        class="glyphicon glyphicon-floppy-save"></i> 保存</a>
			</div>
			<div class="regprotocol-content" id="patient-regprotocol-content">
                <script id="patientProtocolContent" type="text/plain" style="height:100px;"></script>
            </div>
		</div>
	</div>
	
  <!--  <div class="table-content" style="padding-top:0">
        <div class="regprotocol">
            <div class="regprotocol-title clearfix">
                <p class="pull-left">医生端注册协议</p>
                <p class="time">修改时间：<span class="modify-time" id="doctorLastUpdateTime"></span></p>
                <a href="javascript:;" onclick="saveDoctorProtocol()" class="edit"><i
                        class="glyphicon glyphicon-floppy-save"></i> 保存</a>
            </div>
            <div class="regprotocol-content" id="doctor-regprotocol-content">
                <script id="doctorProtocolContent" type="text/plain" style="height:100px;"></script>
            </div>
        </div>
        <div class="regprotocol regprotocol-patient">
            <div class="regprotocol-title clearfix">
                <p class="pull-left">患者端注册协议</p>
                <p class="time">修改时间：<span class="modify-time" id="patientLastUpdateTime"></span></p>
                <a href="javascript:;" onclick="savePatientProtocol()" class="edit"><i
                        class="glyphicon glyphicon-floppy-save"></i> 保存</a>
            </div>
            <div class="regprotocol-content" id="doctor-regprotocol-content">
                <script id="patientProtocolContent" type="text/plain" style="height:100px;"></script>
            </div>
        </div>
    </div> -->

    <!-- 更新弹出层 -->
    <div id="updateLayer" style="display:none" class="zf-modal">
        <p class="deleteInfo modal-body">您确定要保存该条注册协议吗？</p>
    </div>
</div>
</#macro>
<#macro html_foot>
    <@p.js src="base/adminregisterprotocol.js"/>
    <@p.js src="third/uedit/ueditor.config.js"/>
    <@p.js src="third/uedit/ueditor.all.js"/>
    <@p.js src="third/uedit/lang/zh-cn/zh-cn.js"/>
</#macro>
