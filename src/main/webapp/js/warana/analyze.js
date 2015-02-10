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

    var analyzeResumes = function () {
        var selected = [];
        $('.files-checkbox:checked').each(function () {
            selected.push(dataTbl.fnGetData($(this).closest("tr").get(0))[1]);
        });

        WARANA.displayLoadingModel();

        var ajaxInitData = {
            url: 'analyze/analyzelist',
            data: JSON.stringify(selected),
            contentType: "application/json"
        };

        var successFn = function (result) {
            if (result) {
                WARANA.hideLoadingModel();
                WARANA.successMessageWithCallBack(loadStatPage, "Selected CVs analyzed successfully");
            } else {
                WARANA.message(WARANA.messageType.ERROR, "Error has occurred");
            }
        };

        WARANA.common.ajaxCall(ajaxInitData, successFn);

    };

    var loadStatPage = function () {
        location.href = "/warana/viewstat";
    };

    var backBtnClick = function () {
        WARANA.messageConfirmation(backBtnSuccessFn, "Leave Page Confirmation", "Are you sure want to leave this page?");
    };

    var backBtnSuccessFn = function () {
        location.href = "/warana/dashboard";
    };

    return {
        init: function () {
            setResumesToProcessDataTable();

            $(document).on("click", "#select-all", selectAll);
            $(document).on("click", "#analyze-candidate-btn", analyzeResumes);
            $(document).on("click", "#backBtn", backBtnClick);
        }
    }

}();

$(function () {
    WARANA.module.analyzeResume.init();
});


