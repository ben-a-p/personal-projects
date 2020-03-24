<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<div class="homePageList container-fluid">
	<c:forEach items="${parks}" var="park">
		<div class = "homePageParkBox container-fluid" >
			<c:url value="/parkDetails" var="parkDetailsHref">
				<c:param name="code" value="${park.parkCode}"/>
				<c:param name="lat" value="${park.latitude }"/>
				<c:param name="lon" value="${park.longitude}"/>
			</c:url>
			<c:url value="/img/parks/${park.parkCode.toLowerCase()}.jpg" var="parkImage"/>
			<a href="${parkDetailsHref}">
			<img class ="col-xs-6"src = "${parkImage}"/>
			</a>
			<div id = "homeParkText">
				<h2>
				${park.parkName} - ${park.state}
				</h2>
				<p>
				${park.parkDescription}
				</p>
			</div>
		</div>
	</c:forEach>
</div>








<c:import url="/WEB-INF/jsp/common/footer.jsp"/>