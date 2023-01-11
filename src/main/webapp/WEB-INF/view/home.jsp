<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
  <head>
    <title>HTML 5 Boilerplate</title>
  </head>
  <body>
  <h1>Welcome to home page</h1>
  
  <p>
  
  User: <security:authentication property="principal.username"  />
  <br><br>
  Role (s):<security:authentication property="principal.authorities"  />
  
  </p>
  
  <hr><hr>
  
  <security:authorize access="hasRole('MANAGER')" >
  <p>
  
  <a  href="${pageContext.request.contextPath}/leaders" >LeaderShip Meeting</a>
  (only for Managers)
  
  </p>
  </security:authorize>
  
  <security:authorize  access="hasRole('ADMIN')"  >
  
   <p>
  
  <a  href="${pageContext.request.contextPath}/systems" >IT Systems Meeting</a>
  (only for Admins)
  
  </p>
  
  </security:authorize>
  
  
  <form:form  action="${pageContext.request.contextPath}/logout"      method="POST">
  
  <input type="submit" value="Logout"/>
  
  
  </form:form>
  </body>
</html>