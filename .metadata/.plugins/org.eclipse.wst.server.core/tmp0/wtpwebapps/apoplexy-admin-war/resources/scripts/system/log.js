var queryLogCondition = {
	pageNum:1,
	pageSize:10,
	model:null,
	userName:null,
	startTime:null,
	endTime:null
};

var queryLogList = function() {
	
	url = YHu.util.ctxPath("/log/queryLogList");
	YHu.ui.tableLoad("#logtable", url, queryLogCondition);
 
};

var setCondition = function() {
	queryLogCondition.model = $("#model").val();
	queryLogCondition.userName = $("#user-name").val();
	queryLogCondition.startTime = $("#start-time").val();
	queryLogCondition.endTime = $("#end-time").val();
};

$("document").ready(function() {

	$("#search").click(function(){
		
		 setCondition();
		 queryLogList();
	});
	$("#clear").click(function() {

		$("#model").val("");
		$("#user-name").val("");
		$("#start-time").val("");
		$("#end-time").val("");
		setCondition();
		queryLogList();
	});

	 
	setCondition();
	queryLogList();
});

var pageHandler = function(event, pageSize) {
	event.preventDefault();
	queryLogCondition.pageNum = event.target.title;
	if (pageSize != null && typeof (pageSize) != "undefined") {
		queryLogCondition.pageSize = pageSize;
		queryLogCondition.pageNum = 1;
	}
	queryLogList();
};

/*删除日志*/
var deleteLog = function(no) {
	
	var id = "id" + no;
    var discussValue = $("#" + id).text();
	layer.open({
	    type: 1,
	    title: '删除日志',
	    area: ['350px', '180px'],
	    /*maxWidth: '450px',*/
	    shadeClose: false,
	    content: $('#deleteLayer'),
	    btn: ['确定', '取消'],
	    btn1: function(index, layero){
	    	YHu.ui.loading();
            var handleRequest = $.post(YHu.util.ctxPath('/log/deleteLogList'),
                {id : discussValue});
            handleRequest.done(function(jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryVersionList();
                    YHu.ui.alert("删除日志成功！");
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


