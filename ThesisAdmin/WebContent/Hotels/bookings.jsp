<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hotel Bookings</title>
<link rel="stylesheet" type="text/css" href="../stylesheet.css">
<script>
function activeTab(){
	document.getElementById("hotelsId").className = "topNavColor";
	document.getElementById("bookings").className = "navColor";
};
</script>
<script>
	function clickFunc(){
		document.getElementById("hotelBookingsBtn").click();
	}
</script>
<style>
	#hotelBookingsBtn {
		background-color: transparent;
		border: none;
	}
</style>
</head>
<body onload="activeTab(); clickFunc();">

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
			<li><a id="shipsId" href="../Ships/companiesForm.jsp" target="">Ships</a></li>
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

	<form action="/ThesisAdmin/DbConnect" method="post" target="manageFrame">
		<input type="submit" value="" id="hotelBookingsBtn" name="hotelBookingsBtn">
	</form>

	<br><br>
	<iframe src="" class="manageFrame" name="manageFrame">
	
	</iframe>
</body>
</html>