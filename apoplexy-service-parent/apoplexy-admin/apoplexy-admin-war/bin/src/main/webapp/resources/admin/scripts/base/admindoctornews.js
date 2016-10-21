var queryDoctorNewsCondition = {
	pageNum:1,
	pageSize:10,
	type:null,
	newType:null,
	title:null
};

var adminIno = {
	    id:null,
	    title:null,
	    subTitle:null,
	    newType:null,
	    smallLogo:null,
	    source:null,
	    content:null,
	    createTime:null,
	    type:null
	};


var clearCondition = function () {

    $("#admin-doctornews-name").val('');
    $("#doctorselect").val('');
    $("#doctor-content").val('');
};


var queryAdminDoctorNewsList = function() {
	url = YHu.util.ctxPath("/doctorNews/queryDoctorNews");
	YHu.ui.tableLoad("#admindoctornewstable", url, queryDoctorNewsCondition);
};

var setCondition = function() {
	queryDoctorNewsCondition.type = $("#doctor-content").val();
	queryDoctorNewsCondition.newType=  $("#doctorselect").val();
	queryDoctorNewsCondition.title = $("#admin-doctornews-name").val();
};

$("document").ready(function() {

	$("#search").click(function(){
		
		 setCondition();
		 queryAdminDoctorNewsList();
	});

	
	$("#clear").click(function() {

	    clearCondition();
	    setCondition();
	    queryAdminDoctorNewsList();
	});

	
	
	setCondition();
	queryAdminDoctorNewsList();
	
	
});


var deletedoctornews = function(no) {
	 var detailNo = "detailNo" + no;
	    var detailValue = $("#" + detailNo).text();

	    layer.open({
	        type : 1,
	        title : "删除医生端咨询信息",
	        area: ['350px', '180px'],
	        content: $('#deleteLayer'),
	        btn : [ "确定", "取消" ],
	        yes : function() {
	            YHu.ui.loading();
	            var handleRequest = $.post(YHu.util.ctxPath('/doctorNews/deleteDoctorNews'),
	                {id : detailValue});
	            handleRequest.done(function(jsonResult) {
	                YHu.ui.closeLoading();
	                if (jsonResult.success) {
	                    YHu.ui.closeAllLayer();
	                    queryAdminDoctorNewsList();
	                    YHu.ui.alert("删除医生端咨询信息成功！");
	                } else {
	                    YHu.ui.closeAllLayer();
	                    YHu.ui.alert(jsonResult.message);
	                }
	            });
	        },
	        no : function() {
	            YHu.ui.closeAllLayer();
	        }
	    });
};


var addDoctorNews = function () {

    $("#addDoctorNewsTitle").val("");
    $("#addDoctorSubTitle").val("");
    $("#addDoctorSmallLogo").val("");
    $("#addDoctorNewsType").val("");
    $("#addDoctorType").val("");
    $("#addDoctorNewsSource").val("");
    $("#addDoctorNewsContent").val("");
    $("#addDoctorNewsCreateTime").val("");
    layer.open({
        type : 1,
        title : "新增医生端咨询资料端信息",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#addLayer'),
        btn : [ "确定", "取消" ],
        yes : function() {
            YHu.ui.loading();

            adminIno.title = $("#addDoctorNewsTitle").val();
            adminIno.subTitle = $("#addDoctorSubTitle").val();
            adminIno.newType = $("#addDoctorNewsType").val();
            adminIno.smallLogo = $("#addDoctorSmallLogo").val();
            adminIno.source = $("#addDoctorNewsSource").val();
            adminIno.content = $("#addDoctorNewsContent").val();
            adminIno.createTime = $("#addDoctorNewsCreateTime").val();
            adminIno.type = $("#addDoctorType").val();
            var handleRequest = $.post(YHu.util.ctxPath('/doctorNews/addDoctorNews'),adminIno);
            handleRequest.done(function(jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryAdminDoctorNewsList();
                    YHu.ui.alert("新增医生端咨询资料成功！");
                } else {
                    YHu.ui.closeAllLayer();
                    YHu.ui.alert(jsonResult.message);
                }
            });
        },
        no : function() {
            YHu.ui.closeAllLayer();
        }
    });
};


var modfiydoctornews = function(no) {

    var detailValue = $("#detailNo" + no).text();
     
    $("#modifyDoctorNewsTitle").val($("#title" + no).text());
    $("#modifyDoctorSubTitle").val($("#subTitle" + no).text());
    $("#modifyDoctorSmallLogo").val($("#smallLogo" + no).text());
     if($("#doctorNewsType"+no).text()=="1"){
    	 $("#modifyDoctorNewsType").find("option[value='1']").attr("selected",true);
     } else if($("#doctorNewsType"+no).text()=="2"){
    	 $("#modifyDoctorNewsType").find("option[value='2']").attr("selected",true);
     }
     
     if($("#type"+no).text()=="0"){
    	 $("#modifyDoctorType").find("option[value='0']").attr("selected",true);
     } else if($("#type"+no).text()=="1"){
    	 $("#modifyDoctorType").find("option[value='1']").attr("selected",true);
     } else if($("#type"+no).text()=="2"){
    	 $("#modifyDoctorType").find("option[value='2']").attr("selected",true);
     }
     
    $("#modifyDoctorNewsSource").val($("#source"+no).text());
    $("#modifyDoctorNewsContent").val($("#content" + no).text());
    $("#modifyDoctorNewsCreateTime").val($("#createTime"+ no).text());

    
    layer.open({
        type : 1,
        title : "修改医生端咨询资料",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#modifyLayer'),
        btn : [ "确定", "取消" ],
        yes : function() {
            YHu.ui.loading();
            adminIno.id = detailValue;
            adminIno.title = $("#modifyDoctorNewsTitle").val();
            adminIno.subTitle = $("#modifyDoctorSubTitle").val();
            adminIno.newType = $("#modifyDoctorNewsType").val();
            adminIno.smallLogo = $("#modifyDoctorSmallLogo").val();
            adminIno.source = $("#modifyDoctorNewsSource").val();
            adminIno.content = $("#modifyDoctorNewsContent").val();
            adminIno.createTime = $("#modifyDoctorNewsCreateTime").val();
            adminIno.type = $("#modifyDoctorType").val();
            
            var handleRequest = $.post(YHu.util.ctxPath('/doctorNews/updateDoctorNews'), adminIno);
            handleRequest.done(function(jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryAdminDoctorNewsList();
                    YHu.ui.alert("修改医生端咨询资料成功！");
                } else {
                    YHu.ui.closeAllLayer();
                    YHu.ui.alert(jsonResult.message);
                }
            });
        },
        no : function() {
            YHu.ui.closeAllLayer();
        }
    });
};


var pageHandler = function(event, pageSize) {
	event.preventDefault();
	queryDoctorNewsCondition.pageNum = event.target.title;
	if (pageSize != null && typeof (pageSize) != "undefined") {
		queryDoctorNewsCondition.pageSize = pageSize;
		queryDoctorNewsCondition.pageNum = 1;
	}
	queryAdminDoctorNewsList();
};