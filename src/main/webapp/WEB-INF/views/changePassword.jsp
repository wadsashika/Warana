<%@include file="header.jsp" %>

<div id="wrapper">
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="dash-board-title">Change Password</h1>
                <hr class="pages-title-hr">
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="panel panel-primary">
                    <div class="panel-body">
                        <div id="password-body" class="form-horizontal">
                            <div class="form-group">
                                <div class="col-sm-12 col-md-3"></div>
                                <div class="col-sm-12 col-md-2">
                                    <label for="oldPassword" class="mand not-bold">Old Password</label>
                                </div>
                                <div class="col-sm-8 col-md-4">
                                    <input type="password" class="form-control" name="oldPassword" id="oldPassword"
                                           data-valid="required minlength" data-min=8>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12 col-md-3"></div>
                                <div class="col-sm-12 col-md-2">
                                    <label for="newPassword" class="mand not-bold">New Password</label>
                                </div>
                                <div class="col-sm-8 col-md-4">
                                    <input type="password" class="form-control" name="newPassword" id="newPassword"
                                           data-valid="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12 col-md-3"></div>
                                <div class="col-sm-12 col-md-2">
                                    <label for="reEnterPassword" class="mand not-bold">Re-Enter New Password</label>
                                </div>
                                <div class="col-sm-8 col-md-4">
                                    <input type="password" class="form-control" name="rePassword" id="reEnterPassword"
                                           data-valid="required equalTo" data-ele="newPassword">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-footer">
                        <div id="operation-buttons-row" class="action-btn-row">
                            <button type="button" class="btn btn-primary action-btn" id="backBtn">
                                <span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> Back
                            </button>
                            <button type="button" class="btn btn-primary action-btn" id="savePassword">
                                <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> Save
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<link href='<c:url value="/css/warana/pages-commons.css" />' rel="stylesheet" type="text/css"/>

<script src='<c:url value="/js/warana/changePassword.js" />' type="text/javascript"></script>
<%@include file="footer.jsp" %>
