var querypatienNewsCondition = {
	pageNum:1,
	pageSize:10,
	type:null,
	title:null
};

var queryAdminPatienNewsList = function() {
	url = YHu.util.ctxPath("/patienNews/queryPatienNews");
	YHu.ui.tableLoad("#adminpatientnewstable", url, querypatienNewsCondition);
};

var setCondition = function() {
	querypatienNewsCondition.type = $("#patientnews-content").val();
	querypatienNewsCondition.title = $("#admin-patientnews-name").val();
};

$("document").ready(function() {

	$("#search").click(function(){
		
		 setCondition();
		 queryAdminPatienNewsList();
	});

	setCondition();
	queryAdminPatienNewsList();
});



var adminIno = {
	    id:null,
	    title:null,
	    subTitle:null,
	    smallLogo:null,
	    source:null,
	    content:null,
	    createTime:null,
	    type:null
	};

var deletepatientnews = function(no) {
    var detailNo = "detailNo" + no;
    var detailValue = $("#" + detailNo).text();

    $.layer({
        type : 1,
        title : "删除患者端咨询信息",
        shade : [ 0.3, "#000" ],
        offset : [ '150px', '' ],
        border : [ 5, 0.3, '#000' ],
        area : [ '503px', '200px' ],
        page : {
            dom : '#deleteLayer'
        },
        btns : 2,
        btn : [ "确定", "取消" ],
        yes : function() {
            YHu.ui.loading();
            var handleRequest = $.post(YHu.util.ctxPath('/patienNews/deletePatienNews'),
                {id : detailValue});
            handleRequest.done(function(jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryAdminPatienNewsList();
                    YHu.ui.alert("删除患者端咨询材料成功！");
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


var addPatientNews = function () {

    $("#addPatientNewsTitle").val("");
    $("#addPatientSubTitle").val("");
    $("#addPatientSmallLogo").val("");
    $("#addPatientType").val("");
    $("#addPatientNewsSource").val("");
    $("#addPatientNewsContent").val("");
    $("#addPatientNewsCreateTime").val("");

    layer.open({
        type : 1,
        title : "新增患者端咨询资料端信息",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#addLayer'),
        btn : [ "确定", "取消" ],
        yes : function() {
            YHu.ui.loading();

            adminIno.title = $("#addPatientNewsTitle").val();
            adminIno.subTitle = $("#addPatientSubTitle").val();
            adminIno.smallLogo = $("#addPatientSmallLogo").val();
            adminIno.source = $("#addPatientNewsSource").val();
            adminIno.content = $("#addPatientNewsContent").val();
            adminIno.createTime = $("#addPatientNewsCreateTime").val();
            adminIno.type = $("#addPatientType").val();
            var handleRequest = $.post(YHu.util.ctxPath('/patienNews/addPatienNews'),adminIno);
            handleRequest.done(function(jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryAdminPatienNewsList();
                    YHu.ui.alert("新增患者端咨询资料成功！");
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


var modfiypatientnews = function(no) {

    var detailValue = $("#detailNo" + no).text();
     
    $("#modifyPatientNewsTitle").val($("#title" + no).text());
    $("#modifyPatientSubTitle").val($("#subTitle" + no).text());
    $("#modifyPatientSmallLogo").val($("#smallLogo" + no).text());

     
     if($("#newsType"+no).text()=="0"){
    	 $("#modifyPatientType").find("option[value='0']").attr("selected",true);
     } else if($("#newsType"+no).text()=="1"){
    	 $("#modifyPatientType").find("option[value='1']").attr("selected",true);
     } else if($("#newsType"+no).text()=="2"){
    	 $("#modifyPatientType").find("option[value='2']").attr("selected",true);
     }
     
    $("#modifyPatientNewsSource").val($("#source"+no).text());
    $("#modifyPatientNewsContent").val($("#content" + no).text());
    $("#modifyPatientNewsCreateTime").val($("#createTime"+ no).text());

    
    layer.open({
        type : 1,
        title : "修改患者端咨询资料",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#modifyLayer'),
        btn : [ "确定", "取消" ],
        yes : function() {
            YHu.ui.loading();
            adminIno.id = detailValue;
            adminIno.title = $("#modifyPatientNewsTitle").val();
            adminIno.subTitle = $("#modifyPatientSubTitle").val();
    
            adminIno.smallLogo = $("#modifyPatientSmallLogo").val();
            adminIno.source = $("#modifyPatientNewsSource").val();
            adminIno.content = $("#modifyPatientNewsContent").val();
            adminIno.createTime = $("#modifyPatientNewsCreateTime").val();
            adminIno.type = $("#modifyPatientType").val();
            
            var handleRequest = $.post(YHu.util.ctxPath('/patienNews/updatePatienNews'), adminIno);
            handleRequest.done(function(jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryAdminPatienNewsList();
                    YHu.ui.alert("修改患者端咨询资料成功！");
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
	querypatienNewsCondition.pageNum = event.target.title;
	if (pageSize != null && typeof (pageSize) != "undefined") {
		querypatienNewsCondition.pageSize = pageSize;
		querypatienNewsCondition.pageNum = 1;
	}
	queryAdminPatienNewsList();
};