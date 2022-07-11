<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
    <%@ include file="../components/layout/page-header.jsp" %>
</head>
<body>
<%@ include file="../components/atoms/navbar.jsp" %>
<div class="container-fluid">
    <section>
        <div class="row">
            <div class="col-xl-3 col-sm-6 col-12 mb-4 p-4">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex justify-content-between px-md-1">
                            <div class="align-self-center">
                                <i class="fas fa-pencil-alt text-info fa-3x"></i>
                            </div>
                            <div class="text-end">
                                <h3>${totalWebsites}</h3>
                                <p class="mb-0">Total Websites</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xl-3 col-sm-6 col-12 mb-4">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex justify-content-between px-md-1">
                            <div class="align-self-center">
                                <i class="far fa-comment-alt text-warning fa-3x"></i>
                            </div>
                            <div class="text-end">
                                <h3>${totalLinks}</h3>
                                <p class="mb-0">Total Links</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xl-3 col-sm-6 col-12 mb-4">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex justify-content-between px-md-1">
                            <div class="align-self-center">
                                <i class="fas fa-chart-line text-success fa-3x"></i>
                            </div>
                            <div class="text-end">
                                <h3>${totalUsers}</h3>
                                <p class="mb-0">Total Users</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
</body>
</html>
