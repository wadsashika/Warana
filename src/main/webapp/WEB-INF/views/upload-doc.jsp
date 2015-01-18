<%@include file="header.jsp" %>

<div id="wrapper">
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="dash-board-title">Upload Resume</h1>
                <hr style="margin-bottom:40px">
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        Upload Your Resumes
                    </div>
                    <div class="panel-body">
                        <div class="col-xs-12">
                            <div class="col-lg-3"></div>
                            <div class="file-drop-area col-xs-12 col-lg-6" id="add-files">
                                <button type="button" class="btn btn-default col-xs-12">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                    Click or drop file here
                                </button>
                            </div>
                            <div class="col-lg-3"></div>
                        </div>
                        <div class="float-left table table-striped" class="files" id="previews">
                            <div id="template">
                                <!-- This is used as the file preview template -->
                                <table id="upload-info-table" class="table table-condensed" data-show-header="false">
                                    <thead>
                                    <tr>
                                        <th style="width: 20%"></th>
                                        <th style="width: 25%"></th>
                                        <th style="width: 10%"></th>
                                        <th style="width: 30%"></th>
                                        <th style="width: 10%"></th>
                                    </tr>
                                    </thead>
                                    <tr>
                                        <td>
                                            <div>
                                                <span class="preview"><img data-dz-thumbnail/></span>
                                            </div>
                                        </td>
                                        <td>
                                            <div>
                                                <p class="name" data-dz-name></p>
                                                <strong class="error text-danger" data-dz-errormessage></strong>
                                            </div>
                                        </td>
                                        <td>
                                            <div>
                                                <p class="size" data-dz-size></p>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="progress progress-striped active" role="progressbar"
                                                 aria-valuemin="0"
                                                 aria-valuemax="100" aria-valuenow="0">
                                                <div class="progress-bar progress-bar-success"
                                                     data-dz-uploadprogress>
                                                </div>
                                            </div>
                                        </td>
                                        <td>
                                            <div>
                                                <button data-dz-remove class="btn btn-danger delete">
                                                    <i class="glyphicon glyphicon-trash"></i>
                                                    <span>Remove</span>
                                                </button>
                                            </div>
                                        </td>

                                    </tr>
                                </table>
                            </div>

                        </div>

                        <div id="notification-div"></div>
                    </div>
                    <div class="panel-footer">
                        <div class="action-btn-row">
                            <button type="button" id="upload-all" class="btn btn-primary action-btn">
                                <span class="glyphicon glyphicon-upload" aria-hidden="true"></span> Upload
                            </button>
                            <button type="button" id="remove-all" class="btn btn-primary action-btn">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Cancel All
                            </button>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<link href='<c:url value="/css/warana/pages-commons.css" />' rel="stylesheet" type="text/css"/>
<link href='<c:url value="/css/dropzone.css" />' rel="stylesheet" type="text/css"/>
<link href='<c:url value="/css/warana/upload.css" />' rel="stylesheet" type="text/css"/>

<script src='<c:url value="/js/dropzone.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/warana/upload.js" />' type="text/javascript"></script>

<%@include file="footer.jsp" %>