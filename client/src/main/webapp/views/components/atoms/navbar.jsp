<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Kalpix LTD</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="nav-link"><a href="/auth/dashboard">Dashboard</a></li>
            <li class="nav-link"><a href="/websites/list">Websites</a></li>
            <li class="nav-link"><a href="/links/list">Links</a></li>
        </ul>
        <button class="btn btn-danger navbar-btn" onclick="window.location = '/websites/crawl'">Download</button>
    </div>

<%--    change active when pathname from window location is equal to route--%>
    <script>
        var links = ["/auth/dashboard", "/websites/list", "/links/list"];
        var pathname = window.location.pathname;
        var path = pathname.split('/');
        var route = path[1];
        var active = document.getElementsByClassName('nav-link');
        for (var i = 0; i < active.length; i++) {
            console.log(active[i].href);
            if(links[i].split('/')[1] == route){
                active[i].className += " active";
            }
        }
    </script>

</nav>