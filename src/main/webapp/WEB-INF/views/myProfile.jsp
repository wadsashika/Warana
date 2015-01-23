<%@include file="header.jsp" %>

<div id="wrapper">
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="dash-board-title">My Profile</h1>
                <hr class="pages-title-hr">
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="panel panel-primary">
                    <div class="panel-body">
                        <div id="profile-body" class="form-horizontal">
                            <div class="form-group">
                                <div class="col-sm-12 col-md-2">
                                    <label for="firstName" class="mand not-bold">First Name</label>
                                </div>
                                <div class="col-sm-8 col-md-4">
                                    <input type="text" class="form-control" name="firstName" id="firstName"
                                           data-valid="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12 col-md-2">
                                    <label for="lastName" class="mand not-bold">Last Name</label>
                                </div>
                                <div class="col-sm-8 col-md-4">
                                    <input type="text" class="form-control" name="lastName" id="lastName"
                                           data-valid="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12 col-md-2">
                                    <label for="email" class="mand not-bold">E-mail</label>
                                </div>
                                <div class="col-sm-8 col-md-4">
                                    <input type="email" class="form-control" name="email" id="email"
                                           data-valid="required email">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12 col-md-2">
                                </div>
                                <div class="col-sm-8 col-md-4">
                                    <button type="button" class="btn btn-primary" id="changePassword">Change Password
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-footer">
                        <div id="operation-buttons-row" class="action-btn-row">
                            <button type="button" class="btn btn-primary action-btn" id="backBtn">
                                <span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> Back
                            </button>
                            <button type="button" class="btn btn-primary action-btn" id="saveProfile">
                                <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> Save
                            </button>
                            <button type="button" class="btn btn-primary action-btn" id="editProfile">
                                <span class="glyphicon glyphicon-edit" aria-hidden="true"></span> Edit
                            </button>
                            <button type="button" class="btn btn-primary action-btn" id="resetProfile">
                                <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> Reset
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<link href='<c:url value="/css/warana/pages-commons.css" />' rel="stylesheet" type="text/css"/>

<script src='<c:url value="/js/warana/myProfile.js" />' type="text/javascript"></script>
<%@include file="footer.jsp" %>
