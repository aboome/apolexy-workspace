var queryDoctorDiscuss = {
		pageNum : 1,
		pageSize : 10,
		doctorName : null,
		hospitalName : null,
		startTime : null,
		endTime : null
};

var queryDoctorDiscussList = function() {

	url = YHu.util.ctxPath("/cases/queryCasesList");
	YHu.ui.tableLoad("#casediscusstable", url, queryDoctorDiscuss);

};


var setCondition = function() {
	queryDoctorDiscuss.doctorName = $("#doctor-name").val();
	queryDoctorDiscuss.hospitalName = $("#hospital-name").val();
	queryDoctorDiscuss.startTime = $("#start-time").val();
	queryDoctorDiscuss.endTime = $("#end-time").val();
};

	$("document").ready(function() {

	$("#search").click(function() {

		setCondition();
		queryDoctorDiscussList();
	});
	$("#clear").click(function() {

		$("#doctor-name").val("");
		$("#hospital-name").val("");
		$("#start-time").val("");
		$("#end-time").val("");
		setCondition();
		queryDoctorDiscussList();
	});

	setCondition();
	queryDoctorDiscussList();
});

var pageHandler = function(event, pageSize) {
	event.preventDefault();
	queryDoctorDiscuss.pageNum = event.target.title;
	if (pageSize != null && typeof (pageSize) != "undefined") {
		queryDoctorDiscuss.pageSize = pageSize;
		queryDoctorDiscuss.pageNum = 1;
	}
	queryDoctorDiscussList();
};


var deleteDiscuss = function(no) {
	
	var discussId = "discussId" + no;
    var discussValue = $("#" + discussId).text();
	layer.open({
	    type: 1,
	    title: '删除病例讨论',
	    area: ['350px', '180px'],
	    /*maxWidth: '450px',*/
	    shadeClose: false,
	    content: $('#deleteLayer'),
	    btn: ['确定', '取消'],
	    yes: function(index, layero){
	    	YHu.ui.loading();
            var handleRequest = $.post(YHu.util.ctxPath('/cases/deleteCases'),
                {id : discussValue});
            handleRequest.done(function(jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryDoctorDiscussList();
                    YHu.ui.alert("删除病例讨论信息成功！");
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

var discussDetail = function (no) {

    var discussId = "discussId" + no;
    var discussValue = $("#" + discussId).text();

    YHu.ui.loading();

    $("#detailLayer").load(YHu.util.ctxPath('/cases/queryCasesDetails'), {id : discussValue},function () {

        YHu.ui.closeLoading();
        
        layer.open({
            type: 1,
            title: '病例讨论详情',
            area: ['550px', '380px'],
            shadeClose: false,
            content: $('#detailLayer'),
            btn: ['关闭'],
            yes: function(index, layero){
            	YHu.ui.closeAllLayer();
            }
        });

    });

};



var discussReviewList = function (no) {
	var pageNum = 1;
	var pageSize = 10;
    var discussId = "discussId" + no;
    var discussValue = $("#" + discussId).text();

    YHu.ui.loading();

    $("#detailLayer").load(YHu.util.ctxPath('/cases/queryCasesReply'), {id : discussValue,pageNum : pageNum,pageSize : pageSize},function () {

        YHu.ui.closeLoading();
        
        layer.open({
            type: 1,
            title: '讨论回复列表',
            area: ['550px', '380px'],
            shadeClose: false,
            content: $('#detailLayer'),
            btn: ['关闭'],
            yes: function(index, layero){
            	YHu.ui.closeAllLayer();
            }
        });

    });

};


var deleteDiscussReview = function(no) {
	
	var replyId = "replyId" + no;
    var replyValue = $("#" + replyId).text();
	layer.open({
	    type: 1,
	    title: '删除病例讨论回复',
	    area: ['350px', '180px'],
	    /*maxWidth: '450px',*/
	    shadeClose: false,
	    content: $('#deleteLayer'),
	    btn: ['确定', '取消'],
	    yes: function(index, layero){
	    	YHu.ui.loading();
            var handleRequest = $.post(YHu.util.ctxPath('/cases/deleteCasesReply'),
                {id : replyValue});
            handleRequest.done(function(jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    
                    YHu.ui.alert("删除病例讨论信息成功！");
                    $('#detailLayer').empty();
                    discussReviewList();
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