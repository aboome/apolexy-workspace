/**
 * Created by wunder on 2016/10/10.
 */
var doctorNews = {
    title: null,
    subTitle: null,
    smallLogo: null,
    newsType: null,
    type: null,
    sourceType: null,
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

    ue = UE.getEditor('newsContent', {
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

var saveDoctorNews = function () {

    if (!$("#addDoctorNewsForm").valid()) {
        return;
    }

    YHu.util.imageUpload("newsImage", YHu.util.ctxPath("/admin/imageUpload"), saveDoctorNewsContent);

};

var saveDoctorNewsContent = function (imageId) {

    doctorNews.title = $("#addDoctorNewsTitle").val();
    doctorNews.subTitle = $("#addDoctorSubTitle").val();
    doctorNews.smallLogo = imageId;
    doctorNews.newsType = $("#addDoctorNewsType").val();
    doctorNews.type = $("#addDoctorType").val();
    doctorNews.source = $("#addDoctorNewsSource").val();
    doctorNews.sourceType = $("#addDoctorSrc").val();

    if (doctorNews.sourceType == sourceType.URL) {
        doctorNews.content = $("#addDoctorNewsUrl").val();
    } else {
        doctorNews.content = ue.getContent();
    }

    YHu.ui.loading();
    var handleRequest = $.post(YHu.util.ctxPath('/doctorNews/addDoctorNews'), doctorNews);
    handleRequest.done(function (jsonResult) {
        YHu.ui.closeLoading();
        if (jsonResult.success) {
            YHu.ui.closeAllLayer();
            YHu.ui.alert("新增医生端资讯信息成功", function () {
                window.location.href = YHu.util.ctxPath("/doctorNews/doctorNewsMain");
            });
        } else {
            YHu.ui.closeAllLayer();
            YHu.ui.alert(jsonResult.message);
        }
    });

};

var cancelSave = function () {

    window.location.href = YHu.util.ctxPath("/doctorNews/doctorNewsMain");

};


$('#addDoctorSrc').on('change', function () {

    var srcType = $(this).val();

    if (srcType == sourceType.URL) {
        $('#plainInfoText').hide();
        $('#plainInfoUrl').show();
    } else {
        $('#plainInfoText').show();
        $('#plainInfoUrl').hide();
    }

});

