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
	
		<h1>eZoo <small>Login</small></h1>
		<hr class="paw-primary">
		
		<sf:form action="login" modelAttribute="userToValidate" method="post" class="form-horizontal">
		
		  <div class="form-group">
		    <sf:label path="username" class="col-sm-4 control-label">Username</sf:label>
		    <div class="col-sm-4">
		      <sf:input class="form-control" path="username"/><sf:errors path="username" />
		    </div>
		  </div>
		  <div class="form-group">
		    <sf:label path="password" class="col-sm-4 control-label">Password</sf:label>
		    <div class="col-sm-4">
		      <sf:input class="form-control" path="password" /><sf:errors path="password" />
		    </div>
		  </div>
		  <div class="form-group">
		    <sf:label path="role" class="col-sm-4 control-label">User Role</sf:label>
		    <div class="col-sm-4">
				<sf:select path="role" class="form-control">
					<sf:option value="">
						-- SELECT A ROLE --
					</sf:option>
					<sf:option value="ROLE_USER">
						ROLE_USER
					</sf:option>
					<sf:option value="ROLE_ADMIN">
						ROLE_ADMIN
					</sf:option>
				</sf:select>
				<sf:errors path="role"/>
			</div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <sf:button type="submit" class="btn btn-primary">Log In</sf:button>
		    </div>
		  </div>
		</sf:form>
		<form action="register" method="get" class="form-horizontal">
			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-1">
					<button type="submit" class="btn btn-primary">Register</button>
				</div>
			</div>
		</form>
	  </div>
	</header>


	<!-- Footer -->
	<jsp:include page="footer.jsp" />