var queryAdminCondition = {
    pageNum: 1,
    pageSize: 10,
    userName: null
};

var adminIno = {
    id: null,
    userName: null,
    passWord: null,
    phone: null,
    email: null,
    job: null,
    userDesc: null,
    realName: null
};

var modifyPasswordInfo = {
    oldPassword: null,
    newPassword: null
};

var queryAdminList = function () {

    url = YHu.util.ctxPath("/userManagement/queryUserManagement");
    YHu.ui.tableLoad("#adminTable", url, queryAdminCondition);

};

var setCondition = function () {
    queryAdminCondition.userName = $("#admin-name").val();
};

$("#clear").click(function () {

    clearCondition();
    setCondition();
    queryAdminList();
});

var clearCondition = function () {

    $("#admin-name").val('');
};

$("document").ready(function () {

    $("#search").click(function () {

        setCondition();
        queryAdminList();
    });

    setCondition();
    queryAdminList();
});

var deleteMember = function (no) {
    var detailNo = "detailNo" + no;
    var detailValue = $("#" + detailNo).text();

    layer.open({
        type: 1,
        title: "删除管理员信息",
        area: ['350px', '180px'],
        content: $('#deleteLayer'),
        btn: ["确定", "取消"],
        yes: function () {
            YHu.ui.loading();
            var handleRequest = $.post(YHu.util.ctxPath('/userManagement/deleteUserManagement'),
                {id: detailValue});
            handleRequest.done(function (jsonResult) {
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
        no: function () {
            YHu.ui.closeAllLayer();
        }
    });
};

var modifyMember = function (no) {

    var detailValue = $("#detailNo" + no).text();

    $("#modifyAdminName").val($("#userName" + no).text());
    $("#modifyPhone").val($("#phone" + no).text());
    $("#modifyEmail").val($("#email" + no).text());
    $("#modifyJob").val($("#job" + no).text());
    $("#modifyDesc").val($("#desc" + no).text());
    $("#modifyRealName").val($("#realName" + no).text());

    $('.form-group').removeClass('has-error');
    $('.form-group').removeClass('has-success');
    $('span.help-block').remove();

    layer.open({
        type: 1,
        title: "修改管理员信息",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#modifyLayer'),
        btn: ["确定", "取消"],
        yes: function () {

            if (!$('#modifyAdmin').valid()) {
                return 0;
            }
            YHu.ui.loading();
            adminIno.id = detailValue;
            adminIno.userName = $("#modifyAdminName").val();
            adminIno.phone = $("#modifyPhone").val();
            adminIno.email = $("#modifyEmail").val();
            adminIno.job = $("#modifyJob").val();
            adminIno.userDesc = $("#modifyDesc").val();
            adminIno.realName = $("#modifyRealName").val();

            var handleRequest = $.post(YHu.util.ctxPath('/userManagement/updateUserManagement'), adminIno);
            handleRequest.done(function (jsonResult) {
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
        no: function () {
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

    $('.form-group').removeClass('has-error');
    $('.form-group').removeClass('has-success');
    $('span.help-block').remove();

    layer.open({
        type: 1,
        title: "新增管理员信息",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#addLayer'),
        btn: ["确定", "取消"],
        yes: function () {

            if (!$('#addAdmin').valid()) {
                return 0;
            }

            YHu.ui.loading();

            adminIno.userName = $("#addAdminName").val();
            adminIno.passWord = hex_md5($("#addPassword").val());
            adminIno.phone = $("#addPhone").val();
            adminIno.email = $("#addEmail").val();
            adminIno.job = $("#addJob").val();
            adminIno.userDesc = $("#addDesc").val();
            adminIno.realName = $("#addRealName").val();

            var handleRequest = $.post(YHu.util.ctxPath('/userManagement/addUserManagement'), adminIno);
            handleRequest.done(function (jsonResult) {
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
        no: function () {
            YHu.ui.closeAllLayer();
        }
    });
};

var resetPassword = function (no) {

    var id = $("#detailNo" + no).text();

    $("#newPassword").val("");
    $("#repeatPassword").val("");

    $('.form-group').removeClass('has-error');
    $('.form-group').removeClass('has-success');
    $('span.help-block').remove();

    layer.open({
        type: 1,
        title: "重置管理员密码",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#resetPasswordLayer'),
        btn: ["确定", "取消"],
        yes: function () {
            if (!$('#resetPassword').valid()) {
                return 0;
            }
            YHu.ui.loading();

            adminIno.id = id;
            adminIno.passWord = hex_md5($("#newPassword").val());

            var handleRequest = $.post(YHu.util.ctxPath('/userManagement/resetPassword'), adminIno);
            handleRequest.done(function (jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryAdminList();
                    YHu.ui.alert("重置管理员密码成功！");
                } else {
                    YHu.ui.closeAllLayer();
                    YHu.ui.alert(jsonResult.message);
                }
            });
        },
        no: function () {
            YHu.ui.closeAllLayer();
        }
    });

};

var unLockUser = function (no) {

    var id = $("#detailNo" + no).text();

    layer.open({
        type: 1,
        title: "解锁管理员",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#unlockLayer'),
        btn: ["确定", "取消"],
        yes: function () {
            YHu.ui.loading();
            var handleRequest = $.post(YHu.util.ctxPath('/userManagement/unlockUser'), {id: id});
            handleRequest.done(function (jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryAdminList();
                    YHu.ui.alert("解锁管理员成功！");
                } else {
                    YHu.ui.closeAllLayer();
                    YHu.ui.alert(jsonResult.message);
                }
            });
        },
        no: function () {
            YHu.ui.closeAllLayer();
        }
    });

};

var bindRole = function (no) {

    var id = $("#detailNo" + no).text();

    $("#roleInfo").val($("#roleId" + no).text());

    layer.open({
        type: 1,
        title: "绑定角色",
        area: ['350px', '180px'],
        content: $('#bindLayer'),
        btn: ["确定", "取消"],
        yes: function () {
            YHu.ui.loading();

            var roleId = $("#roleInfo").val();

            var handleRequest = $.post(YHu.util.ctxPath('/userManagement/bindingRole'), {userId: id, roleId: roleId});
            handleRequest.done(function (jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    YHu.ui.alert("绑定角色成功！", function () {
                        queryAdminList();
                        YHu.ui.closeAllLayer();
                    });
                } else {
                    YHu.ui.closeAllLayer();
                    YHu.ui.alert(jsonResult.message);
                }
            });
        },
        no: function () {
            YHu.ui.closeAllLayer();
        }
    });

};

var pageHandler = function (event, pageSize) {
    event.preventDefault();
    queryAdminCondition.pageNum = event.target.title;
    if (pageSize != null && typeof (pageSize) != "undefined") {
        queryAdminCondition.pageSize = pageSize;
        queryAdminCondition.pageNum = 1;
    }
    queryAdminList();
};

var modifyPassword = function () {

    layer.open({
        type: 1,
        title: "修改密码",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#modifyPasswordLayer'),
        btn: ["确定", "取消"],
        yes: function () {

            if (!$('#modifyPasswordForm').valid()) {
                return 0;
            }

            YHu.ui.loading();

            modifyPasswordInfo.oldPassword = hex_md5($("#oldPassword").val());
            modifyPasswordInfo.newPassword = hex_md5($("#newPassword").val());

            var handleRequest = $.post(YHu.util.ctxPath('/userManagement/modifyPassword'), modifyPasswordInfo);
            handleRequest.done(function (jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryAdminList();
                    YHu.ui.alert("修改密码成功");
                } else {
                    YHu.ui.closeAllLayer();
                    YHu.ui.alert(jsonResult.message);
                }
            });
        },
        no: function () {
            YHu.ui.closeAllLayer();
        }
    });

};