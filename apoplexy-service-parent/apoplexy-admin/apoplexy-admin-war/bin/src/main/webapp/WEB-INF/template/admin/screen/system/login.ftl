<#include "/admin/layout/baseLayout.ftl">
<#macro html_title>登录</#macro>
<#macro html_body>
<!-- cover begin -->
<div id="coverpage">
    <div id="onepics">
        <div class="onepic_wrap">
            <img src="${adminResourcesPath}/theme/images/login-bg.jpg" class="wrap_pic">
        </div>
    </div>
</div>
<!-- cover end -->
<div class="container-fluid">
    <div class="form-wraper">
        <h2 class="sys-name">卒中助手后台管理系统</h2>
        <div class="zf-login-form">
            <div class="zf-login-group">
                <div class="zf-login-input">
                    <span class="zf-icon-login username-icon"></span>
                    <input type="text" class="form-control" id="userName" placeholder="用户名/账号">
                </div>
            </div>
            <div class="zf-login-group">
                <div class="zf-login-input">
                    <span class="zf-icon-login password-icon"></span>
                    <input type="password" class="form-control" id="password" placeholder="输入密码">
                </div>
            </div>
            <div class="">
                <div class="login-btn">
                    <button id="login" class="btn btn-default btn-block">登录</button>
                </div>
            </div>
        </div>
    </div>
</div>
</#macro>
<#macro html_foot>
    <@p.js src="system/login.js"/>	
    <@p.js src="third/md5.js"/>
</#macro>