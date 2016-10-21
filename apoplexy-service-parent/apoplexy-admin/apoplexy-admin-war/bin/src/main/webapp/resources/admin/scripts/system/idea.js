var queryIdeaCondition = {
	pageNum:1,
	pageSize:10
};

var queryIdeaList = function() {
	
	url = YHu.util.ctxPath("/comReply/queryIdeaReplyList");
	YHu.ui.tableLoad("#ideaListtable", url, queryIdeaCondition);
 
};

 
$("document").ready(function() {

	$("#search").click(function(){
		
		 queryIdeaList();
	});

	queryIdeaList();
});

var pageHandler = function(event, pageSize) {
	event.preventDefault();
	queryIdeaCondition.pageNum = event.target.title;
	if (pageSize != null && typeof (pageSize) != "undefined") {
		queryIdeaCondition.pageSize = pageSize;
		queryIdeaCondition.pageNum = 1;
	}
	queryIdeaList();
};

var deleteIdea = function(no) {
	
	var id = "id" + no;
    var discussValue = $("#" + id).text();
	layer.open({
	    type: 1,
	    title: '删除意见反馈',
	    area: ['350px', '180px'],
	    /*maxWidth: '450px',*/
	    shadeClose: false,
	    content: $('#deleteLayer'),
	    btn: ['取消', '确定'],
	    btn2: function(index, layero){
	    	YHu.ui.loading();
            var handleRequest = $.post(YHu.util.ctxPath('/comReply/deleteIdeaReply'),
                {id : discussValue});
            handleRequest.done(function(jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryIdeaList();
                    YHu.ui.alert("删除意见反馈成功！");
                } else {
                    YHu.ui.closeAllLayer();
                    YHu.ui.alert(jsonResult.message);
                }
            });
	    },
	    btn1: function(index, layero){
	    	YHu.ui.closeAllLayer();
	    }
	});
};

 