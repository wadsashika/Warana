$(document).ready(function(){
    var previewNode = document.querySelector("#template");
    previewNode.id = "";
    var previewTemplate = previewNode.parentNode.innerHTML;
    previewNode.parentNode.removeChild(previewNode);

    var myDropzone = new Dropzone(document.body, { // Make the whole body a dropzone
        url: "fileupload", // Set the url
        thumbnailWidth: 80,
        thumbnailHeight: 80,
        parallelUploads: 20,
        previewTemplate: previewTemplate,
        previewsContainer: "#previews", // Define the container to display the previews
        autoProcessQueue:false,
        clickable: "#add-files"
    });

    document.querySelector("#remove-all").addEventListener("click", function() {
        myDropzone.removeAllFiles();
    });
    document.querySelector("#upload-all").addEventListener("click", function() {
        myDropzone.processQueue();
    });
});