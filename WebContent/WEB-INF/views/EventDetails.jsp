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
	
		<h1>eZoo <small>Event Details</small></h1>
		<hr class="paw-primary">
		
  		  <div style="font-size: 3em">
		  	<c:out value="${eventToDisplay.eventName}  [event ID: ${eventToDisplay.eventID}]" /></td>
		  </div>
		  <div></div>
		  <div style="font-size: 2em">
		  	<c:out value="Description:     ${eventToDisplay.description}" /></td>
		  </div>
		  <div style="font-size: 2em">
		  	<c:out value="Date Begin:     ${eventToDisplay.startTime}" /></td>
		  </div>
		  <div style="font-size: 2em">
		  	<c:out value="Date End:     ${eventToDisplay.endTime}" /></td>
		  </div>
		  <div style="font-size: 2em">
		  	<c:out value="Created By:     ${eventToDisplay.creator}" /></td>
		  </div>

	  </div>
	</header>


	<!-- Footer -->
	<jsp:include page="footer.jsp" />