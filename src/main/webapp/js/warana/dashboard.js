WARANA.namespace("module.dashboard");

WARANA.module.dashboard = function () {

    var viewBarChart = function (data) {
        var id = data;
        var bars = [];


        $.ajax({
            url: "dashboard/getbarchartdata",
            type: "POST",
            success: function(data){

                var jsonObj = JSON.parse(data);

                for (var i = 0; i < jsonObj.length; i++) {
                    var singleBar = [];
                    singleBar.push(jsonObj[i].technology);
                    singleBar.push(jsonObj[i].score);
                    bars.push(singleBar);
                }

                $('#bar-chart-area').empty();
                // Build the chart
                $("#bar-chart-area").highcharts({
                    chart: {
                        type: 'column'
                    },
                    title: {
                        text: 'Company Skills Proficiencies'
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
                        pointFormat: 'Proficiency: <b>{point.y:.1f}</b>'
                    },
                    series: [
                        {
                            name: 'Proficiency',
                            data: bars,
                            dataLabels: {
                                enabled: false,
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