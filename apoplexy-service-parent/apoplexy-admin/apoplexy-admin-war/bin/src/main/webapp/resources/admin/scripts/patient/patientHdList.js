var queryHdCondition = {
	pageNum:1,
	pageSize:10,
	patientName:null,
	areaName:null,
	startTime:null,
	endTime:null
};

var queryPatientHdList = function() {
	
	url = YHu.util.ctxPath("/healthData/queryHDList");
	YHu.ui.tableLoad("#patientHdListtable", url,queryHdCondition);

};

var setCondition = function() {
	queryHdCondition.patientName = $("#patient-name").val();
	queryHdCondition.areaName = $("#area-name").val();
	queryHdCondition.startTime = $("#start-time").val();
	queryHdCondition.endTime = $("#end-time").val();
	
	
	
};

$("document").ready(function() {

	$("#search").click(function(){
		 queryHrCondition.pageNum = 1;
		 setCondition();
		 queryPatientHdList();
	});
	$("#clear").click(function() {

		$("#patient-name").val("");
		$("#area-name").val("");
		$("#start-time").val("");
		$("#end-time").val("");
		setCondition();
		queryPatientHdList();
	});

	setCondition();
	queryPatientHdList();
});

var pageHandler = function(event, pageSize) {
	event.preventDefault();
	queryHdCondition.pageNum = event.target.title;
	if (pageSize != null && typeof (pageSize) != "undefined") {
		queryHdCondition.pageSize = pageSize;
		queryHdCondition.pageNum = 1;
	}
	queryPatientHdList();
}; 