<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ page import="java.io.IOException"%>
<%@	page import="java.io.PrintWriter"%>
<%@	page import="java.sql.Connection"%>
<%@	page import="java.sql.DriverManager"%>
<%@	page import="java.sql.ResultSet"%>
<%@	page import="java.sql.Statement"%>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSetMetaData"%>
<%@ page import="java.time.temporal.ChronoUnit"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="java.time.Duration"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.time.format.DateTimeFormatter"%>



<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rental Search</title>
<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Tangerine">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
</script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js">
</script>
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
		<form id="form" class="form-horizontal" action="RentalSearch.jsp" method="post" target="">
			
			<div class="form">

				<ul class="nav nav-tabs">
					<li><a data-toggle="tab" href="#cars">Cars</a></li>
					<li><a data-toggle="tab" href="#motors">Motor Bikes</a></li>
				</ul>

				<br>
				<br>
				<br>
				<br>
				<br>

				<div class="tab-content">			
					<div id="cars" class="tab-pane fade in active">
						<div class="form-group">
							<label class="control-label col-xs-12 col-sm-4 col-md-2" for="car_pickup">Pick-Up:</label>
							<div class="col-xs-12 col-sm-4 col-md-2">
								<input type="date" class="form-control" id="car_pickup" name="car_pickup">
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-xs-12 col-sm-4 col-md-2" for="car_dropoff">Drop-Off:</label>
							<div class="col-xs-12 col-sm-4 col-md-2">
								<input type="date" class="form-control" id="car_dropoff" name="car_dropoff">
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-xs-12 col-sm-4 col-md-2"
								for="car_cc">Car cc:</label>
							<div class="col-xs-12 col-sm-4 col-md-2">
								<select class="form-control" id="car_cc" name="car_cc">
									<option>1000</option>
									<option>1200</option>
									<option>1600</option>
									<option>1800</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-xs-12 col-sm-4 col-md-2"
								for="passengers">Passengers:</label>
							<div class="col-xs-12 col-sm-4 col-md-2">
								<select class="form-control" id="passengers" name="passengers">
									<option>2</option>
									<option>4</option>
									<option>5</option>
									<option>7</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-xs-12 col-sm-4 col-md-2"
								for="car_category">Category:</label>
							<div class="col-xs-12 col-sm-4 col-md-2">
								<select class="form-control" id="car_category" name="car_category">
									<option>A</option>
									<option>B</option>
									<option>C</option>
								</select>
							</div>
						</div>

						<div class="col-sm-offset-5 col-md-offset-2">
							<button type="submit" class="btn btn-info" name="car_search">Search</button>
						</div>
					</div>
					
					
					<div id="motors" class="tab-pane fade">
						<div class="form-group">
							<label class="control-label col-xs-12 col-sm-4 col-md-2" for="motor_pickup">Pick-Up:</label>
							<div class="col-xs-12 col-sm-4 col-md-2">
								<input type="date" class="form-control" id="motor_pickup" name="motor_pickup">
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-xs-12 col-sm-4 col-md-2" for="motor_dropoff">Drop-Off:</label>
							<div class="col-xs-12 col-sm-4 col-md-2">
								<input type="date" class="form-control" id="motor_dropoff" name="motor_dropoff">
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-xs-12 col-sm-4 col-md-2" for="motor_cc">Motor Bike cc:</label>
							<div class="col-xs-12 col-sm-4 col-md-2">
								<select class="form-control" id="motor_cc" name="motor_cc">
									<option>125</option>
									<option>200</option>
									<option>400</option>
									<option>600</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-xs-12 col-sm-4 col-md-2"
								for="motor_category">Category:</label>
							<div class="col-xs-12 col-sm-4 col-md-2">
								<select class="form-control" id="motor_category" name="motor_category">
									<option>A</option>
									<option>B</option>
									<option>C</option>
								</select>
							</div>
						</div>

						<div class="col-sm-offset-5 col-md-offset-2">
							<button type="submit" class="btn btn-info" name="motor_search">Search</button>
						</div>
					</div>
					

				</div>
			</div>

		</form>
		<br><br><br><br><br><br>
		</div>

	<%
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (Exception e) {
			e.printStackTrace();
		}

		String query = "";
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		ResultSetMetaData metaData = null;
		PreparedStatement ps = null;
		int columnCnt = 0;
		//String[] header = null;
		int k = 0;
		int rowCnt = 0;
		String[] rowValues = null;

		String car_cc = request.getParameter("car_cc");
		String passengers = request.getParameter("passengers");
		String car_category = request.getParameter("car_category");
		String motor_cc = request.getParameter("motor_cc");
		String motor_category = request.getParameter("motor_category");
		String car_pickup =request.getParameter("car_pickup");
		String motor_pickup = request.getParameter("motor_pickup");
		String car_dropoff = request.getParameter("car_dropoff");
		String motor_dropoff = request.getParameter("motor_dropoff");
		
		DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
		LocalDate date1 = null;
		LocalDate date2 = null;

		
		
	try{
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
		
		if(request.getParameter("car_search") != null){
			
			query = "select Cars.Rental_Office,Cars.Car_ID,Cars.Car_cc,Cars.Passengers,Cars.Category,Car_Charges.Charge from Cars inner join Car_Charges on Cars.Car_cc=? and Cars.Passengers=? " +
				"and Cars.Category=? and Car_Charges.Days=1 and Cars.Car_id=Car_Charges.Car_id and not exists (select Car_ID from car_bookings where Cars.Car_ID=car_bookings.Car_ID and car_bookings.pick_up>=? "+
			"and car_bookings.drop_off<=? )" ;
			
			ps = conn.prepareStatement(query);
			ps.setString(1, car_cc);
			ps.setString(2, passengers);
			ps.setString(3, car_category);
			ps.setDate(4 , java.sql.Date.valueOf(car_pickup));
			ps.setDate(5 , java.sql.Date.valueOf(car_dropoff));
			rs = ps.executeQuery();
			
			date1 = LocalDate.parse(car_pickup,dtf);
			date2 = LocalDate.parse(car_dropoff,dtf);
		
		}else{
			
			query = "select Motorcycles.Rental_Office,Motorcycles.Motorcycle_ID,Motorcycles.Motorcycle_cc,Motorcycles.Category,Motorcycle_Charges.Charge from Motorcycles inner join Motorcycle_Charges on "+
					"Motorcycles.Motorcycle_id=Motorcycle_Charges.Motorcycle_id and Motorcycles.Motorcycle_cc=? and Motorcycles.Category=? and Motorcycle_Charges.Days=1 and not exists "+
					"(select Motorcycle_ID from Motor_bookings where Motorcycles.Motorcycle_ID=Motor_bookings.Motorcycle_ID and Motor_bookings.pick_up>=? "+
					"and Motor_bookings.drop_off<=? )" ;
				
				ps = conn.prepareStatement(query);
				ps.setString(1, motor_cc);
				ps.setString(2, motor_category);
				ps.setDate(3 , java.sql.Date.valueOf(motor_pickup));
				ps.setDate(4 , java.sql.Date.valueOf(motor_dropoff));
				rs = ps.executeQuery();	
				
				date1 = LocalDate.parse(motor_pickup,dtf);
				date2 = LocalDate.parse(motor_dropoff,dtf);
				
		}
		
		Duration duration = Duration.between(date1.atStartOfDay(), date2.atStartOfDay());
		long days = duration.toDays();
		

		if(rs.isBeforeFirst()){
				
			metaData = rs.getMetaData();
			columnCnt = metaData.getColumnCount();
			
			while(rs.next()){
				
				long charge = rs.getInt("Charge") * days;
				rowCnt = rs.getRow(); 
		
				if(request.getParameter("car_search") != null){ %>
					<div class="container">
						<form id="form" class="form-horizontal" action="CarBooking.jsp" method="post" target="">
							<div class='form-group'>
							
							 	<input class="form-control" style="border: none"  type="hidden" value="<%= car_pickup %>" name="car_pickup"/>
								<input class="form-control" style="border: none"  type="hidden" value="<%= car_dropoff %>" name="car_dropoff"/>
								
								<div class="table-responsive">
									<table class='table table-bordered'>
					
										<%//if( k == 0){  %>
											
										<thead>
											<tr>
					
											<%	for(int i = 1; i <= columnCnt; i++){ 
													String header = "";
													
													if(i == 1){
														header = "Rental Office";
													}
													else if(i == 2){
														header = "Car ID";
													}
													else if(i == 3){
														header = "Car cc";
													}
													else if(i == 4){
														header = "Passengers";
													}
													else if(i == 5){
														header = "Car Category";
													}
													else if(i == 6){
														header = "Charge"; 
													}%>
													
												<th><%= header %></th>
												<%	} %>
					
											</tr>
										</thead>
											<% 	//k = 1; 
										//} %>
					
										<tbody>
											<tr>
								
												<% for(int i = 1; i <= columnCnt; i++){
													String name = "";
													
													if(i == 1){
														name = "rental_office";
													}
													else if(i == 2){
														name = "car_id";
													}
													else if(i == 3){
														name = "car_cc";
													}
													else if(i == 4){
														name = "passengers";
													}
													else if(i == 5){
														name = "car_category";
													}
													else if(i == 6){
														name = "charge"; 
													}
													
													if(i!=6){%>	
														<td height='100'><input class='form-control' style='border: none' type="hidden" value="<%=  rs.getString(i) %>" name="<%= name %>" /> <%= rs.getString(i) %></td>
													<%}else{ %>
														<td height='100'><input class='form-control' style='border: none' type="hidden" value="<%=  charge %>" name="<%= name %>" /> <%= charge %> &euro;</td>
													
											<% 		}
												}%>
																 
												<td height='100'><input type="submit" value="Book&nbsp;Now" /></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</form>
					</div>
		<%	}else{ %>
				<div class="container">
						<form id="form" class="form-horizontal" action="MotoBooking.jsp" method="post" target="">
							<div class='form-group'>
							
							 	<input class="form-control" style="border: none"  type="hidden" value="<%= motor_pickup %>" name="motor_pickup"/>
								<input class="form-control" style="border: none"  type="hidden" value="<%= motor_dropoff %>" name="motor_dropoff"/> 
								
								<div class="table-responsive">
									<table class='table table-bordered'>
					
										<%if( k == 0){  %>
											
										<thead>
											<tr>
					
											<%	for(int i = 1; i <= columnCnt; i++){ %>
					
												<th><%= metaData.getColumnName(i) %></th>
												<%	} %>
					
											</tr>
										</thead>
											<% 	k = 1; 
										} %>
					
										<tbody>
											<tr>
								
												<% for(int i = 1; i <= columnCnt; i++){
													String name = "";
									
													
													if(i == 1){
														name = "rental_office";
													}
													else if(i == 2){
														name = "motor_id";
													}
													else if(i == 3){
														name = "motor_cc";
													}
													else if(i == 4){
														name = "motor_category";
													} 
													else if(i == 5){
														name = "charge";
													}
					
													if(i!=5){%>	
														<td height='100'><input class='form-control' style='border: none' type="hidden" value="<%=  rs.getString(i) %>" name="<%= name %>" /> <%= rs.getString(i) %></td>
													<%}else{ %>
														<td height='100'><input class='form-control' style='border: none' type="hidden" value="<%=  charge %>" name="<%= name %>" /> <%= charge %> &euro;</td>
													
											<% 		}
												}%>
										 
												<td height='100'><input type="submit" value="Book&nbsp;Now" /></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</form>
				
					</div>
				<%} 
		
			 } 
			
			rs.close();
			ps.close();
			conn.close();
	}else{ %>
		<script> alert("No results for these values"); </script>

	<%
		}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stm != null)
					stm.close();
			} catch (SQLException se2) {

			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {

			}
		}
	%>



</body>
</html>