<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <%@ include file="../components/layout/page-header.jsp" %>
</head>
<body>
<%@ include file="../components/atoms/navbar.jsp" %>
<div class="mt-2">
  <h3 class="alert alert-light">Download Webpage</h3>
</div>

<c:if test="${not empty error}">
  <div class="alert alert-danger" role="alert">
      ${error}
  </div>
</c:if>


<form class="mt-5 col-sm-4" style="margin: 0 auto" method="post" action="/websites/crawl">
  <div class="form-group">
    <label for="url">Webpage Url</label>
    <input required type="text" class="form-control" value="${url}" name="url" id="url" placeholder="Enter Webpage Url">
  </div>
  <button type="submit" class="btn btn-primary mt-4">Download</button>
</form>
<%@ include file="../components/layout/page-footer.jsp" %>
</body>
</html>
