<%@include file="header.jsp" %>

<link href='<c:url value="/css/bootstrap.min.css" />' rel="stylesheet" type="text/css"/>
<link href='<c:url value="/css/warana/pages-commons.css" />' rel="stylesheet" type="text/css"/>
<link href='<c:url value="/css/dataTables.bootstrap.css" />' rel="stylesheet" type="text/css"/>

<script src='<c:url value="/js/warana/process-resume.js" />' type="text/javascript"></script>


<div id = "wrapper">
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="dash-board-title">Process Resume</h1><hr style="margin-bottom:40px">
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12" >
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        Files List
                    </div>
                    <div class="panel-body">
                        <div id = "select-all-row">
                            <h4 style="margin-left: 10px"><input type="checkbox" id = "select-all" style="margin-right: 10px"/>Select All</h4>
                        </div>
                        <div class="table-responsive">
                            <table class="table table-hover">
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
                                                <button type="button" class="btn btn-danger">
                                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>

                        <div id = "operation-buttons-row" class="pull-right">
                            <button type="button" id="clear-selection" class="btn btn-warning" style="margin-right: 10px;">
                                <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> Clear
                            </button>
                            <button type="button" class="btn btn-success">
                                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span> Process
                            </button>
                        </div>
                    </div>
                    <div class="panel-footer">
                        Panel Footer
                    </div>
                </div>
                <!-- /.col-lg-4 -->
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>