<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hotel Booking</title>
<link rel="stylesheet" type="text/css"
	href="http://fonts.googleapis.com/css?family=Tangerine">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function() {
		$(hotels).css("background-color", "#CD5C5C");
	});
</script>
<style>

.navbar {
	margin: 0;
	border-radius: 0;
}

.navbar-default {
	background-color: #20B2AA;
	border: none;
}

.navbar-default .navbar-nav > li> a {
	color: white;
	text-align: center;
}

.navbar-default .navbar-nav > li > a:hover {
	color: white;
	background-color: #CD5C5C;
	text-align: center;
	display: block;
}

.navbar-toggle {
	border: none;
}

.navbar-default .navbar-toggle:hover {
	background-color: #CD5C5C;
}

.navbar-default .navbar-toggle .icon-bar {
	background-color: #ffffff;
}

.navbar-default .navbar-collapse {
	border: none;
}

h1 {
	font-family: vollkorn;
}

@media ( max-width : 766px) {
	.btn {
		width: 100%;
	}
}
</style>
</head>
<body>

	<%
		String checkIn = request.getParameter("checkin");
		String checkOut = request.getParameter("checkout");
		String adults = request.getParameter("adults");
		String kids = request.getParameter("kids");
		String hotel = request.getParameter("hotel");
		String number = request.getParameter("number");
		String price = request.getParameter("price");
	%>

	<nav class="navbar navbar-default">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>                        
	      </button>
	    </div>
	    <div class="collapse navbar-collapse" id="myNavbar">
	      <ul class="nav navbar-nav">
			<li><a id="home" href="../index.html"><span class="glyphicon glyphicon-home"></span></a></li>
			<li><a id="hotels" href="../Hotels/hotels.html">Hotels</a></li>
			<li><a id="rentals" href="../RentalOffices/rentals.html">Rental Offices</a></li>
			<li><a id="museums" href="../Museums/museums.jsp">Museums</a></li>
			<li><a id="landmarks" href="../Landmarks/landmarks.jsp">Landmarks</a></li>
			<li><a id="flights" href="../Flights/flights.html">Flights</a></li>
			<li><a id="ships" href="../Ships/ships.html">Ships</a></li>
			<li><a id="excursions" href="../Excursions/excursions.jsp">Excursion Offices</a></li>
	      </ul>
	    </div>
	  </div>
	</nav>


	<div class="container">
		<br><br>
		<h4>Please fill your personal information</h4>
		<br>

		<form class="form-horizontal" action="../../Thesis_User/ThanksPage"	method="post">

			<div class="form-group">
				<input class="form-control" style="border: none" type="hidden"
					value="<%=checkIn%>" name="checkin" /> <input class="form-control"
					style="border: none" type="hidden" value="<%=checkOut%>"
					name="checkout" /> <input class="form-control" style="border: none"
					type="hidden" value="<%=adults%>" name="adults" /> <input
					class="form-control" style="border: none" type="hidden"
					value="<%=kids%>" name="kids" /> <input class="form-control"
					style="border: none" type="hidden" value="<%=hotel%>" name="hotel" />
				<input class="form-control" style="border: none" type="hidden"
					value="<%=number%>" name="number" /> <input class="form-control"
					style="border: none" type="hidden" value="<%=price%>" name="price" />
			</div>


			<div class="form-group">
				<label class="col-xs-12 col-sm-4 col-md-1" for="name">Name</label>
				<div class="col-xs-12 col-sm-4 col-md-2">
					<input type=text class="form-control input-sm" id="name"
						name="name">
				</div>
			</div>

			<div class="form-group">
				<label class="col-xs-12 col-sm-4 col-md-1" for="surname">Surname</label>
				<div class="col-xs-12 col-sm-4 col-md-2">
					<input type="text" class="form-control input-sm" id="surname"
						name="surname">
				</div>
			</div>

			<div class="form-group">
				<label class="col-xs-12 col-sm-4 col-md-1" for="phone">Phone</label>
				<div class="col-xs-12 col-sm-4 col-md-2">
					<input type="text" class="form-control input-sm" id="phone"
						name="phone">
				</div>
			</div>

			<div class="form-group">
				<label class="col-xs-12 col-sm-4 col-md-1" for="email">Email</label>
				<div class="col-xs-12 col-sm-4 col-md-2">
					<input type="text" class="form-control input-sm" id="email"
						name="email">
				</div>
			</div>

			<div class="col-sm-offset-5 col-md-offset-1">
				<button type="submit" class="btn btn-info" name="booknow">Book Now</button>
			</div>
		</form>
	</div>

	<br>
	<br>

</body>
</html>