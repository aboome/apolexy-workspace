<#include "/admin/layout/baseLayout.ftl">
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
                
                <div class="search-btn">
                    <button type="button" class="btn search" id="search">查询结果</button>
                    <button type="button" class="btn clear" id="clear">清空条件</button>
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
        </div>

        <div id="admindoctortable" class="hospital-table">
            
        </div>
    </div>
    <div class="footer">
        <div class="page-term form-horizontal">
            <label for="size" class="control-label size-label">每页显示：</label>
            <div class="page-size">
                <select class="form-control input-sm" id="size">
                    <option value="10">10</option>
                    <option value="20">20</option>
                    <option value="30">30</option>
                    <option value="40">40</option>
                    <option value="50">50</option>
                </select>
            </div>
            <label for="size" class="control-label size-label">条</label>
            <div class="page-count">
                全部记录共：<span class="count-num">550</span>条
            </div>
        </div>
        <div class="page-content">
            <ul class="pagination">
                <li><a href="#">&laquo;</a></li>
                <li class="active"><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li><a href="#">&raquo;</a></li>
            </ul>
        </div>
    </div>

 <!-- 弹出层 -->
     <div id="deleteLayer" style="display:none" class="zf-modal">
        <p class="deleteInfo">您确定要删除该条记录吗？</p>
    </div>
    
       <!-- 新增弹出层 -->
    <div id="addLayer" style="display:none" class="zf-modal">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="addDoctorName" class="control-label zf-name-label">医生名称</label>
                <input type="text" class="form-control zf-layer-input" id="addDoctorName" placeholder="请输入医生名称">
            </div>
            
            <div class="form-group">
                <label for="addHospital" class="control-label zf-name-label">所在医院</label>
                <input type="text" class="form-control zf-layer-input" id="addHospital" placeholder="请输入所在医院">
            </div>
            
            <div class="form-group">
                <label for="addDepartment" class="control-label zf-name-label">科室</label>
                    <input type="text" class="form-control zf-layer-input" id="addDepartment" placeholder="请输入科室">
            </div>
            
                <div class="hospital-name">
                 <label for="addSex" class="control-label zf-name-label">性别</label>
                <select  id="addSex">
                 <option value="">请选择性别</option>
	                <option value="1">男</option>
	                <option value="0">女</option>
                </select>
                </div>
                
             <div class="form-group">
                <label for="addPhone" class="control-label zf-name-label">电话</label>
                <input type="text" class="form-control zf-layer-input" id="addPhone" placeholder="请输入电话">
            </div>  
            
            <div class="form-group">
                <label for="addTitle" class="control-label zf-name-label">职称</label>
                <input type="text" class="form-control zf-layer-input" id="addTitle" placeholder="请输入职称">
            </div>   
            <div class="form-group">
                <label for="addJob" class="control-label zf-name-label">工作岗位</label>
                <input type="text" class="form-control zf-layer-input" id="addJob" placeholder="请输入工作岗位">
            </div>
            <div class="form-group">
                <label for="addEffectiveTime" class="control-label zf-name-label">有效期</label>
                <input type="text" class="form-control zf-layer-input" id="addEffectiveTime" onClick="WdatePicker()" placeholder="请输有效期">
            </div>
            
                   <div class="form-group">
                <label for="addEmail" class="control-label zf-name-label">邮箱</label>
                <input type="text" class="form-control zf-layer-input" id="addEmail" placeholder="请输入电子邮箱">
            </div>  
        </form>
    </div>
    
    
    
        <!-- 修改弹出层 -->
    <div id="modifyLayer" style="display:none" class="zf-modal">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="modifyDoctorName" class="control-label zf-name-label">医生名称</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="modifyDoctorName"
                           placeholder="请输入医生名称">
                </div>
            </div>
            <div class="form-group">
                <label for="modifyHospital" class="control-label zf-name-label">所在医院</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="modifyHospital"
                           placeholder="请输入所在医院">
                </div>
            </div>
            <div class="form-group">
                <label for="modifyDepartment" class="control-label zf-name-label">科室</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="modifyDepartment"
                           placeholder="请输入科室">
                </div>
            </div>
            
                <div class="form-group">
                 <label for="modifySex" class="control-label zf-name-label">性别</label>
                <select  id="modifySex">
                 <option value="">请选择性别</option>
	                <option value="1">男</option>
	                <option value="0">女</option>
                </select>
                </div>
                
            <div class="form-group">
                <label for="modifyPhone" class="control-label zf-name-label">电话</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="modifyPhone"
                           placeholder="请输入电话">
                </div>
            </div>
            <div class="form-group">
                <label for="modifyTitle" class="control-label zf-name-label">职称</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="modifyTitle"
                           placeholder="请输入职称">
                </div>
            </div>
            <div class="form-group">
                <label for="modifyJob" class="control-label zf-name-label">工作岗位</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="modifyJob"
                           placeholder="请输入工作岗位">
                </div>
            </div>
            <div class="form-group">
                <label for="modifyEffectiveTime" class="control-label zf-name-label">有效日期</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="modifyEffectiveTime" onClick="WdatePicker()"
                           placeholder="请输入有效日期">
                </div>
            </div>
            
                     <div class="form-group">
                <label for="modifyEmail" class="control-label zf-name-label">电子邮箱</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="modifyEmail" 
                    placeholder="请输入电子邮箱">
                </div>
            </div>
            
        </form>
    </div>
    
</div>
</#macro>
<#macro html_foot>
    <@p.js src="base/admindoctor.js"/>	
    <@p.js src="third/My97DatePicker/WdatePicker.js"/>
</#macro>