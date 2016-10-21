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
        title: "删除意见反馈信息",
        area: ['350px', '180px'],
        content: $('#deleteLayer'),
        btn: ["确定", "取消"],
        yes: function () {
            YHu.ui.loading();
            var handleRequest = $.post(YHu.util.ctxPath('/comReply/deleteIdeaReply'),
                {id: discussValue});
            handleRequest.done(function (jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryIdeaList();
                    YHu.ui.alert("删除意见反馈信息成功！");
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

 