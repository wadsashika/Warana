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
    <c:if test="${not empty resumesToProcess}">
        <script type="text/javascript">
            $(document).ready(function () {
                $('#analyze-candidate-btn').prop('disabled', false);
            });
        </script>
    </c:if>

    <c:if test="${empty resumesToProcess}">
        <script type="text/javascript">
            $(document).ready(function () {
                $('#analyze-candidate-btn').prop('disabled', true);
            });
        </script>
    </c:if>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="dash-board-title">Analyze Resume</h1>
                <hr class="pages-title-hr">
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div id="select-all-row">
                            <h4><input type="checkbox" id="select-all" class="pages-select-all"/>Select All</h4>
                        </div>
                        <div class="table-responsive">
                            <table class="table table-striped table-hover" id="resumes-to-process-table">
                                <thead>
                                <tr>
                                    <th>Select</th>
                                    <th>Id</th>
                                    <th>Name</th>
                                    <th>Email</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${resumesToProcess}" var="result">
                                    <tr>
                                        <td><input type="checkbox" class="files-checkbox"></td>
                                        <td>
                                            <c:out value="${result.id}"></c:out>
                                        </td>
                                        <td>
                                            <div><c:out value="${result.name}"></c:out></div>
                                        </td>
                                        <td>
                                            <div><c:out value="${result.email}"></c:out></div>
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
                            <button type="button" class="btn btn-primary action-btn btn-success" id="analyze-candidate-btn">
                                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span> Analyze
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>