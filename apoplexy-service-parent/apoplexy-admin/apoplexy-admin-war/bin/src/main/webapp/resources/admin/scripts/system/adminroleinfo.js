var queryRoleinfoAdminCondition = {
	pageNum:1,
	pageSize:10,
	roleName:null
};

var roleInfo = {
    id:null,
    roleName:null,
    description:null
};

var queryRoleinfoAdminList = function() {
	
	url = YHu.util.ctxPath("/roleInfo/queryRoleInfo");
	YHu.ui.tableLoad("#adminroleinfotable", url, queryRoleinfoAdminCondition);

};

var setCondition = function() {
	queryRoleinfoAdminCondition.roleName = $("#admin-role-name").val();
};

$("document").ready(function() {

	$("#search").click(function(){
		
		 setCondition();
		 queryRoleinfoAdminList();
	});

	setCondition();
	queryRoleinfoAdminList();
});

$("#clear").click(function() {

    clearCondition();
    setCondition();
    queryRoleinfoAdminList();

});

var clearCondition = function () {

    $("#admin-role-name").val('');

};


var pageHandler = function(event, pageSize) {
	event.preventDefault();
	queryRoleinfoAdminCondition.pageNum = event.target.title;
	if (pageSize != null && typeof (pageSize) != "undefined") {
		queryRoleinfoAdminCondition.pageSize = pageSize;
		queryRoleinfoAdminCondition.pageNum = 1;
	}
	queryRoleinfoAdminList();
}; 

var deleteRoleInfo = function(no) {

    var detailValue = $("#detailNo" + no).text();

    $.layer({
        type : 1,
        title : "删除角色信息",
        area: ['350px', '180px'],
        content: $('#deleteLayer'),
        btn : [ "确定", "取消" ],
        yes : function() {
            YHu.ui.loading();
            var handleRequest = $.post(YHu.util.ctxPath('/roleInfo/deleteRoleInfo'), {id : detailValue});
            handleRequest.done(function(jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryRoleinfoAdminList();
                    YHu.ui.alert("删除角色信息成功！");
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

var modifyRoleInfo = function(no) {

    var detailValue = $("#detailNo" + no).text();

    $("#modifyRoleName").val($("#roleName" + no).text());
    $("#modifyDesc").val($("#desc" + no).text());

    layer.open({
        type : 1,
        title : "修改角色信息",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#modifyLayer'),
        btn : [ "确定", "取消" ],
        yes : function() {
            YHu.ui.loading();

            roleInfo.id = detailValue;
            roleInfo.roleName = $("#modifyRoleName").val();
            roleInfo.description = $("#modifyDesc").val();

            var handleRequest = $.post(YHu.util.ctxPath('/roleInfo/updateRoleInfo'), roleInfo);
            handleRequest.done(function(jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryRoleinfoAdminList();
                    YHu.ui.alert("修改角色信息成功！");
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

var addRoleInfo = function () {

    $("#addRoleName").val("");
    $("#addDesc").val("");

    layer.open({
        type : 1,
        title : "新增角色信息",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#addLayer'),
        btn : [ "确定", "取消" ],
        yes : function() {
            YHu.ui.loading();

            roleInfo.roleName = $("#addRoleName").val();
            roleInfo.description = $("#addDesc").val();

            var handleRequest = $.post(YHu.util.ctxPath('/roleInfo/addRoleInfo'),roleInfo);
            handleRequest.done(function(jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryRoleinfoAdminList();
                    YHu.ui.alert("新增角色信息成功！");
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