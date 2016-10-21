var queryAdminHospitalCondition = {
    pageNum: 1,
    pageSize: 10,
    hospitalName: null
};

var hospitalIno = {
    id: null,
    hospitalName: null,
    hospitalDesc: null,
    hospitalAddr: null,
    imageId: null,
    parentHospitalId: null,
    level: null,
    lon: null,
    lat: null,
    areaCode: null,
    union:null
};

queryAreaCodeCondition = {
    level: null
};

areaCodeLevel = {
    PROVINCE: 1,
    CITY: 2,
    COUNTY: 3,
    TOWNSHIP: 4
};

var queryHospitalList = function () {
    var url = YHu.util.ctxPath("/hospital/queryHospital");
    YHu.ui.tableLoad("#adminhospitaltable", url, queryAdminHospitalCondition);
    loadAreaCode();
    loadHospital();
};

var clearCondition = function () {
    $("#admin-hospital-name").val('');
};

var setCondition = function () {
    queryAdminHospitalCondition.hospitalName = $("#admin-hospital-name").val();
};

$("document").ready(function () {
    setCondition();
    queryHospitalList();
});

$("#search").click(function () {
    setCondition();
    queryHospitalList();
});

$("#clear").click(function () {

    clearCondition();
    setCondition();
    queryHospitalList();
});

var addHospital = function () {

    $("#addHospitalName").val("");
    $("#addHospitaldesc").val("");
    $("#addHospitalAddr").val("");
    $("#hospitalImage").val("");
    $("#addLevel").val("");
    $("#addparentHospitalName").val("");
    $("#addHospitalLon").val("");
    $("#addHospitalLat").val("");
    $("#addAreaCode").val("");
    $('#addUnion').val("");

    $('.form-group').removeClass('has-error');
    $('.form-group').removeClass('has-success');
    $('span.help-block').remove();

    layer.open({
        type: 1,
        title: "新增医院信息",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#addLayer'),
        btn: ["确定", "取消"],
        yes: function () {

            if (!$('#addHospitalInfo').valid()) {
                return 0;
            }

            YHu.ui.loading();

            YHu.util.imageUpload("hospitalImage",YHu.util.ctxPath("/admin/imageUpload"),addHospitalContent);

        },
        no: function () {
            YHu.ui.closeAllLayer();
        }
    });
};

var addHospitalContent = function (imageId) {

    hospitalIno.hospitalName = $("#addHospitalName").val();
    hospitalIno.hospitalDesc = $("#addHospitaldesc").val();
    hospitalIno.hospitalAddr = $("#addHospitalAddr").val();
    hospitalIno.imageId = imageId;
    hospitalIno.parentHospitalId = $("#addParentHospitalId").val();
    hospitalIno.level = $("#addLevel").val();
    hospitalIno.lon = $("#addHospitalLon").val();
    hospitalIno.lat = $("#addHospitalLat").val();
    hospitalIno.areaCode = $("#addAreaCode").val();
    hospitalIno.union = $("#addUnion").val();

    var handleRequest = $.post(YHu.util.ctxPath('/hospital/addHospital'), hospitalIno);
    handleRequest.done(function (jsonResult) {
        YHu.ui.closeLoading();
        if (jsonResult.success) {
            YHu.ui.closeAllLayer();
            YHu.ui.alert("新增医院信息成功",function(){
                YHu.ui.closeAllLayer();
                queryHospitalList();
            });
        } else {
            YHu.ui.closeAllLayer();
            YHu.ui.alert(jsonResult.message);
        }
    });
};

var viewHospital = function (no) {

    var imageUuid = $("#imageId" + no).text();

    var imageSrc = YHu.util.ctxPath("/admin/image/view/") + imageUuid;

    $("#hospitalDetail").attr('src', imageSrc);

    layer.open({
        type: 1,
        title: "医院图片信息",
        area: ['800px','600px'],
        shadeClose: false,
        content: $('#detailLayer'),
        btn: ["关闭"],
        yes: function () {
            YHu.ui.closeAllLayer();
        }
    });

};

