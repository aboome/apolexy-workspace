<#include "/common/baselib.ftl">
<#--详情弹出层-->
<div class="media zf-top-wrap">
	<div class="pull-left media-left avatar" id="avatar">
		<img id="avatarUrl" src="${ctx}/admin/image/view/${astDetail.doctorMemberDmo.avatar}">
	</div>
	<div class="media-body">
		<h4 class="media-heading">
			<p id="doctorName" class="inline doctorName">${astDetail.doctorMemberDmo.doctorName}</p>
			<p id="hospitalName" class="inline hospitalName"> ${astDetail.doctorMemberDmo.hospital} (<span id="doctorJob">${astDetail.doctorMemberDmo.job}</span>)</p>
		</h4>
		<div id="createTime" class="zf-top createTime">
			<#if astDetail.astCasesDmo.createTime??>
				<@p.out value="${astDetail.astCasesDmo.createTime?string('yyyy-MM-dd HH:mm:ss')}" />
			</#if>
		</div>
		
		<div class="zf-top clearfix">
			<h6 class="detail-layer-bg">溶栓组织到达时间</h6>
			<p id="thrombolysisArriveTime" class="font-size-14 col-xs-10">
				<#if astDetail.astCasesDmo.thrombolysisArriveTime??>
					<@p.out value="${astDetail.astCasesDmo.thrombolysisArriveTime?string('yyyy-MM-dd HH:mm:ss')}" />
				</#if>
			</p>
		</div>
		<div class="zf-top clearfix">
			<h6 class="detail-layer-bg">病前MRS</h6>
			<p id="mrs" class="font-size-14 col-xs-10">${astDetail.astCasesDmo.mrs}</p>
		</div>
		<div class="zf-top">
			<h6 class="detail-layer-bg">基线NIHSS</h6>
			<ul>
				<li class="clearfix">
					<p class="pull-left font-size-14 col-xs-10">总分</p>
					<p id="nihss" class="pull-right font-size-14 padding-right col-xs-2">${astDetail.astCasesDmo.nihssTotalFee}</p>
				</li>
				<#list astDetail.astNihssList as astNihssList>
				<li class="clearfix">
					<p class="pull-left font-size-14 col-xs-10" id="question${astNihssList_index}">${astNihssList.question}</p>
					<p class="pull-right font-size-14 padding-right col-xs-2" id="answer${astNihssList_index}">${astNihssList.result}</p>
				</li>
				</#list>
			</ul>
		</div>
	
		<div class="zf-top">
			<h6 class="detail-layer-bg">既往史</h6>
			<ul>
				<#list astDetail.hisQuestionList as his>
				<li class="clearfix">
					<p class="pull-left font-size-14 col-xs-10" id="question${his_index}">${his.question}</p>
					<p class="pull-right font-size-14 padding-right col-xs-2" id="answer${his_index}"><#if his.result==0>否<#elseif his.result==1>是</#if></p>
				</li>
				</#list>
			</ul>
		</div>
		
		<div class="zf-top">
			<h6 class="detail-layer-bg">个人史</h6>
			<ul>
				<li class="clearfix">
					<p class="pull-left font-size-14 col-xs-10">体重</p>
					<p id="weight" class="pull-right font-size-14 padding-right col-xs-2">${astDetail.astCasesDmo.weight}</p>
				</li>
				<li class="clearfix">
					<p class="pull-left font-size-14 col-xs-10">吸烟</p>
					<p id="smock" class="pull-right font-size-14 padding-right col-xs-2"><#if astDetail.astCasesDmo.smock==0>否<#elseif astDetail.astCasesDmo.smock==1>是</#if></p>
				</li>
				<li class="clearfix">
					<p class="pull-left font-size-14 col-xs-10">妊娠</p>
					<p id="pregnancy" class="pull-right font-size-14 padding-right col-xs-2"><#if astDetail.astCasesDmo.pregnancy==0>否<#elseif astDetail.astCasesDmo.pregnancy==1>是</#if></p>
				</li>
			</ul>
		</div>
		<div class="zf-top">
			<h6 class="detail-layer-bg">既往用药史</h6>
			<ul>
				<#list astDetail.astHisMedicaitionList as hisMedicaition>
				<li class="clearfix">
					<p class="pull-left font-size-14 col-xs-10" id="question${hisMedicaition_index}">${hisMedicaition.question}</p>
					<p class="pull-right font-size-14 padding-right col-xs-2" id="answer${hisMedicaition_index}"><#if hisMedicaition.result==0>否<#elseif hisMedicaition.result==1>是</#if></p>
				</li>
				</#list>
			</ul>
		</div>

		<div class="zf-top">
			<h6 class="detail-layer-bg">急诊CT平扫</h6>
			<#list astDetail.ctList as ctList>
				<div class="imageList">
					<img src="${ctx}/admin/image/view/${ctList.imageId}"/>
				</div>
			</#list>
		</div>
		<div class="zf-top">
			<h6 class="detail-layer-bg">急诊CTA上传</h6>
			<#list astDetail.ctaList as ctaList>
				<div class="imageList">
					<img src="${ctx}/admin/image/view/${ctaList.imageId}"/>
				</div>
			</#list>
		</div>
		<div class="zf-top bottom-line">
			<h6 class="detail-layer-bg">急诊CTP上传</h6>
			<#list astDetail.ctpList as ctpList>
				<div class="imageList">
					<img src="${ctx}/admin/image/view/${ctpList.imageId}"/>
				</div>
			</#list>
		</div>
		
		
		<div class="zf-top pull-right font-size-14">
			<p class="inline margin-right">
				<i class="glyphicon glyphicon-eye-open"></i> <span id="readCount">${astDetail.astCasesDmo.readCount}</span>
			</p>
			<p class="inline margin-right">
				<i class="glyphicon glyphicon-comment"></i> <span id="commentCount">${astDetail.astCasesDmo.commentCount}</span>
			</p>
			<p class="inline margin-right">
				<i class="glyphicon glyphicon-heart-empty"></i> <span id="likeCount">${astDetail.astCasesDmo.likeCount}</span>
			</p>
			<p class="inline margin-right">
				<i class="glyphicon glyphicon-star-empty"></i> <span id="collectionCount">${astDetail.astCasesDmo.collectionCount}</span>
			</p>
		</div>
	</div>
</div>














