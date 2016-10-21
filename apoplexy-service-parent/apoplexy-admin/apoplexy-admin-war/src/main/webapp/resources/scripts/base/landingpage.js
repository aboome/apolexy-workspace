var queryLandingPageCondition = {
    owner: null
};

var landingPageIno = {
    id: null,
    imageId: null,
    owner: null,
    sort: null
};

var ownerType = {
    doctor: 1,
    patient: 2
};

var queryLandingPageList = function () {
    setCondition(ownerType.patient);
    YHu.ui.tableLoad("#patientLandingPageTable", YHu.util.ctxPath("/landingPage/queryLandingPageList"), queryLandingPageCondition);
    setCondition(ownerType.doctor);
    YHu.ui.tableLoad("#doctorLandingPageTable", YHu.util.ctxPath("/landingPage/queryLandingPageList"), queryLandingPageCondition);
};

var setCondition = function (owner) {
    queryLandingPageCondition.owner = owner;
};

$("document").ready(function () {
    queryLandingPageList();
});

var deleteLandingPage = function (no) {

    var detailNo = "detailNo" + no;
    var detailValue = $("#" + detailNo).text();

    layer.open({
        type: 1,
        title: "删除开机动画信息",
        area: ['350px', '180px'],
        shadeClose: false,
        content: $('#deleteLayer'),
        btn: ["确定", "取消"],
        yes: function () {
            YHu.ui.loading();
            var handleRequest = $.post(YHu.util.ctxPath('/landingPage/deleteLandingPage'), {id: detailValue});
            handleRequest.done(function (jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    YHu.ui.alert("删除开机动画信息成功",function () {
                        YHu.ui.closeAllLayer();
                        queryLandingPageList();
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

var addLandingPage = function () {

    $("#landingPageFilePath").val("");
    $("#addOwner").val("");
    $("#addSort").val("");

    $('.form-group').removeClass('has-error');
    $('.form-group').removeClass('has-success');
    $('span.help-block').remove();

    layer.open({
        type: 1,
        title: "新增开机动画信息",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#addLayer'),
        btn: ["确定", "取消"],
        yes: function () {

            if (!$('#landingpageInfo').valid()) {
                return 0;
            }

            YHu.ui.loading();

            YHu.util.imageUpload("landingPageFilePath",YHu.util.ctxPath("/admin/imageUpload"),addLandingPageContent);

        },
        no: function () {
            YHu.ui.closeAllLayer();
        }
    });
};

var addLandingPageContent = function (imageId) {

    landingPageIno.imageId = imageId;
    landingPageIno.owner = $("#addOwner").val();
    landingPageIno.sort = $("#addSort").val();

    var handleRequest = $.post(YHu.util.ctxPath('/landingPage/addLandingPage'), landingPageIno);
    handleRequest.done(function (jsonResult) {
        YHu.ui.closeLoading();
        if (jsonResult.success) {
            YHu.ui.closeAllLayer();
            YHu.ui.alert("新增开机动画成功",function () {
                YHu.ui.closeAllLayer();
                queryLandingPageList();
            });
        } else {
            YHu.ui.closeAllLayer();
            YHu.ui.alert(jsonResult.message);
        }
    });
};

var viewLandingPage = function (no) {

    var imageUuid = $("#imageId" + no).text();

    var imageSrc = YHu.util.ctxPath("/admin/image/view/") + imageUuid;

    $("#landingPageDetail").attr('src', imageSrc);

    layer.open({
        type: 1,
        title: "开机动画信息",
        area: ['800px', '600px'],
        shadeClose: false,
        content: $('#detailLayer'),
        btn: ["关闭"],
        yes: function () {
            YHu.ui.closeAllLayer();
        }
    });

};

var modifyLandingPage = function (no) {

    var detailNo = "detailNo" + no;
    var detailValue = $("#" + detailNo).text();

    $("#landingPageFilePath").text($("#imageId" + no).text());
    $("#addOwner").val($("#owner" + no).text());
    $("#addSort").val($("#sort" + no).text());

    $('.form-group').removeClass('has-error');
    $('.form-group').removeClass('has-success');
    $('span.help-block').remove();

    layer.open({
        type: 1,
        title: "修改开机动画信息",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#addLayer'),
        btn: ["确定", "取消"],
        yes: function () {

            if (!$('#landingpageInfo').valid()) {
                return 0;
            }

            landingPageIno.id = detailValue;

            YHu.ui.loading();

            YHu.util.imageUpload("landingPageFilePath",YHu.util.ctxPath("/admin/imageUpload"),modifyLandingPageContent);

        },
        no: function () {
            YHu.ui.closeAllLayer();
        }
    });
};

var modifyLandingPageContent = function (imageId) {

    landingPageIno.imageId = imageId;
    landingPageIno.owner = $("#addOwner").val();
    landingPageIno.sort = $("#addSort").val();

    var handleRequest = $.post(YHu.util.ctxPath('/landingPage/updateLandingPage'), landingPageIno);
    handleRequest.done(function (jsonResult) {
        YHu.ui.closeLoading();
        if (jsonResult.success) {
            YHu.ui.closeAllLayer();
            YHu.ui.alert("修改开机动画信息成功",function () {
                YHu.ui.closeAllLayer();
                queryLandingPageList();
            });
        } else {
            YHu.ui.closeAllLayer();
            YHu.ui.alert(jsonResult.message);
        }
    });

};