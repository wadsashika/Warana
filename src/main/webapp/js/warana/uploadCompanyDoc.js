WARANA.namespace("module.uploadCompanyDoc");

WARANA.module.uploadCv = function () {
    var previewNode = null;
    var previewTemplate = null;
    var myDropzone = null;

    var initialize = function () {
        previewNode = document.querySelector("#template");
        previewNode.id = "";
        previewTemplate = previewNode.parentNode.innerHTML;
        previewNode.parentNode.removeChild(previewNode);

        myDropzone = new Dropzone(document.body, { // Make the whole body a dropzone
            url: "fileupload", // Set the url
            thumbnailWidth: 80,
            thumbnailHeight: 80,
            parallelUploads: 20,
            previewTemplate: previewTemplate,
            previewsContainer: "#previews", // Define the container to display the previews
            autoProcessQueue: false,
            clickable: "#add-files",
            success: function (file, response) {
                var status = response.status;
                var errorString = "";
                var notifications_div = document.getElementById("notification-div");


                if (status == "false") {
                    var fileNames = response.files;
                    for (var a = 0; a < fileNames.length; a++) {
                        errorString = errorString.concat(fileNames[a] + ",");
                    }

                    notifications_div.className = "alert alert-danger";
                    notifications_div.innerHTML = "Error Occurred. Files " + errorString + " Could Not Save to Server";
                }
                else if (status == "true") {
                    notifications_div.className = "alert alert-success";
                    notifications_div.innerHTML = "All Files stored in the server successfully";
                    $("#process-all").show();
                }
                document.getElementById("previews").innerHTML = "";
                notifications_div.style.display = "block";
            }
        });
    };

    var clearMessage = function () {
        document.getElementById("notification-div").style.display = "none";
    };

    var removeAllFilesConfirmation = function () {
        var title = "Cancel All Uploads";
        var msg = "Are you sure want to cancel all files?";

        WARANA.messageConfirmation(removeAllFiles, title, msg);
    };
    var processDoc = function () {
        if (jQuery('body').find('#resultLoading').attr('id') != 'resultLoading') {
            jQuery('body').append('<div id="resultLoading" style="display:none"><div><img src="/warana/images/ajax-loader.gif"></div><div class="bg"></div></div>');
        }

        jQuery('#resultLoading').css({
            'width': '100%',
            'height': '100%',
            'position': 'fixed',
            'z-index': '10000000',
            'top': '0',
            'left': '0',
            'right': '0',
            'bottom': '0',
            'margin': 'auto'
        });

        jQuery('#resultLoading .bg').css({
            'background': '#000000',
            'opacity': '0.7',
            'width': '100%',
            'height': '100%',
            'position': 'absolute',
            'top': '0'
        });

        jQuery('#resultLoading>div:first').css({
            'width': '250px',
            'height': '75px',
            'text-align': 'center',
            'position': 'fixed',
            'top': '0',
            'left': '0',
            'right': '0',
            'bottom': '0',
            'margin': 'auto',
            'font-size': '16px',
            'z-index': '10',
            'color': '#ffffff'

        });

        jQuery('#resultLoading .bg').height('100%');
        jQuery('#resultLoading').fadeIn(300);
        jQuery('body').css('cursor', 'wait');
        var ajaxInitData = {
            url: "processDocuments",
            contentType: "application/json"
        };

        var successFn = function (result) {
            jQuery('#resultLoading .bg').height('100%');
            jQuery('#resultLoading').fadeOut(300);
            jQuery('body').css('cursor', 'default');
            console.log("sdsdsddsd")
        };

        WARANA.common.ajaxCall(ajaxInitData, successFn);
    }

    var removeAllFiles = function () {
        myDropzone.removeAllFiles();
    };

    var uploadAllFiles = function () {
        $("#process-all").hide();
        myDropzone.processQueue();
    };

    var backBtnClick = function () {
        WARANA.messageConfirmation(backBtnSuccessFn, "Leave Page Confirmation", "Are you sure want to leave this page?");
    };

    var backBtnSuccessFn = function () {
        location.href = "/warana/dashboard";
    };

    return {
        init: function () {
            initialize();
            $("#process-all").hide();
            $(document).on("click", "#remove-all", removeAllFilesConfirmation);
            $(document).on("click", "#upload-all", uploadAllFiles);
            $(document).on("click", "#add-doc-btn", clearMessage);
            $(document).on("click", "#backBtn", backBtnClick);
            $(document).on("click", "#process-all", processDoc);
        }
    };

}();

$(function () {
    WARANA.module.uploadCv.init();
});


