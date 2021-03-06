<#include "/layout/baseLayout.ftl">
<#macro html_title>角色管理</#macro>
<#macro html_body>
<div class="hospital-wrap">
    <div class="search-term">

        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="admin-role-name" class="control-label hospital-name-label">角色名称：</label>
                <div class="hospital-name">
                    <input typee="text" class="form-control hospital-name-input" id="admin-role-name" placeholder="请输入角色名称">
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
            <button type="button" class="btn add" id="add" onclick="addRoleInfo()"><i
                    class="glyphicon glyphicon-plus"></i>新增
            </button>
        </div>
        <div id="adminroleinfotable" class="hospital-table">

        </div>
    </div>
    <!-- 删除弹出层 -->
    <div id="deleteLayer" style="display:none" class="zf-modal">
        <p class="deleteInfo modal-body">您确定要删除该条记录吗？</p>
    </div>
    <!-- 新增弹出层 -->
    <div id="addLayer" style="display:none" class="zf-modal">
        <form class="form-horizontal" role="form" id="addRoleForm">
            <div class="form-group">
                <label for="addRoleName" class="control-label zf-name-label col-xs-4">角色名称</label>
                <div class="zf-layer-input col-xs-8">
                    <input type="text" class="form-control zf-name-input" id="addRoleName" name="addRoleName"
                           placeholder="请输入角色名称">
                </div>
            </div>
            <div class="form-group">
                <label for="addDesc" class="control-label zf-name-label col-xs-4">备注</label>
                <div class="zf-layer-input col-xs-8">
                    <input type="text" class="form-control zf-name-input" id="addDesc" name="addDesc"
                           placeholder="请输入角色的备注">
                </div>
            </div>
        </form>
    </div>

    <!-- 修改弹出层 -->
    <div id="modifyLayer" style="display:none" class="zf-modal">
        <form class="form-horizontal" role="form" id="modifyRoleForm">
            <div class="form-group">
                <label for="modifyRoleName" class="control-label zf-name-label col-xs-4">角色名称</label>
                <div class="zf-layer-input col-xs-8">
                    <input type="text" class="form-control zf-name-input" id="modifyRoleName" name="modifyRoleName"
                           placeholder="请输入角色名称">
                </div>
            </div>
            <div class="form-group">
                <label for="modifyDesc" class="control-label zf-name-label col-xs-4">备注</label>
                <div class="zf-layer-input col-xs-8">
                    <input type="text" class="form-control zf-name-input" id="modifyDesc" name="modifyDesc"
                           placeholder="请输入角色的备注">
                </div>
            </div>
        </form>
    </div>

    <div id="updateMenuLayer" class="zTreeDemoBackground none">
        <ul id="treeDemo" class="ztree"></ul>
    </div>

</div>
</#macro>
<#macro html_foot>
    <@p.js src="system/role_info.js"/>
    <@p.js src="third/zTree_v3/js/jquery.ztree.core.min.js"/>
    <@p.js src="third/zTree_v3/js/jquery.ztree.excheck.min.js"/>
</#macro>

<#macro html_head>
    <link rel="stylesheet" type="text/css" href="${resourcesPath}/scripts/third/zTree_v3/css/zTreeStyle/zTreeStyle.css"/>
</#macro>