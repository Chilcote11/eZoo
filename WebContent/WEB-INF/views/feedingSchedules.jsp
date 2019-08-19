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
 
		<h1>eZoo <small>Feeding Schedules</small></h1>
		<hr class="paw-primary">
				
			<table class="table table-striped table-hover table-responsive ezoo-datatable">
				<thead>
					<tr>
						<th class="text-center">Update</th>
						<th class="text-center">Delete</th>
						<th class="text-center">Schedule ID</th>
						<th class="text-center">Feeding Time</th>
						<th class="text-center">Recurrence</th>
						<th class="text-center">Food</th>
						<th class="text-center">Notes</th>
						<th class="text-center">Animals Assigned</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="schedule" items="${feedingSchedules}">
						<tr>
							<td>
								<sf:form action="updateFeedingSchedule" modelAttribute="scheduleToUpdate" method="get"">
									<sf:hidden path="scheduleID" value="${schedule.scheduleID}" />
									<sf:hidden path="feedingTime" value="${schedule.feedingTime}" />
									<sf:hidden path="recurrence" value="${schedule.recurrence}" />
									<sf:hidden path="food" value="${schedule.food}" />
									<sf:hidden path="notes" value="${schedule.notes}" />
									<sf:button type="submit" class="btn btn-primary">Update</sf:button>
								</sf:form>
							</td>
							<td>
								<sf:form action="deleteFeedingSchedule" modelAttribute="scheduleToDelete" method="post">
									<sf:hidden path="scheduleID" value="${schedule.scheduleID}" />
									<sf:hidden path="feedingTime" value="${schedule.feedingTime}" />
									<sf:hidden path="recurrence" value="${schedule.recurrence}" />
									<sf:hidden path="food" value="${schedule.food}" />
									<sf:hidden path="notes" value="${schedule.notes}" />
									<sf:button type="submit" class="btn btn-primary">Delete</sf:button>
								</sf:form>
							</td>
							<td><fmt:formatNumber value="${schedule.scheduleID}"/></td>
							<td><c:out value="${schedule.feedingTime}" /></td>
							<td><c:out value="${schedule.recurrence}" /></td>
							<td><c:out value="${schedule.food}" /></td>
							<td><c:out value="${schedule.notes}" /></td>
							<td><c:out value="${schedule.animals}" /></td>									
						</tr>
					</c:forEach>
				</tbody>
			</table>
	
	  </div>
	</header>
	
	<!-- Footer -->
	<jsp:include page="footer.jsp" />
