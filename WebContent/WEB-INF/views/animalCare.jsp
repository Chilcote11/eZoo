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
	
		<h1>eZoo <small>Animal Care</small></h1>
		<hr class="paw-primary">
		<table class="table table-striped table-hover table-responsive ezoo-datatable">
			<thead>
				<tr>
					<th class="text-center">Delete?</th>
					<th class="text-center">Name</th>
					<th class="text-center">Kingdom</th>
					<th class="text-center">Phylum</th>
					<th class="text-center">Class</th>
					<th class="text-center">Order</th>
					<th class="text-center">Family</th>
					<th class="text-center">Genus</th>
					<th class="text-center">Species</th>
					<th class="text-center">Height(ft)</th>
					<th class="text-center">Weight(lbs)</th>
					<th class="text-center">Type</th>
					<th class="text-center">Health Status</th>
					<th class="text-center">Animal ID</th>
					<th class="text-center">Feeding Schedule ID</th>
					<th class="text-center">Feeding Schedule Assignment</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="animal" items="${animals}">
					<tr>
						<td>
							<sf:form action="AnimalDelete" modelAttribute="animal" method="post">
								<sf:hidden path="animalID" value="${animal.animalID}"/>
								<sf:button type="submit" class="btn btn-primary">Delete</sf:button>
							</sf:form>
						</td>
						<td><c:out value="${animal.name}" /></td>
						<td><c:out value="${animal.taxKingdom}" /></td>
						<td><c:out value="${animal.taxPhylum}" /></td>
						<td><c:out value="${animal.taxClass}" /></td>
						<td><c:out value="${animal.taxOrder}" /></td>
						<td><c:out value="${animal.taxFamily}" /></td>
						<td><c:out value="${animal.taxGenus}" /></td>
						<td><c:out value="${animal.taxSpecies}" /></td>
						
						<td><fmt:formatNumber value="${animal.height}"/></td>
						<td><fmt:formatNumber value="${animal.weight}"/></td>
						
						<td><c:out value="${animal.type}" /></td>
						<td><c:out value="${animal.healthStatus}" /></td>
						<td><fmt:formatNumber value="${animal.animalID}"/></td>
						<td><fmt:formatNumber value="${animal.feedingScheduleID}"/></td>
						<td>
							<c:if test="${animal.feedingScheduleID == 0 || animal.feedingScheduleID == null}">
								<sf:form action="FSAssign" modelAttribute="animal" method="get">
									<sf:hidden path="animalID" value="${animal.animalID}"/>
									<sf:button type="submit" class="btn btn-primary">Assign</sf:button>
								</sf:form>
							</c:if>
							<c:if test="${animal.feedingScheduleID >= 1}">
								<sf:form action="FSUnassign" modelAttribute="animal" method="post">
									<sf:hidden path="animalID" value="${animal.animalID}"/>
									<sf:button type="submit" class="btn btn-primary">Unassign</sf:button>
								</sf:form>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>	

	  </div>
	</header>
	
	<section>
	  <div class="container">
	    
	    <h1><small>Cool Facts</small></h1>
		<h4>Largest Animal (lbs):
		  <small>
		    <c:if test="${not empty largestAnimal}">
		      ${largestAnimal.name}, at ${largestAnimal.weight } lbs
		    </c:if>
		  </small>
		</h4>
		<h4>Longest Named Animal:
		  <small>
		    <c:if test="${not empty longestNamedAnimal}">
		      ${longestNamedAnimal.name }
		    </c:if>
		  </small>
		</h4>
	    
	  </div>
	</section>

	<!-- Footer -->
	<jsp:include page="footer.jsp" />