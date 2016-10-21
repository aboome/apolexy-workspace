<#include "/admin/layout/contentLayout.ftl">
<#macro html_body>
<div class="hospital-wrap">
    <div class="search-term">
        <div class="form-horizontal" role="form">
            <div class="form-group">
                <label for="hospital-name" class="control-label hospital-name-label">管理员用户名：</label>
                <div class="hospital-name">
                    <input typee="text" class="form-control hospital-name-input" id="admin-name"
                           placeholder="请输入管理员用户名">
                </div>
                <div class="search-btn">
                    <button type="button" class="btn search" id="search">查询结果</button>
                    <button type="button" class="btn clear" id="clear">清空条件</button>
                </div>
            </div>
        </div>
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
        <p class="deleteInfo">您确定要删除该条记录吗？</p>
    </div>
    <!-- 新增弹出层 -->
    <div id="addLayer" style="display:none" class="zf-modal">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="addAdminName" class="control-label zf-name-label">用户名</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="addAdminName"
                           placeholder="请输入管理员的用户名">
                </div>
            </div>
            <div class="form-group">
                <label for="addPassword" class="control-label zf-name-label">登录密码</label>
                <div class="zf-layer-input">
                    <input type="password" class="form-control zf-name-input" id="addPassword"
                           placeholder="请输入管理员的登录密码">
                </div>
            </div>
            <div class="form-group">
                <label for="addPhone" class="control-label zf-name-label">联系电话</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="addPhone"
                           placeholder="请输入管理员的联系电话">
                </div>
            </div>
            <div class="form-group">
                <label for="addEmail" class="control-label zf-name-label">电子邮箱</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="addEmail"
                           placeholder="请输入管理员的电子邮箱">
                </div>
            </div>
            <div class="form-group">
                <label for="addJob" class="col-sm-2 control-label zf-name-label">职务</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="addJob"
                           placeholder="请输入管理员的职务">
                </div>
            </div>
            <div class="form-group">
                <label for="addDesc" class="control-label zf-name-label">备注</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="addDesc"
                           placeholder="请输入管理员的备注信息">
                </div>
            </div>
            <div class="form-group">
                <label for="addRealName" class="control-label zf-name-label">真实姓名</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="addRealName"
                           placeholder="请输入管理员的真实姓名">
                </div>
            </div>
        </form>
    </div>

    <!-- 修改弹出层 -->
    <div id="modifyLayer" style="display:none" class="zf-modal">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="modifyAdminName" class="control-label zf-name-label">用户名</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="modifyAdminName"
                           placeholder="请输入管理员的用户名">
                </div>
            </div>
            <div class="form-group">
                <label for="modifyPassword" class="control-label zf-name-label">登录密码</label>
                <div class="zf-layer-input">
                    <input type="password" class="form-control zf-name-input" id="modifyPassword"
                           placeholder="请输入管理员的登录密码">
                </div>
            </div>
            <div class="form-group">
                <label for="modifyPhone" class="control-label zf-name-label">联系电话</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="modifyPhone"
                           placeholder="请输入管理员的联系电话">
                </div>
            </div>
            <div class="form-group">
                <label for="modifyEmail" class="control-label zf-name-label">电子邮箱</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="modifyEmail"
                           placeholder="请输入管理员的电子邮箱">
                </div>
            </div>
            <div class="form-group">
                <label for="modifyJob" class="control-label zf-name-label">职务</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="modifyJob"
                           placeholder="请输入管理员的职务信息">
                </div>
            </div>
            <div class="form-group">
                <label for="modifyDesc" class="control-label zf-name-label">备注</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="modifyDesc"
                           placeholder="请输入管理员的备注信息">
                </div>
            </div>
            <div class="form-group">
                <label for="modifyRealName" class="control-label zf-name-label">真实姓名</label>
                <div class="zf-layer-input">
                    <input type="text" class="form-control zf-name-input" id="modifyRealName"
                           placeholder="请输入管理员的真实姓名">
                </div>
            </div>
        </form>
    </div>
</div>
</#macro>
<#macro html_foot>
    <@p.js src="system/admin.js"/>
</#macro>