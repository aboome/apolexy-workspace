<#include "/common/baselib.ftl">
<#--详情弹出层-->
<div class="media zf-top-wrap">
    <#if discussDetail.doctorMemberDmo.avatar??>
        <div id="avatar" class="pull-left media-left avatar">
            <img id="avatarUrl" src="">${discussDetail.doctorMemberDmo.avatar}
        </div>
    </#if>
    <div class="media-body">
        <h4 class="media-heading">
            <p id="doctorName" class="inline doctorName">${discussDetail.doctorMemberDmo.doctorName}</p>
            <p id="hospitalName" class="inline hospitalName">${discussDetail.doctorMemberDmo.hospital}</p>
            <p id="doctorJob" class="inline doctorJob">${discussDetail.doctorMemberDmo.job}</p>
        </h4>
        <div id="createTime" class="zf-top createTime">
        <#if discussDetail.discussCaseDmo.createTime??><@p.out value="${discussDetail.discussCaseDmo.createTime?string('yyyy-MM-dd HH:mm:ss')}" />
     </#if>
        </div>

        <div id="image" class="zf-top">
        <#list discussDetail.imageList as image>
            <div class="imageList">
                <img id="image${image_index}" src="${ctx}/admin/image/view/${image.resourceId}">
            </div>
        </#list>
        </div>
        <!--<div id="video">${discussDetail.video}</div>-->
        <!--<label id="description">${discussDetail.discussCaseDmo.description}</label><br/>-->
        <div id="discussTitle" class="zf-top discussTitle row">
            <div class="col-xs-3">${discussDetail.discussCaseDmo.type}:</div>
            <div class="col-xs-9 font-size-16">${discussDetail.discussCaseDmo.mainDesc}</div>
        </div>

        <div class="zf-top row">
            <h6 class="col-xs-3">主诉:</h6>
            <p id="mainDesc" class="col-xs-9 font-size-16">${discussDetail.discussCaseDmo.mainDesc}</p>
        </div>
        <div class="zf-top row">
            <h6 class="col-xs-3">现病史:</h6>
            <p id="nowIllness" class="col-xs-9 font-size-16">${discussDetail.discussCaseDmo.nowIllness}</p>
        </div>
        <div class="zf-top row">
            <h6 class="col-xs-3">既往史:</h6>
            <p id="historyIllness" class="col-xs-9 font-size-16">${discussDetail.discussCaseDmo.hisIllness}</p>
        </div>
        <div class="zf-top row">
            <h6 class="col-xs-3">查体:</h6>
            <p id="healthCheck" class="col-xs-9 font-size-16">${discussDetail.discussCaseDmo.healthCheck}</p>
        </div>
        <div class="zf-top row">
            <h6 class="col-xs-3">辅助检查:</h6>
            <p id="assistCheck" class="col-xs-9 font-size-16">${discussDetail.discussCaseDmo.assistCheck}</p>
        </div>

    <#if discussDetail.video?? && discussDetail.video.resourceId??&&discussDetail.video.resourceId != "">
        <div id="video" class="zf-top">
            <video id="video1" width="320" controls="controls">
                <source src="${ctx}/admin/video/view/${discussDetail.video.resourceId}" type="video/mp4">
                <source src="${ctx}/admin/video/view/${discussDetail.video.resourceId}" type="video/ogg">
                您的浏览器不支持该视频格式。
            </video>
        </div>
    </#if>


        <div class="zf-top pull-right font-size-14">
            <p class="inline margin-right">
                <i class="glyphicon glyphicon-eye-open"></i> <span
                    id="readCount">${discussDetail.discussCaseDmo.readCount}</span>
            </p>
            <p class="inline margin-right">
                <i class="glyphicon glyphicon-comment"></i> <span
                    id="commentCount">${discussDetail.discussCaseDmo.commentCount}</span>
            </p>
            <p class="inline margin-right">
                <i class="glyphicon glyphicon-heart-empty"></i> <span
                    id="likeCount">${discussDetail.discussCaseDmo.likeCount}</span>
            </p>
            <p class="inline margin-right">
                <i class="glyphicon glyphicon-star-empty"></i> <span
                    id="collectionCount">${discussDetail.discussCaseDmo.collectionCount}</span>
            </p>
        </div>
    </div>
</div>