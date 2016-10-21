var queryDoctorManage = {
		pageNum:1,
		pageSize:10,
		doctorName:null,
		hospitalName:null,
		startTime:null,
		endTime:null
};

var queryDoctorManageList = function() {

	url = YHu.util.ctxPath("/ast/queryAstlList");
	YHu.ui.tableLoad("#casemanagetable", url, queryDoctorManage);

};

var setCondition = function() {
	queryDoctorManage.doctorName = $("#doctor-name").val();
	queryDoctorManage.hospitalName = $("#hospital-name").val();
	queryDoctorManage.startTime = $("#start-time").val();
	queryDoctorManage.endTime = $("#end-time").val();
};

$("document").ready(function() {

	$("#search").click(function(){

		setCondition();
		queryDoctorManageList();
	});

	$("#clear").click(function() {

		$("#doctor-name").val("");
		$("#hospital-name").val("");
		$("#start-time").val("");
		$("#end-time").val("");
		setCondition();
		queryDoctorManageList();
	});

	setCondition();
	queryDoctorManageList();
});

var pageHandler = function(event, pageSize) {
	event.preventDefault();
	queryDoctorManage.pageNum = event.target.title;
	if (pageSize != null && typeof (pageSize) != "undefined") {
		queryDoctorManage.pageSize = pageSize;
		queryDoctorManage.pageNum = 1;
	}
	queryDoctorManageList();
};


var deleteManage = function(no) {
	
	 var manageId = "manageId" + no;
	 var manageValue = $("#" + manageId).text();
	layer.open({
	    type: 1,
	    title: '删除AST信息',
	    area: ['350px', '180px'],
	    /*maxWidth: '450px',*/
	    shadeClose: false,
	    content: $('#deleteLayer'),
	    btn: ['确定', '取消'],
	    yes: function(index, layero){
	    	YHu.ui.loading();
            var handleRequest = $.post(YHu.util.ctxPath('/ast/deleteAstCases'),
                {id : manageValue});
            handleRequest.done(function(jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryDoctorManageList();
                    YHu.ui.alert("删除AST管理信息成功！");
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

var manageDetail = function (no) {

    var manageId = "manageId" + no;
    var manageValue = $("#" + manageId).text();

    YHu.ui.loading();

    $("#detailLayer").load(YHu.util.ctxPath('/ast/queryAstDetails'), {recordId : manageValue},function () {

        YHu.ui.closeLoading();
        
        layer.open({
            type: 1,
            title: 'AST病例详情',
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

var astReviewList = function (no) {
	var pageNum = 1;
	var pageSize = 10;
    var manageId = "manageId" + no;
    var manageValue = $("#" + manageId).text();

    YHu.ui.loading();

    $("#detailLayer").load(YHu.util.ctxPath('/ast/queryAstReply'), {id : manageValue,pageNum : pageNum,pageSize : pageSize},function () {

        YHu.ui.closeLoading();
        
        layer.open({
            type: 1,
            title: 'ast回复列表',
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

var deleteAstReview = function(no) {
	
	var astReplyId = "astReplyId" + no;
    var astReplyValue = $("#" + astReplyId).text();
	layer.open({
	    type: 1,
	    title: '删除ast讨论回复',
	    area: ['350px', '180px'],
	    /*maxWidth: '450px',*/
	    shadeClose: false,
	    content: $('#deleteLayer'),
	    btn: ['确定', '取消'],
	    yes: function(index, layero){
	    	YHu.ui.loading();
            var handleRequest = $.post(YHu.util.ctxPath('/ast/deleteAstReply'),
                {id : astReplyValue});
            handleRequest.done(function(jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    astReviewList();
                    YHu.ui.alert("删除ast讨论信息成功！");
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