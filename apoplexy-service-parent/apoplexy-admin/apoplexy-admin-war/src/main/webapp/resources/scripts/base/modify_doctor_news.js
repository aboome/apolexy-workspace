/**
 * Created by wunder on 2016/10/10.
 */
var doctorNews = {
    id: null,
    title: null,
    subTitle: null,
    smallLogo: null,
    newsType: null,
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

        loadNewsInfo(ue);

    });

});

var saveDoctorNews = function () {

    if (!$("#modifyDoctorNewsForm").valid()) {
        return;
    }

    YHu.util.imageUpload("newsImage", YHu.util.ctxPath("/admin/imageUpload"), saveDoctorNewsContent);

};

var saveDoctorNewsContent = function (imageId) {

    doctorNews.id = $("#newsId").text();
    doctorNews.title = $("#modifyDoctorNewsTitle").val();
    doctorNews.subTitle = $("#modifyDoctorSubTitle").val();
    doctorNews.smallLogo = imageId;
    doctorNews.newsType = $("#modifyDoctorNewsType").val();
    doctorNews.type = $("#modifyDoctorType").val();
    doctorNews.source = $("#modifyDoctorNewsSource").val();
    doctorNews.sourceType = $("#modifyDoctorSrc").val();

    if (doctorNews.sourceType == sourceType.URL) {
        doctorNews.content = $("#modifyDoctorNewsUrl").val();
    } else {
        doctorNews.content = ue.getContent();
    }

    YHu.ui.loading();
    var handleRequest = $.post(YHu.util.ctxPath('/doctorNews/modifyDoctorNews'), doctorNews);
    handleRequest.done(function (jsonResult) {
        YHu.ui.closeLoading();
        if (jsonResult.success) {
            YHu.ui.closeAllLayer();
            YHu.ui.alert("修改医生端资讯信息成功", function () {
                window.location.href = YHu.util.ctxPath("/doctorNews/doctorNewsMain");
            });
        } else {
            YHu.ui.closeAllLayer();
            YHu.ui.alert(jsonResult.message);
        }
    });

};

var loadNewsInfo = function (ue) {

    var id = $("#newsId").text();

    var url = "http://" + window.location.host + YHu_Config.bathPath;

    YHu.ui.loading();
    var handleRequest = $.post(YHu.util.ctxPath('/doctorNews/doctorNewsDetail'), {id: id, url: url});
    handleRequest.done(function (jsonResult) {
        YHu.ui.closeLoading();
        if (jsonResult.success) {
            YHu.ui.closeAllLayer();

            var data = jsonResult.data;

            $("#modifyDoctorNewsTitle").val(data.title);
            $("#modifyDoctorSubTitle").val(data.subTitle);
            $("#newsImage").text(data.smallLogo);
            $("#modifyDoctorNewsType").val(data.newType);
            $("#modifyDoctorType").val(data.type);
            $("#modifyDoctorNewsSource").val(data.source);
            $("#modifyDoctorSrc").val(data.contentType);

            if (data.contentType == sourceType.URL) {
                $("#modifyDoctorNewsUrl").val(data.content);
                $('#plainInfoText').hide();
                $('#plainInfoUrl').show();
            } else {
                ue.setContent(data.content);
                $('#plainInfoText').show();
                $('#plainInfoUrl').hide();
            }

        } else {
            YHu.ui.closeAllLayer();
            YHu.ui.alert(jsonResult.message);
        }
    });

};

var cancelSave = function () {

    window.location.href = YHu.util.ctxPath("/doctorNews/doctorNewsMain");

};

$('#modifyDoctorSrc').on('change', function () {

    var srcType = $(this).val();

    if (srcType == sourceType.URL) {
        $('#plainInfoText').hide();
        $('#plainInfoUrl').show();
    } else {
        $('#plainInfoText').show();
        $('#plainInfoUrl').hide();
    }

});