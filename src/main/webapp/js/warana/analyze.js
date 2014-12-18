WARANA.namespace("module.analyzeResume");

WARANA.module.analyzeResume = function () {

    var dataTbl;

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

    var analyzeResumes = function(){
        var selected = [];
        $('.files-checkbox:checked').each(function () {
            selected.push(dataTbl.fnGetData($(this).closest("tr").get(0))[1]);
        });

        $.ajax({
            type: 'POST',
            url: 'analyze/analyzelist',
            data: JSON.stringify(selected),
            contentType: "application/json",
            success: function (data) {
                alert(data);
            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });
    };


    return {
        init: function () {
            setResumesToProcessDataTable();

            $(document).on("click", "#select-all", selectAll);
            $(document).on("click", "#clear-selection", clearSelected);
            $(document).on("click", "#analyze-candidate-btn", analyzeResumes);
        }
    }

}();

$(function () {
    WARANA.module.analyzeResume.init();
});


