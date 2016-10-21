/**
 * Created by wunder on 2016/10/10.
 */
var patientNews = {
    id: null,
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

        loadNewsInfo(ue);

    });

});


var savePatientNews = function () {

    if(!$("#modifyPatientNewsForm").valid()){
        return;
    }

    YHu.util.imageUpload("newsImage",YHu.util.ctxPath("/admin/imageUpload"),savePatientNewsContent);

};

var savePatientNewsContent = function (imageId) {

    patientNews.id = $("#newsId").text();
    patientNews.title = $("#modifyPatientNewsTitle").val();
    patientNews.subTitle = $("#modifyPatientSubTitle").val();
    patientNews.smallLogo = imageId;
    patientNews.type = $("#modifyPatientType").val();
    patientNews.source = $("#modifyPatientNewsSource").val();
    patientNews.sourceType = $("#modifyPatientSrc").val();

    if(patientNews.sourceType == sourceType.URL){
        patientNews.content = $("#modifyPatientNewsUrl").val();
    }else {
        patientNews.content = ue.getContent();
    }

    YHu.ui.loading();
    var handleRequest = $.post(YHu.util.ctxPath('/patientNews/modifyPatientNews'), patientNews);
    handleRequest.done(function (jsonResult) {
        YHu.ui.closeLoading();
        if (jsonResult.success) {
            YHu.ui.closeAllLayer();
            YHu.ui.alert("修改患者端资讯信息成功",function(){
                window.location.href = YHu.util.ctxPath("/patientNews/patientNewsMain");
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
    var handleRequest = $.post(YHu.util.ctxPath('/patientNews/patientNewsDetail'), {id: id, url: url});
    handleRequest.done(function (jsonResult) {
        YHu.ui.closeLoading();
        if (jsonResult.success) {
            YHu.ui.closeAllLayer();

            var data = jsonResult.data;

            $("#modifyPatientNewsTitle").val(data.title);
            $("#modifyPatientSubTitle").val(data.subTitle);
            $("#newsImage").text(data.smallLogo);
            $("#modifyPatientType").val(data.type);
            $("#modifyPatientNewsSource").val(data.source);
            $("#modifyPatientSrc").val(data.contentType);

            if(data.contentType == sourceType.URL){
                $("#modifyPatientNewsUrl").val(data.content);
                $('#plainInfoText').hide();
                $('#plainInfoUrl').show();
            }else {
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

    window.location.href = YHu.util.ctxPath("/patientNews/patientNewsMain");

};

$('#modifyPatientSrc').on('change', function(){

    var srcType = $(this).val();

    if(srcType == sourceType.URL){
        $('#plainInfoText').hide();
        $('#plainInfoUrl').show();
    } else {
        $('#plainInfoText').show();
        $('#plainInfoUrl').hide();
    }

});