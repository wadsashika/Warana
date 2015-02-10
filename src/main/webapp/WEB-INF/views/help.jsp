<%@include file="header.jsp" %>
<link href='<c:url value="/css/metisMenu.min.css" />' rel="stylesheet" type="text/css"/>
<link href='<c:url value="/css/dataTables.bootstrap.css" />' rel="stylesheet" type="text/css"/>
<link href='<c:url value="/css/warana/pages-commons.css" />' rel="stylesheet" type="text/css"/>
<link href='<c:url value="/css/font-awesome.min.css" />' rel="stylesheet" type="text/css"/>
<link href='<c:url value="/css/warana/view-stat.css" />' rel="stylesheet" type="text/css"/>
<link href='<c:url value="/css/warana/help.css" />' rel="stylesheet" type="text/css"/>

<script src='<c:url value="/js/metisMenu.min.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/jquery.dataTables.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/dataTables.bootstrap.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/sb-admin-2.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/warana/view-stat.js" />' type="text/javascript"></script>
<script src='<c:url value="/js/warana/help.js" />' type="text/javascript"></script>

<div id="wrapper">
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="dash-board-title">Warana Help</h1>
                <hr class="pages-title-hr">
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-body">

                        <dl>
                            <dt class="tab1"><a href="#chap1">Creating an Account</a></dt>
                            <dt class="tab1"><a href="#chap2">Uploading Resumes</a></dt>
                            <dt class="tab1"><a href="#chap3">Processing Resumes</a></dt>
                            <dt class="tab1"><a href="#chap4">Analyzing Candidates</a></dt>
                            <dt class="tab1"><a href="#chap5">View Candidate Statistics</a></dt>
                            <dd class="tab2"><a href="#chap5-1">Advanced Search</a></dd>
                            <dd class="tab2">
                                <a href="#chap5-2">View Profile</a>
                                <ul>
                                    <li><a href="#chap5-2-1">Skill proficiency Tab</a></li>
                                    <li><a href="#chap5-2-2">Spider Web Chart Tab</a></li>
                                </ul>
                            </dd>
                        </dl>
                        <hr>

                        <div class="tab1" id = "content-div">
                            <h3><a name="chap1">Creating an Account</a></h3>

                            <p>
                                If you have an account already, you can click on the Sign In tab and login to your
                                account.
                                If you do not have an account already,
                                click on the Sign Up for the account and you can login to the system via this account
                            </p>

                            <h3><a name="chap2">Uploading Resumes</a></h3>

                            <p>
                                When you are at the Dashboard, there you can see four main task tiles. At there you can
                                find
                                the Resume Upload icon and click on it.
                                You will be redirected to the upload page and then either drag and drop the PDF
                                Documents of
                                the resumes or click on the panel and
                                choose the relevant file list from the file browser. Click on the Upload button to
                                upload
                                resumes. Click on the Cancel All Button, if you want to cancel the uploading.
                            </p>

                            <h3><a name="chap3">Processing Resumes</a></h3>

                            <p>
                                Click on the Process Resume Tile and you are being directed to the page where a list of
                                resumes have been shown. Select the ones you need to process and click on the
                                Process Button.<br>
                                At this step the selected resumes are processed and information is extracted from both
                                the
                                candidate's resume as well as from his/her online
                                profiles. <br>
                                At the end of this process, an aggregated profile for the candidate is being generated.
                                These set of candidates will be available in the analyzing page for comparing them with
                                the
                                company skills.
                            </p>

                            <h3><a name="chap4">Analyzing Candidates</a></h3>

                            <p>
                                You will be redirected to this step either after resume processing step or when you
                                click on
                                the Analyze tile from the dashboard. When you are redirected to this step, you will be
                                shown
                                a list of candidates who are available for generating scores at the analyzing process.
                                Select the desired candidates and click on the Analyze button to begin the analyze
                                process.
                                <br>
                                After the completion of the process you will be directed to the View Statistics Page,
                                where
                                you can analyze the candidates' statistics.
                            </p>

                            <h3><a name="chap5">View Candidate Statistics</a></h3>

                            <p>
                                You are provided a set of analytical views to compare the candidates with the company
                                Skills
                                as well as with other candidates.<br>
                            </p>

                            <div class="tab1">
                                <h4><a name="chap5-1">Advanced Search</a></h4>

                                <p>
                                    Click on the Advanced Search Option at the beginning of the page and type the
                                    desired Skills
                                    to be searched. You will be prompted with available skills when you are typing in
                                    the field.
                                    Then click on the Submit button and a Stack Bar Chart will be displayed according to
                                    your
                                    search query. <br>
                                    The bars will be displayed in different slots regarding each of the skill you
                                    entered and
                                    the high will be according to the candidate's proficiency for the Skill.
                                    This allows you to easily compare the available candidates at the same time and the
                                    graphical view will provide the best clarification.
                                </p>
                            </div>

                            <div class="tab1">
                                <h4><a name="chap5-2">View Profile</a></h4>

                                <p>
                                    Click on the View button for a certain candidate, which is shown on the candidate
                                    list. You
                                    will be shown a modal popup with several tabs.
                                    Under each tab there will be shown the extracted information for the candidate.
                                </p>
                            </div>

                            <div class="tab2">
                                <h5><a name="chap5-2-1">Skill proficiency Tab</a></h5>

                                <p>
                                    Under this tab, a Bar chart will be shown for the candidate's skills and showing his
                                    proficiency value for the relevant skill. This allow you to
                                    compare the candidate based on his skills.
                                </p>
                            </div>

                            <div class="tab2">
                                <h5><a name="chap5-2-2">Spider Web Chart Tab</a></h5>

                                <p>
                                    Under this tab, a spider web chart will be shown. You can find two polygons on the
                                    chart for
                                    both the company as well as the candidate. In this statistics representation
                                    candidate's polygon has been placed on top of the company's polygon. All the
                                    statistical
                                    values are calculated based on a common skill corpus.
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel-footer">
                    <div class="action-btn-row">
                        <button type="button" id="backBtn" class="btn btn-primary action-btn">
                            <span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> Back
                        </button>
                    </div>
                </div>
                <!-- /.col-lg-4 -->
            </div>
        </div>

    </div>
</div>
<%@include file="footer.jsp" %>