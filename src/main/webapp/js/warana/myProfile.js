WARANA.namespace("module.myProfile");

WARANA.module.myProfile = function () {
    var userOriginalProfile = null;

    var initProfile = function () {
        var ajaxInitData = {
            url: 'myprofile/init',
            contentType: "application/json"
        }

        var successFn = function (data) {
            if (data.success) {
                var user = $.evalJSON(data.result.userProfile);
                userOriginalProfile = user;

                $("#firstName").val(user.firstName);
                $("#lastName").val(user.lastName);
                $("#email").val(user.email);

                disableTextField();
            }
        };

        WARANA.common.ajaxCall(ajaxInitData,successFn);
    };

    var editProfile = function(){
        enableTextField();
    };

    var saveEditedProfile = function(){
        WARANA.messageConfirmation(save, "Save Confirmation", "Are you sure want to save changes?");
    };

    var save = function(){
        var data = getProfileData();

        var saveUser = function(){
            var ajaxInitData = {
                url: "myprofile/save",
                data: $.toJSON(data),
                contentType: "application/json"
            }

            var successFn = function(result){
                if(result.success){
                    setEditedProfileDataToOrigin();
                    disableTextField();
                    WARANA.message(WARANA.messageType.SUCCESS, "Your profile updated successfully");
                }else{
                    WARANA.message(WARANA.messageType.ERROR, "Your profile update failed");
                }
            };

            WARANA.common.ajaxCall(ajaxInitData,successFn);
        };

        $("#profile-body").validation({
            validated: saveUser
        });

    };

    var resetProfile = function(){
        $("#firstName").val(userOriginalProfile.firstName);
        $("#lastName").val(userOriginalProfile.lastName);
        $("#email").val(userOriginalProfile.email);
        disableTextField();
    };

    var goBackClick = function(){
        WARANA.messageConfirmation(goBack, "Leave Page Confirmation", "Your changes will discarded. Are you sure want to leave this page?");
    };

    var goBack = function(){
        location.href = "/warana/dashboard";
    };

    var getProfileData = function(){
        var data = {
            firstName : $("#firstName").val(),
            lastName : $("#lastName").val(),
            email : $("#email").val(),
            password : "",
            rePassword : ""
        };

        return data;
    };

    var setEditedProfileDataToOrigin = function(){
        userOriginalProfile.firstName = $("#firstName").val();
        userOriginalProfile.lastName = $("#lastName").val();
        userOriginalProfile.email = $("#email").val();
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

    var changePassword = function(){
        location.href = "/warana/myprofile/changepassword";
    };

    return {
        init: function () {
            initProfile();
            $(document).on("click", "#editProfile", editProfile);
            $(document).on("click", "#saveProfile", saveEditedProfile);
            $(document).on("click", "#resetProfile", resetProfile);
            $(document).on("click", "#backBtn", goBackClick);
            $(document).on("click", "#changePassword", changePassword);
        }
    };
}();

$(function () {
    WARANA.module.myProfile.init();
});