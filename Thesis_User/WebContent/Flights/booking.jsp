<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Booking</title>
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
	$(document).ready(function(){
			$(flights).css("background-color","#CD5C5C");
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

.navbar-default .navbar-nav>li>a {
	color: white;
	text-align: center;
}

.navbar-default .navbar-nav>li>a:hover {
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
	String company_name = request.getParameter("company_name");
	String airplane_id = request.getParameter("airplane_id");
	String flight_id = request.getParameter("flight_id");

	int adults_ticket = Integer.parseInt(request.getParameter("adults_ticket"));
	String childrens_ticket = request.getParameter("childrens_ticket");

	int kids_ticket = 0;

	if (childrens_ticket != null)
		kids_ticket = Integer.parseInt(childrens_ticket);
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
	
		<form class="form-horizontal" action="../../Thesis_User/FlightsThanksPage" method="post">
		
			<div class="form-group">
				<input class="form-control" style="border: none"  type="hidden" value="<%= company_name %>" name="company_name"/> 
				<input class="form-control" style="border: none"  type="hidden" value="<%= airplane_id %>" name="airplane_id"/> 
				<input class="form-control" style="border: none"  type="hidden" value="<%= flight_id %>" name="flight_id"/> 
				<input class="form-control" style="border: none"  type="hidden" value="<%= adults_ticket %>" name="adults_ticket"/>
				<input class="form-control" style="border: none"  type="hidden" value="<%= kids_ticket %>" name="kids_ticket"/>
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
				<button type="submit" class="btn btn-info" name="booknow_flight">Book Now</button>
			</div>
		</form>
	</div>

	<br><br>

</body>
</html>