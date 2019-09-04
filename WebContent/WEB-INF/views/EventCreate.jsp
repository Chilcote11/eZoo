	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
	<!-- Spring Forms taglib include -->
	<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
	
	<!-- 	Just some stuff you need -->
	<header>
	  <div class="container">

	<c:choose>
	<c:when test="${not empty message }">
	  <p class="alert ${messageClass}">${message }</p>
	</c:when>
	</c:choose>
	
		<h1>eZoo <small>Create Event</small></h1>
		<hr class="paw-primary">
		
		<sf:form action="EventCreate" modelAttribute="newEvent" method="post" class="form-horizontal">
		
		  <div class="form-group">
		    <sf:label path="eventID" class="col-sm-4 control-label">Event ID</sf:label>
		    <div class="col-sm-4">
		      <sf:input class="form-control" path="eventID"/><sf:errors path="eventID" />
		    </div>
		  </div>
		  <div class="form-group">
		    <sf:label path="eventName" class="col-sm-4 control-label">Event Name</sf:label>
		    <div class="col-sm-4">
		      <sf:input class="form-control" path="eventName" /><sf:errors path="eventName" />
		    </div>
		  </div>
		  <div class="form-group">
		    <sf:label path="eventDate" class="col-sm-4 control-label">Event Date</sf:label>
		    <div class="col-sm-4">
		      <sf:input class="form-control" path="eventDate" /><sf:errors path="eventDate" />
		    </div>
		  </div>
		  <div class="form-group">
		    <sf:label path="description" class="col-sm-4 control-label">Description</sf:label>
		    <div class="col-sm-4">
		      <sf:input class="form-control" path="description" /><sf:errors path="description" />
		    </div>
		  </div>
		</sf:form>
	  </div>
	</header>


	<!-- Footer -->
	<jsp:include page="footer.jsp" />