<#include "/admin/common/baselib.ftl">
<#--详情弹出层-->
<div id="avatar"><img id="avatarUrl" src="${referralDetail.doctorMemberDmo.avatar}"></div>
<label id="doctorName">${referralDetail.doctorMemberDmo.doctorName}</label>
<label id="hospitalName">${referralDetail.doctorMemberDmo.hospital}</label>
<label id="doctorJob">${referralDetail.doctorMemberDmo.job}</label>
<label id="createTime"><#if referralDetail.referralCaseDmo.createTime??><@p.out value="${referralDetail.referralCaseDmo.createTime?string('yyyy-MM-dd HH:mm:ss')}" /></#if></label>
<div id="video">${referralDetail.video}</div>
<div id="image">
<#list referralDetail.imageList as imageList>${imageList.resourceId}</#list></div>
<label id="title">${referralDetail.title}</label>
<label>主诉：</label><label id="mainDesc">${referralDetail.referralCaseDmo.mainDesc}</label>
<label>现病史：</label><label id="nowIllness">${referralDetail.referralCaseDmo.nowIllness}</label>
<label>既往史：</label><label id="historyIllness">${referralDetail.referralCaseDmo.hisIllness}</label>
<label>查体：</label><label id="healthCheck">${referralDetail.referralCaseDmo.healthCheck}</label>
<label>辅助检查：</label><label id="assistCheck">${referralDetail.referralCaseDmo.assistCheck}</label>
<label>阅读数：</label><label id="readCount">${referralDetail.referralCaseDmo.readCount}</label>
<label>接诊数：</label><label id="collectionCount">${referralDetail.referralCaseDmo.receiveCount}</label>
