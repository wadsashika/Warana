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

    return {
        init: function () {
            startSlider();
            $(document).on("click", "#signupBtn", scrollAnimated);
        }
    };

}();

$(function () {
    WARANA.module.home.init();
});


