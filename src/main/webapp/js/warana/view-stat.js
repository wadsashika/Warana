/**
 * Created by Nadeeshaan on 11/18/2014.
 */
WARANA.viewStat = function(){

    var collapseArea = function(){
        $(this).parent().children('ul.tree').toggle(300);
    };

    var tagInput = function(){
        var colors = ["red", "blue", "green", "yellow", "brown", "black"];
        var elt = $('#tagged-search-field');

        elt.typeahead();

        $('#tagged-search-field').tagsinput({
            typeahead: {
                source: colors
            }
        });
    };

    var loadDataTable = function(techs){
        $.ajax({
            type: "POST",
            url: "getresult",
            data: {technologies:techs},
            success: function (data) {
                var userList = [];
                var resultList = JSON.parse(data);

                for (var i=0; i < resultList.length; i++) {
                    var row = resultList[i];
                    var dataTableRow = [];
                    dataTableRow.push("<input type='checkbox' class='files-checkbox'>");
                    dataTableRow.push(row.name);
                    dataTableRow.push(row.email);
                    dataTableRow.push(row.score);
                    dataTableRow.push('<button type="button" class="btn btn-primary btn-md view-prof btn-center"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> View</button>');
                    dataTableRow.push('<button type="button" class="btn btn-primary btn-md send-email btn-center"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span> Email</button>');

                    userList.push(dataTableRow);
                }

                $('#stat-table-div').html( '<table class="table table-striped table-hover" id="stat-table"></table>' );

                $('#stat-table').dataTable( {
                    "bSort":false,
                    "data": userList,
                    "columns": [
                        { "title":"Select" },
                        { "title": "Name"  },
                        { "title": "Email" },
                        { "title": "Score" },
                        { "title": ""},
                        {"title": ""}
                    ]
                } );
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

    var searchCandidates = function(){
        var technologies = $("#tagged-search-field").val();
        alert(technologies);
//        loadDataTable(technologies);
    };

    return {
        init: function(){
            tagInput();
            loadDataTable();
            $(document).on("click","label.tree-toggler",collapseArea);
            $(document).on("click","#select-all",selectAll);
            $(document).on("click","#clear-selection",clearSelected);
            $(document).on("click","#search-submit",searchCandidates);
        }
    }
}();

$(function(){
    WARANA.viewStat.init();
});
