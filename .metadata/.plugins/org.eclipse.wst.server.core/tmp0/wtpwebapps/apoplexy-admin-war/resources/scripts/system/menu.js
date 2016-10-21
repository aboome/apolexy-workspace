var queryMenuCondition = {
    pageNum: 1,
    pageSize: 10

};

var menuLevel = {
    first: 1,
    second: 2,
    third: 3
};

var menuIno = {
    id: null,
    menuName: null,
    menuUrl: null,
    menuSort: null,
    menuLevel: null,
    parentMenu: null
};

var parentId;

var queryMenuList = function () {
    url = YHu.util.ctxPath("/Menu/queryMenu");
    YHu.ui.tableLoad("#menuTable", url, queryMenuCondition);
};

$("document").ready(function () {
    queryMenuList();
});

var pageHandler = function (event, pageSize) {
    event.preventDefault();
    queryMenuCondition.pageNum = event.target.title;
    if (pageSize != null && typeof (pageSize) != "undefined") {
        queryMenuCondition.pageSize = pageSize;
        queryMenuCondition.pageNum = 1;
    }
    queryMenuList();
};

var addMenu = function () {

    $("#addMenuName").val("");
    $("#addMenuUrl").val("");
    $("#addMenuSort").val("");
    $("#addMenuLevel").val("");
    $("#addParentMenu").val("");

    $('.form-group').removeClass('has-error');
    $('.form-group').removeClass('has-success');
    $('span.help-block').remove();

    layer.open({
        type: 1,
        title: "新增菜单信息",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#addLayer'),
        btn: ["确定", "取消"],
        yes: function () {

            if(!$('#addMenuForm').valid()){
                return 0;
            }
            YHu.ui.loading();
            menuIno.menuName = $("#addMenuName").val();
            menuIno.menuUrl = $("#addMenuUrl").val();
            menuIno.menuSort = $("#addMenuSort").val();
            menuIno.menuLevel = $("#addMenuLevel").val();
            menuIno.parentMenu = $("#addParentMenu").val();

            var handleRequest = $.post(YHu.util.ctxPath('/Menu/addMenu'), menuIno);
            handleRequest.done(function (jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    YHu.ui.alert("新增菜单信息成功",function () {
                        YHu.ui.closeAllLayer();
                        queryMenuList();
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


var modifyMenu = function (no) {

    var detailValue = $("#id" + no).text();
    var level = $("#menuLevel" + no).text();

    $('.form-group').removeClass('has-error');
    $('.form-group').removeClass('has-success');
    $('span.help-block').remove();

    $("#modifyMenuName").val($("#menuName" + no).text());
    $("#modifyMenuUrl").val($("#menuUrl" + no).text());
    $("#modifySort").val($("#sort" + no).text());
    parentId = $("#parentId" + no).text();
    $("#modifyMenuLevel").val($("#menuLevel" + no).text());
    $("#modifyMenuLevel").attr("disabled","disabled");
    $("#modifyMenuLevel").change();

    layer.open({
        type: 1,
        title: "修改菜单信息",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#modifyLayer'),
        btn: ["确定", "取消"],
        yes: function () {

            if(!$('#modifyMenuForm').valid()){
                return 0;
            }
            YHu.ui.loading();
            menuIno.id = detailValue;
            menuIno.menuName = $("#modifyMenuName").val();
            menuIno.menuUrl = $("#modifyMenuUrl").val();
            menuIno.menuSort = $("#modifySort").val();
            menuIno.menuLevel = $("#menuLevel" + no).text();
            menuIno.parentMenu = $("#modifyParentMenu").val();

            var handleRequest = $.post(YHu.util.ctxPath('/Menu/updateMenu'), menuIno);
            handleRequest.done(function (jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    YHu.ui.alert("修改菜单信息成功",function () {
                        YHu.ui.closeAllLayer();
                        queryMenuList();
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


var deleteMenu = function (no) {

    var detailNo = "id" + no;
    var detailValue = $("#" + detailNo).text();

    layer.open({
        type: 1,
        title: "删除菜单信息",
        area: ['350px', '180px'],
        content: $('#deleteLayer'),
        btn: ["确定", "取消"],
        yes: function () {
            YHu.ui.loading();
            var handleRequest = $.post(YHu.util.ctxPath('/Menu/deleteMenu'), {id: detailValue});
            handleRequest.done(function (jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    YHu.ui.alert("删除菜单信息成功",function () {
                        YHu.ui.closeAllLayer();
                        queryMenuList();
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

$("#addMenuLevel").change(function () {

    var level = $(this).val();

    if (level == menuLevel.first) {
        $("#addParentMenuDiv").addClass("none");
    } else {
        $("#addParentMenuDiv").removeClass("none");
        loadParentMenu(level);
    }
});

$("#modifyMenuLevel").change(function () {

    var level = $(this).val();

    if (level == menuLevel.first) {
        $("#modifyParentMenuDiv").addClass("none");
    } else {
        $("#modifyParentMenuDiv").removeClass("none");
        loadParentMenu(level);
    }
});

var loadParentMenu = function (menuLevel) {

    YHu.ui.loading();
    var handleRequest = $.post(YHu.util.ctxPath('/Menu/queryMenuList'),{menuLevel: menuLevel});
    handleRequest.done(function (jsonResult) {
        YHu.ui.closeLoading();
        if (jsonResult.success) {
            YHu.ui.closeLoading();
            var data = jsonResult.data;

            $("#addParentMenu").empty();
            $("#addParentMenu").append("<option value=/" /">请选择父级菜单</option>");
            $("#modifyParentMenu").empty();
            $("#modifyParentMenu").append("<option value=/" /">请选择父级菜单</option>");
            for (var i = 0; i < data.length; i++) {
                $("#addParentMenu").append("<option value=" + data[i].id + ">" + data[i].menuName + "</option>");
                $("#modifyParentMenu").append("<option value=" + data[i].id + ">" + data[i].menuName + "</option>");
            }
            $("#modifyParentMenu").val(parentId);

        } else {
            YHu.ui.closeLoading();
        }
    });
};
