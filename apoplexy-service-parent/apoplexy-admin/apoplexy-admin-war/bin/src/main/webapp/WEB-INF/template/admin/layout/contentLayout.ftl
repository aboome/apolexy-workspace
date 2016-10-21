<#-- 基本布局模板 -->
<#include "/admin/common/baselib.ftl">
    <#compress>
    <@html_body/>
    <script type="text/javascript" src="${adminResourcesPath}/scripts/third/jquery.min.js"></script>
    <script type="text/javascript" src="${adminResourcesPath}/scripts/third/bootstrap.min.js"></script>
    <script type="text/javascript" src="${adminResourcesPath}/scripts/third/layer/layer.js"></script>
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
