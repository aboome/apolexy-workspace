/**
 * Created by zhan on 2016/9/5.
 */
$(function() {
    turnPage('index.html');
    
    function resize() {
        var high = $(window).height();
        $('#nav_dot').css('height', high - 140);
    }
    resize();

    $("#nav_dot").niceScroll({
        cursorcolor:"#1FB4AC",
        cursoropacitymax:1,
        touchbehavior:false,
        cursorwidth:"5px",
        cursorborder:"0",
        cursorborderradius:"5px"
    });

    $(window).resize(function(){
        resize();
    });
});


$('.nav-list>li>a').on('click', function(){
    $('.nav-active').removeClass('nav-active');
    $(this).addClass('nav-active');
});
$('.list-item>a').on('click', function(){
    $('.list-item-active').removeClass('list-item-active');
    $(this).addClass('list-item-active');
});



$('.retract').on('click', function () {
    retract();
});

var retract = function () {
    var status = $('.retract').attr('data-id');
    if (status == 0) {
        $('#nav-wrap').removeClass('nav-wrap').addClass('nav-wrap-retract');
        $('.nav-list>li>a').removeClass('nav-a').addClass('nav-a-retract');
        $('.ico').removeClass('right').addClass('right-retract');
        $('#content').removeClass('content').addClass('content-retract');
        $('.retract').css({
            'background-image': 'url("../resources/admin/theme/images/retract-1.png")',
            'background-repeat': 'no-repeat',
            'background-position': 'center',
            'background-size': '21px'
        });
        $('.content').css('padding', '20px 20px 20px 100px');
        $('.retract').attr('data-id', '1');
    } else if (status == 1) {
        $('#nav-wrap').removeClass('nav-wrap-retract').addClass('nav-wrap');
        $('.nav-list>li>a').removeClass('nav-a-retract').addClass('nav-a');
        $('.ico').removeClass('right-retract').addClass('right');
        $('#content').removeClass('content-retract').addClass('content');
        $('.retract').css({
            'background-image': 'url("../resources/admin/theme/images/retract.png")',
            'background-repeat': 'no-repeat',
            'background-position': 'center',
            'background-size': '30px'
        });
        $('.content').css('padding', '20px 20px 20px 305px');
        $('.retract').attr('data-id', '0');
    }
};





var turnPage = function(url){
    var urls = url+'?t='+$.now();
    $('#html-content').load(urls, function(){$('#html-content').fadeIn('show');});
}

var navList = function(id){
    var $item = $("#J_nav_" + id);
    $item.parent().removeClass("none").parent().addClass("selected");

    $('#nav_dot').find('.nav-a').on('click', function(){
        var $div = $(this).siblings('.list-item');
        if ($(this).parent().hasClass("selected")) {
            $div.slideUp(300);
            $(this).parent().removeClass("selected");
        }
        if ($div.is(":hidden")) {
            $("#nav_dot li").find(".list-item").slideUp(300);
            $("#nav_dot li").removeClass("selected");
            $(this).parent().addClass("selected");
            $div.slideDown(300);

        } else {
            $div.slideUp(300);
        }
    });
};




