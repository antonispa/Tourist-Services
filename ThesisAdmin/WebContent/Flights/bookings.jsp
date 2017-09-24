<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Flight Bookings</title>
<link rel="stylesheet" type="text/css" href="../stylesheet.css">
<script>
function activeTab(){
	document.getElementById("flightsId").className = "topNavColor";
	document.getElementById("bookings").className = "navColor";
};
</script>
<script>
	function clickFunc(){
		document.getElementById("flightBookingsBtn").click();
	}
</script>
<style>
	#flightBookingsBtn {
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
			<li><a id="hotelsId" href="hotelsForm.jsp" target="_self">Hotels</a></li>
			<li><a id="rentalsId" href="../RentalOffices/rentalOfficesForm.jsp" target="_self">Rentals</a></li>
			<li><a id="museumsId" href="../Museums/museumsForm.jsp" target="_self">Museums</a></li>
			<li><a id="landmarksId" href="../Landmarks/landmarksForm.jsp" target="_self">Landmarks</a></li>
			<li><a id="flightsId" href="../Flights/companiesForm.jsp" target="_self">Flights</a></li>
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
	</nav>	av>

	<form action="/ThesisAdmin/DbConnect" method="post" target="manageFrame">
		<input type="submit" value="" id="flightBookingsBtn" name="flightBookingsBtn">
	</form>

	<br><br>
	<iframe src="" class="manageFrame" name="manageFrame">
	
	</iframe>
</body>
</html>