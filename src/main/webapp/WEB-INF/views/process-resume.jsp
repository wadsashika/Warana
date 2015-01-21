<%@include file="header.jsp" %>

<div id="wrapper">
    <c:if test="${not empty files}">
        <script type="text/javascript">
            $(document).ready(function () {
                $('#process-resume').prop('disabled', false);
            });
        </script>
    </c:if>

    <c:if test="${empty files}">
        <script type="text/javascript">
            $(document).ready(function () {
                $('#process-resume').prop('disabled', true);
            });
        </script>
    </c:if>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="dash-board-title">Process Resume</h1>
                <hr class="pages-title-hr">
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        Files List
                    </div>
                    <div class="panel-body">
                        <div id="select-all-row">
                            <h4><input type="checkbox" id="select-all" class="pages-select-all"/>Select All</h4>
                        </div>
                        <div class="table-responsive">
                            <table class="table table-striped table-hover" id="unprocessed-resume-list">
                                <thead>
                                <tr>
                                    <th>Select</th>
                                    <th>File Name</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${files}" var="file">
                                    <tr>
                                        <td><input type="checkbox" class="files-checkbox"></td>
                                        <td>${file}</td>
                                        <td>
                                            <button type="button" class="btn btn-danger delete-resume">
                                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                                Delete
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="panel-footer">
                        <div id="operation-buttons-row" class="action-btn-row">
                            <button type="button" id="backBtn" class="btn btn-primary action-btn">
                                <span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> Back
                            </button>
                            <button type="button" class="btn btn-primary action-btn" id="process-resume">
                                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span> Process
                            </button>
                        </div>
                    </div>
                </div>
                <!-- /.col-lg-4 -->
            </div>
        </div>
    </div>
</div>

<link href='<c:url value="/css/metisMenu.min.css" />' rel="stylesheet" type="text/css"/>
<link href='<c:url value="/css/dataTables.bootstrap.css" />' rel="stylesheet" type="text/css"/>
<link href='<c:url value="/css/warana/pages-commons.css" />' rel="stylesheet" type="text/css"/>
<link href='<c:url value="/css/font-awesome.min.css" />' rel="stylesheet" type="text/css"/>

<script src='<c:url value="/js/metisMenu.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery.dataTables.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/dataTables.bootstrap.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/sb-admin-2.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/warana/process-resume.js" />' type="text/javascript"></script>

<%@include file="footer.jsp" %>