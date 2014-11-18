<%@include file="header.jsp" %>

<link href='<c:url value="/css/bootstrap-modal.css" />' rel="stylesheet" type="text/css"/>
<link href='<c:url value="/css/bootstrap-modal-bs3patch.css" />' rel="stylesheet" type="text/css"/>

<script src='<c:url value="/js/bootstrap-modal.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/bootstrap-modalmanager.js" />' type="text/javascript"></script>

<div class="body-container col-sm-12 col-md-12">
    <c:if test="${not empty error}">
        <script type="text/javascript">
            $(document).ready(function () {
                $("#loginBtn").trigger("click");
            });
        </script>
    </c:if>
    <div id="login-popup" class="modal  fade" tabindex="-1" data-width="350">
        <c:if test="${not empty error}">
            <div class="login-error">
                <div class="ln">Username and password do not match</div>
            </div>
        </c:if>
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
            <h3>User Login</h3>
        </div>
        <c:url value="/j_spring_security_check" var="loginUrl"/>
        <form class="login-form" role="form" action="${loginUrl}" method='POST'>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="form-group">
                            <input type="email" class="form-control" placeholder="Email" name="j_username"
                                   id="inputEmail3">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="form-group">
                            <input type="password" class="form-control" placeholder="Password" name="j_password"
                                   id="inputPassword3">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary col-sm-12">Login</button>
            </div>
        </form>
    </div>
</div>

<%@include file="slider.jsp" %>

<div class="hero">
    <div class="container">
        <div class="hero-head">
            <h1><strong>Warana</strong></h1>

            <p>We provide the best solution for the problems arise in the recruitment process of any enterprises</p>
        </div>
    </div>
</div>

<div class="feature">
    <div class="container">
        <div class="head">
            <h2><strong>Feature</strong></h2>
        </div>

        <div class="row">
            <div class="col-md-3 col-sm-6">
                <div class="feature-item">
                    <span class="glyphicon glyphicon-ok green"></span>

                    <h3><strong>Feature 1</strong></h3>

                    <p>This is a test</p>
                </div>
            </div>
            <div class="col-md-3 col-sm-6">
                <div class="feature-item">
                    <span class="glyphicon glyphicon-user red"></span>

                    <h3><strong>Feature 2</strong></h3>

                    <p>This is a test</p>
                </div>
            </div>
            <div class="col-md-3 col-sm-6">
                <div class="feature-item">
                    <span class="glyphicon glyphicon-eye-open blue"></span>

                    <h3><strong>Feature 3</strong></h3>

                    <p>This is a test</p>
                </div>
            </div>
            <div class="col-md-3 col-sm-6">
                <div class="feature-item">
                    <span class="glyphicon glyphicon-plane orange"></span>

                    <h3><strong>Feature 4</strong></h3>

                    <p>This is a test</p>
                </div>
            </div>
            <span id="signup-form"/>
        </div>
    </div>
</div>

<div class="signup">
    <div class="container">
        <div class="head">
            <h2><strong>Sign Up</strong></h2>
        </div>

        <div class="col-sm-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div id="signup-form-body" class="form-horizontal">
                        <div class="form-group">
                            <div class="col-sm-12 col-md-3">
                                <label for="firstName" class="text-left">First Name</label>
                            </div>
                            <div class="col-sm-8 col-md-5">
                                <input type="text" class="form-control" name="firstName" id="firstName"
                                       data-valid="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12 col-md-3">
                                <label for="lastName" class="text-left">Last Name</label>
                            </div>
                            <div class="col-sm-8 col-md-5">
                                <input type="text" class="form-control" name="firstName" id="lastName"
                                       data-valid="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12 col-md-3">
                                <label for="email" class="text-left">E-mail</label>
                            </div>
                            <div class="col-sm-8 col-md-5">
                                <input type="email" class="form-control" name="firstName" id="email"
                                       data-valid="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12 col-md-3">
                                <label for="password" class="text-left">Password</label>
                            </div>
                            <div class="col-sm-8 col-md-5">
                                <input type="password" class="form-control" name="firstName" id="password"
                                       data-valid="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12 col-md-3">
                                <label for="reEnterPassword" class="text-left">Re-Enter Password</label>
                            </div>
                            <div class="col-sm-8 col-md-5">
                                <input type="password" class="form-control" name="firstName" id="reEnterPassword"
                                       data-valid="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12 col-md-3">
                            </div>
                            <div class="col-sm-8 col-md-5">
                                <button type="reset" class="btn btn-primary"><span class="glyphicon glyphicon-refresh"/>
                                    Reset
                                </button>
                                <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-save"/>
                                    Save
                                </button>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>
