<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
  <title>mongoDb test</title>
  <!-- Bootstrap core CSS -->
  <link href="<c:url value='resources/css/bootstrap.min.css' />" rel="stylesheet">
</head>
<body>
  <div class="container">

    <div class="jumbotron" style="margin-top: 20px;">

      <h1>MongoDb Test Project</h1>

      <p class="lead">
        just test project, nothing else...
      </p>

      <sec:authorize access="!isAuthenticated()">
        <p><a class="btn btn-lg btn-success" href="<c:url value="/login" />" role="button">Login</a></p>
      </sec:authorize>

      <sec:authorize access="isAuthenticated()">
        <p>Welcome <sec:authentication property="principal.username"/></p>
        <p><a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Logout</a></p>
        <p><a class="btn btn-lg btn-danger" href="<c:url value="/index" />" role="button">Mongo operations</a></p>
      </sec:authorize>

    </div>

    <div class="footer">
      <p>© Ignesis 2015</p>
    </div>

  </div>
</body>
</html>
