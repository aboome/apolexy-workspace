var queryPatientNewsCondition = {
    pageNum: 1,
    pageSize: 10,
    type: null,
    title: null
};

var sourceType = {
    URL: 0,
    HTML: 1
};

var queryPatientNewsList = function () {
    url = YHu.util.ctxPath("/patientNews/queryPatientNewsList");
    YHu.ui.tableLoad("#patientNewsTable", url, queryPatientNewsCondition);
};

var setCondition = function () {
    queryPatientNewsCondition.type = $("#patientnews-content").val();
    queryPatientNewsCondition.title = $("#admin-patientnews-name").val();
};

var clearCondition = function () {

    $("#patientnews-content").val('');
    $("#admin-patientnews-name").val('');
};


$("document").ready(function () {

    window.UEDITOR_HOME_URL = YHu.util.ctxPath('/resources/admin/scripts/third/uedit/');

    $("#search").click(function () {

        setCondition();
        queryPatientNewsList();
    });

    $("#clear").click(function () {
        clearCondition();
        setCondition();
        queryPatientNewsList();
    });

    setCondition();
    queryPatientNewsList();
});


var deletePatientNews = function (no) {

    var id = $("#detailNo" + no).text();

    layer.open({
        type: 1,
        title: "删除患者端资讯信息",
        area: ['350px', '180px'],
        content: $('#deleteLayer'),
        btn: ["确定", "取消"],
        yes: function () {
            YHu.ui.loading();
            var handleRequest = $.post(YHu.util.ctxPath('/patientNews/deletePatientNews'), {id: id});
            handleRequest.done(function (jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    YHu.ui.alert("删除患者端资讯信息成功",function () {
                        YHu.ui.closeAllLayer();
                        queryPatientNewsList();
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


var addPatientNews = function () {

    window.location.href = YHu.util.ctxPath("/patientNews/addPatientNewsManager");

};

var modifyPatientNews = function (no) {

    var id = $("#detailNo" + no).text();

    window.location.href = YHu.util.ctxPath("/patientNews/modifyPatientNewsManager/"+id);

};


var pageHandler = function (event, pageSize) {
    event.preventDefault();
    queryPatientNewsCondition.pageNum = event.target.title;
    if (pageSize != null && typeof (pageSize) != "undefined") {
        queryPatientNewsCondition.pageSize = pageSize;
        queryPatientNewsCondition.pageNum = 1;
    }
    queryPatientNewsList();
};

var viewImage = function (no) {

    var imageUuid = $("#smallLogo" + no).text();

    var imageSrc = YHu.util.ctxPath("/admin/image/view/") + imageUuid;

    $("#newsImage").attr('src', imageSrc);

    layer.open({
        type: 1,
        title: "资讯缩略图",
        area: ['800px', '600px'],
        shadeClose: false,
        content: $('#imageLayer'),
        btn: ["关闭"],
        yes: function () {
            YHu.ui.closeAllLayer();
        }
    });
};

var viewDetail = function (no) {

    var contentType = $("#contentType" + no).text();
    var id = $("#detailNo" + no).text();

    var queryDetail =  {
        id:id,
        url:null
    };

    queryDetail.id = id;
    queryDetail.url = YHu.util.ctxPath("/");

    YHu.ui.loading();
    var handleRequest = $.post(YHu.util.ctxPath('/patientNews/patientNewsDetail'), queryDetail);
    handleRequest.done(function (jsonResult) {
        YHu.ui.closeLoading();
        if (jsonResult.success) {
            YHu.ui.closeLoading();
            var data = jsonResult.data;

            if (contentType == sourceType.HTML) {
                $("#newsContentFrame").hide();
                $("#newsContentDiv").html(data.content);
                $("#newsContentDiv").show();
            } else {
                $("#newsContentDiv").hide();
                $("#newsContentFrame").attr('src', data.content);
                $("#newsContentFrame").show();
            }

            layer.open({
                type: 1,
                title: "资讯内容详情",
                area: ['800px', '600px'],
                shadeClose: false,
                content: $('#detailLayer'),
                btn: ["关闭"],
                yes: function () {
                    YHu.ui.closeAllLayer();
                }
            });

        } else {
            YHu.ui.closeLoading();
        }
    });
};
