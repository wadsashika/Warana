WARANA.namespace("module.viewStat");

WARANA.module.viewStat = function () {

    var dataTbl;

    var collapseArea = function () {
        $(this).parent().children('ul.tree').toggle(300);
    };

    var tagInput = function () {
        var technologies = [];

        var ajaxInitData = {
            url: 'viewstat/gettechnologies',
            contentType: "application/json"
        };

        var successFn = function (result) {
            if (result.success) {
                var technologyList = $.evalJSON(result.result.technologyList);

                for (var i = 0; i < technologyList.length; i++) {
                    technologies.push(technologyList[i]);
                }
            }
        };

        WARANA.common.ajaxCall(ajaxInitData, successFn);

        var elt = $('#tagged-search-field');
        elt.val("");
        elt.typeahead();

        $('#tagged-search-field').tagsinput({
            typeahead: {
                source: technologies
            }
        });
    };

    var loadDataTable = function () {
        $("#back-btn").hide();

        var ajaxInitData = {
            url: 'viewstat/getresult',
            contentType: "application/json"
        };

        var successFn = function (data) {
            var userList = [];
            if (data.success) {
                var resultList = $.evalJSON(data.result.resultList);

                for (var i = 0; i < resultList.length; i++) {
                    var row = resultList[i];
                    var dataTableRow = [];

                    var score_val = row.score;
                    dataTableRow.push("<input type='checkbox' class='files-checkbox'>");
                    dataTableRow.push(row.id);
                    dataTableRow.push(row.name);
                    dataTableRow.push(row.email);

                    if(score_val >= 0 && score_val <= 33){
                        dataTableRow.push('<div class="progress progress-striped active "><div class="progress-bar progress-bar-1 progress-bar-danger" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: '+score_val+'%;"><div id="pbar-val">'+score_val+'%</div></div></div>');
                    }
                    else if(score_val >= 34 && score_val <= 66){
                        dataTableRow.push('<div class="progress progress-striped active "><div class="progress-bar progress-bar-1 progress-bar-warning" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: '+score_val+'%;"><div id="pbar-val">'+score_val+'%</div></div></div>');
                    }
                    else if(score_val >= 67 && score_val <= 100){
                        dataTableRow.push('<div class="progress progress-striped active "><div class="progress-bar progress-bar-1 progress-bar-success" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: '+score_val+'%;"><div id="pbar-val">'+score_val+'%</div></div></div>');
                    }

//                    dataTableRow.push(row.score);
                    dataTableRow.push('<button type="button" class="btn btn-success btn-sm view-prof btn-center"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> View</button>');

                    userList.push(dataTableRow);
                }

            }

            $('#stat-table-div').html('<table class="table table-striped table-hover" id="stat-table" ></table>');

            dataTbl = $('#stat-table').dataTable({
                "bSort": false,
                "data": userList,
                "columns": [
                    { "title": "Select" },
                    { "title": "Id"},
                    { "title": "Name"  },
                    { "title": "Email" },
                    { "title": "Score" },
                    { "title": ""}
                ],
                "columnDefs": [
                    {
                        "targets": [ 1 ],
                        "visible": false
                    }
                ]
            });
        };

        WARANA.common.ajaxCall(ajaxInitData, successFn);

    };

    var selectAll = function () {
        if (this.checked) {
            $('.files-checkbox').each(function () {
                this.checked = true;
            });
        } else {
            $('.files-checkbox').each(function () {
                this.checked = false;
            });
        }
    };

    var clearSelected = function () {
        $('.files-checkbox').each(function () {
            this.checked = false;
        });
    };

    var searchCandidates = function () {
        var technologies = $("#tagged-search-field").val();
        technologies = technologies.split(',');
        $('#stat-table-div').empty();

        var ajaxInitData = {
            url: 'viewstat/advsearchresult',
            data: $.toJSON(technologies),
            contentType: "application/json"
        };

        var successFn = function (data) {
            var userList = [];

            if (data.success) {
                var resultList = $.evalJSON(data.result.advancedSearchResults);

                for (var i = 0; i < resultList.length; i++) {
                    var row = resultList[i];
                    var dataTableRow = [];
                    dataTableRow.push("<input type='checkbox' class='files-checkbox'>");
                    dataTableRow.push(row.id);
                    dataTableRow.push(row.name);
                    dataTableRow.push(row.email);
                    var score_val = row.score;

                    if(score_val >= 0 && score_val <= 33){
                        dataTableRow.push('<div class="progress progress-striped active "><div class="progress-bar progress-bar-1 progress-bar-danger" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: '+score_val+'%;"><div id="pbar-val">'+score_val+'%</div></div></div>');
                    }
                    else if(score_val >= 34 && score_val <= 66){
                        dataTableRow.push('<div class="progress progress-striped active "><div class="progress-bar progress-bar-1 progress-bar-warning" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: '+score_val+'%;"><div id="pbar-val">'+score_val+'%</div></div></div>');
                    }
                    else if(score_val >= 67 && score_val <= 100){
                        dataTableRow.push('<div class="progress progress-striped active "><div class="progress-bar progress-bar-1 progress-bar-success" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: '+score_val+'%;"><div id="pbar-val">'+score_val+'%</div></div></div>');
                    }

                    dataTableRow.push('<button type="button" class="btn btn-success btn-sm view-prof btn-center"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> View</button>');

                    userList.push(dataTableRow);
                }

            }

            $('#stat-table-div').html('<table class="table table-striped table-hover" id="stat-table"></table>');

            dataTbl = $('#stat-table').dataTable({
                "bSort": false,
                "data": userList,
                "columns": [
                    { "title": "Select" },
                    { "title": "Id"},
                    { "title": "Name"  },
                    { "title": "Email" },
                    { "title": "Score" },
                    { "title": ""}

                ],
                "columnDefs": [
                    {
                        "targets": [ 1 ],
                        "visible": false
                    }
                ]
            });
        };

        WARANA.common.ajaxCall(ajaxInitData, successFn);

        processCompareAllBarChart();

        document.getElementById("compareAllMainDiv").style.display = "block";
    };

    var drawCompareAllBarChart = function (series, categories) {
        $("#back-btn").show();
        $("#compareAllChartArea").empty();

        $("#compareAllChartArea").highcharts({
            chart: {
                type: 'column',
                marginTop: 80,
                marginRight: 40
            },
            credits: {
                enabled: false
            },
            title: {
                text: 'Skill Expertise Comparison'
            },
            xAxis: {
                categories: categories
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Absolute Proficiency'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px;">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0;font-size:9px;">{series.name}: </td>' +
                    '<td style="padding:0;font-size:9px"><b>{point.y:.1f}</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 1
                }
            },
            series: series
        });


        $(document).on('click', "#compare-all-href", function () {
            console.log("Clicked");
            $('#compareAllChartArea').highcharts().reflow();
        });
    };

    var drawSpiderChart = function (cId, cName) {

        var candidateId = cId;
        var candidateName = cName;

        var categories = [];
        var seriesData = [];
        var companyPolygonData = [];
        $.ajax({
            url: "viewstat/getspiderwebdata",
            type: "POST",
            data: {"id": candidateId},
            success: function (data) {
                var jsonObj = JSON.parse(data);
                for (var a = 0; a < jsonObj.length; a++) {
                    categories.push(jsonObj[a].techName);
                    seriesData.push(parseFloat(jsonObj[a].percentage));
                    companyPolygonData.push(parseFloat(jsonObj[a].cscore));
                }
                console.log(categories);
                console.log(seriesData);

                $('#spider-web-region').highcharts({

                    chart: {
                        polar: true,
                        type: 'area'
                    },

                    title: {
                        text: 'Candidate Mapping',
                        x: -80
                    },

                    pane: {
                        size: '80%'
                    },
                    xAxis: {
                        categories: categories,
                        tickmarkPlacement: 'on',
                        lineWidth: 0
                    },

                    yAxis: {
                        gridLineInterpolation: 'polygon',
                        lineWidth: 0
                    },

                    tooltip: {
                        shared: true,
                        pointFormat: '<span style="color:{series.color}">{series.name}: <b>{point.y:,.0f}</b><br/>'
                    },
                    credits: {
                        enabled: false
                    },

                    legend: {
                        align: 'right',
                        verticalAlign: 'top',
                        y: 70,
                        layout: 'vertical'
                    },
                    series: [

                        {
                            type: 'area',
                            color: '#90ED7D',
                            name: 'Company Polygon',
                            data: companyPolygonData,
                            pointPlacement: 'on'
                        },
                        {
                            type: 'line',
                            color: '#434348',
                            name: candidateName,
                            data: seriesData,
                            pointPlacement: 'on'
                        }
                    ]

                });
            }
        });

        $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            $('#spider-web-region').highcharts().reflow();
        });
    }

    var processCompareAllBarChart = function () {

        var technologies = $("#tagged-search-field").val();
        var techsArr = technologies.split(",");
        var series = [];
        var categories = [];

        for (var a = 0; a < techsArr.length; a++) {
            categories.push(techsArr[a]);
        }

        var ajaxInitData = {
            url: 'viewstat/getcomparestats',
            data: $.toJSON(techsArr),
            contentType: "application/json"
        };

        var successFn = function (data) {
            if (data.success) {
                var jsonObj = $.evalJSON(data.result.compareAllList);
//                var nameSelector = document.getElementById("name-select-selector");
//                nameSelector.options.length = 0;

                for (var a = 0; a < jsonObj.length; a++) {
                    var nameVal = jsonObj[a].name;
                    var associativeData = {};
                    var dataSet = [];
                    var candidateId = jsonObj[a].id;
                    var map = jsonObj[a].technologyScoreMap;

                    var option = document.createElement("option");
                    option.value = candidateId;
                    option.text = nameVal;
//                    nameSelector.appendChild(option);

                    for (var i = 0; i < techsArr.length; i++) {
                        dataSet.push(parseFloat(map[techsArr[i]]));
                    }

                    associativeData["name"] = nameVal;
                    associativeData["data"] = dataSet;

                    series.push(associativeData);
                }
                drawCompareAllBarChart(series, categories);
            }
        };

        WARANA.common.ajaxCall(ajaxInitData, successFn);

    };

    var drawStatChart = function (data) {
        // Empty the div before loading chart
        $('#chart-region').empty();
        // Build the chart
        $("#chart-region").highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: 'Skill Proficiency'
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
                    data: data,
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

        $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            $('#chart-region').highcharts().reflow();
        });
    }

    var viewProfileClick = function () {
        var id = dataTbl.fnGetData($(this).closest("tr").get(0))[1];
        var cName = dataTbl.fnGetData($(this).closest("tr").get(0))[2];

        var ajaxInitData = {
            url: 'analyze/profile',
            data: $.toJSON(id),
            contentType: "application/json"
        };

        var successFn = function (data) {
            if (data.success) {
//                console.log(data.result);
                var profile = $.evalJSON(data.result.profile);
                var projects = $.evalJSON(data.result.projects);
                var publications = $.evalJSON(data.result.publications);
                var achievement = $.evalJSON(data.result.achievement);
                var workexp = $.evalJSON(data.result.workexp);
                var education = $.evalJSON(data.result.education);
            }

            var eduTab = document.getElementById("education");
            eduTab.innerHTML = "";

            var achTab = document.getElementById("achievements");
            achTab.innerHTML = "";

            var wrkTab = document.getElementById("work-exp");
            wrkTab.innerHTML = "";

            var projTab = document.getElementById("projects");
            projTab.innerHTML = "";

            var pubTab = document.getElementById("publications");
            pubTab.innerHTML = "";

            var profImg = document.getElementById("prof-img");
            profImg.src = "images\/default.png";

            var profileDl = document.getElementById("profile-detail-dl");
            profileDl.innerHTML = "";

            var userName = document.getElementById("myModalLabel");
            userName.innerHTML = profile.name;

            var nameDt = document.createElement('dt');
            nameDt.innerHTML = "Name";
            profileDl.appendChild(nameDt);
            var nameDd = document.createElement('dd');
            nameDd.innerHTML = (profile.name != null) ? profile.name : "";
            profileDl.appendChild(nameDd);

            var addressDt = document.createElement('dt');
            addressDt.innerHTML = "Address";
            profileDl.appendChild(addressDt);
            var addressDd = document.createElement('dd');
            addressDd.innerHTML = (profile.address != null) ? profile.address : "";
            profileDl.appendChild(addressDd);

            var emailDt = document.createElement('dt');
            emailDt.innerHTML = "Email";
            profileDl.appendChild(emailDt);
            var emailDd = document.createElement('dd');
            emailDd.innerHTML = (profile.email != null) ? profile.email : "";
            profileDl.appendChild(emailDd);

            var genderDt = document.createElement('dt');
            genderDt.innerHTML = "Gender";
            profileDl.appendChild(genderDt);
            var genderDd = document.createElement('dd');
            genderDd.innerHTML = (profile.gender != null) ? profile.gender : "";
            profileDl.appendChild(genderDd);

            var maritalDt = document.createElement('dt');
            maritalDt.innerHTML = "Marital Status";
            profileDl.appendChild(maritalDt);
            var maritalDd = document.createElement('dd');
            maritalDd.innerHTML = (profile.marital_status != null) ? profile.marital_status : "";
            profileDl.appendChild(maritalDd);

            /**
             * Setting the Educations Information tab data
             */

            for (var a = 0; a < education.length; a++) {
                var div = document.createElement("div");
                div.className = "prof-div";

                var dl = document.createElement('dl');

                var dt = document.createElement('dt');
                dt.className = "prof-dt";

                var dd = document.createElement('dd');
                dd.className = "prof-dd";

                var specialization = document.createElement('p');
                specialization.className = "prof-p";

                var grade = document.createElement('p');
                grade.className = "prof-p";

                specialization.innerHTML = (education[a].specialization != null) ? education[a].specialization : "";
                grade.innerHTML = (education[a].grade != null) ? education[a].grade : "";

                dt.innerHTML = "<span class='glyphicon glyphicon-home prof-glyp' aria-hidden='true'></span>" + education[a].institution_name;
                dd.innerHTML = (achievement[a].date != null) ? achievement[a].date : "";
                dl.appendChild(dt);
                dl.appendChild(dd);
                div.appendChild(dl);
                div.appendChild(specialization);
                div.appendChild(grade);
                eduTab.appendChild(div);
                eduTab.appendChild(div);
            }

            /**
             * Setting the Achievements Information tab data
             */

            for (var a = 0; a < achievement.length; a++) {
                var div = document.createElement("div");
                div.className = "prof-div";

                var dl = document.createElement('dl');

                var dt = document.createElement('dt');
                dt.className = "prof-dt";

                var dd = document.createElement('dd');
                dd.className = "prof-dd";

                dt.innerHTML = "<span class='glyphicon glyphicon-flag prof-glyp' aria-hidden='true'></span>" + achievement[a].description;
                dd.innerHTML = achievement[a].date;
                dl.appendChild(dt);
                dl.appendChild(dd);
                div.appendChild(dl);
                achTab.appendChild(div);
            }

            /**
             * Setting the Work Experience Information tab data
             */

            for (var a = 0; a < workexp.length; a++) {
                var div = document.createElement("div");
                div.className = "prof-div";

                var dl = document.createElement('dl');

                var dt = document.createElement('dt');
                dt.className = "prof-dt";

                var dd = document.createElement('dd');
                dd.className = "prof-dd";

                var post = document.createElement('p');
                post.className = "prof-p";

                dt.innerHTML = "<span class='glyphicon glyphicon-bookmark prof-glyp' aria-hidden='true'></span>" + workexp[a].company_name;
                dd.innerHTML = workexp[a].from + " - " + workexp[a].to;
                dd.innerHTML = ((workexp[a].from != null) ? workexp[a].from : "") + ((workexp[a].to != null) ? (" - " + workexp[a].to) : "");
                post.innerHTML = (workexp[a].post != null) ? workexp[a].post : "";
                dl.appendChild(dt);
                dl.appendChild(dd);
                div.appendChild(dl);
                div.appendChild(post);
                wrkTab.appendChild(div);
            }

            /**
             * Setting the Projects Information tab data
             */

            for (var a = 0; a < projects.length; a++) {
                var div = document.createElement("div");
                div.className = "prof-div";

                var dl = document.createElement('dl');

                var dt = document.createElement('dt');
                dt.className = "prof-dt";

                var dd = document.createElement('dd');
                dd.className = "prof-dd";

                dt.innerHTML = "<span class='glyphicon glyphicon-folder-open prof-glyp' aria-hidden='true'></span>" + projects[a].proj_name;
                dd.innerHTML = (projects[a].description != null) ? projects[a].description : "";
                dl.appendChild(dt);
                dl.appendChild(dd);
                div.appendChild(dl);
                projTab.appendChild(div);
            }

            /**
             * Setting the Publications Information tab data
             */

            for (var a = 0; a < publications.length; a++) {
                var div = document.createElement("div");
                div.className = "prof-div";

                var dl = document.createElement('dl');

                var dt = document.createElement('dt');
                dt.className = "prof-dt";

                var dd = document.createElement('dd');
                dd.className = "prof-dd";

                dt.innerHTML = "<span class=' glyphicon glyphicon-book prof-glyp' aria-hidden='true'></span>" + publications[a].name;
                dd.innerHTML = (publications[a].description != null) ? publications[a].description : "";
                dl.appendChild(dt);
                dl.appendChild(dd);
                div.appendChild(dl);
                pubTab.appendChild(div);
            }
        };

        WARANA.common.ajaxCall(ajaxInitData, successFn);

        viewStatChart(id);
        drawSpiderChart(id, cName);

        $("#profile-info").modal(
            {
                show: true
            }
        );
    };

    var viewStatChart = function (data) {
        var id = data;
        var statChartData = [];

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

    var changeArrowUpDown = function () {
        if ($(this).children("span:first").hasClass("currentDown")) {
            $(this).children("span:first").removeClass("currentDown");
            $(this).children("span:first").removeClass("glyphicon glyphicon-chevron-down");
            $(this).children("span:first").addClass("currentUp");
            $(this).children("span:first").addClass("glyphicon glyphicon-chevron-up");
        }
        else if ($(this).children("span:first").hasClass("currentUp")) {
            $(this).children("span:first").removeClass("currentUp");
            $(this).children("span:first").removeClass("glyphicon glyphicon-chevron-up");
            $(this).children("span:first").addClass("currentDown");
            $(this).children("span:first").addClass("glyphicon glyphicon-chevron-down");
        }
    };

    var goBack = function () {
        location.href = "/warana/viewstat";
    };

    return {
        init: function () {
            tagInput();
            loadDataTable();
            $(document).on("click", "label.tree-toggler", collapseArea);
            $(document).on("click", "#select-all", selectAll);
            $(document).on("click", "#clear-selection", clearSelected);
            $(document).on("click", "#search-submit", searchCandidates);
            $(document).on("click", ".view-prof", viewProfileClick);
            $(document).on("click", "#advHref", changeArrowUpDown);
            $(document).on("click", "#compare-all-href", changeArrowUpDown);
            $(document).on("click", "#back-btn", goBack);
        }
    }
}();

$(function () {
    WARANA.module.viewStat.init();
});
