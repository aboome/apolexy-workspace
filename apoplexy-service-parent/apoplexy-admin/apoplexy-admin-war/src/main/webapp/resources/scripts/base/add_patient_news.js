var patientNews = {
    title: null,
    subTitle: null,
    smallLogo: null,
    type: null,
    source: null,
    content: null,
    createTime: null
};

var sourceType = {
    URL: 0,
    HTML: 1
};

var ue = null;

$(function () {

    ue = UE.getEditor('newsContent',{
        toolbars: [
            [
                'source',
                'undo', //撤销
                'redo', //重做
                '|',
                'bold',
                'italic',
                'fontfamily', //字体
                'fontsize', //字号
                'justifyleft', //居左对齐
                'justifyright', //居右对齐
                'justifycenter', //居中对齐
                'justifyjustify', //两端对齐
                'forecolor',//字体颜色
                'simpleupload' //单图上传
            ]
        ]
    });

    ue.ready(function () {

    });

});


var savePatientNews = function () {

    if(!$("#addPatientNewsForm").valid()){
        return;
    }

    YHu.util.imageUpload("newsImage",YHu.util.ctxPath("/admin/imageUpload"),savePatientNewsContent);
};

var savePatientNewsContent = function (imageId) {

    patientNews.title = $("#addPatientNewsTitle").val();
    patientNews.subTitle = $("#addPatientSubTitle").val();
    patientNews.smallLogo = imageId;
    patientNews.type = $("#addPatientType").val();
    patientNews.source = $("#addPatientNewsSource").val();
    patientNews.sourceType = $("#addPatientSrc").val();

    if (patientNews.sourceType == sourceType.URL) {
        patientNews.content = $("#addPatientNewsUrl").val();
    } else {
        patientNews.content = ue.getContent();
    }

    YHu.ui.loading();
    var handleRequest = $.post(YHu.util.ctxPath('/patientNews/addPatientNews'), patientNews);
    handleRequest.done(function (jsonResult) {
        YHu.ui.closeLoading();
        if (jsonResult.success) {
            YHu.ui.closeAllLayer();

            YHu.ui.alert("新增患者端资讯信息成功", function () {
                window.location.href = YHu.util.ctxPath("/patientNews/patientNewsMain");
            });

        } else {
            YHu.ui.closeAllLayer();
            YHu.ui.alert(jsonResult.message);
        }
    });
};

var cancelSave = function () {

    window.location.href = YHu.util.ctxPath("/patientNews/patientNewsMain");

};

$('#addPatientSrc').on('change', function () {

    var srcType = $(this).val();

    if (srcType == sourceType.URL) {
        $('#plainInfoText').hide();
        $('#plainInfoUrl').show();
    } else {
        $('#plainInfoText').show();
        $('#plainInfoUrl').hide();
    }

});
