
$(function () {

    var eChart = function (data) {

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        var option = {

            title: {
                text: '发单数据统计',
                x: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['市级医院', '区县级医院', '社区级医院']
            },
            series: [
                {
                    name: '医院等级',
                    type: 'pie',
                    radius: '55%',
                    center: ['50%', '60%'],
                    data: data,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

    };
    
    var queryReferralStatistics = function () {

        YHu.ui.loading();
        var handleRequest = $.post(YHu.util.ctxPath('/referralStatis/queryReferralStatiscom'));
        handleRequest.done(function (jsonResult) {
            YHu.ui.closeLoading();
            if (jsonResult.success) {
                YHu.ui.closeAllLayer();
                eChart(jsonResult.data)
            } else {
                YHu.ui.closeAllLayer();
            }
        });

    };

    queryReferralStatistics();

});



$(function () {

    var eChart = function (data) {

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('referralMain'));

        var option = {

            title: {
                text: '发单数据统计',
                x: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['急性', '高危']
            },
            series: [
                {
                    name: '转诊患者类型',
                    type: 'pie',
                    radius: '55%',
                    center: ['50%', '60%'],
                    data: data,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

    };
    
    var queryReceiveReferral = function () {

        YHu.ui.loading();
        var handleRequest = $.post(YHu.util.ctxPath('/referralStatis/queryReceiveReferralStatuscom'));
        handleRequest.done(function (jsonResult) {
            YHu.ui.closeLoading();
            if (jsonResult.success) {
                YHu.ui.closeAllLayer();
                eChart(jsonResult.data)
            } else {
                YHu.ui.closeAllLayer();
            }
        });

    };

    queryReceiveReferral();

});


$(function () {

    var eChart = function (data) {

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('HospitalLevlReferralMain'));

        var option = {

            title: {
                text: '接单数据统计',
                x: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['市级医院', '区县级医院', '社区级医院','省级医院']
            },
            series: [
                {
                    name: '医院等级',
                    type: 'pie',
                    radius: '55%',
                    center: ['50%', '60%'],
                    data: data,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

    };
    
    var queryReceiveReferralRecv = function () {

        YHu.ui.loading();
        var handleRequest = $.post(YHu.util.ctxPath('/referralStatis/queryReceiveReferralRevcCom'));
        handleRequest.done(function (jsonResult) {
            YHu.ui.closeLoading();
            if (jsonResult.success) {
                YHu.ui.closeAllLayer();
                eChart(jsonResult.data)
            } else {
                YHu.ui.closeAllLayer();
            }
        });

    };

    queryReceiveReferralRecv();

});


$(function () {

    var eChart = function (data) {

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('patientTypeReferralMain'));

        var option = {

            title: {
                text: '接单数据统计',
                x: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['急性', '高危']
            },
            series: [
                {
                    name: '患者类型',
                    type: 'pie',
                    radius: '55%',
                    center: ['50%', '60%'],
                    data: data,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

    };
    
    var queryPantientType = function () {

        YHu.ui.loading();
        var handleRequest = $.post(YHu.util.ctxPath('/referralStatis/queryReceivePatientCom'));
        handleRequest.done(function (jsonResult) {
            YHu.ui.closeLoading();
            if (jsonResult.success) {
                YHu.ui.closeAllLayer();
                eChart(jsonResult.data)
            } else {
                YHu.ui.closeAllLayer();
            }
        });

    };

    queryPantientType();

});



$(function () {

    var eChart = function (data) {

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('receiveTiemMain'));

        var option = {

            title: {
                text: '发单数据统计',
                x: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
            },
            series: [
                {
                    name: '月份',
                    type: 'pie',
                    radius: '55%',
                    center: ['50%', '60%'],
                    data: data,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

    };
    
    var queryReceiveTiem = function () {

        YHu.ui.loading();
        var handleRequest = $.post(YHu.util.ctxPath('/referralStatis/queryReceiveTiemCom'));
        handleRequest.done(function (jsonResult) {
            YHu.ui.closeLoading();
            if (jsonResult.success) {
                YHu.ui.closeAllLayer();
                eChart(jsonResult.data)
            } else {
                YHu.ui.closeAllLayer();
            }
        });

    };

    queryReceiveTiem();

});


$(function () {

    var eChart = function (data) {

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('referralTiemMain'));

        var option = {

            title: {
                text: '接单数据统计',
                x: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
            },
            series: [
                {
                    name: '月份',
                    type: 'pie',
                    radius: '55%',
                    center: ['50%', '60%'],
                    data: data,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

    };
    
    var queryReferralTiem = function () {

        YHu.ui.loading();
        var handleRequest = $.post(YHu.util.ctxPath('/referralStatis/queryReferralTiemCom'));
        handleRequest.done(function (jsonResult) {
            YHu.ui.closeLoading();
            if (jsonResult.success) {
                YHu.ui.closeAllLayer();
                eChart(jsonResult.data)
            } else {
                YHu.ui.closeAllLayer();
            }
        });

    };

    queryReferralTiem();

});