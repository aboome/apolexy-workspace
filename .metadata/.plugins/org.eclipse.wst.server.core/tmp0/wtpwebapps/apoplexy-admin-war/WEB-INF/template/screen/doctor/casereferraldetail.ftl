<#include "/common/baselib.ftl">
<#--详情弹出层-->
<div class="media zf-top-wrap">
    <div class="pull-left media-left avatar" id="avatar">
        <img id="avatarUrl" src="${ctx}/admin/image/view/${referralDetail.doctorMemberDmo.avatar}">
    </div>
    <div class="media-body">
        <h4 class="media-heading">
            <p id="doctorName" class="inline doctorName">${referralDetail.doctorMemberDmo.doctorName}</p>
            <p id="hospitalName" class="inline hospitalName"> ${referralDetail.doctorMemberDmo.hospital} (<span
                    id="doctorJob">${referralDetail.doctorMemberDmo.job}</span>)</p>

        </h4>
        <div id="createTime" class="zf-top createTime">
        <#if referralDetail.referralCaseDmo.createTime??>
				<@p.out value="${referralDetail.referralCaseDmo.createTime?string('yyyy-MM-dd HH:mm:ss')}" />
			</#if>
        </div>
        <div id="image" class="zf-top">
        <#list referralDetail.imageList as image>
            <div class="imageList">
                <img id="image${image_index}" src="${ctx}/admin/image/view/${image.resourceId}">
            </div>
        </#list>
        </div>
        <div id="discussTitle" class="zf-top discussTitle row">
            <div class="col-xs-3">${referralDetail.referralCaseDmo.type}:</div>
            <div class="col-xs-9 font-size-16">${referralDetail.referralCaseDmo.mainDesc}</div>
        </div>
        <div class="zf-top row">
            <h6 class="col-xs-3">主诉:</h6>
            <p id="mainDesc" class="col-xs-9 font-size-16">${referralDetail.referralCaseDmo.mainDesc}</p>
        </div>
        <div class="zf-top row">
            <h6 class="col-xs-3">现病史:</h6>
            <p id="nowIllness" class="col-xs-9 font-size-16">${referralDetail.referralCaseDmo.nowIllness}</p>
        </div>
        <div class="zf-top row">
            <h6 class="col-xs-3">既往史:</h6>
            <p id="historyIllness" class="col-xs-9 font-size-16">${referralDetail.referralCaseDmo.hisIllness}</p>
        </div>
        <div class="zf-top row">
            <h6 class="col-xs-3">查体:</h6>
            <p id="healthCheck" class="col-xs-9 font-size-16">${referralDetail.referralCaseDmo.healthCheck}</p>
        </div>
        <div class="zf-top row">
            <h6 class="col-xs-3">辅助检查:</h6>
            <p id="assistCheck" class="col-xs-9 font-size-16">${referralDetail.referralCaseDmo.assistCheck}</p>
        </div>
        <div class="zf-top row">
            <h6 class="col-xs-3">阅读数:</h6>
            <p id="readCount" class="col-xs-9 font-size-16">${referralDetail.referralCaseDmo.readCount}</p>
        </div>
        <div class="zf-top row">
            <h6 class="col-xs-3">接诊数:</h6>
            <p id="collectionCount" class="col-xs-9 font-size-16">${referralDetail.referralCaseDmo.receiveCount}</p>
        </div>
    <#if referralDetail.video??>
        <div id="video" class="zf-top">
            <video id="video1" width="320" controls="controls">
                <source src="${ctx}/admin/video/view/${referralDetail.video}" type="video/mp4">
                <source src="${ctx}/admin/video/view/${referralDetail.video}" type="video/ogg">
                您的浏览器不支持该视频格式。
            </video>
        </div>
    </#if>
    </div>
</div>