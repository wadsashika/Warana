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


<div id="wrapper">
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="dash-board-title">Analytics</h1>
                <hr style="margin-bottom:40px">
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-green">
                    <div class="panel-heading">
                        User Scores List
                    </div>
                    <div class="panel-body">
                        <div id="select-all-row">
                            <h4 style="margin-left: 10px"><input type="checkbox" id="select-all"
                                                                 style="margin-right: 10px"/>Select All</h4>
                        </div>
                        <div class="table-responsive">
                            <table class="table table-striped table-hover" id="analyzed-results-table">
                                <thead>
                                <tr>
                                    <th>Select</th>
                                    <th>
                                        <div style="margin-left: 75px">User Name</div>
                                    </th>
                                    <th>
                                        <div style="margin-left: 75px">Score</div>
                                    </th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${results}" var="result">
                                    <tr>
                                        <td><input type="checkbox" class="files-checkbox"></td>
                                        <td>
                                            <div style="margin-left: 75px"><c:out value="${result.name}"></c:out></div>
                                        </td>
                                        <td>
                                            <div style="margin-left: 75px"><c:out value="${result.score}"></c:out></div>

                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-primary btn-md view-prof btn-center">
                                                <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                                                View
                                            </button>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-info btn-md send-email btn-center">
                                                <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
                                                Email
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
                            <button type="button" id="clear-selection" class="btn btn-warning btn-center"
                                    style="margin-right: 10px;">
                                <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> Clear
                            </button>
                        </div>
                    </div>
                </div>
                <!-- /.col-lg-4 -->
            </div>
        </div>

        <%--Define the view profile modal--%>
        <%--<div class="modal fade" id="profile-info" tabindex="-1" role="dialog" aria-labelledby="profile-info-label"--%>
             <%--aria-hidden="true">--%>
            <%--<div class="modal-dialog">--%>
                <%--<div class="modal-content">--%>
                    <%--<div class="modal-header">--%>
                        <%--<h4 class="modal-title" id="myModalLabel">User Name</h4>--%>
                    <%--</div>--%>
                    <%--<div class="modal-body">--%>
                        <%--<div id="basic-info" class="row"></div>--%>

                        <%--<ul class="nav nav-tabs">--%>
                            <%--<li class="active">--%>
                                <%--<a href="#education" data-toggle="tab">Education</a>--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<a href="#achievements" data-toggle="tab">Achievements</a>--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<a href="#work-exp" data-toggle="tab">Work</a>--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<a href="#projects" data-toggle="tab">Project</a>--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<a href="#publications" data-toggle="tab">Publications</a>--%>
                            <%--</li>--%>
                        <%--</ul>--%>

                        <%--<div class="tab-content">--%>
                            <%--<div class="tab-pane fade in active" id="education">--%>
                                <%--<h4>Education Tab</h4>--%>
                                <%--<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>--%>
                            <%--</div>--%>
                            <%--<div class="tab-pane fade" id="achievements">--%>
                                <%--<h4>Achievements Tab</h4>--%>
                                <%--<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>--%>
                            <%--</div>--%>
                            <%--<div class="tab-pane fade" id="work-exp">--%>
                                <%--<h4>Work Experience Tab</h4>--%>
                                <%--<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>--%>
                            <%--</div>--%>
                            <%--<div class="tab-pane fade" id="projects">--%>
                                <%--<h4>Projects Tab</h4>--%>
                                <%--<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>--%>
                            <%--</div>--%>
                            <%--<div class="tab-pane fade" id="publications">--%>
                                <%--<h4>Publications Tab</h4>--%>
                                <%--<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="modal-footer">--%>
                        <%--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>--%>
                        <%--<button type="button" class="btn btn-primary">Save changes</button>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>

        <%@include file="view-profile-modal.jsp" %>

    </div>
</div>

<%@include file="footer.jsp" %>