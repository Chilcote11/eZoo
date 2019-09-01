	<%@ page isErrorPage="true" %>
	<!-- Header content -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
	<!-- Spring Forms taglib include -->
	<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

	<!-- Content for the body -->
	<header>
	  <div class="container">
	
		<%
			//display message if present
			out.println("<p class=\"alert alert-danger\">Something went wrong</p>");
		%>
		
		<h1>eZoo <small>error page</small></h1>
		<hr class="paw-primary">
		<p>
		  Sorry, you do not have permission to view this page <i class="fa fa-frown-o fa-3"></i> 
		</p>
		<p>
			<a href="<c:url value="/" />" class="btn btn-primary btn small">
			Return to Home Page
			</a>			
		</p>

	  </div>
	</header>

	<!-- Additional Facilities -->
	<jsp:include page="footer.jsp" />