var queryAdminDoctorCondition = {
    pageNum: 1,
    pageSize: 10,
    doctorName: null,
    hospital: null
};

var doctorIno = {
    id: null,
    doctorName: null,
    hospital: null,
    department: null,
    title: null,
    job: null,
    effectiveTime: null,
    sex: null,
    phone: null,
    email: null,
    avatar:null
};


var queryAdminDoctorList = function () {
    url = YHu.util.ctxPath("/doctor/queryDoctor");
    YHu.ui.tableLoad("#admindoctortable", url, queryAdminDoctorCondition);
    loadHospital();
};

var setCondition = function () {
    queryAdminDoctorCondition.doctorName = $("#admin-doctor-name").val();
    queryAdminDoctorCondition.hospital = $("#admin-doctorhospital-name").val();

};

var clearCondition = function () {

    $("#admin-doctor-name").val('');
    $("#admin-doctorhospital-name").val('');
};


$("document").ready(function () {

    $("#search").click(function () {

        setCondition();
        queryAdminDoctorList();
    });

    $("#clear").click(function () {

        clearCondition();
        setCondition();
        queryAdminDoctorList();
    });


    setCondition();
    queryAdminDoctorList();
});


var deleteDoctor = function (no) {
    var detailNo = "detailNo" + no;
    var detailValue = $("#" + detailNo).text();

    layer.open({
        type: 1,
        title: "删除医生信息",
        area: ['350px', '180px'],
        content: $('#deleteLayer'),
        btn: ["确定", "取消"],
        yes: function () {
            YHu.ui.loading();
            var handleRequest = $.post(YHu.util.ctxPath('/doctor/deleteDoctor'),
                {id: detailValue});
            handleRequest.done(function (jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    YHu.ui.alert("删除医生信息成功",function(){
                        YHu.ui.closeAllLayer();
                        queryAdminDoctorList();
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


$(".import").click(function () {
    layer.open({
        type: 1,
        title: "导入医生信息",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#exportDoctor'),
        btn: ["确认导入", "取消"],
        yes: function () {
            YHu.ui.loading();
            uploadFileToServer("filePath", YHu.util.ctxPath('/doctor/importDoctor'));
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
                    title: "导入医生信息",
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
                        queryAdminDoctorList();
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


var addDoctor = function () {

    $("#addDoctorName").val("");
    $("#addHospital").val("");
    $("#addDepartment").val("");
    $("#addTitle").val("");
    $("#addJob").val("");
    $("#addEffectiveTime").val("");
    $("#addSex").val("");
    $("#addPhone").val("");
    $("#addEmail").val("");

    $('.form-group').removeClass('has-error');
    $('.form-group').removeClass('has-success');
    $('span.help-block').remove();

    layer.open({
        type: 1,
        title: "新增医生信息",
        area: 'auto',
        maxWidth: '550px',
        shadeClose: false,
        content: $('#addLayer'),
        btn: ["确定", "取消"],
        yes: function () {
            if (!$('#doctorInfo').valid()) {
                return 0;
            }
            YHu.ui.loading();

            YHu.util.imageUpload("addDoctorAvatarImage",YHu.util.ctxPath("/admin/imageUpload"),addDoctorContent);

        },
        no: function () {
            YHu.ui.closeAllLayer();
        }
    });
};

var addDoctorContent = function (imageId) {

    doctorIno.doctorName = $("#addDoctorName").val();
    doctorIno.hospital = $("#addHospital").val();
    doctorIno.department = $("#addDepartment").val();
    doctorIno.title = $("#addTitle").val();
    doctorIno.job = $("#addJob").val();
    doctorIno.effectiveTime = $("#addEffectiveTime").val();
    doctorIno.sex = $("#addSex").val();
    doctorIno.phone = $("#addPhone").val();
    doctorIno.email = $("#addEmail").val();
    doctorIno.avatar = imageId;

    var handleRequest = $.post(YHu.util.ctxPath('/doctor/addDoctor'), doctorIno);
    handleRequest.done(function (jsonResult) {
        YHu.ui.closeLoading();
        if (jsonResult.success) {
            YHu.ui.closeAllLayer();
            YHu.ui.alert("新增医生信息成功",function(){
                YHu.ui.closeAllLayer();
                queryAdminDoctorList();
            });
        } else {
            YHu.ui.closeAllLayer();
            YHu.ui.alert(jsonResult.message);
        }
    });

};


var modifyDoctor = function (no) {

    var detailValue = $("#detailNo" + no).text();

    $("#modifyDoctorName").val($("#doctorName" + no).text());
    $("#modifyHospital").val($("#hospitalId" + no).text());
    $("#modifyDepartment").val($("#department" + no).text());
    $("#modifySex").val($("#doctorSex" + no).text());
    $("#modifyPhone").val($("#doctorPhone" + no).text());
    $("#modifyTitle").val($("#title" + no).text());
    $("#modifyJob").val($("#job" + no).text());
    $("#modifyEffectiveTime").val($("#effectiveTime" + no).text());
    $("#modifyEmail").val($("#doctorEmail" + no).text());
    $("#modifyHospitalId").val($("#modifyHospitalId" + no).text());

    $('.form-group').removeClass('has-error');
    $('.form-group').removeClass('has-success');
    $('span.help-block').remove();

    layer.open({
        type: 1,
        title: "修改医生信息",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#modifyLayer'),
        btn: ["确定", "取消"],
        yes: function () {
            if (!$('#modifyDoctor').valid()) {
                return 0;
            }
            YHu.ui.loading();

            doctorIno.id = detailValue;

            YHu.util.imageUpload("modifyDoctorAvatarImage",YHu.util.ctxPath("/admin/imageUpload"),modifyDoctorContent);

        },
        no: function () {
            YHu.ui.closeAllLayer();
        }
    });
};

var modifyDoctorContent = function (imageId) {

    doctorIno.doctorName = $("#modifyDoctorName").val();
    doctorIno.hospital = $("#modifyHospital").find("option:selected").text();
    doctorIno.department = $("#modifyDepartment").val();
    doctorIno.sex = $("#modifySex").val();
    doctorIno.phone = $("#modifyPhone").val();
    doctorIno.title = $("#modifyTitle").val();
    doctorIno.job = $("#modifyJob").val();
    doctorIno.effectiveTime = $("#modifyEffectiveTime").val();
    doctorIno.email = $("#modifyEmail").val();
    doctorIno.modifyHospitalId = $("#modifyHospitalId").val();
    doctorIno.avatar = imageId;

    var handleRequest = $.post(YHu.util.ctxPath('/doctor/updateDoctor'), doctorIno);
    handleRequest.done(function (jsonResult) {
        YHu.ui.closeLoading();
        if (jsonResult.success) {
            YHu.ui.closeAllLayer();
            YHu.ui.alert("修改医生信息成功",function(){
                YHu.ui.closeAllLayer();
                queryAdminDoctorList();
            });
        } else {
            YHu.ui.closeAllLayer();
            YHu.ui.alert(jsonResult.message);
        }
    });

};


var pageHandler = function (event, pageSize) {
    event.preventDefault();
    queryAdminDoctorCondition.pageNum = event.target.title;
    if (pageSize != null && typeof (pageSize) != "undefined") {
        queryAdminDoctorCondition.pageSize = pageSize;
        queryAdminDoctorCondition.pageNum = 1;
    }
    queryAdminDoctorList();
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
                $("#addHospital").append("<option value=" + data[i].id + ">" + data[i].hospitalName + "</option>");
                $("#modifyHospital").append("<option value=" + data[i].id + ">" + data[i].hospitalName + "</option>");
            }

        } else {
            YHu.ui.closeLoading();
        }
    });
};

var viewAvatar = function (no) {

    var imageUuid = $("#avatar" + no).text();

    var imageSrc = YHu.util.ctxPath("/admin/image/view/") + imageUuid;

    $("#doctorAvatar").attr('src', imageSrc);

    layer.open({
        type: 1,
        title: "医生头像信息",
        area: ['800px', '600px'],
        shadeClose: false,
        content: $('#detailLayer'),
        btn: ["关闭"],
        yes: function () {
            YHu.ui.closeAllLayer();
        }
    });
};
