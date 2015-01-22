WARANA.namespace("module.myProfile");

WARANA.module.myProfile = function () {

    var initProfile = function () {
        var ajaxInitData = {
            url: 'myprofile/init',
            contentType: "application/json"
        }

        var successFn = function (data) {
            if (data.success) {
                var user = $.evalJSON(data.result.userProfile);

                $("#firstName").val(user.firstName);
                $("#lastName").val(user.lastName);
                $("#email").val(user.email);

                disableTextField();
            }
        };

        WARANA.common.ajaxCall(ajaxInitData,successFn);
    };

    var disableTextField = function () {
        $("#firstName").disabled();
        $("#lastName").disabled();
        $("#email").disabled();

        disableButtons();
    };

    var enableTextField = function () {
        $("#firstName").enabled();
        $("#lastName").enabled();
        $("#email").enabled();

        enableButtons();
    };

    var disableButtons = function () {
        $("#saveProfile").disabled();
        $("#resetProfile").disabled();

        $("#editProfile").enabled();
    };

    var enableButtons = function () {
        $("#saveProfile").enabled();
        $("#resetProfile").enabled();

        $("#editProfile").disabled();
    };

    return {
        init: function () {
            initProfile();
        }
    };
}();

$(function () {
    WARANA.module.myProfile.init();
});