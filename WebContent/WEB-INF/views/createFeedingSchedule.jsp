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
	<%
	  model.addAttribute("message", null);
	  model.addAttribute("messageClass", null);
	%>
	</c:when>
	</c:choose>
	
		<h1>eZoo <small>Create Feeding Schedule</small></h1>
		<hr class="paw-primary">
		
		<sf:form action="createFeedingSchedule" commandName="newFeedingSchedule" method="post" class="form-horizontal">
		
		  <div class="form-group">
		    <sf:label path="scheduleID" class="col-sm-4 control-label">Feeding Schedule ID</sf:label>
		    <div class="col-sm-4">
		      <sf:input type="number" class="form-control" path="scheduleID" placeholder="ScheduleID" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <sf:label path="feedingTime" class="col-sm-4 control-label">Time</sf:label>
		    <div class="col-sm-4">
		      <sf:input type="text" class="form-control" path="feedingTime" placeholder="FeedingTime" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <sf:label path="recurrence" class="col-sm-4 control-label">Recurrence</sf:label>
		    <div class="col-sm-4">
		      <sf:input type="text" class="form-control" path="recurrence" placeholder="Recurrence" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <sf:label path="food" class="col-sm-4 control-label">Food</sf:label>
		    <div class="col-sm-4">
		      <sf:input type="text" class="form-control" path="food" placeholder="Food" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <sf:label path="notes" class="col-sm-4 control-label">Notes</sf:label>
		    <div class="col-sm-4">
		      <sf:input type="text" class="form-control" path="notes" placeholder="Notes"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <sf:button type="submit" class="btn btn-primary">Create</sf:button>
		    </div>
		  </div>
		</sf:form>
	  </div>
	</header>


	<!-- Footer -->
	<jsp:include page="footer.jsp" />