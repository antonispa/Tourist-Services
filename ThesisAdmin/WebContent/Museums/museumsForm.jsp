<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Museum's Form</title>
<link rel="stylesheet" type="text/css" href="../stylesheet.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
</script>
<script>
	$(document).ready(function(){
			$(museumsId).addClass("topNavColor");
			$(museums).addClass("navColor");
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
			<li><a id="museumsId" href="museumsForm.jsp" target="">Museums</a></li>
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
			<li><a id="museums" href="museumsForm.jsp" target="">Museums</a></li>
		</ul>	
	</nav>				
	
	<div class="contentFrame">
		<form action="/ThesisAdmin/DbConnect" method="post">
			<fieldset style="height: auto">
				<br>
				Name: <br> <input type="text" name="museumName"><br><br>
				Address: <br> <input type="text" name="museumAddress"><br><br>
				Phone: <br> <input type="text" name="museumPhone"><br><br>
				Adult's ticket: <br> <input type="text" name="adultsTicket"><br><br>
				Children's ticket : <br> <input type="text" name="childrensTicket"><br><br> 
				Opening Time: <br> <input type="text" name="openingTime"><br><br>
				Closing Time: <br> <input type="text" name="closingTime"><br><br> 
				
				<input type="submit" value="insert" name="museumButton">
			</fieldset>
		</form>
	</div>

	<br><br>
	<iframe src="museumManagement.html" class="contentFrame" name="contentFrame">
		
	</iframe>
</body>
</html>