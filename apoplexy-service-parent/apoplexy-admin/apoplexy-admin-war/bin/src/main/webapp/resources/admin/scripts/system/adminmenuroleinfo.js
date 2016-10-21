var queryAdminMenuCondition = {
	pageNum:1,
	pageSize:10,

};

var queryAdminMenuList = function() {
	url = YHu.util.ctxPath("/Menu/queryMenu");
	YHu.ui.tableLoad("#adminmenuinfotable", url, queryAdminMenuCondition);
};



$("document").ready(function() {

	queryAdminMenuList();
});

var pageHandler = function(event, pageSize) {
	event.preventDefault();
	queryAdminMenuCondition.pageNum = event.target.title;
	if (pageSize != null && typeof (pageSize) != "undefined") {
		queryAdminMenuCondition.pageSize = pageSize;
		queryAdminMenuCondition.pageNum = 1;
	}
	queryAdminMenuList();
};

var deletemenuin = function(no) {
    var detailNo = "id" + no;
    var detailValue = $("#" + detailNo).text();
    $.layer({
        type : 1,
        title : "删除菜单",
        shade : [ 0.3, "#000" ],
        offset : [ '150px', '' ],
        border : [ 5, 0.3, '#000' ],
        area : [ '503px', '200px' ],
        page : {
            dom : '#deleteLayer'
        },
        btns : 2,
        btn : [ "确定", "取消" ],
        yes : function() {
            YHu.ui.loading();
            var handleRequest = $.post(YHu.util.ctxPath('/Menu/deleteMenu'),
                {id : detailValue});
            handleRequest.done(function(jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryAdminMenuList();
                    YHu.ui.alert("删除菜单成功！");
                } else {
                    YHu.ui.closeAllLayer();
                    YHu.ui.alert(jsonResult.message);
                }
            });
        },
        no : function() {
            YHu.ui.closeAllLayer();
        }
    });
};
