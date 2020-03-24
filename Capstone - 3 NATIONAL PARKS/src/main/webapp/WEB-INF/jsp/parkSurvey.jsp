<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url value="/parkSurvey" var="parkSurveyUrl" />

<div id = "formDiv">
<form:form action="${parkSurveyUrl}" method="POST" modelAttribute="surveyData">
	<div class="form-group">
		<label>E-mail Address</label>
		<form:input placeholder="example@gmail.com" class = "form-control" name="emailAddress" type="email" path="emailAddress" required="required" />
		<form:errors path="emailAddress" />
	</div>
	<div class="form-group">
		<label>State of Residence</label>
		<form:select class="form-control" name="state" path="state" required="required">
			<form:option value="hidden">Please select a State</form:option>
			<c:forEach var="state" items="${states}">
				<form:option value="${state.name}">${state.abbreviation}</form:option>
			</c:forEach>
		</form:select>
		<form:errors path="state" />
	</div>
	<div class="form-group">
		<label>Physical Activity Level</label>
		<form:select class="form-control" name="activityLevel" path="activityLevel" required="required">
			<form:option value="hidden">Please select an Activity Level</form:option>
			<c:forEach var="activityLevel" items="${activityLevel}">
				<form:option value="${activityLevel.name}">${activityLevel.name}</form:option>
			</c:forEach>
		</form:select>
		<form:errors path="activityLevel" />
	</div>
	<div class="form-group">
	<label for="favorite Park">Choose a Park</label>
	<form:select class="form-control" name="parkCode" path="parkCode">
	<form:option value="hidden">Please select a Park</form:option>
		<c:forEach var="park" items="${parks}">
			<form:option value="${park.parkCode}">${park.parkName}</form:option>
		</c:forEach>
	</form:select>
	</div>
	<button class=" btn btn-primary" type="submit">Submit</button>
</form:form>
</div>








<c:import url="/WEB-INF/jsp/common/footer.jsp" />