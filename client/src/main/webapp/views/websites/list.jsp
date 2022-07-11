<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <%@ include file="../components/layout/page-header.jsp" %>
</head>
<body>
<%@ include file="../components/atoms/navbar.jsp" %>
<div class="mt-2">
  <h3 class="alert alert-light">Kalpix LTD Websites List</h3>
</div>
<div class="">
  <div class="card mt-2">
    <div class="card-block">
      <table class="table table-hover">
        <thead>
        <tr>
          <th>Website ID</th>
          <th>Website Name</th>
          <th>Download Start Date</th>
          <th>Download End Date</th>
          <th>Total Elapsed Time</th>
          <th>Total Downloaded Kilobytes</th>
          <th>Number of Links</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="website" items="${websites}">
          <tr>
            <td>${website.id}</td>
            <td>${website.website_name}</td>
            <td>${website.download_start_date_time}</td>
            <td>${website.download_end_date_time}</td>
            <td>${website.total_elapsed_time}</td>
            <td>${website.total_downloaded_kilobytes}</td>
            <td>${website.links.size()}</td>
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
