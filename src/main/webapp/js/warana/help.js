WARANA.namespace("module.home.help");

WARANA.module.home.help = function () {

    var goBack = function(){
        location.href = "/warana/dashboard";
    };

    return {
        init: function () {
            $(document).on("click", "#backBtn", goBack);
        }
    };

}();

$(function () {
    WARANA.module.home.help.init();
});


