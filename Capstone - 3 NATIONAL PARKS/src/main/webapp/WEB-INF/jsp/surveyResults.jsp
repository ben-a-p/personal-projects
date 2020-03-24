<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp"/>

<div class="homePageList">
	<c:forEach items="${parks}" var="park">
		<div class = "homePageParkBox">
			<c:url value="/parkDetails" var="parkDetailsHref">
				<c:param name="code" value="${park.parkCode}"/>
			</c:url>
			<c:url value="/img/parks/${park.parkCode.toLowerCase()}.jpg" var="parkImage"/>
			<a href="${parkDetailsHref}">
			<img src = "${parkImage}"/>
			</a>
			<div id = "homeParkText">
				<h2>${park.parkName} - ${park.state}</h2>
				<h3>${park.numberOfVotes} Votes</h3>
				<p>${park.parkDescription}</p>
			</div>
		</div>
	</c:forEach>
</div>









<c:import url="/WEB-INF/jsp/common/footer.jsp" />