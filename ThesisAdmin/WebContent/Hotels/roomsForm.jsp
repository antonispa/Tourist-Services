<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Room's Form</title>
<link rel="stylesheet" type="text/css" href="../stylesheet.css">
<script>
function activeTab(){
	document.getElementById("hotelsId").className = "topNavColor";
	document.getElementById("rooms").className = "navColor";
};
</script>
</head>
<body onload="activeTab();">

	<% 
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
	
		if (session.getAttribute("user") == null){
		response.sendRedirect("../login.jsp");
	}%>

	<nav class="topNav">
		<ul>
			<li><a id="hotelsId" href="hotelsForm.jsp" target="">Hotels</a></li>
			<li><a id="rentalsId" href="../RentalOffices/rentalOfficesForm.jsp" target="">Rentals</a></li>
			<li><a id="museumsId" href="../Museums/museumsForm.jsp" target="">Museums</a></li>
			<li><a id="landmarksId" href="../Landmarks/landmarksForm.jsp" target="">Landmarks</a></li>
			<li><a id="flightsId" href="../Flights/companiesForm.jsp" target="">Flights</a></li>
			<li><a id="shipsId" href="../Ships/shipsForm.jsp" target="">Ships</a></li>
			<li><a id="excursionsId" href="../Excursions/excOfficesForm.jsp" target="">Excursions</a></li>

			<li style="float: right; padding:5px 5px 0 0;">
				<form action="../../ThesisAdmin/Logout" method="post">
					<input type="submit" value="logout">
				</form>
			</li>
		</ul>
	</nav>

	<nav class="sideNav">
		<ul>
			<li><a id="hotels" href="hotelsForm.jsp" target="">Hotels</a></li>
			<li><a id="rooms" href="roomsForm.jsp" target="">Rooms</a></li>
			<li><a id="services" href="serviceForm.jsp" target="">Room Services</a></li>
			<li><a id="bookings" href="bookings.jsp" target="">Bookings</a></li>
		</ul>	
	</nav>
	
	<div class="contentFrame">
		<form action="/ThesisAdmin/DbConnect" method="post">
			<fieldset style="height: auto">
				<br>
				
				Hotel Name: <br> <input type="text" name="roomHotelName"><br><br>
				Room Number: <br> <input type="text" name="roomNumber"><br><br>
				Room Type: <br> <input type="text" name="roomType"><br><br>
				Room Price: <br> <input type="text" name="roomPrice"><br><br>
	
				<input type="submit" value="insert" name="roomButton">
			</fieldset>
		</form>
	</div>
	<br><br>
	
	<iframe src="roomManage.html" class="contentFrame" name="manageFrame">
	
	</iframe>
</body>
</html>