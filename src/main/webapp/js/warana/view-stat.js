WARANA.namespace("module.viewStat");

WARANA.module.viewStat = function () {

    var dataTbl;

    var collapseArea = function () {
        $(this).parent().children('ul.tree').toggle(300);
    };

    var tagInput = function () {
        var colors = ["red", "blue", "green", "yellow", "brown", "black"];
        var elt = $('#tagged-search-field');

        elt.typeahead();

        $('#tagged-search-field').tagsinput({
            typeahead: {
                source: colors
            }
        });
    };

    var loadDataTable = function (techs) {
        $.ajax({
            type: "POST",
            url: "getresult",
            data: {technologies: techs},
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
        alert(technologies);
//        loadDataTable(technologies);
    };

    var viewProfileClick = function(){
        var id = dataTbl.fnGetData($(this).closest("tr").get(0))[1];

        $.ajax({
            type : 'POST',
            url : 'analyze/profile',
            data : {id:id},
            success: function(data){
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

                for( var a = 0; a<education.length; a++){
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

                for( var a = 0; a<achievement.length; a++){
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

                for( var a = 0; a<workexp.length; a++){
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

                for( var a = 0; a<projects.length; a++){
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

                for( var a = 0; a<publications.length; a++){
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
//
        $("#profile-info").modal(
            {
                show : true
            }
        );
    };

    return {
        init: function () {
            tagInput();
            loadDataTable();
            $(document).on("click", "label.tree-toggler", collapseArea);
            $(document).on("click", "#select-all", selectAll);
            $(document).on("click", "#clear-selection", clearSelected);
            $(document).on("click", "#search-submit", searchCandidates);
            $(document).on("click", "#search-submit", searchCandidates);
            $(document).on("click", ".view-prof", viewProfileClick);
        }
    }
}();

$(function () {
    WARANA.module.viewStat.init();
});
