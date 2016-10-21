<#include "/common/baselib.ftl">
<#--医生评价弹出层-->

<div class="doctorEvaluate-div">
	<div class="doctorEvaluate row">
		<p class="col-xs-5">医生姓名：</p><p id="doctorName" class="col-xs-7">${referralComment.doctorName}</p>
	</div>
	<div class="doctorEvaluate row">
		<p class="col-xs-5">评价时间：</p><p id="createTime" class="col-xs-7"><#if referralComment.createTime??><@p.out value="${referralComment.createTime?string('yyyy-MM-dd HH:mm:ss')}" /></#if></p>
	</div>
	<div class="doctorEvaluate row">
		<p class="col-xs-5">病例完整度：</p><p id="caseIntegrity" class="col-xs-7">${referralComment.caseIntegrity}</p>
	</div>
	<div class="doctorEvaluate row">
		<p class="col-xs-5">病例详细度：</p><p id="caseDetail" class="col-xs-7">${referralComment.caseDetail}</p>
	</div>
</div>