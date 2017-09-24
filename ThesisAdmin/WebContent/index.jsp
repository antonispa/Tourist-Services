<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Administrator</title>
<link rel="stylesheet" type="text/css" href="stylesheet.css">

</head>
<body style="background-color: #F5F5F5">
	
	
	<% 
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
	
		if (session.getAttribute("user") == null){
		response.sendRedirect("login.jsp");
	}%>
	
	<nav class="topNav">
		<ul>
			<li><a id="hotelsId" href="Hotels/hotelsForm.jsp" target="">Hotels</a></li>
			<li><a id="rentalsId" href="RentalOffices/rentalOfficesForm.jsp" target="">Rentals</a></li>
			<li><a id="museumsId" href="Museums/museumsForm.jsp" target="">Museums</a></li>
			<li><a id="landmarksId" href="Landmarks/landmarksForm.jsp" target="">Landmarks</a></li>
			<li><a id="flightsId" href="Flights/companiesForm.jsp" target="">Flights</a></li>
			<li><a id="shipsId" href="Ships/companiesForm.jsp" target="">Ships</a></li>
			<li><a id="excursionsId" href="Excursions/excOfficesForm.jsp" target="">Excursions</a></li>

			<li style="float: right; padding:5px 5px 0 0;">
				<form action="../ThesisAdmin/Logout" method="post">
					<input type="submit" value="logout">
				</form>
			</li>
		</ul>
	</nav>

</body>
</html>