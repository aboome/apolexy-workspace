var queryAdminCondition = {
	pageNum:1,
	pageSize:10,
	userName:null
};

var adminIno = {
    id:null,
    userName:null,
    passWord:null,
    phone:null,
    email:null,
    job:null,
    userDesc:null,
    realName:null
};

var queryAdminList = function() {
	
	url = YHu.util.ctxPath("/userManagement/queryUserManagement");
	YHu.ui.tableLoad("#adminTable", url, queryAdminCondition);

};

var setCondition = function() {
	queryAdminCondition.userName = $("#admin-name").val();
};

$("#clear").click(function() {

    clearCondition();
    setCondition();
    queryAdminList();
});

var clearCondition = function () {

    $("#admin-name").val('');
};

$("document").ready(function() {

	$("#search").click(function(){
		
		 setCondition();
		 queryAdminList();
	});

	setCondition();
	queryAdminList();
});

var deleteMember = function(no) {
    var detailNo = "detailNo" + no;
    var detailValue = $("#" + detailNo).text();

    layer.open({
        type : 1,
        title : "删除管理员信息",
        area: ['350px', '180px'],
        content: $('#deleteLayer'),
        btn : [ "确定", "取消" ],
        yes : function() {
            YHu.ui.loading();
            var handleRequest = $.post(YHu.util.ctxPath('/userManagement/deleteUserManagement'),
                {id : detailValue});
            handleRequest.done(function(jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryAdminList();
                    YHu.ui.alert("删除管理员信息成功！");
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

var modifyMember = function(no) {

    var detailValue = $("#detailNo" + no).text();

    $("#modifyAdminName").val($("#userName" + no).text());
    $("#modifyPhone").val($("#phone" + no).text());
    $("#modifyEmail").val($("#email" + no).text());
    $("#modifyJob").val($("#job" + no).text());
    $("#modifyDesc").val($("#desc" +no).text());
    $("#modifyRealName").val($("#realName" + no).text());
    $("#modifyPassword").val("");
    layer.open({
        type : 1,
        title : "修改管理员信息",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#modifyLayer'),
        btn : [ "确定", "取消" ],
        yes : function() {
            YHu.ui.loading();
            adminIno.id = detailValue;
            adminIno.userName = $("#modifyAdminName").val();
            adminIno.passWord = hex_md5($("#modifyPassword").val());
            adminIno.phone = $("#modifyPhone").val();
            adminIno.email = $("#modifyEmail").val();
            adminIno.job = $("#modifyJob").val();
            adminIno.userDesc = $("#modifyDesc").val();
            adminIno.realName = $("#modifyRealName").val();
            
            var handleRequest = $.post(YHu.util.ctxPath('/userManagement/updateUserManagement'), adminIno);
            handleRequest.done(function(jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryAdminList();
                    YHu.ui.alert("修改管理员信息成功！");
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

var addMember = function () {

    $("#addAdminName").val("");
    $("#addPassword").val("");
    $("#addPhone").val("");
    $("#addEmail").val("");
    $("#addJob").val("");
    $("#addDesc").val("");
    $("#addRealName").val("");

    layer.open({
        type : 1,
        title : "新增管理员信息",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#addLayer'),
        btn : [ "确定", "取消" ],
        yes : function() {
            YHu.ui.loading();

            adminIno.userName = $("#addAdminName").val();
            adminIno.passWord = hex_md5($("#addPassword").val());
            adminIno.phone = $("#addPhone").val();
            adminIno.email = $("#addEmail").val();
            adminIno.job = $("#addJob").val();
            adminIno.userDesc = $("#addDesc").val();
            adminIno.realName = $("#addRealName").val();

            var handleRequest = $.post(YHu.util.ctxPath('/userManagement/addUserManagement'),adminIno);
            handleRequest.done(function(jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryAdminList();
                    YHu.ui.alert("新增管理员信息成功！");
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
	queryAdminCondition.pageNum = event.target.title;
	if (pageSize != null && typeof (pageSize) != "undefined") {
		queryAdminCondition.pageSize = pageSize;
		queryAdminCondition.pageNum = 1;
	}
	queryAdminList();
};