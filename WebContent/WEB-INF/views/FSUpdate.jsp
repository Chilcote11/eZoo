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
	
		<h1>eZoo <small>Update Feeding Schedule</small></h1>
		<hr class="paw-primary">
		
		<sf:form action="FSUpdate" modelAttribute="scheduleToUpdate" method="post" class="form-horizontal">
		
  		  <div style="font-size: 2em">
		  	<c:out value="Schedule ID: ${scheduleToUpdate.scheduleID}" /></td>
		  	<sf:hidden path="scheduleID" value="${scheduleToUpdate.scheduleID}" />
		  </div>
		  <div class="form-group">
		    <sf:label path="feedingTime" class="col-sm-4 control-label">Time</sf:label>
		    <div class="col-sm-4">
		      <sf:input class="form-control" path="feedingTime" /><sf:errors path="feedingTime" />
		    </div>
		  </div>
		  <div class="form-group">
		    <sf:label path="recurrence" class="col-sm-4 control-label">Recurrence</sf:label>
		    <div class="col-sm-4">
		      <sf:input class="form-control" path="recurrence" /><sf:errors path="recurrence" />
		    </div>
		  </div>
		  <div class="form-group">
		    <sf:label path="food" class="col-sm-4 control-label">Food</sf:label>
		    <div class="col-sm-4">
		      <sf:input class="form-control" path="food" /><sf:errors path="food" />
		    </div>
		  </div>
		  <div class="form-group">
		    <sf:label path="notes" class="col-sm-4 control-label">Notes</sf:label>
		    <div class="col-sm-4">
		      <sf:input class="form-control" path="notes" /><sf:errors path="notes" />
		    </div>
		  </div>
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