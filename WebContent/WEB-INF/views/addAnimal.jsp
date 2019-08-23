	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
	<!-- Spring Forms taglib include -->
	<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
	
	<!-- tried including this to use model.addAttribute() ... no luck -->
	<!--%@ page import="org.springframework.ui.Model" %-->
	
	<!-- 	Just some stuff you need -->
	<header>
	  <div class="container">
	  
	<c:choose>
	<c:when test="${not empty message }">
	  <p class="alert ${messageClass}">${message }</p>
	</c:when>
	</c:choose>
	
		<h1>eZoo <small>Add Animal</small></h1>
		<hr class="paw-primary">
		
		<sf:form action="addAnimal" modelAttribute="newAnimal" method="post" class="form-horizontal">
		
		  <div class="form-group">
		    <sf:label path="animalID" class="col-sm-4 control-label">ID</sf:label>
		    <div class="col-sm-4">
		      <sf:input type="number" class="form-control" path="animalID" placeholder="ID" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <sf:label path="name" class="col-sm-4 control-label">Name</sf:label>
		    <div class="col-sm-4">
		      <sf:input type="text" class="form-control" path="name" placeholder="Name" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <sf:label path="healthStatus" class="col-sm-4 control-label">Health</sf:label>
		    <div class="col-sm-4">
				<sf:select required="required" path="healthStatus" class="form-control">
					<sf:option value="Healthy">
						Healthy
					</sf:option>
					<sf:option value="Sick">
						Sick
					</sf:option>
					<sf:option value="Injured">
						Injured
					</sf:option>
					<sf:option value="Dead">
						Dead
					</sf:option>
				</sf:select>
			</div>	
		  </div>
		  <div class="form-group">
		    <sf:label path="type" class="col-sm-4 control-label">Type</sf:label>
		    <div class="col-sm-4">
				<sf:select required="required" path="type" class="form-control">
					<sf:option value="Mammal (Terrestrial)">
						Mammal (Terrestrial)
					</sf:option>
					<sf:option value="Mammal (Aquatic)">
						Mammal (Aquatic)
					</sf:option>
					<sf:option value="Mammal (Aviary)">
						Mammal (Aviary)
					</sf:option>
					<sf:option value="Fish">
						Fish
					</sf:option>
					<sf:option value="Amphibian">
						Amphibian
					</sf:option>
					<sf:option value="Reptile">
						Reptile
					</sf:option>
					<sf:option value="Bird">
						Bird
					</sf:option>
				</sf:select>
		    </div>
		  </div>
		  <div class="form-group">
		    <sf:label path="height" class="col-sm-4 control-label">Height (in)</sf:label>
		    <div class="col-sm-4">
		      <sf:input type="number" step="0.01" class="form-control" path="height" placeholder="Height" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <sf:label path="weight" class="col-sm-4 control-label">Weight (lb)</sf:label>
		    <div class="col-sm-4">
		      <sf:input type="number" step="0.01" class="form-control" path="weight" placeholder="Weight" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <sf:label path="taxKingdom" class="col-sm-4 control-label">Kingdom</sf:label>
		    <div class="col-sm-4">
		      <sf:input type="text" class="form-control" path="taxKingdom" placeholder="Kingdom" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <sf:label path="taxPhylum" class="col-sm-4 control-label">Phylum</sf:label>
		    <div class="col-sm-4">
		      <sf:input type="text" class="form-control" path="taxPhylum" placeholder="Phylum" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <sf:label path="taxClass" class="col-sm-4 control-label">Class</sf:label>
		    <div class="col-sm-4">
		      <sf:input type="text" class="form-control" path="taxClass" placeholder="Class" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <sf:label path="taxOrder" class="col-sm-4 control-label">Order</sf:label>
		    <div class="col-sm-4">
		      <sf:input type="text" class="form-control" path="taxOrder" placeholder="Order" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <sf:label path="taxFamily" class="col-sm-4 control-label">Family</sf:label>
		    <div class="col-sm-4">
		      <sf:input type="text" class="form-control" path="taxFamily" placeholder="Family" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <sf:label path="taxGenus" class="col-sm-4 control-label">Genus</sf:label>
		    <div class="col-sm-4">
		      <sf:input type="text" class="form-control" path="taxGenus" placeholder="Genus" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <sf:label path="taxSpecies" class="col-sm-4 control-label">Species</sf:label>
		    <div class="col-sm-4">
		      <sf:input type="text" class="form-control" path="taxSpecies" placeholder="Species" required="required"/>
		    </div>
		  </div>
  		  <div class="form-group">
		    <sf:label path="feedingScheduleID" class="col-sm-4 control-label">FeedingScheduleID</sf:label>
		    <div class="col-sm-4">
		      <sf:input type="number" class="form-control" path="feedingScheduleID" placeholder="FeedingScheduleID"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <sf:button type="submit" class="btn btn-primary">Add</sf:button>
		    </div>
		  </div>
		</sf:form>
	  </div>
	</header>


	<!-- Footer -->
	<jsp:include page="footer.jsp" />