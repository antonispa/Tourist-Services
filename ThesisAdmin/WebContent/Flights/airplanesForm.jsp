<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Airplanes</title>
<link rel="stylesheet" type="text/css" href="../stylesheet.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
</script>
<script>
	$(document).ready(function(){
			$(flightsId).addClass("topNavColor");
			$(airplanes).addClass("navColor");
	});
</script>
</head>
<body>

	<% 
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
	
		if (session.getAttribute("user") == null){
		response.sendRedirect("../login.jsp");
	}%>

	<nav class="topNav">
		<ul>
			<li><a id="hotelsId" href="../Hotels/hotelsForm.jsp" target="">Hotels</a></li>
			<li><a id="rentalsId" href="../RentalOffices/rentalOfficesForm.jsp" target="">Rentals</a></li>
			<li><a id="museumsId" href="../Museums/museumsForm.jsp" target="">Museums</a></li>
			<li><a id="landmarksId" href="../Landmarks/landmarksForm.jsp" target="">Landmarks</a></li>
			<li><a id="flightsId" href="companiesForm.jsp" target="">Flights</a></li>
			<li><a id="shipsId" href="../Ships/companiesForm.jsp" target="">Ships</a></li>
			<li><a id="excursionsId" href="../Excursions/excOfficesForm.jsp" target="">Excursions</a></li>
			
			<li style="float: right; padding: 5px 5px 0 0;">
				<form action="../../ThesisAdmin/Logout" method="post">
					<input type="submit" value="logout">
				</form>
			</li>
		</ul>
	</nav>
	
	<nav class="sideNav">
		<ul>
			<li><a id="companies" href="companiesForm.jsp" target="">Companies</a></li>
			<li><a id="airplanes" href="airplanesForm.jsp" target="">Airplanes</a></li>
			<li><a id="routes" href="routesForm.jsp" target="">Routes</a></li>
			<li><a id="bookings" href="bookings.jsp" target="">Bookings</a></li>
		</ul>	
	</nav>	
	
	<div class="contentFrame">
		<form action="/ThesisAdmin/DbConnect" method="post">
			<fieldset style="height: auto">
			<br>
				Company Name: <br> <input type="text" name="companyName"></input><br><br>
				Airplane ID: <br> <input type="text" name="airplaneID"></input><br><br>
				<input type="submit" value="insert" name="airplaneButton">
			</fieldset>
		</form>
	</div>
	<br><br>
	<iframe src="airplaneMan.html" class="contentFrame" name="contentFrame">
		
	</iframe>

</body>
</html>