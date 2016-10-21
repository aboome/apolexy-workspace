<#include "/layout/baseLayout.ftl">
<#macro html_title>菜单管理</#macro>
<#macro html_body>
<div class="hospital-wrap" style="padding: 15px 21px">

    <div class="table-content" style="padding-top: 0">
        <div class="add-and-import">
            <button typee="button" class="btn add" id="add" onclick="addMenu()"><i
                    class="glyphicon glyphicon-plus"></i>新增
            </button>

        </div>

        <div id="menuTable" class="hospital-table">

        </div>
    </div>

    <!-- 新增弹出层 -->
    <div id="addLayer" style="display:none" class="zf-modal">
        <form class="form-horizontal" role="form" id="addMenuForm">
            <div class="form-group">
                <label for="addMenuName" class="control-label zf-name-label col-xs-4">菜单名称</label>
                <div class="col-xs-8">
                    <input type="text" class="form-control zf-layer-input" id="addMenuName" name="addMenuName" placeholder="请输菜单名称">
                </div>
            </div>

            <div class="form-group">
                <label for="addMenuUrl" class="control-label zf-name-label col-xs-4">菜单URL</label>
                <div class="col-xs-8">
                    <input type="text" class="form-control zf-layer-input" id="addMenuUrl" name="addMenuUrl" placeholder="请输入菜单URL">
                </div>
            </div>

            <div class="form-group">
                <label for="addMenuSort" class="control-label zf-name-label col-xs-4">菜单排序</label>
                <div class="col-xs-8">
                    <input type="text" class="form-control zf-layer-input" id="addMenuSort" name="addMenuSort" placeholder="请输入菜单排序">
                </div>
            </div>

            <div class="form-group">
                <label for="addMenuLevel" class="control-label zf-name-label col-xs-4">菜单等级</label>
                <div class="col-xs-8">
                    <select id="addMenuLevel" class="form-control" name="addMenuLevel">
                        <option value="">请选择菜单类型</option>
                        <option value="1">一级菜单</option>
                        <option value="2">二级菜单</option>
                        <#--<option value="3">三级菜单</option>-->
                    </select>
                </div>
            </div>

            <div id="addParentMenuDiv" class="form-group none">
                <label for="addParentMenu" class="control-label zf-name-label col-xs-4">父级菜单</label>
                <div class="col-xs-8">
                    <select id="addParentMenu" class="form-control" name="addParentMenu">
                        <option value="">请选择父级菜单</option>
                    </select>
                </div>
            </div>

        </form>
    </div>

    <!-- 修改弹出层 -->
    <div id="modifyLayer" style="display:none" class="zf-modal">
        <form class="form-horizontal" role="form" id="modifyMenuForm">

            <div class="form-group">
                <label for="modifyMenuName" class="control-label zf-name-label col-xs-4">菜单名称</label>
                <div class="col-xs-8">
                    <input type="text" class="form-control zf-name-input" id="modifyMenuName" name="modifyMenuName" placeholder="请输入菜单名称">
                </div>
            </div>

            <div class="form-group">
                <label for="modifyMenuUrl" class="control-label zf-name-label col-xs-4">菜单URL</label>
                <div class="col-xs-8">
                    <input type="text" class="form-control zf-layer-input" id="modifyMenuUrl" name="modifyMenuUrl" placeholder="请输入菜单URL">
                </div>
            </div>

            <div class="form-group">
                <label for="modifySort" class="control-label zf-name-label col-xs-4">菜单排序</label>
                <div class="col-xs-8">
                    <input type="text" class="form-control zf-name-input" id="modifySort" name="modifySort" placeholder="请输入菜单排序">
                </div>
            </div>

            <div class="form-group">
                <label for="modifyMenuLevel" class="control-label zf-name-label col-xs-4">菜单等级</label>
                <div class="col-xs-8">
                    <select id="modifyMenuLevel" class="form-control" name="modifyMenuLevel">
                        <option value="">请选择菜单类型</option>
                        <option value="1">一级菜单</option>
                        <option value="2">二级菜单</option>
                    <#--<option value="3">三级菜单</option>-->
                    </select>
                </div>
            </div>

            <div id="modifyParentMenuDiv" class="form-group none">
                <label for="modifyParentMenu" class="control-label zf-name-label col-xs-4">父级菜单</label>
                <div class="col-xs-8">
                    <select id="modifyParentMenu" class="form-control" name="modifyParentMenu">
                        <option value="">请选择父级菜单</option>
                    </select>
                </div>
            </div>

        </form>
    </div>


</div>
</#macro>
<!-- 弹出层 -->
<div id="deleteLayer" style="display:none" class="zf-modal">
    <p class="deleteInfo modal-body">您确定要删除该条记录吗？</p>
</div>


</div>
<#macro html_foot>
    <@p.js src="system/menu.js"/>
</#macro>