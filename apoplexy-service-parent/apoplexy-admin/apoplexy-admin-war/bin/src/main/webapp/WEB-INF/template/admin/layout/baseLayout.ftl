<#-- 基本布局模板 -->
<#include "/admin/common/baselib.ftl">
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title><@html_title/> - 卒中助手后台管理系统</title>
    <#if head_meta??>  
        <@head_meta/>
    </#if>
    <link rel="stylesheet" type="text/css" href="${adminResourcesPath}/theme/css/calendar-system.css" />
    <@p.css src="style.css"/>
    <@p.css src="jquery.autocomplete.css"/>
    <@p.css src="bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${adminResourcesPath}/scripts/third/layer/skin/layer.css" />
    <@p.css src="login.css"/>
    <@p.css src="bootstrap-addtabs.css"/>
    <@p.css src="navbar.css"/>
    <@p.css src="reset.css"/>
    <@p.css src="app.css"/>
    <@p.css src="hospitalManage.css"/>
    <@p.css src="index.css"/>
    <@p.css src="menuManage.css"/>
    <@p.css src="reset.css"/>

    
    <#if html_head??>  
    	<@html_head/>
    </#if>
</head>
<body>
    <#compress>
    <@html_body/>
    <script type="text/javascript" src="${adminResourcesPath}/scripts/third/jquery.min.js"></script>
    <script type="text/javascript" src="${adminResourcesPath}/scripts/third/bootstrap.min.js"></script>
    <@p.js src="third/layer/layer.js"/>
    <@p.js src="third/bootstrap-3.3.0-dist/dist/js/bootstrap.min.js"/>
    <@p.js src="third/bootstrap-addtabs.js"/>
    <@p.js src="third/jquery.nicescroll.js"/>	
    
    
    
    <script type="text/javascript">
    var YHu_Config = {
		bathPath : "${ctx}"
	};
	</script>
    <@p.js src="common/global.js"/>	
    <#if html_foot??>
        <@html_foot/>
    </#if>
    </#compress>
</body>
</html>