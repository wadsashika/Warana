/**
 * Created by Nadeeshaan on 11/15/2014.
 */

WARANA.processResume = function () {
    var selectAll = function(){
        if(this.checked) {
            $('.files-checkbox').each(function() {
                this.checked = true;
            });
        }else{
            $('.files-checkbox').each(function() {
                this.checked = false;
            });
        }
    };

    var clearSelection = function () {
        $('.files-checkbox').each(function(){
            this.checked = false;
        });
    };

    var setFileListDataTable = function(){
        $("#unprocessed-resume-list").dataTable(
            {
                "bSort": false
            }
        );
    };

    var deleteResumeRow = function(){
        var fileName = $(this).closest('tr').children('td:eq(1)').text();
        var row = $(this).closest('tr').first();
        var nRow = row[0];

        $.ajax({
            type : 'POST',
            url : 'process/delete',
            data : {fileName:fileName},
            success: function(data){
                var fileDeleted = data;

                alert(data);
                if (fileDeleted == true){
                    $("#unprocessed-resume-list").dataTable().fnDeleteRow(nRow);
                }
                else{
                    alert("Cannot find the File");
                }
            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });


    };

    return {
        init: function(){
            setFileListDataTable();
            $(document).on("click", "#select-all", selectAll);
            $(document).on("click","#clear-selection",clearSelection);
            $(document).on("click",".delete-resume", deleteResumeRow);
        }
    }
}();

$(function(){
    WARANA.processResume.init();
});