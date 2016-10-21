/**
 * Created by wunder on 16/9/24.
 */

$(function () {

    var eChart = function (data) {

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        var option = {

            title: {
                text: '医院数据统计',
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

    var queryHospitalStatistics = function () {

        YHu.ui.loading();
        var handleRequest = $.post(YHu.util.ctxPath('/hospitalStatistics/queryHospitalStatistics'));
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

    queryHospitalStatistics();

});



