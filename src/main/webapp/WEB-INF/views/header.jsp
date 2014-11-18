<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>Welcome to Warana</title>

    <%--common css files--%>
    <link href='<c:url value="/css/bootstrap.css" />' rel="stylesheet" type="text/css"/>
    <link href='<c:url value="/css/bootstrap-theme.css" />' rel="stylesheet" type="text/css"/>
    <link href='<c:url value="/css/bootstrap-dialog.css" />' rel="stylesheet" type="text/css"/>
    <link href='<c:url value="/css/jquery.pnotify.default.css" />' rel="stylesheet" type="text/css"/>
    <link href='<c:url value="/css/warana/style.css" />' rel="stylesheet" type="text/css"/>
    <link href='<c:url value="/css/camera.css" />' rel="stylesheet" type="text/css"/>

    <%--common js files--%>
    <script src='<c:url value="/js/jquery-1.10.2.js" />' type="text/javascript"></script>
    <script src='<c:url value="/js/jquery-migrate-1.2.1.js" />' type="text/javascript"></script>
    <script src='<c:url value="/js/jquery.json.min.js" />' type="text/javascript"></script>
    <script src='<c:url value="/js/jquery.pnotify.js" />' type="text/javascript"></script>
    <script src='<c:url value="/js/bootstrap.js" />' type="text/javascript"></script>
    <script src='<c:url value="/js/bootstrap-dialog.js" />' type="text/javascript"></script>
    <script src='<c:url value="/js/underscore.js" />' type="text/javascript"></script>
    <script src='<c:url value="/js/moment.min.js" />' type="text/javascript"></script>
    <script src='<c:url value="/js/warana.common.js" />' type="text/javascript"></script>
    <script src='<c:url value="/js/warana.validation.js" />' type="text/javascript"></script>
    <script src='<c:url value="/js/base.js" />' type="text/javascript"></script>
    <script src='<c:url value="/js/camera.js" />' type="text/javascript"></script>
    <script src='<c:url value="/js/jquery.easing.1.3.js" />' type="text/javascript"></script>

</head>
<body>

<header>
    <nav class="navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="logo-holder">
                <a href='<c:url value="/home" />'><img src='<c:url value="/images/logo-icon.png" />'></a>
            </div>
            <sec:authorize ifNotGranted="ROLE_USER">
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <button id="loginBtn" data-toggle="modal" href="#login-popup" class="top-buttons">Login
                            </button>
                        </li>
                        <li>
                            <button href="#signup-form" class="top-buttons last-button">Sign Up</button>
                        </li>
                    </ul>
                </div>
            </sec:authorize>
            <sec:authorize ifAnyGranted="ROLE_USER">
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <button  data-toggle="dropdown" class="top-buttons dropdown-toggle">Dulanga Sashika
                            </button>
                            <ul class="dropdown-menu home-links" role="menu">
                                <li><a href="<c:url value="/dashboard"/>"><span class="glyphicon glyphicon-dashboard"/> Dashboard</a></li>
                                <li><a href="<c:url value="/#"/>"><span class="glyphicon glyphicon-eye-open"/> View Statics</a></li>
                                <li><a href="<c:url value="/j_spring_security_logout" />"><span class="glyphicon glyphicon-log-out"/> Logout</a></li>
                            </ul>
                        </li>

                    </ul>
                </div>
            </sec:authorize>
        </div>

    </nav>
</header>
