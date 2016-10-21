<#include "/admin/common/baselib.ftl">


<#--详情弹出层-->
<div id="avatar"><img id="avatarUrl" src="${astDetail.doctorMemberDmo.avatar}"></div>
<label id="doctorName">${astDetail.doctorMemberDmo.doctorName}</label>
<label id="hospitalName">${astDetail.doctorMemberDmo.hospital}</label>job
<label id="job">${astDetail.doctorMemberDmo.job}</label><br/>
<label id="createTime"><#if astDetail.astCasesDmo.createTime??><@p.out value="${astDetail.astCasesDmo.createTime?string('yyyy-MM-dd HH:mm:ss')}" /></#if></label><br />
<label>溶栓组织到达时间</label><label id="thrombolysisArriveTime"><#if astDetail.astCasesDmo.thrombolysisArriveTime??><@p.out value="${astDetail.astCasesDmo.thrombolysisArriveTime?string('yyyy-MM-dd HH:mm:ss')}" /></#if></label><br />
<label>病前MRS</label><br/>
<label id="mrs">${astDetail.astCasesDmo.mrs}</label>
<label>基线NIHSS</label><br/>
<label>基线NIHSS总得分</label><label id="nihssTotalFee${nihss_index}">${astDetail.astCasesDmo.nihssTotalFee}</label>
<table class="table table-hover table-bordered table-striped table-condensed">
    <thead>
    <tr class="thead">
        <th>问题</th>
        <th>回答</th>
    </tr>
    </thead>
    <tbody>
    <tr><!-- 该行仅为控制隔行变色 --></tr>
        <#list astDetail.astNihssList as astNihssList>
        <tr>
            <td id="question${astNihssList_index}">${astNihssList.question}</td>
            <td id="answer${astNihssList_index}">${astNihssList.result}</td>
        </tr>
        </#list>
    </tbody>
</table>

<label>既往史：</label><br/>
<table class="table table-hover table-bordered table-striped table-condensed">
    <thead>
    <tr class="thead">
        <th>问题</th>
        <th>回答</th>
    </tr>
    </thead>
    <tbody>
    <tr><!-- 该行仅为控制隔行变色 --></tr>
        <#list astDetail.hisIllness as his>
        <tr>
            <td id="question${his_index}">${his.detailIndex}.${his.question}</td>
            <td id="answer${his_index}"><#if his.result==0>否<#elseif his.result==1>是</#if></td>
        </tr>
        </#list>
    </tbody>
</table>
<label>个人史：</label>
<div>
<label>体重</label>
<label id="weight">${astDetail.astCasesDmo.weight}</label><br>
<label>吸烟</label>
<label id="smock"><#if astDetail.astCasesDmo.smock==0>否<#elseif astDetail.astCasesDmo.smock==1>是</#if></label><br>
<label>妊娠</label>
<label id="pregnancy"><#if astDetail.astCasesDmo.pregnancy==0>否<#elseif astDetail.astCasesDmo.pregnancy==1>是</#if></label>
</div>
<label>既往用药史：</label>

<table class="table table-hover table-bordered table-striped table-condensed">
    <thead>
    <tr class="thead">
        <th>问题</th>
        <th>回答</th>
    </tr>
    </thead>
    <tbody>
    <tr><!-- 该行仅为控制隔行变色 --></tr>
        <#list astDetail.hisMedicaitionList as hisMedicaition>
        <tr>
            <td id="question${hisMedicaition_index}">${hisMedicaition.detailIndex}.${hisMedicaition.question}</td>
            <td id="answer${hisMedicaition_index}"><#if hisMedicaition.result==0>否<#elseif hisMedicaition.result==1>是</#if></td>
        </tr>
        </#list>
    </tbody>
</table>

<label>急诊CT平扫：</label>
<div>
<#list astDetail.ctList as ctList>
      <td id="imageId${ctList_index}">${ctList.imageId}</td>
</#list>
</div>
<label>急诊CTA上传：</label>
<div>
<#list astDetail.ctaList as ctaList>
      <td id="imageId${ctaList_index}">${ctaList.imageId}</td>
</#list>
</div>
<label>急诊CTP上传：</label>
<div>
<#list astDetail.ctpList as ctpList>
      <td id="imageId${ctpList_index}">${ctpList.imageId}</td>
</#list>
</div>
<label>阅读数：</label><label id="readCount">${astDetail.astCasesDmo.readCount}</label>
<label>评论数：</label><label id="commentCount">${astDetail.astCasesDmo.commentCount}</label>
<label>赞：</label><label id="likeCount">${astDetail.astCasesDmo.likeCount}</label>
<label>收藏数：</label><label id="collectionCount">${astDetail.astCasesDmo.collectionCount}</label>
