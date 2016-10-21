var queryBannerCondition = {
    owner: null,
    sort: null
};

var bannerIno = {
    id: null,
    title: null,
    image: null,
    owner: null,
    sort: null,
    url: null
};

var ownerType = {
    doctor: 1,
    patient: 2
};

var queryBannerList = function () {
    setCondition(ownerType.doctor);
    YHu.ui.tableLoad("#doctorBannerTable", YHu.util.ctxPath("/bannerInfo/queryBannerInfo"), queryBannerCondition);
    setCondition(ownerType.patient);
    YHu.ui.tableLoad("#patientBannerTable", YHu.util.ctxPath("/bannerInfo/queryBannerInfo"), queryBannerCondition);
};

var setCondition = function (owner) {
    queryBannerCondition.owner = owner;
};

$("document").ready(function () {
    queryBannerList();
});

var addBannerInfo = function () {

    $("#addBannerInfoTitle").val("");
    $("#BannerInfoImage").val("");
    $("#addOwner").val("");
    $("#addSort").val("");
    $("#addUrl").val("");

    $('.form-group').removeClass('has-error');
    $('.form-group').removeClass('has-success');
    $('span.help-block').remove();

    layer.open({
        type: 1,
        title: "新增手机端推荐位",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#addLayer'),
        btn: ["确定", "取消"],
        yes: function () {

            if (!$('#addBannerInfo').valid()) {
                return 0;
            }

            YHu.ui.loading();

            YHu.util.imageUpload("bannerInfoImage",YHu.util.ctxPath("/admin/imageUpload"),addBannerContent);

        },
        no: function () {
            YHu.ui.closeAllLayer();
        }
    });
};

var addBannerContent = function (imageId) {

    bannerIno.title = $("#addBannerInfoTitle").val();
    bannerIno.image = imageId;
    bannerIno.owner = $("#addOwner").val();
    bannerIno.url = $("#addUrl").val();
    bannerIno.sort = $("#addSort").val();

    var handleRequest = $.post(YHu.util.ctxPath('/bannerInfo/addBannerInfo'), bannerIno);
    handleRequest.done(function (jsonResult) {
        YHu.ui.closeLoading();
        if (jsonResult.success) {
            YHu.ui.closeAllLayer();
            YHu.ui.alert("新增手机端推荐位成功！",function(){
                YHu.ui.closeAllLayer();
                queryBannerList();
            });
        } else {
            YHu.ui.closeAllLayer();
            YHu.ui.alert(jsonResult.message);
        }
    });

};

var viewBannerInfo = function (no) {

    var imageUuid = $("#imageId" + no).text();

    var imageSrc = YHu.util.ctxPath("/admin/image/view/") + imageUuid;

    $("#bannerDetail").attr('src', imageSrc);

    layer.open({
        type: 1,
        title: "推荐位图片信息",
        area: ['800px', '600px'],
        shadeClose: false,
        content: $('#detailLayer'),
        btn: ["关闭"],
        yes: function () {
            YHu.ui.closeAllLayer();
        }
    });

};

var modifyBanner = function (no) {

    var detailValue = $("#detailNo" + no).text();
    var sort = $("#doctorbannerSort" + no).text();

    $("#modifyBannerInfoTitle").val($("#title" + no).text());
    $("#modifyUrl").val($("#url" + no).text());
    $("#modifySort").val($("#bannerSort" + no).text());
    $("#modifyOwner").val($("#owner"+no).text());

    $('.form-group').removeClass('has-error');
    $('.form-group').removeClass('has-success');
    $('span.help-block').remove();

    layer.open({
        type: 1,
        title: "修改主页推荐位信息",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#modifyLayer'),
        btn: ["确定", "取消"],
        yes: function () {

            if (!$('#modifyBannerInfo').valid()) {
                return 0;
            }

            YHu.ui.loading();
            bannerIno.id = detailValue;

            YHu.util.imageUpload("modifyImage",YHu.util.ctxPath("/admin/imageUpload"),modifyBannerContent);

        },
        no: function () {
            YHu.ui.closeAllLayer();
        }
    });
};

var modifyBannerContent = function (imageId) {

    bannerIno.title = $("#modifyBannerInfoTitle").val();
    bannerIno.image = imageId;
    bannerIno.url = $("#modifyUrl").val();
    bannerIno.sort = $("#modifySort").val();
    bannerIno.owner = $("#modifyOwner").val();

    var handleRequest = $.post(YHu.util.ctxPath('/bannerInfo/updateBannerInfo'), bannerIno);
    handleRequest.done(function (jsonResult) {
        YHu.ui.closeLoading();
        if (jsonResult.success) {
            YHu.ui.closeAllLayer();
            YHu.ui.alert("修改主页推荐位信息成功",function () {
                YHu.ui.closeAllLayer();
                queryBannerList();
            });
        } else {
            YHu.ui.closeAllLayer();
            YHu.ui.alert(jsonResult.message);
        }
    });
};

var deleteBanner = function (no) {

    var detailNo = "detailNo" + no;
    var detailValue = $("#" + detailNo).text();

    layer.open({
        type: 1,
        title: "删除推荐位信息",
        area: ['350px', '180px'],
        content: $('#deleteLayer'),
        btn: ["确定", "取消"],
        yes: function () {
            YHu.ui.loading();
            var handleRequest = $.post(YHu.util.ctxPath('/bannerInfo/deleteBannerInfo'), {id: detailValue});
            handleRequest.done(function (jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    YHu.ui.alert("删除推荐位信息成功",function () {
                        YHu.ui.closeAllLayer();
                        queryBannerList();
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
