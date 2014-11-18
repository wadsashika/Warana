<%@include file="header.jsp" %>
<link href='<c:url value="/css/metisMenu.min.css" />' rel="stylesheet" type="text/css"/>
<link href='<c:url value="/css/dataTables.bootstrap.css" />' rel="stylesheet" type="text/css"/>
<link href='<c:url value="/css/warana/pages-commons.css" />' rel="stylesheet" type="text/css"/>
<link href='<c:url value="/css/font-awesome.min.css" />' rel="stylesheet" type="text/css"/>
<link href='<c:url value="/css/warana/analyze.css" />' rel="stylesheet" type="text/css"/>

<script src='<c:url value="/js/metisMenu.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery.dataTables.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/dataTables.bootstrap.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/sb-admin-2.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/warana/analyze.js" />' type="text/javascript"></script>


<div id = "wrapper">
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="dash-board-title">Analytics</h1><hr style="margin-bottom:40px">
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12" >
                <div class="panel panel-green">
                    <div class="panel-heading">
                        User Scores List
                    </div>
                    <div class="panel-body">
                        <div id = "select-all-row">
                            <h4 style="margin-left: 10px"><input type="checkbox" id = "select-all" style="margin-right: 10px"/>Select All</h4>
                        </div>
                        <div class="table-responsive">
                            <table class="table table-striped table-hover" id="analyzed-results-table">
                                <thead>
                                <tr>
                                    <th>Select</th>
                                    <th><div style="margin-left: 75px">User Name</div></th>
                                    <th><div style="margin-left: 75px">Score</div></th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${results}" var="result">
                                        <tr>
                                            <td><input type="checkbox" class="files-checkbox"></td>
                                            <td><div style="margin-left: 75px"><c:out value="${result.name}"></c:out></div></td>
                                            <td><div style="margin-left: 75px"><c:out value="${result.score}"></div></c:out></td>
                                            <td>
                                                <button type="button" class="btn btn-primary btn-md view-prof btn-center">
                                                    <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> View
                                                </button>
                                            </td>
                                            <td>
                                                <button type="button" class="btn btn-info btn-md send-email btn-center">
                                                    <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span> Email
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="panel-footer">
                        <div class="process-btn-div">
                            <button type="button" id="clear-selection" class="btn btn-warning btn-center" style="margin-right: 10px;">
                                <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> Clear
                            </button>
                        </div>
                    </div>
                </div>
                <!-- /.col-lg-4 -->
            </div>
        </div>

        <%--Define the view profile modal--%>
        <div class="modal fade" id="profile-info" tabindex="-1" role="dialog" aria-labelledby="profile-info-label" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="myModalLabel">User Name</h4>
                    </div>
                    <div class="modal-body">
                        <div id="basic-info" class="row"></div>
                        <div id = "nav-tabs">
                            <ul class="nav nav-tabs">
                                <li role="presentation" class="active">
                                    <a href="#" aria-controls="profile" role="tab" data-toggle="tab">Education</a>
                                </li>
                                <li role="presentation">
                                    <a href="#" aria-controls="profile" role="tab" data-toggle="tab">Projects</a>
                                    <div>
                                        <h4>Projects Info</h4>
                                    </div>
                                </li>
                                <li role="presentation">
                                    <a href="#" aria-controls="profile" role="tab" data-toggle="tab">Experience</a>
                                    <div>
                                        <h4>Experience Info</h4>
                                    </div>
                                </li>
                                <li role="presentation">
                                    <a href="#" aria-controls="profile" role="tab" data-toggle="tab">Achievements</a>
                                    <div>
                                        <h4>Achievements Info</h4>
                                    </div>
                                </li>
                                <li role="presentation">
                                    <a href="#" aria-controls="profile" role="tab" data-toggle="tab">Publications</a>
                                    <div>
                                        <h4>Publications Info</h4>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<%@include file="footer.jsp" %>