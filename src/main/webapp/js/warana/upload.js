WARANA.namespace("module.uploadCv");

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

            init: function(){
                this.on("queuecomplete", function (file) {
                    alert("All files have uploaded ");
                    document.getElementById("previews").innerHTML = "";
                });
            }
        });
    };

    var removeAllFiles = function () {
        myDropzone.removeAllFiles();
    };

    var uploadAllFiles = function () {
        myDropzone.processQueue();
    };

    return {
        init: function () {
            initialize();
            $(document).on("click", "#remove-all", removeAllFiles);
            $(document).on("click", "#upload-all", uploadAllFiles);
        }
    };

}();

$(function () {
    WARANA.module.uploadCv.init();
});


