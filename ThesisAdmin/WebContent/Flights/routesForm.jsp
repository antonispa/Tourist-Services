<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Flight Routes</title>
<link rel="stylesheet" type="text/css" href="../stylesheet.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
</script>
<script>
	$(document).ready(function(){
			$(flightsId).addClass("topNavColor");
			$(routes).addClass("navColor");
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
			<li><a id="hotelsId" href="../Hotels/hotelsForm.jsp" target="_self">Hotels</a></li>
			<li><a id="rentalsId" href="../RentalOffices/rentalOfficesForm.jsp" target="_self">Rentals</a></li>
			<li><a id="museumsId" href="../Museums/museumsForm.jsp" target="_self">Museums</a></li>
			<li><a id="landmarksId" href="../Landmarks/landmarksForm.jsp" target="_self">Landmarks</a></li>
			<li><a id="flightsId" href="companiesForm.jsp" target="_self">Flights</a></li>
			<li><a id="shipsId" href="../Ships/companiesForm.jsp" target="_self">Ships</a></li>
			<li><a id="excursionsId" href="../Excursions/excOfficesForm.jsp" target="_self">Excursions</a></li>

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
				Flight ID: <br> <input type="text" name="flightID"></input><br><br>
				Departure: <br> <input type="text" name="departure"></input><br><br>
				Departure Date: <br> <input type="date" name="departureDate"></input><br><br>
				Departure Time: <br> <input type="time" name="departureTime"></input><br><br>
				Arrival: <br> <input type="text" name="arrival"></input><br><br>
				Arrival Date: <br> <input type="date" name="arrivalDate"></input><br><br>
				Arrival Time: <br> <input type="time" name="arrivalTime"></input><br><br>
				Adult's Ticket: <br> <input type="number" name="adultsTicket"></input><br><br>
				Children's Ticket: <br> <input type="number" name="childrensTicket"></input><br><br>
				<input type="submit" value="insert" name="flightRouteButton">
			</fieldset>
		</form>
	</div>
	<br><br>
	<iframe src="flightRoutesManage.html" class="contentFrame" name="contentFrame">
		
	</iframe>

</body>
</html>

