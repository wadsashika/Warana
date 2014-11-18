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


    return {
        init: function () {
            startSlider();
        }
    };

}();

$(function () {
    WARANA.module.home.init();
});