var modifyHospital = function (no) {

    var detailValue = $("#detailNo" + no).text();

    $("#modifyHospitalName").val($("#hospitalName" + no).text());
    $("#modifyHospitalDesc").val($("#hospitalDesc" + no).text());
    $("#modifyHospitalAddr").val($("#hospitalAddr" + no).text());
    $("#modifyLevel").val($("#level" + no).text());
    $("#modifyImage").text($("#imageId" + no).text());
    $("#modifyParentHospitalId").val($("#parentHospitalId" + no).text());
    $("#modifyHospitalLon").val($("#lon" + no).text());
    $("#modifyHospitalLat").val($("#lat" + no).text());
    $("#modifyAreaCode").val($("#areaCode" + no).text());
    $("#modifyUnion").val($("#union" + no).text());

    $('.form-group').removeClass('has-error');
    $('.form-group').removeClass('has-success');
    $('span.help-block').remove();

    layer.open({
        type: 1,
        title: "修改医院信息",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#modifyLayer'),
        btn: ["确定", "取消"],
        yes: function () {

            if (!$('#modifyHospitalInfo').valid()) {
                return 0;
            }

            YHu.ui.loading();
            hospitalIno.id = detailValue;

            YHu.util.imageUpload("modifyImage",YHu.util.ctxPath("/admin/imageUpload"),modifyHospitalContent);

        },
        no: function () {
            YHu.ui.closeAllLayer();
        }
    });
};

var modifyHospitalContent = function (imageId) {

    hospitalIno.hospitalName = $("#modifyHospitalName").val();
    hospitalIno.hospitalDesc = $("#modifyHospitalDesc").val();
    hospitalIno.hospitalAddr = $("#modifyHospitalAddr").val();
    hospitalIno.imageId = imageId;
    hospitalIno.parentHospitalId = $("#modifyParentHospitalId").val();
    hospitalIno.level = $("#modifyLevel").val();
    hospitalIno.lon = $("#modifyHospitalLon").val();
    hospitalIno.lat = $("#modifyHospitalLat").val();
    hospitalIno.areaCode = $("#modifyAreaCode").val();
    hospitalIno.union = $("#modifyUnion").val();

    var handleRequest = $.post(YHu.util.ctxPath('/hospital/updateHospital'), hospitalIno);
    handleRequest.done(function (jsonResult) {
        YHu.ui.closeLoading();
        if (jsonResult.success) {
            YHu.ui.closeAllLayer();
            YHu.ui.alert("修改医院信息成功",function(){
                YHu.ui.closeAllLayer();
                queryHospitalList();
            });
        } else {
            YHu.ui.closeAllLayer();
            YHu.ui.alert(jsonResult.message);
        }
    });

};

