<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Excursion Bookings</title>
<link rel="stylesheet" type="text/css" href="../stylesheet.css">
<script>
function activeTab(){
	document.getElementById("excursionsId").className = "topNavColor";
	document.getElementById("bookings").className = "navColor";
};
</script>
<script>
	function clickFunc(){
		document.getElementById("excursionBookingsBtn").click();
	}
</script>
<style>
	#excursionBookingsBtn {
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
			<li><a id="hotelsId" href="../Hotels/hotelsForm.jsp" target="">Hotels</a></li>
			<li><a id="rentalsId" href="../RentalOffices/rentalOfficesForm.jsp" target="">Rentals</a></li>
			<li><a id="museumsId" href="../Museums/museumsForm.jsp" target="">Museums</a></li>
			<li><a id="landmarksId" href="../Landmarks/landmarksForm.jsp" target="">Landmarks</a></li>
			<li><a id="flightsId" href="../Flights/companiesForm.jsp" target="">Flights</a></li>
			<li><a id="shipsId" href="../Ships/companiesForm.jsp" target="">Ships</a></li>
			<li><a id="excursionsId" href="excOfficesForm.jsp" target="">Excursions</a></li>

			<li style="float: right; padding: 5px 5px 0 0;">
				<form action="../../ThesisAdmin/Logout" method="post">
					<input type="submit" value="logout">
				</form>
			</li>
		</ul>
	</nav>

	<nav class="sideNav">
		<ul>
			<li><a id="excOffices" href="excOfficesForm.jsp" target="">Excursion Offices</a></li>
			<li><a id="excursions" href="excursionsForm.jsp" target="">Excursions</a></li>
			<li><a id="bookings" href="bookings.jsp" target="">Bookings</a></li>
		</ul>	
	</nav>	

	<form action="/ThesisAdmin/DbConnect" method="post" target="manageFrame">
		<input type="submit" value="" id="excursionBookingsBtn" name="excursionBookingsBtn">
	</form>

	<br><br>
	<iframe src="" class="manageFrame" name="manageFrame">
	
	</iframe>
</body>
</html>