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
	
		<h1>eZoo <small>Assign Feeding Schedule</small></h1>
		<hr class="paw-primary">
		
		<form action="assignFeedingSchedule" method="post" class="form-horizontal">
		
		  <div class="form-group">
		    <label for="scheduleID" class="col-sm-4 control-label">Feeding Schedule ID</label>
		    <div class="col-sm-4">
		      <input type="number" class="form-control" id="scheduleID" name="scheduleID" placeholder="ScheduleID" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="feedingTime" class="col-sm-4 control-label">Time</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="feedingTime" name="feedingTime" placeholder="FeedingTime" required="required"/>
		    </div>
		  </div>
		  </div>
		  <div class="form-group">
		    <label for="recurrence" class="col-sm-4 control-label">Recurrence</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="recurrence" name="recurrence" placeholder="Recurrence" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="food" class="col-sm-4 control-label">Food</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="food" name="food" placeholder="Food" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="notes" class="col-sm-4 control-label">Notes</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="notes" name="notes" placeholder="Notes" required="required"/>
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="animalID" class="col-sm-4 control-label">Animal ID</label>
		    <div class="col-sm-4">
		      <input type="number" class="form-control" id="animalID" name="animalID" placeholder="animalID" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="name" class="col-sm-4 control-label">Name</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="name" name="name" placeholder="Name" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="healthStatus" class="col-sm-4 control-label">Health</label>
		    <div class="col-sm-4">
					<select required="required" name="healthStatus" class="form-control">
						<option value="Healthy">
							Healthy
						</option>
						<option value="Sick">
							Sick
						</option>
						<option value="Injured">
							Injured
						</option>
						<option value="Dead">
							Dead
						</option>
					</select>
				</div>
			</div>	
		  <div class="form-group">
		    <label for="type" class="col-sm-4 control-label">Type</label>
		    <div class="col-sm-4">
					<select required="required" name="type" class="form-control">
						<option value="Mammal (Terrestrial)">
							Mammal (Terrestrial)
						</option>
						<option value="Mammal (Aquatic)">
							Mammal (Aquatic)
						</option>
						<option value="Mammal (Aviary)">
							Mammal (Aviary)
						</option>
						<option value="Fish">
							Fish
						</option>
						<option value="Amphibian">
							Amphibian
						</option>
						<option value="Reptile">
							Reptile
						</option>
						<option value="Bird">
							Bird
						</option>
					</select>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="height" class="col-sm-4 control-label">Height (in)</label>
		    <div class="col-sm-4">
		      <input type="number" step="0.01" class="form-control" id="height" name="height" placeholder="Height" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="weight" class="col-sm-4 control-label">Weight (lb)</label>
		    <div class="col-sm-4">
		      <input type="number" step="0.01" class="form-control" id="weight" name="weight" placeholder="Weight" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="kingdom" class="col-sm-4 control-label">Kingdom</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="kingdom" name="kingdom" placeholder="Kingdom" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="phylum" class="col-sm-4 control-label">Phylum</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="phylum" name="phylum" placeholder="Phylum" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="class" class="col-sm-4 control-label">Class</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="class" name="clazz" placeholder="Class" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="order" class="col-sm-4 control-label">Order</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="order" name="order" placeholder="Order" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="family" class="col-sm-4 control-label">Family</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="family" name="family" placeholder="Family" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="genus" class="col-sm-4 control-label">Genus</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="genus" name="genus" placeholder="Genus" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="species" class="col-sm-4 control-label">Species</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="species" name="species" placeholder="Species" required="required"/>
		    </div>
		  </div>
  		  <div class="form-group">
		    <label for="feedingScheduleID" class="col-sm-4 control-label">Feeding Schedule ID</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="feedingScheduleID" name="feedingScheduleID" placeholder="FeedingScheduleID" required="required"/>
		    </div>
		  </div>
		  
		  
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <button type="submit" class="btn btn-primary">Assign</button>
		    </div>
		  </div>
		</form>
	  </div>
	</header>


	<!-- Footer -->
	<jsp:include page="footer.jsp" />