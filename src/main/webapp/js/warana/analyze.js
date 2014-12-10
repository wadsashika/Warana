/**
 * Created by Nadeeshaan on 11/15/2014.
 */

WARANA.analyzeResume = function() {

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
        $("#resumes-to-process-table").dataTable(
            {
                "bSort": false
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
    
    
    return {
        init: function () {
            setResultsDataTable();
            setResumesToProcessDataTable();

            $(document).on("click", "#select-all", selectAll);
            $(document).on("click", "#clear-selection", clearSelected);
            $(document).on("click", ".view-prof", viewProfileClick);
        }
    }
    
}();

$(function(){
    WARANA.analyzeResume.init();
});


