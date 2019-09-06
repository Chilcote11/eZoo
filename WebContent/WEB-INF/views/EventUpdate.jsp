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
	
		<h1>eZoo <small>Update Event</small></h1>
		<hr class="paw-primary">
		
		<sf:form action="EventUpdate" modelAttribute="eventToUpdate" method="post" class="form-horizontal">
		
  		  <div style="font-size: 2em">
		  	<c:out value="Event ID: ${eventToUpdate.eventID}" /></td>
		  	<sf:hidden path="eventID" value="${eventToUpdate.eventID}" />
		  </div>
		  <div class="form-group">
		    <sf:label path="eventName" class="col-sm-4 control-label">Event Name</sf:label>
		    <div class="col-sm-4">
		      <sf:input class="form-control" path="eventName" /><sf:errors path="eventName" />
		    </div>
		  </div>
		  <div class="form-group">
		    <sf:label path="description" class="col-sm-4 control-label">Description</sf:label>
		    <div class="col-sm-4">
		      <sf:input class="form-control" path="description" /><sf:errors path="description" />
		    </div>
		  </div>
  		  <div class="form-group">
		    <sf:label path="startTime" class="col-sm-4 control-label">Start Time</sf:label>
		    <div class="col-sm-4">
		      <sf:input class="form-control" path="startTime" /><sf:errors path="startTime" />
		    </div>
		  </div>
  		  <div class="form-group">
		    <sf:label path="endTime" class="col-sm-4 control-label">End Time</sf:label>
		    <div class="col-sm-4">
		      <sf:input class="form-control" path="endTime" /><sf:errors path="endTime" />
		    </div>
		  </div>
		  <sf:hidden path="creator" value="${creator}" />
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <sf:button type="submit" class="btn btn-primary">Update</sf:button>
		    </div>
		  </div>
		</sf:form>
	  </div>
	</header>


	<!-- Footer -->
	<jsp:include page="footer.jsp" />