var versionIno = {
    id: null,
    version: null,
    upgradeUrl: null
};

var queryVersionCondition = {

};

var queryVersionList = function () {

    url = YHu.util.ctxPath("/version/queryVersion");
    YHu.ui.tableLoad("#versionListtable", url,queryVersionCondition);

};

$("document").ready(function () {
    queryVersionList();
});

var modifyVersion = function (no) {

    var detailValue = $("#id" + no).text();

    $("#modifyVersion").val($("#version" + no).text());
    $("#modifyURL").val($("#upgradeUrl" + no).text());

    $('.form-group').removeClass('has-error');
    $('.form-group').removeClass('has-success');
    $('span.help-block').remove();

    layer.open({
        type: 1,
        title: "修改版本信息",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#modifyLayer'),
        btn: ["确定", "取消"],
        yes: function () {

            if(!$('#versionForm').valid()){
                return 0;
            }

            YHu.ui.loading();

            versionIno.id = detailValue;
            versionIno.version = $("#modifyVersion").val();
            versionIno.upgradeUrl = $("#modifyURL").val();

            var handleRequest = $.post(YHu.util.ctxPath('/version/updateVersion'), versionIno);
            handleRequest.done(function (jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryVersionList();
                    YHu.ui.alert("修改版本成功！");
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