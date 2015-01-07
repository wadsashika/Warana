WARANA.namespace("module.home");

WARANA.module.home = function () {

    var startSlider = function () {
        jQuery('#camera_wrap').camera({
            height: '25%',
            loader: 'bar',
            pagination: false,
            thumbnails: false,
            hover: false,
            opacityOnGrid: false,
            imagePath: 'images/'
        });
    };

    var scrollAnimated = function (event) {
        event.preventDefault();
        $('html, body').animate({
            scrollTop: $("#signup-form").offset().top
        }, 1000);
    };

    var getFormData = function () {
        return $("#signup-form-body").formSerilize();
    }

    var signupUser = function () {

        var signup = function () {
            var data = getFormData();

            var ajaxInitData = {
                url: "signup",
                data: $.toJSON(data),
                contentType: "application/json"
            };

            var successFn = function (result) {
                if (result.success) {
                    WARANA.message(WARANA.messageType.SUCCESS, result.message);
                    loginSuccessFn();
                } else {
                    WARANA.message(WARANA.messageType.ERROR, result.message);
                }
            };

            WARANA.common.ajaxCall(ajaxInitData, successFn);
        };

        $("#signup-form-body").validation({
            validated: signup
        });
    }

    var loginSuccessFn = function () {
        var email = $("#email").val();
        clearFields();
        $("#loginBtn").trigger("click");
        $("#inputEmail3").val(email);
        $("#inputPassword3").val("");
    };

    var clearFields = function () {
        $("#firstName").val("");
        $("#lastName").val("");
        $("#email").val("");
        $("#password").val("");
        $("#reEnterPassword").val("");
    };

    return {
        init: function () {
            startSlider();
            $(document).on("click", "#signupBtn", scrollAnimated);
            $(document).on("click", "#signupBtnSm", scrollAnimated);
            $(document).on("click", "#saveBtn", signupUser);
            $(document).on("click", "#resetBtn", clearFields);
        }
    };

}();

$(function () {
    WARANA.module.home.init();
});


