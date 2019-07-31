	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<!-- 	Just some stuff you need -->
	<header>
	  <div class="container">

	<c:choose>
	<c:when test="${not empty message }">
	  <p class="alert ${messageClass}">${message }</p>
	<%
	  session.setAttribute("message", null);
	  session.setAttribute("messageClass", null);
	%>
	</c:when>
	</c:choose>
 
		<h1>eZoo <small>Feeding Schedules</small></h1>
		<hr class="paw-primary">
		
		<form action="deleteFeedingSchedule" method="post">
		
			<table class="table table-striped table-hover table-responsive ezoo-datatable">
				<thead>
					<tr>
						<th class="text-center">Remove</th>
						<th class="text-center">Schedule ID</th>
						<th class="text-center">Feeding Time</th>
						<th class="text-center">Recurrence</th>
						<th class="text-center">Food</th>
						<th class="text-center">Notes</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="schedule" items="${feedingSchedules}">
						<tr>
							<td><button type="submit" class="btn btn-primary">Delete</button>
							<td><fmt:formatNumber value="${schedule.feedingScheduleID}"/></td>
							
							<td><c:out value="${schedule.feedingTime}" /></td>
							<td><c:out value="${schedule.recurrence}" /></td>
							<td><c:out value="${schedule.food}" /></td>
							<td><c:out value="${schedule.notes}" /></td>	
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
		</form>	
	  </div>
	</header>
	
	<!-- Footer -->
	<jsp:include page="footer.jsp" />
