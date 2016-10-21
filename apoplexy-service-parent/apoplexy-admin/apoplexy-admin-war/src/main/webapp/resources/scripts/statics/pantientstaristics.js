

$(function () {

    var eChart = function (data) {

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        var option = {

            title: {
                text: '患者数据统计',
                x: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['黄浦区', '卢湾区', '徐汇区','长宁区','静安区','普陀区','闸北区','虹口区','杨浦区','闵行区','宝山区','嘉定区','浦东新区','金山区','松江区','青浦区','奉贤区','未知地区']
            },
            series: [
                {
                    name: '患者地区',
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
    
    var queryPantientStatistics = function () {

        YHu.ui.loading();
        var handleRequest = $.post(YHu.util.ctxPath('/patientStatis/queryPatientStatistics'));
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

    queryPantientStatistics();

});


$(function () {

    var eChart = function (data) {

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('patientMain'));

        var option = {

            title: {
                text: '患者注册数据统计',
                x: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['基础注册', '填写量表']
            },
            series: [
                {
                    name: '患者注册',
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
    
    var queryPantientFastStatistics = function () {

        YHu.ui.loading();
        var handleRequest = $.post(YHu.util.ctxPath('/patientStatis/queryPatientFastStatistics'));
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

    queryPantientFastStatistics();

});



$(function () {

    var eChart = function (data) {

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('patientIncidenceMain'));

        var option = {

            title: {
                text: '患者注册数据统计',
                x: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['高危患者', '非高危患者']
            },
            series: [
                {
                    name: '患者注册',
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
    
    var queryPantientByIncidenceStatistics = function () {

        YHu.ui.loading();
        var handleRequest = $.post(YHu.util.ctxPath('/patientStatis/queryPatientByIncidence'));
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

    queryPantientByIncidenceStatistics();

});
