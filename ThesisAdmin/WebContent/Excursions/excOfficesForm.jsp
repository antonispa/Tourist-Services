<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Excursion Offices Form</title>
<link rel="stylesheet" type="text/css" href="../stylesheet.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
</script>
<script>
	$(document).ready(function(){
		$(excursionsId).addClass("topNavColor");
		$(excOffices).addClass("navColor");
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
	
	<div class="contentFrame">
		<form action="/ThesisAdmin/DbConnect" method="post">
			<fieldset style="height: auto;">
				<br>
				Excursion Office: <br> <input type="text" name="excOffice"></input><br><br>
				Address: <br> <input type="text" name="excOfficeAddress"></input><br><br>
				Phone: <br> <input type="text" name="excOfficePhone"></input><br><br>
				Email: <br> <input type="text" name="excOfficeEmail"></input><br><br>
				<input type="submit" value="insert" name="excOfficeBtn">
			</fieldset>
		</form>
		<br><br><br>
	</div>		
	
	<iframe src="excOfficesManage.html" class="contentFrame" name="contentFrame">
		 
	</iframe>
</body>
</html>
