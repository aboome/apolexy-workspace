var queryAdminDoctorCondition = {
	pageNum:1,
	pageSize:10,
	doctorName:null,
	hospital:null
};

var adminIno = {
	    id:null,
	    doctorName:null,
	    hospital:null,
	    department:null,
	    title:null,
	    job:null,
	    effectiveTime:null,
	    sex:null,
	    phone:null,
	    effectiveTime:null,
	    email:null
	};


var queryAdminDoctorList = function() {
	url = YHu.util.ctxPath("/doctor/queryDoctor");
	YHu.ui.tableLoad("#admindoctortable", url, queryAdminDoctorCondition);
};

var setCondition = function() {
	queryAdminDoctorCondition.doctorName = $("#admin-doctor-name").val();
	queryAdminDoctorCondition.hospital=  $("#admin-doctorhospital-name").val();
};

var clearCondition = function () {

    $("#admin-doctor-name").val('');
    $("#admin-doctorhospital-name").val('');
};


$("document").ready(function() {

	$("#search").click(function(){
		
		 setCondition();
		 queryAdminDoctorList();
	});
	
	$("#clear").click(function() {

	    clearCondition();
	    setCondition();
	    queryAdminDoctorList();
	});


	setCondition();
	queryAdminDoctorList();
});








var deleteDoctor = function(no) {
	 var detailNo = "detailNo" + no;
	    var detailValue = $("#" + detailNo).text();

	    layer.open({
	        type : 1,
	        title : "删除医生信息",
	        area: ['350px', '180px'],
	        content: $('#deleteLayer'),
	        btn : [ "确定", "取消" ],
	        yes : function() {
	            YHu.ui.loading();
	            var handleRequest = $.post(YHu.util.ctxPath('/doctor/deleteDoctor'),
	                {id : detailValue});
	            handleRequest.done(function(jsonResult) {
	                YHu.ui.closeLoading();
	                if (jsonResult.success) {
	                    YHu.ui.closeAllLayer();
	                    queryAdminDoctorList();
	                    YHu.ui.alert("删除医生信息成功！");
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

var addDoctor = function () {

    $("#addDoctorName").val("");
    $("#addHospital").val("");
    $("#addDepartment").val("");
    $("#addTitle").val("");
    $("#addJob").val("");
    $("#addEffectiveTime").val("");
    $("#addSex").val("");
    $("#addPhone").val("");
    $("#addEmail").val("");
    layer.open({
        type : 1,
        title : "新增医生信息",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#addLayer'),
        btn : [ "确定", "取消" ],
        yes : function() {
            YHu.ui.loading();

            adminIno.doctorName = $("#addDoctorName").val();
            adminIno.hospital = $("#addHospital").val();
            adminIno.department = $("#addDepartment").val();
            adminIno.title = $("#addTitle").val();
            adminIno.job = $("#addJob").val();
            adminIno.effectiveTime = $("#addEffectiveTime").val();
            adminIno.sex = $("#addSex").val(); 
            adminIno.phone = $("#addPhone").val();
            adminIno.email =  $ ("addemail").val();
            var handleRequest = $.post(YHu.util.ctxPath('/doctor/addDoctor'),adminIno);
            handleRequest.done(function(jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryAdminDoctorList();
                    YHu.ui.alert("新增医生信息成功！");
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


var modfiyDoctor = function(no) {

    var detailValue = $("#detailNo" + no).text();
     
    $("#modifyDoctorName").val($("#doctorName" + no).text());
    $("#modifyHospital").val($("#hospital" + no).text());
    $("#modifyDepartment").val($("#department" + no).text());
     if($("#doctorSex"+no).text()=="1"){
    	 $("#modifySex").find("option[value='1']").attr("selected",true);
     } else if($("#doctorSex"+no).text()=="0"){
    	 $("#modifySex").find("option[value='0']").attr("selected",true);
     }
    $("#modifyPhone").val($("#doctorPhone"+no).text());
    $("#modifyTitle").val($("#title" + no).text());
    $("#modifyJob").val($("#job"+ no).text());
    $("#modifyEffectiveTime").val($("#effectiveTime"+no).text());
    $("#modifyEmail").val($("#doctorEmail"+no).text());
    
    layer.open({
        type : 1,
        title : "修改医生信息",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#modifyLayer'),
        btn : [ "确定", "取消" ],
        yes : function() {
            YHu.ui.loading();
            adminIno.id = detailValue;
            adminIno.doctorName = $("#modifyDoctorName").val();
            adminIno.hospital = $("#modifyHospital").val();
            adminIno.department = $("#modifyDepartment").val();
            adminIno.sex = $("#modifySex").val();
            adminIno.phone = $("#modifyPhone").val();
            adminIno.title = $("#modifyTitle").val();
            adminIno.job = $("#modifyJob").val();
            adminIno.effectiveTime = $("#modifyEffectiveTime").val();
            adminIno.email = $("#modifyEmail").val();
            
            var handleRequest = $.post(YHu.util.ctxPath('/doctor/updateDoctor'), adminIno);
            handleRequest.done(function(jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryAdminDoctorList();
                    YHu.ui.alert("修改医生信息成功！");
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
	queryAdminDoctorCondition.pageNum = event.target.title;
	if (pageSize != null && typeof (pageSize) != "undefined") {
		queryAdminDoctorCondition.pageSize = pageSize;
		queryAdminDoctorCondition.pageNum = 1;
	}
	queryAdminDoctorList();
};