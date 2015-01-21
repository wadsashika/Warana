<%@include file="header.jsp" %>

<div class="body-container col-sm-12 col-md-12">
    <c:if test="${not empty error}">
        <script type="text/javascript">
            $(document).ready(function () {
                $("#loginBtn").trigger("click");
            });
        </script>
    </c:if>
    <div id="login-popup" class="modal  fade" tabindex="-1" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
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
                            <div class="col-xs-12">
                                <div class="form-group">
                                    <input type="email" class="mand form-control" placeholder="Email" name="j_username"
                                           id="inputEmail3">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="form-group">
                                    <input type="password" class="form-control" placeholder="Password" name="j_password"
                                           id="inputPassword3">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary col-xs-12">Login</button>
                    </div>
                </form>
            </div>
        </div>
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
                    <span class="glyphicon glyphicon-book green"></span>

                    <h3><strong>IR from Resumes</strong></h3>

                    <p>We analyze candidates' resume and extract important information</p>
                </div>
            </div>
            <div class="col-md-3 col-sm-6">
                <div class="feature-item">
                    <span class="glyphicon glyphicon-globe red"></span>

                    <h3><strong>IR from Online</strong></h3>

                    <p>We extract important information of candidates' from the web</p>
                </div>
            </div>
            <div class="col-md-3 col-sm-6">
                <div class="feature-item">
                    <span class="glyphicon glyphicon-user blue"></span>

                    <h3><strong>Aggregate Profiles</strong></h3>

                    <p>Create aggregated profiles including both information extracted from the resume and web</p>
                </div>
            </div>
            <div class="col-md-3 col-sm-6">
                <div class="feature-item">
                    <span class="glyphicon glyphicon-th-list orange"></span>

                    <h3><strong>Detailed Analytics</strong></h3>

                    <p>Produce detailed analytics of the candidates comparing with company knowledge base</p>
                </div>
            </div>
        </div>
    </div>
</div>

<sec:authorize ifNotGranted="ROLE_USER">
    <div class="signup" id="signup-form">
        <div class="container">
            <div class="head">
                <h2><strong>Sign Up</strong></h2>
            </div>

            <div class="col-sm-12">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div id="signup-form-body" class="form-horizontal">
                            <div class="form-group">
                                <div class="col-sm-12 col-md-2"></div>
                                <div class="col-sm-12 col-md-3">
                                    <label for="firstName" class="mand not-bold">First Name</label>
                                </div>
                                <div class="col-sm-8 col-md-5">
                                    <input type="text" class="form-control" name="firstName" id="firstName"
                                           data-valid="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12 col-md-2"></div>
                                <div class="col-sm-12 col-md-3">
                                    <label for="lastName" class="mand not-bold">Last Name</label>
                                </div>
                                <div class="col-sm-8 col-md-5">
                                    <input type="text" class="form-control" name="lastName" id="lastName"
                                           data-valid="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12 col-md-2"></div>
                                <div class="col-sm-12 col-md-3">
                                    <label for="email" class="mand not-bold">E-mail</label>
                                </div>
                                <div class="col-sm-8 col-md-5">
                                    <input type="email" class="form-control" name="email" id="email"
                                           data-valid="required email">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12 col-md-2"></div>
                                <div class="col-sm-12 col-md-3">
                                    <label for="password" class="mand not-bold">Password</label>
                                </div>
                                <div class="col-sm-8 col-md-5">
                                    <input type="password" class="form-control" name="password" id="password"
                                           data-valid="required minlength" data-min=8>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12 col-md-2"></div>
                                <div class="col-sm-12 col-md-3">
                                    <label for="reEnterPassword" class="mand not-bold">Re-Enter Password</label>
                                </div>
                                <div class="col-sm-8 col-md-5">
                                    <input type="password" class="form-control" name="rePassword" id="reEnterPassword"
                                           data-valid="required equalTo" data-ele="password">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12 col-md-2"></div>
                                <div class="col-sm-12 col-md-3">
                                </div>
                                <div class="col-sm-8 col-md-5">
                                    <button type="reset" class="btn btn-primary" id="resetBtn"><span
                                            class="glyphicon glyphicon-refresh"></span>
                                        Reset
                                    </button>
                                    <button type="submit" class="btn btn-primary" id="saveBtn"><span
                                            class="glyphicon glyphicon-floppy-disk"></span>
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
</sec:authorize>

<script src='<c:url value="/js/warana/home.js" />' type="text/javascript"></script>
<%@include file="footer.jsp" %>
