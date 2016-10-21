//var isOpen = true;
	$(".left-bar > li > a").toggle(function(){
		$(this).siblings("ol").slideDown(300);
		$(this).removeClass("close");
	},function(){
		$(this).siblings("ol").slideUp(100);
		$(this).addClass("close");
	});
	$(".left-bar >li >a").hover(function(){
		$(this).children().animate({
			marginLeft:"30px"
		});
	},function(){
		$(this).children("em").animate({
			marginLeft:"0px"
		});
	});
	$(".left-bar>li>ol>li>a").hover(function(){
		$(this).children().animate({
			marginLeft:"30px"
		});
	},function(){
		$(this).children("em").animate({
			marginLeft:"20px"
		});
	});
	$(".add-new").click(function(){
		$.layer({
		    type : 1,
		    title : "false",
		    shade: [0.3,"#000"],
		    offset:['150px' , ''],
		    border: [5, 0.3, '#000'],
		    area : ['503px','auto'],
		    page : {dom : '#layer'},
		    btns : 2,
		    btn : ["确定", "取消"],
		    yes : function(){
		    	alert("http://sentsin.com/jquery/layer/api.html 此插件的API文档");
		    },
		    no : function (){

		    }
		});
	});
