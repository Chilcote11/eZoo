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
 
		<h1>eZoo <small>My Events</small></h1>
		<hr class="paw-primary">
				
			<table class="table table-striped table-hover table-responsive ezoo-datatable">
				<thead>
					<tr>
						<th class="text-center">Event ID</th>
						<th class="text-center">Event name</th>
						<th class="text-center">Event date</th>
						<th class="text-center">Event description</th>
						<th class="text-center">Leave?</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="event" items="${myEvents}">
						<tr>
							<td><fmt:formatNumber value="${event.eventID}"/></td>
							<td><c:out value="${event.eventName}" /></td>
							<td><c:out value="${event.eventDate}" /></td>
							<td><c:out value="${event.description}" /></td>
							<td>
								<sf:form action="EventLeave" modelAttribute="eventToLeave" method="post">
									<sf:hidden path="eventID" value="${event.eventID}" />
									<sf:hidden path="eventName" value="${event.eventName}" />
									<sf:hidden path="eventDate" value="${LocalDateTime.parse(event.eventDate)}" />
									<sf:hidden path="description" value="${event.description}" />
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
