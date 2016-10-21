$(function(){
	$('#doctor-open').on('click', function(){
		uprodown('#doctor-open');
	});
	
	$('#patient-open').on('click', function(){
		uprodown('#patient-open');
	});
});

var uprodown = function(role){
	var index = $(role).attr('data-index');
	if(index == '1'){
		$(role).parents('.regprotocol-content').css('height', 'auto');
		$(role).attr('data-index', '0');
		$(role).children('i').removeClass('glyphicon glyphicon-chevron-right');
		$(role).children('i').addClass('glyphicon glyphicon-chevron-left');
		$(role).children('span').html('收起');
	} else if(index == '0'){
		$(role).parents('.regprotocol-content').css('height', '200px');
		$(role).attr('data-index', '1');
		$(role).children('i').removeClass('glyphicon glyphicon-chevron-left');
		$(role).children('i').addClass('glyphicon glyphicon-chevron-right');
		$(role).children('span').html('展开');
	}
}