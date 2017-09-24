<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Booking</title>
<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Tangerine">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script>
	$(document).ready(function(){
			$(rentals).css("background-color","#CD5C5C");
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
String rentalOffice = request.getParameter("rental_office");
String car_cc = request.getParameter("car_cc");
String car_id = request.getParameter("car_id");
String moto_cc = request.getParameter("moto_cc");
String car_pickup =request.getParameter("car_pickup");
String motor_pickup =request.getParameter("motor_pickup");
String car_dropoff =request.getParameter("car_dropoff");
String motor_dropoff =request.getParameter("motor_dropoff");
long charge = Integer.parseInt(request.getParameter("charge"));
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
		<h4> Please fill your personal information</h4><br>
	
		<form class="form-horizontal" action="../RentalsThanksPage" method="post">
		
			<div class="form-group">
				<input class="form-control" style="border: none"  type="hidden" value="<%= rentalOffice %>" name="rental_office"/>
				<input class="form-control" style="border: none"  type="hidden" value="<%= car_pickup %>" name="car_pickup"/> 
				<input class="form-control" style="border: none"  type="hidden" value="<%= car_dropoff %>" name="car_dropoff"/> 
				<input class="form-control" style="border: none"  type="hidden" value="<%= car_id %>" name="car_id"/>
				<input class="form-control" style="border: none"  type="hidden" value="<%= charge %>" name="charge"/>
			</div>

				
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-4 col-md-2" for="name">Name</label> 
				<div class="col-xs-12 col-sm-4 col-md-2">
					<input type=text class="form-control input-sm" id="name" name="name">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-4 col-md-2" for="surname">Surname</label> 
				<div class="col-xs-12 col-sm-4 col-md-2">
					<input type="text" class="form-control input-sm" id="surname" name="surname">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-4 col-md-2" for="phone">Phone</label> 
				<div class="col-xs-12 col-sm-4 col-md-2">
					<input type="text" class="form-control input-sm" id="phone" name="phone">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-4 col-md-2" for="email">Email</label> 
				<div class="col-xs-12 col-sm-4 col-md-2">
					<input type="text" class="form-control input-sm" id="email" name="email">
				</div>
			</div>
			
			<div class="col-sm-offset-5 col-md-offset-2">
				<button type="submit" class="btn btn-info" name="booknow_car">Book Now</button>
			</div>
		</form>
	</div>

	<br><br>

</body>
</html>