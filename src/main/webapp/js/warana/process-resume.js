/**
 * Created by Nadeeshaan on 11/15/2014.
 */

$(document).ready(function() {
    $('#select-all').click(function(event) {
        if(this.checked) {
            $('.files-checkbox').each(function() {
                this.checked = true;
            });
        }else{
            $('.files-checkbox').each(function() {
                this.checked = false;
            });
        }
    });

    $("#clear-selection").click(function(event){
       $('.files-checkbox').each(function(){
           this.checked = false;
       });
    });
});
