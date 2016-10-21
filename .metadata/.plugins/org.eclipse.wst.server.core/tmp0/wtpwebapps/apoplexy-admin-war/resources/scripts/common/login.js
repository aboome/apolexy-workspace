var loginInfo = {
    userName: null,
    password: null
};

$(function () {

    $("#login").click(function () {
        login();
    });

    $("body").keydown(function () {
        if (event.keyCode == "13") {
            login();
        }
    });

    $("#username").focus(function () {
        $("#login-error").addClass("none");
    });

    $("#password").focus(function () {
        $("#login-error").addClass("none");
    });

    if(window.parent != window.self){
        parent.location.href = YHu.util.ctxPath("/login");
    }

});

function login() {

    if (!$('#loginForm').valid()) {
        return 0;
    }

    $("#login").attr("disabled", "disabled");

    YHu.ui.loading();

    loginInfo.userName = $("#username").val();
    loginInfo.password = hex_md5($("#password").val());

    var handleRequest = $.post(YHu.util.ctxPath('/loginSubmit'), loginInfo);
    handleRequest.done(function (jsonResult) {
        YHu.ui.closeLoading();
        if (jsonResult.success) {
            YHu.ui.closeAllLayer();
            window.location.href = YHu.util.ctxPath("/main");
        } else {
            YHu.ui.closeAllLayer();
            $("#login").removeAttr("disabled");
            $("#login-error-message").text(jsonResult.message);
            $("#login-error").removeClass("none");
        }
    });

}
