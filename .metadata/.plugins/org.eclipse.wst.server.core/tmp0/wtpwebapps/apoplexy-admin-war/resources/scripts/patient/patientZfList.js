var queryZfCondition = {
    pageNum: 1,
    pageSize: 10,
    patientName: null,
    areaName: null,
    startTime: null,
    endTime: null
};

var zfDetail = {
    id: null
};

var queryPatientZfList = function () {

    url = YHu.util.ctxPath("/ZFEmer/queryZFEmerList");
    YHu.ui.tableLoad("#patientZfListtable", url, queryZfCondition);

};

var setCondition = function () {
    queryZfCondition.patientName = $("#patient-name").val();
    queryZfCondition.areaName = $("#area-name").val();
    queryZfCondition.startTime = $("#start-time").val();
    queryZfCondition.endTime = $("#end-time").val();
};

$("document").ready(function () {

    $("#search").click(function () {
        queryZfCondition.pageNum = 1;
        setCondition();
        queryPatientZfList();
    });
    $("#clear").click(function () {

        $("#patient-name").val("");
        $("#area-name").val("");
        $("#start-time").val("");
        $("#end-time").val("");
        setCondition();
        queryPatientZfList();
    });


    setCondition();
    queryPatientZfList();
});

var pageHandler = function (event, pageSize) {
    event.preventDefault();
    queryZfCondition.pageNum = event.target.title;
    if (pageSize != null && typeof (pageSize) != "undefined") {
        queryZfCondition.pageSize = pageSize;
        queryZfCondition.pageNum = 1;
    }
    queryPatientZfList();
};


var viewDetail = function (no) {

    var detailId = $("#id" + no).text();

    $("#detailUserName").text($("#userName" + no).text());
    $("#detailSex").text($("#sex" + no).text());
    $("#detailAge").text($("#age" + no).text());
    $("#detailCreateTime").text($("#createTime" + no).text());
    zfDetail.id = detailId;

    YHu.ui.loading();
    //查询中风急救Fast量表详情

    $("#zfDetailTable").load(YHu.util.ctxPath('/ZFEmer/queryZFEmerDetail'), zfDetail, function () {

        YHu.ui.closeLoading();

        layer.open({
            type: 1,
            title: '中风急救详情',
            area: ['520px', '520px'],

            shadeClose: false,
            content: $('#detailLayer'),
            btn: ['关闭'],
            yes: function () {
                YHu.ui.closeAllLayer();
            }
        });

    });


};
