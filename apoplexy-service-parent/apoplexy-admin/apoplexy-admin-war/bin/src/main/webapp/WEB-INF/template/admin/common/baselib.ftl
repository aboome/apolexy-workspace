<#-- 默认样式 -->
<#global theme="default">
<#-- SESSION KEY -->
<#global UserSession="UserSession">
<#if Session["${UserSession}"]??><#global currentUser = Session["${UserSession}"]></#if>
<#-- 上下文 -->
<#global ctx="${request.contextPath}">
<#-- 静态资源 -->
<#global resourcesPath="${ctx}/resources">
<#-- 静态资源 -->
<#global adminResourcesPath="${ctx}/resources/admin">
<#-- 主题 -->
<#global themePath="${adminResourcesPath}/theme/${theme}">
<#-- 自定义html标签 -->
<#import "/admin/common/htmltag.ftl" as p>
