<!-- Header -->
<jsp:include page="header.jsp" />

<!-- JSTL includes -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- Spring Forms taglib include -->
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<header>
  <div class="container">

	<c:choose>
	<c:when test="${not empty message }">
	  <p class="alert ${messageClass}">${message }</p>
	</c:when>
	</c:choose>

	<h1>eZoo <small>My Events</small></h1>
	<hr class="paw-primary">
			
		<table class="table table-striped table-hover table-responsive ezoo-datatable">
			<thead>
				<tr>
					<th class="text-center">Event ID</th>
					<th class="text-center">Name</th>
					<th class="text-center">Creator</th>
					<th class="text-center">Attending</th>
					<th class="text-center">Date</th>
					<th class="text-center">Status</th>
					<th class="text-center">Details</th>
					<th class="text-center">Leave</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="event" items="${myEvents}">
					<tr>
						<td><fmt:formatNumber value="${event.eventID}"/></td>
						<td><c:out value="${event.eventName}" /></td>
						<td><c:out value="${event.creator}" /></td>
						<td><c:out value="${event.numberAttending}" /></td>
						<td><c:out value="${event.startTime}" /></td>
						<td>
							<c:set var="status" value="${'Closed'}" />
							<c:if test="${ now.isBefore( event.endTime )}">
								<c:set var="status" value="${'In Progress'}" />
								<c:if test="${ now.isBefore( event.startTime ) }" >
									<c:set var="status" value="${'Open'}" />
								</c:if>
							</c:if>
							<c:out value="${status}" />
						</td>
						<td>
							<sf:form action="EventDetails" modelAttribute="eventDetails" method="get">
								<sf:hidden path="eventID" value="${event.eventID}" />
								<sf:hidden path="eventName" value="${event.eventName}" />
								<sf:hidden path="description" value="${event.description}" />
								<sf:hidden path="startTime" value="${LocalDateTime.parse(event.startTime)}" />
								<sf:hidden path="endTime" value="${LocalDateTime.parse(event.endTime)}" />
								<sf:hidden path="creator" value="${event.creator}" />
								<sf:button type="submit" class="btn btn-primary">Details</sf:button>
							</sf:form>
						</td>
						<td>
							<sf:form action="EventLeave" modelAttribute="eventToLeave" method="post">
								<sf:hidden path="eventID" value="${event.eventID}" />
								<sf:hidden path="eventName" value="${event.eventName}" />
								<sf:hidden path="description" value="${event.description}" />
								<sf:hidden path="startTime" value="${LocalDateTime.parse(event.startTime)}" />
								<sf:hidden path="endTime" value="${LocalDateTime.parse(event.endTime)}" />
								<sf:hidden path="endTime" value="${event.creator}" />
								<sf:button type="submit" class="btn btn-primary">Bail</sf:button>
							</sf:form>
						</td>								
					</tr>
				</c:forEach>
			</tbody>
		</table>

  </div>
</header>

<!-- Footer -->
<jsp:include page="footer.jsp" />
