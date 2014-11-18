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

    return {
        init: function(){
            setFileListDataTable();
            $(document).on("click", "#select-all", selectAll);
            $(document).on("click","#clear-selection",clearSelection());
        }
    }
}();

$(function(){
    WARANA.processResume.init();
});