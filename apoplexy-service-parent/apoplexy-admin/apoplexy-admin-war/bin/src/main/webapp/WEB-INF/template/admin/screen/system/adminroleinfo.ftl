<#include "/admin/layout/baseLayout.ftl">
<#macro html_title>角色管理</#macro>
<#macro html_body>
<div class="hospital-wrap">
    <div class="search-term">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="hospital-name" class="control-label hospital-name-label">角色名称：</label>
                <div class="hospital-name">
                    <input type="text" class="form-control hospital-name-input" id="admin-role-name"
                           placeholder="请输入角色名称">
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
            <button type="button" class="btn add" id="add" onclick="addRoleInfo()"><i class="glyphicon glyphicon-plus"></i>新增
            </button>
        </div>
        <div id="adminroleinfotable" class="hospital-table">
            
        </div>
    </div>
    <!-- 删除弹出层 -->
    <div id="deleteLayer" style="display:none" class="zf-modal">
        <p class="deleteInfo">您确定要删除该条记录吗？</p>
    </div>
    <!-- 新增弹出层 -->
    <div id="addLayer" style="display:none" class="zf-modal">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="addRoleName" class="control-label zf-name-label">角色名称</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="addRoleName"
                           placeholder="请输入角色名称">
                </div>
            </div>
            <div class="form-group">
                <label for="addDesc" class="control-label zf-name-label">备注</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="addDesc"
                           placeholder="请输入角色的备注">
                </div>
            </div>
        </form>
    </div>

    <!-- 修改弹出层 -->
    <div id="modifyLayer" style="display:none" class="zf-modal">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="modifyRoleName" class="control-label zf-name-label">角色名称</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="modifyRoleName"
                           placeholder="请输入角色名称">
                </div>
            </div>
            <div class="form-group">
                <label for="modifyDesc" class="control-label zf-name-label">备注</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="modifyDesc"
                           placeholder="请输入角色的备注">
                </div>
            </div>
        </form>
    </div>
</div>
</#macro>
<#macro html_foot>
    <@p.js src="system/adminroleinfo.js"/>	
</#macro>