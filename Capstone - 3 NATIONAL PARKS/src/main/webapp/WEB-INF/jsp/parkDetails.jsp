<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div id="detailGrid" class = "container-fluid">
	<c:url value="/img/parks/${park.parkCode.toLowerCase()}.jpg"
		var="parkImage" />
	<img class="detailImg" src="${parkImage}" />
	<div class = "detailInformation">
		<h2>${park.parkName},${park.state}(Founded in
			${park.yearFounded})</h2>
		<h3>Entry Fee: $ ${park.entryFee}</h3>
		<ul>
			<li>Acreage: ${park.acreage}</li>
			<li>Elevation (feet): ${park.elevation}</li>
			<li>Trail Length (miles): ${park.milesOfTrail}</li>
			<li>Number of Campsites: ${park.numberOfCampsites}</li>
			<li>Annual Visitors: ${park.annualVisitorCount}</li>
			<li>Unique Animal Species: ${park.numberOfAnimalSpecies}</li>
		</ul>
		<p>${park.inspirationalQuote}-${park.quoteSource}</p>
		<p>${park.parkDescription}</p>

	</div>


	<div class = "detailWeather">
		<h3>${park.climate}</h3>

		<c:url value="/parkDetails" var="temperatureButton">
			<c:param name="code" value="${park.parkCode}" />
		</c:url>

		<form action="${temperatureButton}" method="POST">
			<label>Select Temperature Preference</label>
			<select name="temperature">
				<option>farenheit</option>
				<option>celcius</option>
			</select>
			<button type="submit">Submit</button>
		</form>

		<div id="weatherBox">
			<c:forEach items="${dailyWeather}" var="weather" end="4">
				<div class="weatherItem">
					<c:url value="${weather.imageUrl}" var="weatherImg" />
					<img src="${weatherImg}" />
					<c:choose>
						<c:when test="${temperature == 'celcius'}">
							<p>High of: ${weather.highCelcius} Low of:
								${weather.lowCelcius}</p>
						</c:when>
						<c:otherwise>
							<p>High of: ${weather.high} Low of: ${weather.low}</p>
						</c:otherwise>
					</c:choose>
					<p>${weather.tempMessage}</p>
					<p>${weather.forecastMessage}</p>

				</div>
			</c:forEach>
		</div>
	</div>







</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />