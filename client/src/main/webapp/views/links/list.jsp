<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <%@ include file="../components/layout/page-header.jsp" %>
</head>
<body>
<%@ include file="../components/atoms/navbar.jsp" %>
<div class="mt-2">
  <h3 class="alert alert-light">Kalpix LTD Links List</h3>
</div>
<div class="container">
<div class="card mt-2">
  <div class="card-block">
    <table class="table table-hover">
      <thead>
      <tr>
        <th>Link ID</th>
        <th>Link NAME</th>
        <th>Total Elapsed Time</th>
        <th>Total Downloaded Kilobytes</th>
        <th>Website</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="link" items="${links}">
        <tr>
          <td>${link.id}</td>
          <td>${link.link_name}</td>
          <td>${link.total_elapsed_time}</td>
            <td>${link.total_downloaded_kilobytes}</td>
            <td>${link.website != null ? link.website.website_name : "N/A"}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <hr/>
  </div>
</div>
</div>
<%@ include file="../components/layout/page-footer.jsp" %>
</body>
</html>
