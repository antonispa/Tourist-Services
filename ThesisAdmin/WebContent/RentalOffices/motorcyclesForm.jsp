<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Motorcycles</title>
<link rel="stylesheet" type="text/css" href="../stylesheet.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
</script>
<script type="text/javascript">
	$(document).ready(function(){
			$(rentalsId).addClass("topNavColor");
			$(motorcycles).addClass("navColor");			
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
			<li><a id="rentalsId" href="rentalOfficesForm.jsp" target="">Rentals</a></li>
			<li><a id="museumsId" href="../Museums/museumsForm.jsp" target="">Museums</a></li>
			<li><a id="landmarksId" href="../Landmarks/landmarksForm.jsp" target="">Landmarks</a></li>
			<li><a id="flightsId" href="../Flights/companiesForm.jsp" target="">Flights</a></li>
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
			<li><a id="rentalOffices" href="rentalOfficesForm.jsp" target="">Rental Offices</a></li>
			<li><a id="cars" href="carsForm.jsp" target="">Cars</a></li>
			<li><a id="carCharges" href="carCharges.jsp" target="">Car Charges</a></li>
			<li><a id="motorcycles" href="motorcyclesForm.jsp" target="">Motorcycles</a></li>
			<li><a id="motorcycleCharges" href="motorcycleCharges.jsp" target="">Motorcycle Charges</a></li>
			<li><a id="carbookings" href="carbookings.jsp" target="">Car Bookings</a></li>
			<li><a id="motorbookings" href="motorbookings.jsp" target="">Motorcycle Bookings</a></li>
		</ul>	
	</nav>
	
	<div class="contentFrame">
		<form action="/ThesisAdmin/DbConnect" method="post">
		<fieldset style="height: auto">
		<br>
			Rental Office:<br> <input type="text" name="rentalOfficeNameMotor"><br><br>
			Motorcycle id: <br><input type="text" name="motorcycleId"><br><br>
			Motorcycle cc: <br><input type="text" name="motorcycleCc"><br><br>
			Category: <br><input type="text" name="motorcycleCategory"><br><br>
			<input type="submit" value="insert" name="motorcycleButton">
		</fieldset>
	</form>
	</div>
	<br><br>
	<iframe src="motorManagement.html" class="contentFrame">
	
	</iframe>
</body>
</html>