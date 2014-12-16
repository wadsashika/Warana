/**
 * Created by Nadeeshaan on 11/15/2014.
 */

WARANA.analyzeResume = function() {

    var dataTbl;

    var setResultsDataTable = function () {
        $("#analyzed-results-table").dataTable(
            {

                "bSort": false,
                "columns": [
                    {"width": "10%"},
                    { "width": "60%" },
                    {"width": "10%"},
                    {"width": "10%"},
                    {"width": "10%"}
                ]
            }
        );
    };

    var setResumesToProcessDataTable = function () {
        dataTbl = $("#resumes-to-process-table").dataTable(
            {
                "bSort": false,
                "columnDefs": [
                    {
                        "targets": [ 1 ],
                        "visible": false
                    }
                ]
            }
        );
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

    var viewProfileClick = function(){
        var id = this.id;
        $("#profile-info").modal(
            {
                show : true
            }
        );
    };

    var analyzeResumes = function(){
        var selected = [];
        $('.files-checkbox:checked').each(function() {
            selected.push(dataTbl.fnGetData($(this).closest("tr").get(0))[1]);
        });

        $.ajax({
            type : 'POST',
            url : 'analyze/analyzelist',
            data : JSON.stringify(selected),
            contentType: "application/json",
            success: function(data){
                alert(data);
            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });
    };
    
    
    return {
        init: function () {
            var tbl = setResultsDataTable();
            setResumesToProcessDataTable();

            $(document).on("click", "#select-all", selectAll);
            $(document).on("click", "#clear-selection", clearSelected);
            $(document).on("click", ".view-prof", viewProfileClick);
            $(document).on("click", "#analyze-candidate-btn", analyzeResumes);
        }
    }
    
}();

$(function(){
    WARANA.analyzeResume.init();
});


