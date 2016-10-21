var queryHrCondition = {
    pageNum: 1,
    pageSize: 10,
    patientName: null,
    areaName: null,
    startTime: null,
    endTime: null
};

var healthScreenDetail = {
    id: null,
    type: null
};

var detailType = {
    history: '0',
    first: "1"
};

var queryPatientHrList = function () {

    url = YHu.util.ctxPath("/hr/queryHRList");
    YHu.ui.tableLoad("#patientHrListtable", url, queryHrCondition);

};

var setCondition = function () {
    queryHrCondition.patientName = $("#patient-name").val();
    queryHrCondition.areaName = $("#area-name").val();
    queryHrCondition.startTime = $("#start-time").val();
    queryHrCondition.endTime = $("#end-time").val();
};

$("document").ready(function () {

    $("#search").click(function () {

        queryHrCondition.pageNum = 1;
        setCondition();
        queryPatientHrList();
    });

    $("#clear").click(function () {

        $("#patient-name").val("");
        $("#area-name").val("");
        $("#start-time").val("");
        $("#end-time").val("");
        setCondition();
        queryPatientHrList();
    });

    setCondition();
    queryPatientHrList();
});

var pageHandler = function (event, pageSize) {
    event.preventDefault();
    queryHrCondition.pageNum = event.target.title;
    if (pageSize != null && typeof (pageSize) != "undefined") {
        queryHrCondition.pageSize = pageSize;
        queryHrCondition.pageNum = 1;
    }
    queryPatientHrList();
};

var viewDetail = function (no) {

    var detailId = $("#id" + no).text();

    $("#detailAge").text($("#age" + no).text());
    $("#detailSex").text($("#sex" + no).text());

    healthScreenDetail.id = detailId;
    healthScreenDetail.type = detailType.history;

    YHu.ui.loading();
    //查询既往史
    $("#historyDetailTable").load(YHu.util.ctxPath('/hr/queryHRDetails'), healthScreenDetail, function () {

        //查询现病史
        healthScreenDetail.type = detailType.first;

        $("#firstScreenDetailTable").load(YHu.util.ctxPath('/hr/queryHRDetails'), healthScreenDetail, function () {

            YHu.ui.closeLoading();

            layer.open({
                type: 1,
                title: '高危筛查详情',
                area: 'auto',
                maxWidth: '450px',
                shadeClose: false,
                content: $('#detailLayer'),
                btn: ['关闭'],
                yes: function () {
                    YHu.ui.closeAllLayer();
                }
            });

        });
    });

};