<%--Define the view profile modal--%>
<div class="modal fade" id="profile-info" tabindex="-1" role="dialog" aria-labelledby="profile-info-label"
     aria-hidden="true">
    <div class="modal-dialog" id="prof-modal">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel"></h4>
            </div>
            <div class="modal-body">
                <div id="basic-info" class="row col-lg-12 col-md-12">
                    <div id="image-area-div" class="col-lg-5">
                        <img class="img-circle img-responsive" id="prof-img" alt="">
                        </span>
                    </div>

                    <div id="info-area-div" class="col-lg-7">
                        <dl class="dl-horizontal" id="profile-detail-dl">
                        </dl>
                    </div>
                </div>

                <ul class="nav nav-tabs" id="prof-tabs">
                    <li class="active">
                        <a href="#education" data-toggle="tab">Education</a>
                    </li>
                    <li>
                        <a href="#achievements" data-toggle="tab">Achievements</a>
                    </li>
                    <li>
                        <a href="#work-exp" data-toggle="tab">Work</a>
                    </li>
                    <li>
                        <a href="#projects" data-toggle="tab">Project</a>
                    </li>
                    <li>
                        <a href="#publications" data-toggle="tab">Publications</a>
                    </li>
                    <li>
                        <a href="#stat" data-toggle="tab" id="stat-tab">Skill Proficiency</a>
                    </li>
                    <li>
                        <a href="#spider-web" data-toggle="tab" id="spider-web-tab">Spider Web Chart</a>
                    </li>
                </ul>

                <div class="tab-content">
                    <div class="tab-pane fade in active" id="education"></div>
                    <div class="tab-pane fade" id="achievements"></div>
                    <div class="tab-pane fade" id="work-exp"></div>
                    <div class="tab-pane fade" id="projects"></div>
                    <div class="tab-pane fade" id="publications"></div>
                    <div class="tab-pane fade" id="stat"><div id="chart-region"></div></div>
                    <div class="tab-pane fade" id="spider-web"><div id="spider-web-region"></div></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
