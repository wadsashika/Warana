<%@include file="header.jsp" %>

<link href='<c:url value="/css/warana/pages-commons.css" />' rel="stylesheet" type="text/css"/>
<div id="wrapper">
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="dash-board-title">Dashboard</h1>
                <hr class="pages-title-hr">
            </div>
        </div>

        <div class="row">
            <div class="col-md-12 col-lg-6" id="tiles">
                <div class="col-lg-6 col-md-6">
                    <a href="adminpanel/upload">
                        <div class="tile panel-darkPurple">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <i class="glyphicon glyphicon-cloud-upload dashboard-tile-icon"></i>
                                    </div>
                                    <div class="col-xs-12">
                                        <p class="tile-text tile-text-align">Document Upload</p>
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
                                    <div class="col-xs-12">
                                        <i class="glyphicon glyphicon-cog dashboard-tile-icon"></i>
                                    </div>
                                    <div class="col-xs-12">
                                        <p class="tile-text tile-text-align">Process</p>
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
                                    <div class="col-xs-12">
                                        <i class="glyphicon glyphicon-list dashboard-tile-icon"></i>
                                    </div>
                                    <div class="col-xs-12">
                                        <p class="tile-text tile-text-align">Analyze 123</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>