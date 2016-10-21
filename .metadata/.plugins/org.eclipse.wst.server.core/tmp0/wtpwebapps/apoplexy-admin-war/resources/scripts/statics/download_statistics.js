var queryDownloadCondition = {
    startDate: null,
    endDate: null,
    type: null
};

var queryRegister = {
    startDate: null,
    endDate: null
};

var clientType = {
    doctor: "1",
    patient: "2"
};

var setCondition = function() {
	queryDownloadCondition.startDate = $("#start-time").val();
	queryDownloadCondition.endDate=  $("#end-time").val();
};

var clearCondition = function () {

    $("#start-time").val('');
    $("#end-time").val('');
};



$(function () {

    var eChart = function (countDate, countNum, chartId, dataName,theme) {

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById(chartId));

        var option = {
            title: {
                     text: theme,
                     x: 'center'
            },
            color: ['#3398DB'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: countDate,
                    axisTick: {
                        alignWithLabel: true
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: dataName,
                    type: 'bar',
                    barWidth: '60%',
                    data: countNum
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

    };

    var queryDownloadStatistics = function (type, chartId, dataName) {

        queryDownloadCondition.type = type;
        
        YHu.ui.loading();
        var handleRequest = $.post(YHu.util.ctxPath('/downloadStatistics/queryDownloadStatistics'), queryDownloadCondition);
        handleRequest.done(function (jsonResult) {
            YHu.ui.closeLoading();
            if (jsonResult.success) {
                YHu.ui.closeAllLayer();

                var data = jsonResult.data;

                eChart(jsonResult.data.countDate, jsonResult.data.countNum, chartId, dataName, dataName);

            } else {
                YHu.ui.closeAllLayer();
            }
        });


    };
    
    
    var queryRegisterStatistics = function (chartId, dataName) {

        YHu.ui.loading();
        var handleRequest = $.post(YHu.util.ctxPath('/downloadStatistics/queryDoctorRegister'), queryDownloadCondition);
        handleRequest.done(function (jsonResult) {
            YHu.ui.closeLoading();
            if (jsonResult.success) {
                YHu.ui.closeAllLayer();

                eChart(jsonResult.data.countDate, jsonResult.data.countNum, chartId, dataName, dataName);

            } else {
                YHu.ui.closeAllLayer();
            }
        });
    };
    
    
    var queryPatientRegisterStatistics = function (chartId, dataName) {

        YHu.ui.loading();
        var handleRequest = $.post(YHu.util.ctxPath('/downloadStatistics/queryPatientRegister'), queryDownloadCondition);
        handleRequest.done(function (jsonResult) {
            YHu.ui.closeLoading();
            if (jsonResult.success) {
                YHu.ui.closeAllLayer();

                eChart(jsonResult.data.countDate, jsonResult.data.countNum, chartId, dataName, dataName);

            } else {
                YHu.ui.closeAllLayer();
            }
        });
    };
    

    queryDownloadStatistics(clientType.doctor, 'doctorDownload', '医生端APP下载量');
    queryDownloadStatistics(clientType.patient, 'patientDownload', '患者端APP下载量');
    queryRegisterStatistics('doctorRegister','医生端注册量');
    queryPatientRegisterStatistics('patientRegister','患者端注册量');


    $("#search").click(function () {
        queryDownloadCondition.startDate = $("#start-time").val();
        queryDownloadCondition.endDate = $("#end-time").val();
        queryDownloadStatistics(clientType.doctor, 'doctorDownload', '医生端APP下载量');
        queryDownloadStatistics(clientType.patient, 'patientDownload', '患者端APP下载量');
        queryRegisterStatistics('doctorRegister','医生端注册量');
        queryPatientRegisterStatistics('patientRegister','患者端注册量');
    });

    
    $("#clear").click(function() {

	    clearCondition();
	    setCondition();
	    queryDownloadStatistics(clientType.doctor, 'doctorDownload', '医生端APP下载量');
	    queryDownloadStatistics(clientType.patient, 'patientDownload', '患者端APP下载量');
	    queryRegisterStatistics('doctorRegister','医生端注册量');
	    queryPatientRegisterStatistics('patientRegister','患者端注册量');
	});


});


Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};
