<%@include file="header.jsp" %>
<link href='<c:url value="/css/metisMenu.min.css" />' rel="stylesheet" type="text/css"/>
<link href='<c:url value="/css/dataTables.bootstrap.css" />' rel="stylesheet" type="text/css"/>
<link href='<c:url value="/css/warana/pages-commons.css" />' rel="stylesheet" type="text/css"/>
<link href='<c:url value="/css/font-awesome.min.css" />' rel="stylesheet" type="text/css"/>
<link href='<c:url value="/css/bootstrap-tagsinput.css" />' rel="stylesheet" type="text/css"/>
<link href='<c:url value="/css/warana/modal-style.css" />' rel="stylesheet" type="text/css"/>
<link href='<c:url value="/css/warana/view-stat.css" />' rel="stylesheet" type="text/css"/>

<script src='<c:url value="/js/metisMenu.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery.dataTables.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/dataTables.bootstrap.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/sb-admin-2.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/warana/view-stat.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/highcharts.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/highcharts-3d.js" />' type="text/javascript"></script>


<script src='<c:url value="/js/bootstrap-tagsinput.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/typeahead-new.js" />' type="text/javascript"></script>

<div id="wrapper">
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="dash-board-title">View Statistics</h1>
                <hr class="pages-title-hr">
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        User Statistics
                    </div>
                    <div class="panel-body">
                        <div class="panel">
                            <div class="panel-body">
                                <div id="advanced-search-panel" class="viewstat-advanced-search">
                                    <div id="headingOne">
                                        <h4>
                                            <a id="advHref" data-toggle="collapse" data-parent="#accordion"
                                               href="#advancedSearch"
                                               aria-expanded="true" aria-controls="collapseOne">
                                                <span class='glyphicon glyphicon-chevron-down arrowUpDown currentDown'></span>
                                                Advanced Search
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="advancedSearch" class="panel-collapse collapse"
                                         aria-labelledby="headingOne">
                                        <div>
                                            <label class="col-xs-1">Skills </label>
                                            <input id="tagged-search-field" type="text" data-provide="typeahead"/>
                                            <button type="submit" class="btn btn-primary" id="search-submit">Submit
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div id="select-all-row">
                            <h4><input type="checkbox" id="select-all" class="pages-select-all"/>Select All</h4>
                        </div>
                        <div class="table-responsive" id="stat-table-div"></div>

                        <div id="compareAllMainDiv">
                            <div id="compareAllCollapse">
                                <div id="compareAllArea" class="panel-collapse " aria-labelledby="compareAllCollapse">
                                    <div id="compareAllChartArea">
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="panel-footer">
                        <div id="operation-buttons-row" class="action-btn-row">
                            <button type="button" class="btn btn-primary action-btn" id="back-btn">
                                <span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> Back
                            </button>
                        </div>
                    </div>
                </div>
                <!-- /.col-lg-4 -->
            </div>
        </div>

    </div>
</div>
<%@include file="view-profile-modal.jsp" %>
<%@include file="technology-graph.jsp" %>
<%@include file="footer.jsp" %>