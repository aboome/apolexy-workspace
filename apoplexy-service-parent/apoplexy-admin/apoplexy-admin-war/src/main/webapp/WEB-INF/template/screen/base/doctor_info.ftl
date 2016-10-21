<#include "/layout/baseLayout.ftl">
<#macro html_title>医生数据管理</#macro>
<#macro html_body>
<div class="hospital-wrap">
    <div class="search-term">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="hospital-name" class="control-label hospital-name-label">医生名称：</label>
                <div class="hospital-name">
                    <input type="text" class="form-control hospital-name-input" id="admin-doctor-name"
                           placeholder="请输入医生名称">
                </div>
                <label for="hospital-name" class="control-label hospital-name-label">医院名称：</label>
                <div class="hospital-name">
                    <input type="text" class="form-control hospital-name-input" id="admin-doctorhospital-name"
                           placeholder="请输入医院名称">
                </div>

                <div class="search-btn row">
                    <div class="col-xs-1">
                        <button type="button" class="btn search" id="search">查询</button>
                    </div>
                    <div class="col-xs-1">
                        <button type="button" class="btn clear" id="clear">清空</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div class="table-content">
        <div class="add-and-import">
            <button typee="button" class="btn add" id="add" onclick="addDoctor()"><i
                    class="glyphicon glyphicon-plus"></i>新增
            </button>
            <button typee="button" class="btn import"><i class="glyphicon glyphicon-import"></i>导入</button>
            <button typee="button" class="btn download"><a href="${ctx}/resources/template/doctor.xlsx"><i class="fa fa-download"></i>模板下载</a></button>

            <!-- 导入弹出层 -->
            <div id="exportDoctor" class="none zf-modal">
            	<form class="form-horizontal" role="form">
            		<div class="form-group">
            			<label for="filePath" class="control-label col-xs-3">Excel文件</label>
            			<div class="col-xs-9">
            				<input  class="inputfile dis-no form-control" type="file" name="filePath" onchange="filePathOnchange('#filePath')" id="filePath" />
            			</div>
            		</div>
            	</form>

			</div>
            
        </div>

        <div id="admindoctortable" class="hospital-table">
            
        </div>
    </div>

    <!-- 删除弹出层 -->
     <div id="deleteLayer" style="display:none" class="zf-modal">
        <p class="deleteInfo modal-body">您确定要删除该条记录吗？</p>
    </div>
    
     <!-- 新增弹出层 -->
    <div id="addLayer" style="display:none" class="zf-modal">
        <form class="form-horizontal" id="doctorInfo" role="form">
            <div class="form-group">
                <label for="addDoctorName" class="control-label col-xs-4">医生名称</label>
                <div class="col-xs-8">
                	<input type="text" class="form-control" id="addDoctorName" name="addDoctorName" placeholder="请输入医生名称">
                </div>
            </div>
            
            <div class="form-group">
                <label for="addHospital" class="control-label col-xs-4">所在医院</label>
                <div class="col-xs-8">
                 <select class="form-control" id="addHospital" name="addHospital">
                        <option value="">请选择所在医院</option>
                    </select>
                </div>
            </div>
            
            <div class="form-group">
                <label for="addDepartment" class="control-label col-xs-4">科室</label>
                <div class="col-xs-8">
                    <input type="text" class="form-control" id="addDepartment" name="addDepartment" placeholder="请输入科室">
                </div>
            </div>
            
            <div class="form-group">
                <label for="addSex" class="control-label col-xs-4">性别</label>
                <div class="col-xs-8">
	                <select class="form-control" id="addSex" name="addSex">
	                 	<option value="">请选择性别</option>
		                <option value="1">男</option>
		                <option value="0">女</option>
	                </select>
	            </div>
            </div>
                
             <div class="form-group">
                <label for="addPhone" class="control-label col-xs-4">电话</label>
                <div class="col-xs-8">
                	<input type="text" class="form-control" id="addPhone" name="addPhone" placeholder="请输入电话">
                </div>
            </div>  
            
            <div class="form-group">
                <label for="addTitle" class="control-label col-xs-4">职称</label>
                <div class="col-xs-8">
                	<input type="text" class="form-control" id="addTitle" name="addTitle" placeholder="请输入职称">
                </div>
            </div>   
            <div class="form-group">
                <label for="addJob" class="control-label col-xs-4">工作岗位</label>
                <div class="col-xs-8">
                	<input type="text" class="form-control" id="addJob" name="addJob" placeholder="请输入工作岗位">
                </div>
            </div>
            <div class="form-group">
                <label for="addEffectiveTime" class="control-label col-xs-4">有效期</label>
                <div class="col-xs-8">
                	<input type="text" class="Wdate form-control" id="addEffectiveTime" name="addEffectiveTime" onClick="WdatePicker()" placeholder="请选择有效期">
                </div>
            </div>
            
            <div class="form-group">
                <label for="addEmail" class="control-label col-xs-4">邮箱</label>
                <div class="col-xs-8">
                	<input type="text" class="form-control" id="addEmail" name="addEmail" placeholder="请输入电子邮箱">
                </div>
            </div>

            <div class="form-group">
                <label for="addDoctorAvatarImage" class="control-label col-xs-4">头像</label>
                <div class="col-xs-8">
                    <input type="file" name="addDoctorAvatarImage" class="form-control" id="addDoctorAvatarImage"/>
                </div>
            </div>
        </form>
    </div>

        <!-- 修改弹出层 -->
    <div id="modifyLayer" style="display:none" class="zf-modal">
        <form class="form-horizontal" role="form" id="modifyDoctor">
            <div class="form-group">
                <label for="modifyDoctorName" class="control-label col-xs-4">医生名称</label>
                <div class="col-xs-8">
                    <input type="text" class="form-control" id="modifyDoctorName" name="modifyDoctorName"
                           placeholder="请输入医生名称">
                </div>
            </div>
            <div class="form-group">
                <label for="modifyHospital" class="control-label col-xs-4">所在医院</label>
                <div class="col-xs-8">
                    <select class="form-control" id="modifyHospital" name="modifyHospital">
                        <option value="">请选择所在医院</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="modifyDepartment" class="control-label col-xs-4">科室</label>
                <div class="col-xs-8">
                    <input type="text" class="form-control" id="modifyDepartment" name="modifyDepartment"
                           placeholder="请输入科室">
                </div>
            </div>
            
            <div class="form-group">
                 <label for="modifySex" class="control-label col-xs-4">性别</label>
                 <div class="col-xs-8">
	                <select class="form-control" id="modifySex" name="modifySex">
	                 	<option value="">请选择性别</option>
		                <option value="1">男</option>
		                <option value="0">女</option>
	                </select>
                 </div>
             </div>
                
            <div class="form-group">
                <label for="modifyPhone" class="control-label col-xs-4">电话</label>
                <div class="col-xs-8">
                    <input type="text" class="form-control" id="modifyPhone" name="modifyPhone"
                           placeholder="请输入电话">
                </div>
            </div>
            <div class="form-group">
                <label for="modifyTitle" class="control-label col-xs-4">职称</label>
                <div class="col-xs-8">
                    <input type="text" class="form-control" id="modifyTitle" name="modifyTitle"
                           placeholder="请输入职称">
                </div>
            </div>
            <div class="form-group">
                <label for="modifyJob" class="control-label col-xs-4">工作岗位</label>
                <div class="col-xs-8">
                    <input type="text" class="form-control" id="modifyJob" name="modifyJob"
                           placeholder="请输入工作岗位">
                </div>
            </div>
            <div class="form-group">
                <label for="modifyEffectiveTime" class="control-label col-xs-4">有效日期</label>
                <div class="col-xs-8">
                    <input type="text" class="form-control Wdate" id="modifyEffectiveTime" name="modifyEffectiveTime" onClick="WdatePicker()"
                           placeholder="请输入有效日期">
                </div>
            </div>
            
            <div class="form-group">
                <label for="modifyEmail" class="control-label col-xs-4">电子邮箱</label>
                <div class="col-xs-8">
                    <input type="text" class="form-control" id="modifyEmail" name="modifyEmail"
                    placeholder="请输入电子邮箱">
                </div>
            </div>

            <div class="form-group">
                <label for="modifyDoctorAvatar" class="control-label col-xs-4">头像</label>
                <div class="col-xs-8">
                    <input type="file" name="modifyDoctorAvatarImage" class="form-control" id="modifyDoctorAvatarImage"/>
                </div>
            </div>

        </form>
    </div>

    <!-- 弹出层 -->
    <div id="detailLayer" style="display:none" class="zf-modal">
        <div class="modal-body">
            <img id="doctorAvatar" src=""/>
        </div>
    </div>

</div>
</#macro>
<#macro html_foot>
    <@p.js src="base/doctor.js"/>
    <@p.js src="third/My97DatePicker/WdatePicker.js"/>
    <@p.js src="third/ajaxfileupload.js"/>
</#macro>