var deleteHospital = function (no) {
    var detailNo = "detailNo" + no;
    var detailValue = $("#" + detailNo).text();

    layer.open({
        type: 1,
        title: "删除医院信息",
        area: ['350px', '180px'],
        content: $('#deleteLayer'),
        btn: ["确定", "取消"],
        yes: function () {
            YHu.ui.loading();
            var handleRequest = $.post(YHu.util
                .ctxPath('/hospital/deleteHospital'), {
                id: detailValue
            });
            handleRequest.done(function (jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    YHu.ui.alert("删除医院信息成功",function () {
                        YHu.ui.closeAllLayer();
                        queryHospitalList();
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

var pageHandler = function (event, pageSize) {
    event.preventDefault();
    queryAdminHospitalCondition.pageNum = event.target.title;
    if (pageSize != null && typeof (pageSize) != "undefined") {
        queryAdminHospitalCondition.pageSize = pageSize;
        queryAdminHospitalCondition.pageNum = 1;
    }
    queryHospitalList();
};

$(".import").click(function () {
    layer.open({
        type: 1,
        title: "导入医院信息",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#exportHospital'),
        btn: ["确认导入", "取消"],
        yes: function () {
            YHu.ui.loading();
            uploadFileToServer("filePath", YHu.util.ctxPath('/hospital/importHospital'));

        },
        no: function () {
            YHu.ui.closeAllLayer();
        }
    });
});

var uploadFileToServer = function (elementId, url, callback) {
    $.ajaxFileUpload({
        url: url, // 需要链接到服务器地址
        secureuri: false,
        type: "POST",
        fileElementId: elementId, // 文件选择框的id属性
        data: {
            filePath: elementId
        },
        dataType: 'json', // 服务器返回的格式，可以是json
        success: function (result, textStatus) { // 相当于java中try语句块的用法
            YHu.ui.closeAllLayer();
            if (!result.success) {
                $("#importMsg").html(
                    "<p class='import'>" + result.message + "</p>");
                $("#importTransfer").hide();
                $("#serviceFilePath").val("");

            } else {
                $("#serviceFilePath").val(result.data.serverFileName);
                layer.open({
                    type: 1,
                    title: "导入医院信息",
                    area: ['450px', '200px'],

                    shadeClose: false,
                    content: "<p class='import'>根据您导入的Excel进行统计，导入成功共计"
                    + result.data.successCount
                    + "条，导入失败共计<span class='importAmount'>"
                    + result.data.failedCount
                    + "条。</p>",
                    btn: ["确认"],
                    yes: function () {
                        YHu.ui.closeAllLayer();
                        queryHospitalList();
                    }
                });
            }

        },
        error: function (data, status, e) { // 相当于java中catch语句块的用法
            YHu.ui.closeAllLayer();
            $("#importMsg").html("文件上传失败");
        }
    });


};

var validExcelFile = function (filePath) {
    YHu.ui.disableButton("#importButton");
    if ("" == filePath || typeof filePath == "undefined") {
        $("#importMsg").html("<lable>文件导入信息：</lable>文件名不能为空");
        $("#importTransfer").hide();
        $("#serviceFilePath").val("");
        return false;
    }
    if (/^.*?\.(xlsx)$/.test(filePath.toLowerCase())) {
        $("#importMsg").html("");
        YHu.ui.ableButton("#importButton");
        return true;
    } else {
        $("#importMsg").html("<lable>文件导入信息：</lable>只能导入Excel2007的文件");
        $("#importTransfer").hide();
        $("#serviceFilePath").val("");
        return false;
    }
};

var filePathOnchange = function (obj) {
    var objval = $(obj).val();
    var tmpFilePath = objval.substring(objval.lastIndexOf("\\") + 1,
        objval.length);
    tmpFilePath = (tmpFilePath.length > 30 ? (tmpFilePath).substring(0, 25)
    + "..." : tmpFilePath);
    $("#inputFileName").val(tmpFilePath);
    if (!validExcelFile(objval)) {
        return false;
    }
};

var loadAreaCode = function () {

    queryAreaCodeCondition.level = areaCodeLevel.COUNTY;

    YHu.ui.loading();
    var handleRequest = $.post(YHu.util.ctxPath('/areaCode/queryAreaCodeList'), queryAreaCodeCondition);
    handleRequest.done(function (jsonResult) {
        YHu.ui.closeLoading();
        if (jsonResult.success) {
            YHu.ui.closeAllLayer();
            var data = jsonResult.data;

            for (var i = 0; i < data.length; i++) {
                $("#addAreaCode").append("<option value=" + data[i].areaCode + ">" + data[i].areaName + "</option>");
                $("#modifyAreaCode").append("<option value=" + data[i].areaCode + ">" + data[i].areaName + "</option>");
            }

        } else {
            YHu.ui.closeAllLayer();
        }
    });

};

var loadHospital = function () {

    YHu.ui.loading();
    var handleRequest = $.post(YHu.util.ctxPath('/hospital/queryHospitalList'));
    handleRequest.done(function (jsonResult) {
        YHu.ui.closeLoading();
        if (jsonResult.success) {
            YHu.ui.closeLoading();
            var data = jsonResult.data;

            for (var i = 0; i < data.length; i++) {
                $("#addParentHospitalId").append("<option value=" + data[i].id + ">" + data[i].hospitalName + "</option>");
                $("#modifyParentHospitalId").append("<option value=" + data[i].id + ">" + data[i].hospitalName + "</option>");
            }

        } else {
            YHu.ui.closeLoading();
        }
    });
};