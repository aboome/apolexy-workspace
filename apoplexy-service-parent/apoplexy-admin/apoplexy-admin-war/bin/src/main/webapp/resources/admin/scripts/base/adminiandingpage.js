var queryDoctorCondition = {
		owner:null,
};

var queryInpatientCondition = {
		owner:null,
};

var queryAdminDoctorList = function() {
	url = YHu.util.ctxPath("/iandingPage/queryDoctorIandingPage");
	YHu.ui.tableLoad("#adminiandingdoctortable", url, queryDoctorCondition);
};

var queryAdminPatientList = function() {
	url = YHu.util.ctxPath("/iandingPage/querypatientIandingPage");
	YHu.ui.tableLoad("#adminiandingpatienttable", url, queryInpatientCondition);
};

var setDoctorCondition = function() {
	queryDoctorCondition.owner = "1";
};

var setInpatientCondition = function() {
	queryInpatientCondition.owner = "2";
};

$("document").ready(function() {

   
	setDoctorCondition();
	queryAdminDoctorList();
	
	setInpatientCondition();
	queryAdminPatientList();
});


var deletedoctort = function(no) {
    var detailNo = "detailNo" + no;
    var detailValue = $("#" + detailNo).text();

    $.layer({
        type : 1,
        title : "删除医生端开机动画信息",
        shade : [ 0.3, "#000" ],
        offset : [ '150px', '' ],
        border : [ 5, 0.3, '#000' ],
        area : [ '503px', '200px' ],
        page : {
            dom : '#deleteDoctorLayer'
        },
        btns : 2,
        btn : [ "确定", "取消" ],
        yes : function() {
            YHu.ui.loading();
            var handleRequest = $.post(YHu.util.ctxPath('/iandingPage/deleteDoctorIandingPage'),
                {id : detailValue});
            handleRequest.done(function(jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryAdminDoctorList();
                    YHu.ui.alert("删除医生端开机动画信息成功！");
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

var deletepatient = function(no) {
    var detailNo = "pantientDetailNo" + no;
    var detailValue = $("#" + detailNo).text();

    $.layer({
        type : 1,
        title : "删除患者端开机动画信息",
        shade : [ 0.3, "#000" ],
        offset : [ '150px', '' ],
        border : [ 5, 0.3, '#000' ],
        area : [ '503px', '200px' ],
        page : {
            dom : '#deletePatientLayer'
        },
        btns : 2,
        btn : [ "确定", "取消" ],
        yes : function() {
            YHu.ui.loading();
            var handleRequest = $.post(YHu.util.ctxPath('/iandingPage/deletePatientIandingPage'),
                {id : detailValue});
            handleRequest.done(function(jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryAdminPatientList();
                    YHu.ui.alert("删除患者端开机动画信息成功！");
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