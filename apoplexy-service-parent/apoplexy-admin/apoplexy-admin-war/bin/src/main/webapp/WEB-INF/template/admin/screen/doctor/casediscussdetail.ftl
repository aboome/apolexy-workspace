<#include "/admin/common/baselib.ftl">
<#--详情弹出层-->
<div id="avatar"><img id="avatarUrl" src="">${discussDetail.doctorMemberDmo.avatar}</div>
<label id="doctorName">${discussDetail.doctorMemberDmo.doctorName}</label>
<label id="hospitalName">${discussDetail.doctorMemberDmo.hospital}</label>
<label id="doctorJob">${discussDetail.doctorMemberDmo.job}</label>
<label id="createTime"><#if discussDetail.discussCaseDmo.createTime??><@p.out value="${discussDetail.discussCaseDmo.createTime?string('yyyy-MM-dd HH:mm:ss')}" /></#if></label>
<div id="video">${discussDetail.video}</div>
<div id="discussTitle">${discussDetail.discussCaseDmo.type}:${discussDetail.discussCaseDmo.mainDesc}</div>
<label id="description">${discussDetail.discussCaseDmo.description}</label>
<label>主诉：</label><label id="mainDesc">${discussDetail.discussCaseDmo.mainDesc}</label>
<label>现病史：</label><label id="nowIllness">${discussDetail.discussCaseDmo.nowIllness}</label>
<label>既往史：</label><label id="historyIllness">${discussDetail.discussCaseDmo.hisIllness}</label>
<label>查体：</label><label id="healthCheck">${discussDetail.discussCaseDmo.healthCheck}</label>
<label>辅助检查：</label><label id="assistCheck">${discussDetail.discussCaseDmo.assistCheck}</label>
<#list discussDetail.imageList as image>
<span id="image${image_index}">${image.resourceId}</span>
</#list>
 