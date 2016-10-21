var queryAdminHospitalCondition = {
	pageNum:1,
	pageSize:10,
	hospitalName:null
};

var adminIno = {
	    id:null,
	    hospitalName:null,
	    hospitalDesc:null,
	    hospitalAddr:null,
	    imageId:null,
	    parentHospitalName:null,
	    level:null,
	    lon:null,
	    lat:null,
	    areaCode:null
	};

var queryAdminHospitalList = function() {
	var url = YHu.util.ctxPath("/hospital/queryHospital");
	YHu.ui.tableLoad("#adminhospitaltable", url, queryAdminHospitalCondition);
};


var clearCondition = function () {

    $("#admin-hospital-name").val('');

};

var setCondition = function() {
	queryAdminHospitalCondition.hospitalName = $("#admin-hospital-name").val();
};

$("document").ready(function() {

	setCondition();
	queryAdminHospitalList();
});

$("#search").click(function(){
	
	 setCondition();
	 queryAdminHospitalList();
});

$("#clear").click(function() {

    clearCondition();
    setCondition();
    queryAdminHospitalList();
});

var addHospital = function () {

    $("#addHospitalName").val("");
    $("#addHospitaldesc").val("");
    $("#addHospitalAddr").val("");
    $("#addimageId").val("");
    $("#addLevel").val("");
    $("#addparentHospitalName").val("");
    $("#addHospitalLon").val("");
    $("#addHospitalLat").val("");
    $("#addAreaCode").val("");
    layer.open({
        type : 1,
        title : "新增医院信息",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#addLayer'),
        btn : [ "确定", "取消" ],
        yes : function() {
            YHu.ui.loading();

            adminIno.hospitalName = $("#addHospitalName").val();
            adminIno.hospitalDesc = $("#addHospitaldesc").val();
            adminIno.hospitalAddr = $("#addHospitalAddr").val();
            adminIno.imageId = $("#addimageId").val();
            adminIno.parentHospitalName = $("#addparentHospitalName").val();
            adminIno.level = $("#addLevel").val();
            adminIno.lon = $("#addHospitalLon").val();
            adminIno.lat =  $("#addHospitalLat").val();
            adminIno.areaCode =  $("#addAreaCode").val();
            var handleRequest = $.post(YHu.util.ctxPath('/hospital/addHospital'),adminIno);
            handleRequest.done(function(jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryAdminHospitalList();
                    YHu.ui.alert("新增医院信息成功！");
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


var modfiyHospital = function(no) {

    var detailValue = $("#detailNo" + no).text();
     
    $("#modifyHospitalName").val($("#hospitalName" + no).text());
    $("#modifyHospitaldesc").val($("#hospitalDesc" + no).text());
    $("#modifyHospitalAddr").val($("#hospitalAddr"+no).text());
    
     if($("#level"+no).text()=="0"){
    	 $("#modifyLevel").find("option[value='0']").attr("selected",true);
     } else if($("#level"+no).text()=="1"){
    	 $("#modifyLevel").find("option[value='1']").attr("selected",true);
     }else if($("#level"+no).text()=="2"){
    	 $("#modifyLevel").find("option[value='2']").attr("selected",true);
     }else if($("#level"+no).text()=="3"){
    	 $("#modifyLevel").find("option[value='3']").attr("selected",true);
     }
  
    $("#modifyimageId").val($("#imageId" + no).text());
    $("#modifyparentHospitalName").val($("#parentHospitalName"+ no).text());
    $("#modifyHospitalLon").val($("#lon"+no).text());
    $("#modifyHospitalLat").val($("#lat"+no).text());
    $("#modifyAreaCode").val($("#areaCode"+no).text());
    layer.open({
        type : 1,
        title : "修改医院信息",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#modifyLayer'),
        btn : [ "确定", "取消" ],
        yes : function() {
            YHu.ui.loading();
            adminIno.id = detailValue;
            adminIno.hospitalName = $("#modifyHospitalName").val();
            adminIno.hospitalDesc = $("#modifyHospitaldesc").val();
            adminIno.hospitalAddr = $("#modifyHospitalAddr").val();
            adminIno.imageId = $("#modifyimageId").val();
            adminIno.parentHospitalName = $("#modifyparentHospitalName").val();
            adminIno.level = $("#modifyLevel").val();
            adminIno.lon = $("#modifyHospitalLon").val();
            adminIno.lat =  $("#modifyHospitalLat").val();
            adminIno.areaCode =  $("#modifyAreaCode").val();
            
            var handleRequest = $.post(YHu.util.ctxPath('/hospital/updateHospital'), adminIno);
            handleRequest.done(function(jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryAdminHospitalList();
                    YHu.ui.alert("修改医院信息成功！");
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



var deleteHospital = function(no) {
    var detailNo = "detailNo" + no;
    var detailValue = $("#" + detailNo).text();

    layer.open({
        type : 1,
        title : "删除医院信息",
        area: ['350px', '180px'],
        content: $('#deleteLayer'),
        btn : [ "确定", "取消" ],
        yes : function() {
            YHu.ui.loading();
            var handleRequest = $.post(YHu.util.ctxPath('/hospital/deleteHospital'),
                {id : detailValue});
            handleRequest.done(function(jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryAdminHospitalList();
                    YHu.ui.alert("删除医院信息成功！");
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
	queryAdminHospitalCondition.pageNum = event.target.title;
	if (pageSize != null && typeof (pageSize) != "undefined") {
		queryAdminHospitalCondition.pageSize = pageSize;
		queryAdminHospitalCondition.pageNum = 1;
	}
	queryAdminHospitalList();
};