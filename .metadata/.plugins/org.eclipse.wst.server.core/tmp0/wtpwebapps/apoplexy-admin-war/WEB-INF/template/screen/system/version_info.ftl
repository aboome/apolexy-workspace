<#include "/layout/baseLayout.ftl">
<#macro html_title>版本升级管理</#macro>
<#macro html_body>
<div class="hospital-wrap" style="padding: 15px 21px;">
    <div class="table-content" style="padding-top: 0;">
        <div id="versionListtable" class="hospital-table">

        </div>
    </div>

    <!-- 修改弹出层 -->
    <div id="modifyLayer" style="display:none" class="zf-modal">
        <form class="form-horizontal" role="form" id="versionForm">
            <div class="form-group">
                <label for="modifyVersion" class="control-label zf-name-label col-xs-4">版本号</label>
                <div class="zf-layer-input col-xs-8">
                    <input type="text" class="form-control zf-name-input" id="modifyVersion" name="modifyVersion"
                           placeholder="请输入版本号">
                </div>
            </div>
            <div class="form-group">
                <label for="modifyURL" class="control-label zf-name-label col-xs-4">更新链接地址</label>
                <div class="zf-layer-input col-xs-8">
                    <input type="text" class="form-control zf-name-input" id="modifyURL" name="modifyURL"
                           placeholder="请输入更新URL">
                </div>
            </div>
        </form>
    </div>
</div>
</#macro>
<#macro html_foot>
    <@p.js src="system/version.js"/>
</#macro>