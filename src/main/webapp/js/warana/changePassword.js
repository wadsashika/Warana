WARANA.namespace("module.myProfile.changePassword");

WARANA.module.myProfile.changePassword = function () {

    var savePasswordClick = function () {
        WARANA.messageConfirmation(saveCheck, "Save new password", "Are you sure want ot save new password?");
    };

    var saveCheck = function () {

        var save = function () {
            var data = getDateToSave();

            var ajaxInitData = {
                url: "newPassword/save",
                data: $.toJSON(data),
                contentType: "application/json"
            }

            var successFn = function (result) {
                if (result.success) {
                    WARANA.successMessageWithCallBack(goBack, "Your password changed successfully. You can use your new password when you are login next time.");
                } else {
                    WARANA.message(WARANA.messageType.ERROR, "Your password is not correct. Pleas try again.");
                }
            };

            WARANA.common.ajaxCall(ajaxInitData, successFn);
        };

        $("#password-body").validation({
            validated: save
        });

    };

    var getDateToSave = function () {
        var data = {
            oldPassword: $("#oldPassword").val(),
            newPassword: $("#newPassword").val()
        };

        return data;
    };

    var goBackClick = function () {
        WARANA.messageConfirmation(goBack, "Leave Page Confirmation", "Your changes will discarded. Are you sure want to leave this page?");
    };

    var goBack = function () {
        location.href = "/warana/myprofile";
    };

    return {
        init: function () {
            $(document).on("click", "#backBtn", goBackClick);
            $(document).on("click", "#savePassword", savePasswordClick);
        }
    };
}();

$(function () {
    WARANA.module.myProfile.changePassword.init();
});