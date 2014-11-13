<%@include file="header.jsp" %>

<link href='<c:url value="/css/bootstrap-modal.css" />' rel="stylesheet" type="text/css"/>
<link href='<c:url value="/css/bootstrap-modal-bs3patch.css" />' rel="stylesheet" type="text/css"/>

<script src='<c:url value="/js/bootstrap-modal.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/bootstrap-modalmanager.js" />' type="text/javascript"></script>

<div class="body-container col-xs-12">
    <div id="login-popup" class="modal  fade" tabindex="-1" data-width="400">
        <c:if test="${not empty error}">
            <div class="msg-box error">
                <div class="ln">Username and password do not match</div>
            </div>
        </c:if>
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
            <h3>User Login</h3>
        </div>
        <c:url value="/customer/j_spring_security_check" var="loginUrl"/>
        <form role="form" action="${loginUrl}" method='POST'>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-8">
                        <div class="form-group">
                            <label for="inputEmail3">Email address</label>
                            <input type="email" class="form-control" name="j_username" id="inputEmail3">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-8">
                        <div class="form-group">
                            <label for="inputPassword3">Password</label>
                            <input type="password" class="form-control" name="j_password" id="inputPassword3">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <div class="register">
                    <span>Not registered? </span>
                    <a href="<c:url value="/register"/>">
                        <button type="button" class="btn btn-success">Sign Up</button>
                    </a>
                </div>
                <button type="submit" class="btn btn-primary">Log in</button>
            </div>
        </form>
    </div>
</div>

<%@include file="footer.jsp" %>
