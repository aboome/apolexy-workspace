var queryDoctorReferral = {
		pageNum:1,
		pageSize:10,
		doctorName:null,
		hospitalName:null,
		startTime:null,
		endTime:null
};

var queryDoctorReferralList = function() {

	url = YHu.util.ctxPath("/referral/queryReferralList");
	YHu.ui.tableLoad("#casereferraltable", url, queryDoctorReferral);

};

var setCondition = function() {
	queryDoctorReferral.doctorName = $("#doctor-name").val();
	queryDoctorReferral.hospitalName = $("#hospital-name").val();
	queryDoctorReferral.startTime = $("#start-time").val();
	queryDoctorReferral.endTime = $("#end-time").val();
};

$("document").ready(function() {

	$("#search").click(function(){

		setCondition();
		queryDoctorReferralList();
	});

	$("#clear").click(function() {

		$("#doctor-name").val("");
		$("#hospital-name").val("");
		$("#start-time").val("");
		$("#end-time").val("");
		setCondition();
		queryDoctorReferralList();
	});

	setCondition();
	queryDoctorReferralList();
});

var pageHandler = function(event, pageSize) {
	event.preventDefault();
	queryDoctorReferral.pageNum = event.target.title;
	if (pageSize != null && typeof (pageSize) != "undefined") {
		queryDoctorReferral.pageSize = pageSize;
		queryDoctorReferral.pageNum = 1;
	}
	queryDoctorReferralList();
};

var deleteReferral = function(no) {
	
	var referralId = "referralId" + no;
    var referralValue = $("#" + referralId).text();
	layer.open({
	    type: 1,
	    title: '删除转诊信息',
	    area: ['350px', '180px'],
	    /*maxWidth: '450px',*/
	    shadeClose: false,
	    content: $('#deleteLayer'),
	    btn: ['确定', '取消'],
	    yes: function(index, layero){
	    	YHu.ui.loading();
            var handleRequest = $.post(YHu.util.ctxPath('/referral/deleteReferral'),
                {id : referralValue});
            handleRequest.done(function(jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryDoctorReferralList();
                    YHu.ui.alert("删除转诊信息成功！");
                } else {
                    YHu.ui.closeAllLayer();
                    YHu.ui.alert(jsonResult.message);
                }
            });
	    },
	    btn2: function(index, layero){
	    	YHu.ui.closeAllLayer();
	    }
	});
};

var referralDetail = function (no) {

    var referralId = "referralId" + no;
    var referralValue = $("#" + referralId).text();

    YHu.ui.loading();

    $("#detailLayer").load(YHu.util.ctxPath('/referral/queryReferralDetails'), {id : referralValue},function () {

        // $("#avatarUrl").attr('src',YHu.util.ctxPath("/admin/image/view/")+$("#avatarUrl").attr("src"));

        YHu.ui.closeLoading();
        
        layer.open({
            type: 1,
            title: '转诊详情',
            area: ['650px', '520px'],
            shadeClose: false,
            content: $('#detailLayer'),
            btn: ['关闭'],
            yes: function(index, layero){
            	YHu.ui.closeAllLayer();
            }
        });

    });

};



var doctorList = function (no) {
	var pageNum = 1;
	var pageSize = 10;
    var referralId = "referralId" + no;
    var referralValue = $("#" + referralId).text();

    YHu.ui.loading();

    $("#doctorListLayer").load(YHu.util.ctxPath('/referral/queryReveiveList'), {recordId : referralValue,pageNum : pageNum,pageSize : pageSize},function () {

        YHu.ui.closeLoading();
        
        layer.open({
            type: 1,
            title: '接诊医生列表',
            area: 'auto',
            maxWidth: '500px',
            shadeClose: false,
            content: $('#doctorListLayer'),
            btn: ['关闭'],
            yes: function(index, layero){
            	YHu.ui.closeAllLayer();
            }
        });

    });

};


var doctorEvaluate = function (no) {

    var referralId = "referralId" + no;
    var referralValue = $("#" + referralId).text();

    YHu.ui.loading();

    $("#referralCommentLayer").load(YHu.util.ctxPath('/referral/queryReferralComment'), {recordId : referralValue},function () {

        YHu.ui.closeLoading();
        
        layer.open({
            type: 1,
            title: '医生评价',
            area: ['420px', '300px'],
            shadeClose: false,
            content: $('#referralCommentLayer'),
            btn: ['关闭'],
            yes: function(index, layero){
            	YHu.ui.closeAllLayer();
            }
        });

    });

};
