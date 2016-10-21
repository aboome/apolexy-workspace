/**
 * Created by zhan on 2016/9/5.
 */
var myChart1 = echarts.init(document.getElementById('appCount'));
myChart1.showLoading({
    text: '正在努力的读取数据中...', //loading话术
});
myChart1.hideLoading();

var myChart2 = echarts.init(document.getElementById('obs-data'));
myChart2.showLoading({
    text: '正在努力的读取数据中...', //loading话术
});
myChart2.hideLoading();


var option1 = {
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data:['医生端下载','患者端下载']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    toolbox: {
        feature: {
            saveAsImage: {}
        }
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['9月1号','9月2号','9月3号','9月4号','9月5号','9月6号','9月7号','9月8号','9月9号','9月10号',
            '9月11号','9月12号','9月13号','9月14号','9月15号','9月16号','9月17号','9月18号','9月19号','9月20号',
            '9月21号','9月22号','9月23号','9月24号','9月25号','9月26号','9月27号','9月28号','9月29号','9月30号']
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {


            name:'医生端下载',
            type:'line',
            stack: '1',
            data:[140, 132, 101, 134, 190, 230, 230, 132, 191, 134, 90, 230, 20, 132, 1, 334, 290, 230, 210, 137, 101, 134, 390, 230, 210, 132, 101, 134, 90,100]
        },
        {


            name:'患者端下载',
            type:'line',
            stack: '1',
            data:[170, 102, 101, 166, 90, 20, 210, 132, 101, 14, 90, 30, 240, 132, 100, 334, 6390, 230, 210, 137, 101, 134, 110, 230, 210, 132, 11, 134, 90,100]
        }
    ]
};


var option2 = {

    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },

    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: {
        type: 'value',
        boundaryGap: [0, 0.1]
    },
    yAxis: {
        type: 'category',
        data: ['中风急救病人','高危患者','转诊病人']
    },
    series: [

        {
            name: '人数',
            type: 'bar',
            barMaxWidth: 4,
            barGap: '10',
            data: [988, 2560, 4800]
        }
    ]
};


// 使用刚指定的配置项和数据显示图表。
myChart1.setOption(option1, true);
window.onresize = myChart1.resize;

myChart2.setOption(option2, true);
window.onresize = myChart2.resize;