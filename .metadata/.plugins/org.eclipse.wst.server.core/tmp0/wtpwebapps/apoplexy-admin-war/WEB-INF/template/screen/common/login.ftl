<#include "/layout/baseLayout.ftl">
<#macro html_title>登录</#macro>
<#macro html_body>
<div class="signinpanel">
    <div class="row">
        <div class="col-sm-7">
            
        </div>
        <div class="col-sm-5">
            <form id="loginForm" role="form" onsubmit="return false">
                <h4 class="no-margins">卒中助手后台管理系统</h4>
                <span class="has-error m-b-none none" id="login-error"><i class="fa fa-times-circle"></i><label id="login-error-message"></label></span>
                <div class="form-group">
                    <input type="text" class="form-control uname" id="username" name="username" placeholder="用户名"/>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control pword m-b" id="password" name="password" placeholder="密码"/>
                </div>
                <button class="btn btn-success btn-block" id="login">登录</button>
            </form>
        </div>
    </div>
    
</div>
</#macro>
<#macro html_foot>
    <@p.js src="common/login.js"/>
    <@p.js src="third/md5.js"/>
</#macro>
<#macro html_head>
    <@p.css src="login.css"/>
</#macro>