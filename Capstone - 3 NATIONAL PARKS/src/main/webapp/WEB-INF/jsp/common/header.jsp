<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
<meta charset="ISO-8859-1">
<title>National Park Geek</title>
<c:url value="/css/nationalParks.css" var="cssHref"/>
<link rel="stylesheet" href= "${cssHref}"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>

<body>
<header class = "container-fluid">
<c:url value="/img/logo.png" var="logoSource"/>
<a href="<c:url value="/home"/>">
<img src ="${logoSource }"/>
</a>

<ul>
<li><a href="<c:url value="/home"/>">Home</a></li>
<li><a href="<c:url value="/parkSurvey"/>">Survey</a></li>
</ul>

</header>