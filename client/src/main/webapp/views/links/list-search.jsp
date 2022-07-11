<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <%@ include file="../components/layout/page-header.jsp" %>
</head>
<body>
<%@ include file="../components/atoms/navbar.jsp" %>
<div class="mt-2">
  <h3 class="alert alert-light">Kalpix LTD Links Crawler List</h3>
</div>
<div class="">
  <div class="card mt-2">
    <div class="card-block">
      <table class="table table-hover">
        <thead>
        <tr>
          <th>Link Name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="link" items="${links}">
          <tr>
            <td>${link}</td>
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
