

$(function () {

    var eChart = function (data) {

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        var option = {

            title: {
                text: '医生数据统计',
                x: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['市级医生', '区县级医生', '社区级医生']
            },
            series: [
                {
                    name: '医生等级',
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
    
    var queryDoctorStatistics = function () {

        YHu.ui.loading();
        var handleRequest = $.post(YHu.util.ctxPath('/doctorStatis/queryDoctorStatistics'));
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

    queryDoctorStatistics();

});