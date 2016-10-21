var ownerType = {
    doctor: 1,
    patient: 2
};

var registerProtocolInfo = {
    owner: null,
    protocolContent: null
};

var doctorUE = null;

var patientUE = null;

$(function () {

    window.UEDITOR_HOME_URL = YHu.util.ctxPath('/resources/admin/scripts/third/uedit/');

     doctorUE = UE.getEditor('doctorProtocolContent', {
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
                'forecolor' //字体颜色
            ]
        ]
    });

    doctorUE.ready(function () {

        queryRegisterProtocol(ownerType.doctor);
    });

     patientUE = UE.getEditor('patientProtocolContent', {
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
                'forecolor' //字体颜色
            ]
        ]
    });

    patientUE.ready(function () {

        queryRegisterProtocol(ownerType.patient);
    });


});

var saveDoctorProtocol = function () {

    doctorUE.ready(function () {

        registerProtocolInfo.protocolContent = doctorUE.getContent();

        updateRegisterProtocol(ownerType.doctor);

    });

};

var savePatientProtocol = function () {

    patientUE.ready(function () {

        registerProtocolInfo.protocolContent = patientUE.getContent();

        updateRegisterProtocol(ownerType.patient);

    });

};

var updateRegisterProtocol = function (owner) {

    registerProtocolInfo.owner = owner;

    layer.open({
        type: 1,
        title: "保存" + getOwnerTypeDesc(owner) + "注册协议",
        area: ['350px', '180px'],
        content: $('#updateLayer'),
        btn: ["确定", "取消"],
        yes: function () {
            YHu.ui.loading();
            var handleRequest = $.post(YHu.util.ctxPath('/registerProtocol/updateRegisterProtocol'), registerProtocolInfo);
            handleRequest.done(function (jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryRegisterProtocol(owner);
                    YHu.ui.alert("保存" + getOwnerTypeDesc(owner) + "注册协议成功！");
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

var queryRegisterProtocol = function (owner) {

    registerProtocolInfo.owner = owner;

    YHu.ui.loading();
    var handleRequest = $.post(YHu.util.ctxPath('/registerProtocol/queryRegisterProtocol'), registerProtocolInfo);
    handleRequest.done(function (jsonResult) {
        YHu.ui.closeLoading();
        if (jsonResult.success) {
            YHu.ui.closeAllLayer();
            if (owner == ownerType.doctor) {
                doctorUE.setContent(jsonResult.data.protocolContent);
                $("#doctorLastUpdateTime").text(jsonResult.data.lastUpdateTime);
            }
            if (owner == ownerType.patient) {
                patientUE.setContent(jsonResult.data.protocolContent);
                $("#patientLastUpdateTime").text(jsonResult.data.lastUpdateTime);
            }
        } else {
            YHu.ui.closeAllLayer();
        }
    });

};

var getOwnerTypeDesc = function (owner) {

    if (owner == ownerType.doctor) {

        var typeDesc = "医生端";

        return typeDesc;
    }

    if (owner == ownerType.patient) {

        var typeDesc = "患者端";

        return typeDesc;
    }

};

