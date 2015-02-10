WARANA.namespace("module.dashboard");

WARANA.module.dashboard = function () {

    var viewBarChart = function (data) {
        var id = data;
        var bars = [];


        $.ajax({
            url: "viewstat/getspiderwebdata",
            type: "POST",
            success: function(data){

                alert(data);

                var jsonObj = JSON.parse(data);

                for (var i = 0; i < jsonObj.length; i++) {
                    var singleBar = [];
                    singleBar.push(jsonObj[i].technology);
                    singleBar.push(jsonObj[i].percentage);
                    bars.push(singleBar);
                }

                $('#bar-chart-area').empty();
                // Build the chart
                $("#bar-chart-area").highcharts({
                    chart: {
                        type: 'column'
                    },
                    title: {
                        text: 'Company Technology Proficiencies'
                    },
                    xAxis: {
                        type: 'category',
                        labels: {
                            rotation: -45,
                            style: {
                                fontSize: '12px',
                                fontFamily: 'Verdana, sans-serif'
                            }
                        }
                    },
                    yAxis: {
                        min: 0,
                        title: {
                            text: 'Proficiency'
                        }
                    },
                    legend: {
                        enabled: false
                    },
                    credits: {
                        enabled: false
                    },
                    tooltip: {
                        pointFormat: 'Proficiency percentage: <b>{point.y:.1f} %</b>'
                    },
                    series: [
                        {
                            name: 'Proficiency',
                            data: bars,
                            dataLabels: {
                                enabled: true,
                                rotation: 0,
                                color: '#FFFFFF',
                                align: 'right',
                                x: 4,
                                y: 10,
                                style: {
                                    fontSize: '12px',
                                    fontFamily: 'Verdana, sans-serif',
                                    textShadow: '0 0 3px black'
                                }
                            }
                        }
                    ]
                });

            }
        });




        var ajaxInitData = {
            url: 'viewstat/getstat',
            data: $.toJSON(id),
            contentType: "application/json"
        };

        var successFn = function (data) {
            if (data.success) {
                var jsonObj = $.evalJSON(data.result.techScoreList);

                for (var i = 0; i < jsonObj.length; i++) {
                    var pieSlice = [];
                    pieSlice.push(jsonObj[i].technology);
                    pieSlice.push(jsonObj[i].percentage);
                    statChartData.push(pieSlice);
                }

                /**
                 * Drawing chart
                 */
                drawStatChart(statChartData);
            }
        };

        WARANA.common.ajaxCall(ajaxInitData, successFn);

    };

    return {
        init: function () {
            viewBarChart();
        }
    }
}();

$(function () {
    WARANA.module.dashboard.init();
});