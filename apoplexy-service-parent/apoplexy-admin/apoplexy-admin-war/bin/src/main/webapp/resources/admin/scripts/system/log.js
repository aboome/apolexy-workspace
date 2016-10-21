var queryLogCondition = {
	pageNum:1,
	pageSize:10,
	modelName:null,
	userName:null,
	startTime:null,
	endTime:null
 
	
};

var queryLogList = function() {
	
	url = YHu.util.ctxPath("/log/queryLogList");
	YHu.ui.tableLoad("#logtable", url, queryLogCondition);
 
};

var setCondition = function() {
	queryLogCondition.modelName = $("#model-name").val();
	queryLogCondition.userName = $("#user-name").val();
	queryLogCondition.satrtTime = $("#start-time").val();
	queryLogCondition.endtName = $("#end-time").val();
};

$("document").ready(function() {

	$("#search").click(function(){
		
		 setCondition();
		 queryLogList();
	});
	$("#clear").click(function() {

		$("#model-name").val("");
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