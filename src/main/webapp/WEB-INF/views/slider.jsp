<div>
    <div id="slider" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#slider" data-slide-to="0" class="active"></li>
            <li data-target="#slider" data-slide-to="1"></li>
            <li data-target="#slider" data-slide-to="2"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <img src='<c:url value="/images/1.jpg"/>' alt="">
                <div class="carousel-caption">
                    Test Image 1
                </div>
            </div>
            <div class="item">
                <img src='<c:url value="/images/2.jpg"/>' alt="">
                <div class="carousel-caption">
                    Test Image 2
                </div>
            </div>

            <div class="item">
                <img src='<c:url value="/images/3.jpg"/>' alt="">
                <div class="carousel-caption">
                    Test Image 3
                </div>
            </div>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#slider" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#slider" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>
