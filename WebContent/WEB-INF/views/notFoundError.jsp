<%@ page isErrorPage="true" %>
<!-- Header content -->
<jsp:include page="header.jsp" />

<!-- JSTL includes -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Content for the body -->
<header>
  <div class="container">

	<%
		//display message if present
		out.println("<p class=\"alert alert-danger\">Something went wrong</p>");
	%>
	
	<h1>eZoo <small>404 Page not found</small></h1>
	<hr class="paw-primary">
	<p>
	  Cannot find requested resource
	</p>

  </div>
</header>

<!-- Additional Facilities -->
<jsp:include page="footer.jsp" />
