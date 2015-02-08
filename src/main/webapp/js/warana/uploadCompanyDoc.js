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
        WARANA.displayLoadingModel();
        var ajaxInitData = {
            url: "processDocuments",
            contentType: "application/json"
        };

        var successFn = function (result) {
            WARANA.hideLoadingModel();
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


