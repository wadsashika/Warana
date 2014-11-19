EXAMPLE = function(){

    var example = function(){
        var ajaxInitData = {
            url:"exampleAjax",
            contentType:'application/json'
        };

        var successFn = function(result){
            if(result.success){
                alert("success");
                alert(result.result.data);
            }
        };

        WARANA.common.ajaxCall(ajaxInitData,successFn);
    };


    return {
        init : function() {
            example();
        }
    };

}();

$(function(){
    EXAMPLE.init();
});


