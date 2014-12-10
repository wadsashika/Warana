<%@include file="header.jsp" %>


<link href='<c:url value="/css/warana/pages-commons.css" />' rel="stylesheet" type="text/css"/>
<link href='<c:url value="/css/warana/dashboard.css" />' rel="stylesheet" type="text/css"/>

<script src='<c:url value="/js/morris-data.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/morris.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/raphael.min.js" />' type="text/javascript"></script>

<div id="wrapper">
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="dash-board-title">Dashboard</h1>
                <hr style="margin-bottom:40px">
            </div>
        </div>

        <div class="row">
            <div class="col-lg-6" id="tiles">
                <div class="col-lg-6 col-md-6">
                    <a href="upload">
                        <div class="tile panel-darkPurple">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-12 col-md-6">
                                        <i class="glyphicon glyphicon-cloud-upload" style="font-size:60px"></i>
                                    </div>
                                    <div class="col-xs-12 col-md-6 ">
                                        <p class="tile-text tile-text-align">Resume Upload</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>

                <div class="col-lg-6 col-md-6">
                    <a href="process">
                        <div class="tile panel-red">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-12 col-md-6">
                                        <i class="glyphicon glyphicon-cog" style="font-size:60px"></i>
                                    </div>
                                    <div class="col-xs-12 col-md-6 ">
                                        <p class="tile-text tile-text-align">Process Resumes</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>

                <div class="col-lg-6 col-md-6">
                    <a href="analyze">
                        <div class="tile panel-darkGreen">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-12 col-md-6">
                                        <i class="glyphicon glyphicon-list" style="font-size:60px"></i>
                                    </div>
                                    <div class="col-xs-12 col-md-6 ">
                                        <p class="tile-text tile-text-align">Analyze</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>

                <div class="col-lg-6 col-md-6">
                    <a href="viewstat">
                        <div class="tile panel-darkBlue">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-12 col-md-6">
                                        <i class=" glyphicon glyphicon-eye-open" style="font-size:60px"></i>
                                    </div>
                                    <div class="col-xs-12 col-md-6 ">
                                        <p class="tile-text tile-text-align">View Statistics</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>

                <div class="col-lg-6 col-md-6">
                    <a href="#">
                        <div class="tile panel-lightYellow">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-12 col-md-6">
                                        <i class="glyphicon glyphicon-home" style="font-size:60px"></i>
                                    </div>
                                    <div class="col-xs-12 col-md-6 ">
                                        <p class="tile-text tile-text-align">Admin Panel</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
            </div>

            <div class="col-lg-6" id="stats">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Area Chart Example
                        </div>
                        <div class="panel-body">
                            <div id="morris-area-chart"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
