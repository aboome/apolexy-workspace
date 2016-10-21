<#include "/layout/baseLayout.ftl">
<#macro html_title>用户管理</#macro>
<#macro html_body>
<div class="hospital-wrap">
    <div class="search-term">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="hospital-name" class="control-label hospital-name-label">管理员用户名：</label>
                <div class="hospital-name">
                    <input type="text" class="form-control hospital-name-input" id="admin-name" placeholder="请输入管理员用户名">
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
    <div class="table-content">
        <div class="add-and-import">
            <button type="button" class="btn add" id="add" onclick="addMember()"><i
                    class="glyphicon glyphicon-plus"></i>新增
            </button>
        </div>

        <div id="adminTable" class="hospital-table">

        </div>
    </div>

    <!-- 删除弹出层 -->
    <div id="deleteLayer" style="display:none" class="zf-modal">
        <p class="deleteInfo modal-body">您确定要删除该条记录吗？</p>
    </div>

    <!-- 解锁弹出层 -->
    <div id="unlockLayer" style="display:none" class="zf-modal">
        <p class="deleteInfo modal-body">您确定要解锁该用户吗？</p>
    </div>

    <!-- 绑定弹出层 -->
    <div id="bindLayer" style="display:none" class="zf-modal">
    	<form class="form-horizontal" role="form">
    		<div class="form-group">
	        	<label for="roleInfo" class="control-label col-xs-4">绑定角色</label>
	        	<div class="col-xs-8">
	        		<select id="roleInfo" class="form-control " >
	            		<#list roleInfoList as roleInfo>
	            		<option value="${roleInfo.id}">${roleInfo.roleName}</option>
	            		</#list>
	        		</select>
	       		</div>
       		</div>
      	</form>
    </div>

    <!-- 新增弹出层 -->
    <div id="addLayer" style="display:none" class="zf-modal">
        <form class="form-horizontal" role="form" id="addAdmin">
            <div class="form-group">
                <label for="addAdminName" class="control-label zf-name-label col-xs-4">用户名</label>
                <div class="zf-layer-input col-xs-8">
                    <input type="text" class="form-control zf-name-input" id="addAdminName" name="addAdminName"
                           placeholder="请输入管理员的用户名">
                </div>
            </div>
            <div class="form-group">
                <label for="addPassword" class="control-label zf-name-label col-xs-4">登录密码</label>
                <div class="zf-layer-input col-xs-8">
                    <input type="password" class="form-control zf-name-input" id="addPassword" name="addPassword"
                           placeholder="请输入管理员的登录密码">
                </div>
            </div>
            <div class="form-group">
                <label for="addPhone" class="control-label zf-name-label col-xs-4">联系电话</label>
                <div class="zf-layer-input col-xs-8">
                    <input type="text" class="form-control zf-name-input" id="addPhone" name="addPhone"
                           placeholder="请输入管理员的联系电话">
                </div>
            </div>
            <div class="form-group">
                <label for="addEmail" class="control-label zf-name-label col-xs-4">电子邮箱</label>
                <div class="zf-layer-input col-xs-8">
                    <input type="text" class="form-control zf-name-input" id="addEmail" name="addEmail"
                           placeholder="请输入管理员的电子邮箱">
                </div>
            </div>
            <div class="form-group">
                <label for="addJob" class="col-xs-4 control-label zf-name-label col-xs-4">职务</label>
                <div class="zf-layer-input col-xs-8">
                    <input type="text" class="form-control zf-name-input" id="addJob" name="addJob"
                           placeholder="请输入管理员的职务">
                </div>
            </div>
            <div class="form-group">
                <label for="addDesc" class="control-label zf-name-label col-xs-4">备注</label>
                <div class="zf-layer-input col-xs-8">
                    <input type="text" class="form-control zf-name-input" id="addDesc" name="addDesc"
                           placeholder="请输入管理员的备注信息">
                </div>
            </div>
            <div class="form-group">
                <label for="addRealName" class="control-label zf-name-label col-xs-4">真实姓名</label>
                <div class="zf-layer-input col-xs-8">
                    <input type="text" class="form-control zf-name-input" id="addRealName" name="addRealName"
                           placeholder="请输入管理员的真实姓名">
                </div>
            </div>
        </form>
    </div>

    <!-- 修改弹出层 -->
    <div id="modifyLayer" style="display:none" class="zf-modal">
        <form class="form-horizontal" role="form" id="modifyAdmin">
            <div class="form-group">
                <label for="modifyAdminName" class="control-label zf-name-label col-xs-4">用户名</label>
                <div class="zf-layer-input col-xs-8">
                    <input type="text" class="form-control zf-name-input" id="modifyAdminName" name="modifyAdminName"
                           placeholder="请输入管理员的用户名" disabled>
                </div>
            </div>
            <div class="form-group">
                <label for="modifyPhone" class="control-label zf-name-label col-xs-4">联系电话</label>
                <div class="zf-layer-input col-xs-8">
                    <input type="text" class="form-control zf-name-input" id="modifyPhone" name="modifyPhone"
                           placeholder="请输入管理员的联系电话">
                </div>
            </div>
            <div class="form-group">
                <label for="modifyEmail" class="control-label zf-name-label col-xs-4">电子邮箱</label>
                <div class="zf-layer-input col-xs-8">
                    <input type="text" class="form-control zf-name-input" id="modifyEmail" name="modifyEmail"
                           placeholder="请输入管理员的电子邮箱">
                </div>
            </div>
            <div class="form-group">
                <label for="modifyJob" class="control-label zf-name-label col-xs-4">职务</label>
                <div class="zf-layer-input col-xs-8">
                    <input type="text" class="form-control zf-name-input" id="modifyJob" name="modifyJob"
                           placeholder="请输入管理员的职务信息">
                </div>
            </div>
            <div class="form-group">
                <label for="modifyDesc" class="control-label zf-name-label col-xs-4">备注</label>
                <div class="zf-layer-input col-xs-8">
                    <input type="text" class="form-control zf-name-input" id="modifyDesc" name="modifyDesc"
                           placeholder="请输入管理员的备注信息">
                </div>
            </div>
            <div class="form-group">
                <label for="modifyRealName" class="control-label zf-name-label col-xs-4">真实姓名</label>
                <div class="zf-layer-input col-xs-8">
                    <input type="text" class="form-control zf-name-input" id="modifyRealName" name="modifyRealName"
                           placeholder="请输入管理员的真实姓名">
                </div>
            </div>
        </form>
    </div>

    <!-- 重置密码弹出层 -->
    <div id="resetPasswordLayer" style="display:none" class="zf-modal">
        <form class="form-horizontal" role="form" id="modifyPasswordInfo">
            <div class="form-group">
                <label for="newPassword" class="control-label zf-name-label col-xs-4">新密码</label>
                <div class="zf-layer-input col-xs-8">
                    <input type="password" class="form-control zf-name-input" id="newPassword" name="newPassword"
                           placeholder="请输入新密码">
                </div>
            </div>
            <div class="form-group">
                <label for="repeatPassword" class="control-label zf-name-label col-xs-4">确认密码</label>
                <div class="zf-layer-input col-xs-8">
                    <input type="password" class="form-control zf-name-input" id="repeatPassword" name="repeatPassword"
                           placeholder="请输入确认密码">
                </div>
            </div>
        </form>
    </div>

</div>
</#macro>
<#macro html_foot>
    <@p.js src="system/admin.js"/>
    <@p.js src="third/md5.js"/>
</#macro>