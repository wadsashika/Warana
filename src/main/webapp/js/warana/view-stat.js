WARANA.namespace("module.viewStat");

WARANA.module.viewStat = function () {

    var dataTbl;

    var collapseArea = function () {
        $(this).parent().children('ul.tree').toggle(300);
    };

    var tagInput = function () {
        var technologies = [];

        $.ajax({
            type: "POST",
            url: "gettechnologies",
            success: function (data) {
                var jsonObj = JSON.parse(data);
                for (var i = 0; i < jsonObj.length; i++) {
                    technologies.push(jsonObj[i]);
                }
            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });

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
        $.ajax({
            type: "POST",
            url: "getresult",
            success: function (data) {
                var userList = [];
                var resultList = JSON.parse(data);

                for (var i = 0; i < resultList.length; i++) {
                    var row = resultList[i];
                    var dataTableRow = [];
                    dataTableRow.push("<input type='checkbox' class='files-checkbox'>");
                    dataTableRow.push(row.id);
                    dataTableRow.push(row.name);
                    dataTableRow.push(row.email);
                    dataTableRow.push(row.score);
                    dataTableRow.push('<button type="button" class="btn btn-primary btn-md view-prof btn-center"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> View</button>');
                    dataTableRow.push('<button type="button" class="btn btn-primary btn-md send-email btn-center"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span> Email</button>');

                    userList.push(dataTableRow);
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
                        { "title": ""},
                        { "title": ""}
                    ],
                    "columnDefs": [
                        {
                            "targets": [ 1 ],
                            "visible": false
                        }
                    ]
                });
            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });
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

        $('#stat-table-div').empty();

        $.ajax({
            type: "POST",
            url: "advsearchresult",
            data: {technologies: technologies},
            success: function (data) {

                var userList = [];
                var resultList = JSON.parse(data);

                for (var i = 0; i < resultList.length; i++) {
                    var row = resultList[i];
                    var dataTableRow = [];
                    dataTableRow.push("<input type='checkbox' class='files-checkbox'>");
                    dataTableRow.push(row.id);
                    dataTableRow.push(row.name);
                    dataTableRow.push(row.email);
                    dataTableRow.push(row.score);
                    dataTableRow.push('<button type="button" class="btn btn-primary btn-md view-prof btn-center"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> View</button>');
                    dataTableRow.push('<button type="button" class="btn btn-primary btn-md view-tech btn-center"><span class="glyphicon glyphicon-signal" aria-hidden="true"></span> Stat</button>');
                    dataTableRow.push('<button type="button" class="btn btn-primary btn-md send-email btn-center"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span> Email</button>');

                    userList.push(dataTableRow);
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
                        { "title": ""},
                        { "title": ""},
                        { "title": ""}

                    ],
                    "columnDefs": [
                        {
                            "targets": [ 1 ],
                            "visible": false
                        }
                    ]
                });
            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });

        processCompareAllBarChart();

        document.getElementById("compareAllMainDiv").style.display = "block";
    };

    var drawCompareAllBarChart = function (series, categories) {
        $("#back-btn").show();
        $("#compareAllChartArea").empty();

        $("#compareAllChartArea").highcharts({
            chart: {
                type: 'column',
                options3d: {
                    enabled: true,
                    alpha: 0,
                    beta: 15,
                    viewDistance: 100,
                    depth: 30
                },
                marginTop: 80,
                marginRight: 40
            },
            title: {
                text: 'Technology Expertise Comparison'
            },
            xAxis: {
                categories: categories
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Normalized Percentage (%)'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px;">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0;font-size:9px;">{series.name}: </td>' +
                    '<td style="padding:0;font-size:9px"><b>{point.y:.1f} %</b></td></tr>',
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


        $(document).on('click', "#compare-all-href" ,function () {
            console.log("Clicked");
            $('#compareAllChartArea').highcharts().reflow();
        });
    };

    var processCompareAllBarChart = function () {

        var technologies = $("#tagged-search-field").val();
        var techsArr = technologies.split(",");
        var series = [];
        var categories = [];

        for (var a = 0; a < techsArr.length; a++) {
            categories.push(techsArr[a]);
        }

        $.ajax({
            type: "POST",
            url: "getcomparestats",
            data: {technologies: technologies},
            success: function (data) {
                var jsonObj = JSON.parse(data);
                for (var a = 0; a < jsonObj.length; a++) {
                    var nameVal = jsonObj[a].name;
                    var associativeData = {};
                    var dataSet = [];
                    var map = jsonObj[a].technologyScoreMap;

                    for (var i = 0; i < techsArr.length; i++) {
                        dataSet.push(parseFloat(map[techsArr[i]]));
                    }

                    associativeData["name"] = nameVal;
                    associativeData["data"] = dataSet;

                    series.push(associativeData);
                }

                drawCompareAllBarChart(series, categories);

            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });
    };

    var drawChart = function (data) {

        // Empty the div before loading chart
        $('#chart-container').empty();
        // Build the chart
        $('#chart-container').highcharts({
            chart: {
                type: 'pie',
                options3d: {
                    enabled: true,
                    alpha: 45,
                    beta: 0
                },
                borderColor: '#A6A6A6',
                borderWidth: 1,
                borderRadius: 5,
                backgroundColor: '#F8F8F8'
            },
            title: {
                text: 'Technologies Expertise for the Candidate'
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    depth: 35,
                    dataLabels: {
                        enabled: true,
                        format: '{point.name}'
                    }
                }
            },
            credits: {
                enabled: false
            },
            series: [
                {
                    type: 'pie',
                    name: 'Expertise as Percentage',
                    data: data
                }
            ]
        });

        $('#chart-modal').on('show.bs.modal', function () {
            $('#chart-container').css('visibility', 'hidden');
        });
        $('#tech-chart').on('shown.bs.modal', function () {
            $('#chart-container').css('visibility', 'initial');
            $('#chart-container').highcharts().reflow();
        });

    }

    var viewProfileClick = function () {
        var id = dataTbl.fnGetData($(this).closest("tr").get(0))[1];

        $.ajax({
            type: 'POST',
            url: 'analyze/profile',
            data: {id: id},
            success: function (data) {
                var json = JSON.parse(data);
                var profile = JSON.parse(json.profile);
                var projects = JSON.parse(json.projects);
                var publications = JSON.parse(json.publications);
                var achievement = JSON.parse(json.achievement);
                var workexp = JSON.parse(json.workexp);
                var education = JSON.parse(json.education);

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
                nameDd.innerHTML = profile.name;
                profileDl.appendChild(nameDd);

                var addressDt = document.createElement('dt');
                addressDt.innerHTML = "Address";
                profileDl.appendChild(addressDt);
                var addressDd = document.createElement('dd');
                addressDd.innerHTML = profile.address;
                profileDl.appendChild(addressDd);

                var emailDt = document.createElement('dt');
                emailDt.innerHTML = "Email";
                profileDl.appendChild(emailDt);
                var emailDd = document.createElement('dd');
                emailDd.innerHTML = profile.email;
                profileDl.appendChild(emailDd);

                var genderDt = document.createElement('dt');
                genderDt.innerHTML = "Gender";
                profileDl.appendChild(genderDt);
                var genderDd = document.createElement('dd');
                genderDd.innerHTML = profile.gender;
                profileDl.appendChild(genderDd);

                var maritalDt = document.createElement('dt');
                maritalDt.innerHTML = "Marital Status";
                profileDl.appendChild(maritalDt);
                var maritalDd = document.createElement('dd');
                maritalDd.innerHTML = profile.marital_status;
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

                    specialization.innerHTML = education[a].specialization;
                    grade.innerHTML = education[a].grade;

                    dt.innerHTML = "<span class='glyphicon glyphicon-home prof-glyp' aria-hidden='true'></span>" + education[a].institution_name;
                    dd.innerHTML = education[a].duration;
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
                    post.innerHTML = workexp[a].post;
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
                    dd.innerHTML = projects[a].description;
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
                    dd.innerHTML = publications[a].description;
                    dl.appendChild(dt);
                    dl.appendChild(dd);
                    div.appendChild(dl);
                    pubTab.appendChild(div);
                }


            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });

        $("#profile-info").modal(
            {
                show: true
            }
        );
    };

    var viewStatChart = function () {
        var id = dataTbl.fnGetData($(this).closest("tr").get(0))[1];
        var name = dataTbl.fnGetData($(this).closest("tr").get(0))[2];
        var pieChartData = [];

        document.getElementById('graph-modal-title').innerHTML = name;

        $.ajax({
            type: "POST",
            url: "getstat",
            data: {id: id},
            success: function (data) {
                var jsonObj = JSON.parse(data);

                for (var i = 0; i < jsonObj.length; i++) {
                    var pieSlice = [];
                    pieSlice.push(jsonObj[i].technology);
                    pieSlice.push(jsonObj[i].percentage);
                    pieChartData.push(pieSlice);
                }

                /**
                 * Drawing chart
                 */
                $("#tech-chart").modal(
                    {
                        show: true
                    }
                );
                drawChart(pieChartData);
            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });
    };

    var changeArrowUpDown = function () {
        if($(this).children("span:first").hasClass("currentDown")){
            $(this).children("span:first").removeClass("currentDown");
            $(this).children("span:first").removeClass("glyphicon glyphicon-chevron-down");
            $(this).children("span:first").addClass("currentUp");
            $(this).children("span:first").addClass("glyphicon glyphicon-chevron-up");
        }
        else if($(this).children("span:first").hasClass("currentUp")){
            $(this).children("span:first").removeClass("currentUp");
            $(this).children("span:first").removeClass("glyphicon glyphicon-chevron-up");
            $(this).children("span:first").addClass("currentDown");
            $(this).children("span:first").addClass("glyphicon glyphicon-chevron-down");
        }
    };

    var goBack = function(){
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
            $(document).on("click", ".view-tech", viewStatChart);
            $(document).on("click", "#advHref", changeArrowUpDown);
            $(document).on("click", "#compare-all-href", changeArrowUpDown);
            $(document).on("click", "#back-btn", goBack);
        }
    }
}();

$(function () {
    WARANA.module.viewStat.init();
});
