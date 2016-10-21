var queryDoctorNewsCondition = {
    pageNum: 1,
    pageSize: 10,
    type: null,
    newType: null,
    title: null
};

var sourceType = {
    URL: 0,
    HTML: 1
};

var clearCondition = function () {

    $("#admin-doctornews-name").val('');
    $("#doctorselect").val('');
    $("#doctor-content").val('');
};

var queryDoctorNewsList = function () {
    url = YHu.util.ctxPath("/doctorNews/queryDoctorNews");
    YHu.ui.tableLoad("#doctorNewsTable", url, queryDoctorNewsCondition);
};

var setCondition = function () {
    queryDoctorNewsCondition.type = $("#doctor-content").val();
    queryDoctorNewsCondition.newType = $("#doctorselect").val();
    queryDoctorNewsCondition.title = $("#admin-doctornews-name").val();
};

$("document").ready(function () {

    $("#search").click(function () {

        setCondition();
        queryDoctorNewsList();
    });


    $("#clear").click(function () {

        clearCondition();
        setCondition();
        queryDoctorNewsList();
    });

    setCondition();
    queryDoctorNewsList();

});

var deletedoctornews = function (no) {
    var detailNo = "detailNo" + no;
    var detailValue = $("#" + detailNo).text();

    layer.open({
        type: 1,
        title: "删除医生端资讯信息",
        area: ['350px', '180px'],
        content: $('#deleteLayer'),
        btn: ["确定", "取消"],
        yes: function () {
            YHu.ui.loading();
            var handleRequest = $.post(YHu.util.ctxPath('/doctorNews/deleteDoctorNews'),
                {id: detailValue});
            handleRequest.done(function (jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    YHu.ui.alert("删除医生端资讯信息成功",function () {
                        YHu.ui.closeAllLayer();
                        queryDoctorNewsList();
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


var addDoctorNews = function () {

    window.location.href = YHu.util.ctxPath("/doctorNews/addDoctorNewsManager");

};

var modifyDoctorNews = function (no) {

    var id = $("#detailNo" + no).text();

    window.location.href = YHu.util.ctxPath("/doctorNews/modifyDoctorNewsManager/" + id);

};

var pageHandler = function (event, pageSize) {
    event.preventDefault();
    queryDoctorNewsCondition.pageNum = event.target.title;
    if (pageSize != null && typeof (pageSize) != "undefined") {
        queryDoctorNewsCondition.pageSize = pageSize;
        queryDoctorNewsCondition.pageNum = 1;
    }
    queryDoctorNewsList();
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
    var handleRequest = $.post(YHu.util.ctxPath('/doctorNews/doctorNewsDetail'),queryDetail);
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