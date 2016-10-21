$(function() {

    adaptiveWindow();

    $("#login").click(function() {
		login();
	});

    $("body").keydown(function() {
        if (event.keyCode == "13") {//keyCode=13是回车键
        	$("#login").click();
        }
    }); 
});

function login(){

    if(!$('#loginInfo').valid()){
        return 0;
    }

    $(this).attr("disabled","disabled");

    YHu.ui.loading();
    
    $.ajax({
        type:"POST",
        url : YHu.util.ctxPath("/admin/loginSubmit"),
        data : {"userName":$("#userName").val(),"password":hex_md5($("#password").val())},
        success : function(jsonResult) {
        	YHu.ui.closeLoading();
            if(jsonResult.success){
                window.location.href = YHu.util.ctxPath("/admin/main");
            }else{
                $("#login").removeAttr("disabled");
                YHu.ui.alert(jsonResult.message);
            }
        }
    });
}

function adaptiveWindow(){

    var win_height; //浏览器当前窗口可视区域高度
    var win_width; //浏览器当前窗口可视区域宽度
    var original_width = 2400; //图片原始尺寸，编辑可手填
    var original_height = 1600; //图片原始尺寸，编辑可手填

    var pic_width, pic_height, pic_left ,pic_top; //裁剪适配后的图片显示尺寸和左边距、上边距

    OnePicAction();

    function OnePicAction(){
        win_height = $(window).height(); //浏览器当前窗口可视区域高度
        win_width = $(window).width(); //浏览器当前窗口可视区域宽度

        //裁剪图片
        if(Math.ceil(win_height * original_width / original_height) < win_width ){
            pic_width = win_width ;
            pic_height = Math.ceil(win_width * original_height / original_width);
            pic_left = 0;
            pic_top = - Math.ceil((pic_height - win_height) / 2);
        }else{
            pic_height = win_height;
            pic_width = Math.ceil(win_height * original_width / original_height);
            pic_left = - Math.ceil((pic_width - win_width) / 2);
            pic_top = 0;
        }
        $("#onepics .wrap_pic").css("width",pic_width+"px").css("height",pic_height+"px").css("margin-top",pic_top+"px").css("margin-left",pic_left+"px");

    }

    window.onresize = function(){
        OnePicAction();
    }
}
