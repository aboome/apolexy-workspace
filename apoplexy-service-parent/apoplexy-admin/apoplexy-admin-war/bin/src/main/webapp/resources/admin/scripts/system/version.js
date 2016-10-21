var queryIdeaCondition = {
	 
};

var versionIno = {
	    id:null,
	    version:null
};

var queryIdeaList = function() {
	
	url = YHu.util.ctxPath("/version/queryVersion");
	YHu.ui.tableLoad("#versionListtable", url, queryIdeaCondition);
 
};

 
$("document").ready(function() {

	$("#search").click(function(){
		
		 queryIdeaList();
	});

	queryIdeaList();
});


var modifyVersion = function(no) {

    var detailValue = $("#id" + no).text();

    $("#modifyVersion").val($("#version" + no).text());
    layer.open({
        type : 1,
        title : "修改版本号",
        area: 'auto',
        maxWidth: '450px',
        shadeClose: false,
        content: $('#modifyLayer'),
        btn : [ "确定", "取消" ],
        yes : function() {
            YHu.ui.loading();
            versionIno.id = detailValue;
            versionIno.version = $("#modifyVersion").val();
            var handleRequest = $.post(YHu.util.ctxPath('/version/updateVersion'), versionIno);
            handleRequest.done(function(jsonResult) {
                YHu.ui.closeLoading();
                if (jsonResult.success) {
                    YHu.ui.closeAllLayer();
                    queryIdeaList();
                    YHu.ui.alert("修改版本号成功！");
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