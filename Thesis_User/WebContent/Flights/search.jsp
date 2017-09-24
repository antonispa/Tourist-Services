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
<title>Search</title>
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
			<form id="form" class="form-horizontal" action="search.jsp" method="post">
				<h1> Choose a flight and travel now! </h1>
				<br><br><br>
		
				<div class="form">
			
				<br><br>  
					<div class="form-group">
						<label class="control-label col-xs-12 col-sm-4 col-md-2"  for="departure">Departure:</label>
						<div class="col-xs-12 col-sm-4 col-md-2">
							<input type="date" class="form-control" id="departure" name="departure">
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-xs-12 col-sm-4 col-md-2" for="adults">Adults:</label>
						<div class="col-xs-12 col-sm-4 col-md-2">
							<select class="form-control" id="adults" name="adults">
								<option>1</option>
								<option>2</option>
								<option>3</option>
								<option>4</option>
							</select>
						</div>
					</div>
	
					<div class="form-group">
						<label class="control-label col-xs-12 col-sm-4 col-md-2" for="kids">Kids:</label>
						<div class="col-xs-12 col-sm-4 col-md-2">
							<select class="form-control" id="kids" name="kids">
								<option>0</option>
								<option>1</option>
								<option>2</option>
								<option>3</option>
							</select>
						</div>
					</div>
	
					<div class="col-sm-offset-5 col-md-offset-2">
						<button type="submit" class="btn btn-info" name="flightSearch">Search</button>
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
		int k = 0;
		int rowCnt = 0;
		String[] rowValues = null;

		String departure = request.getParameter("departure");
		String arrival = request.getParameter("arrival");
		int adults = Integer.parseInt(request.getParameter("adults"));
		String kid = request.getParameter("kids");

		int kids = 0;

		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "root", "root");

			query = "select * from flight_routes where departure_date=?";

			ps = conn.prepareStatement(query);
			ps.setString(1, departure);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {

				metaData = rs.getMetaData();
				columnCnt = metaData.getColumnCount();

				while (rs.next()) {

					int adultsTicket = adults * rs.getInt("Adults_Ticket");
					int kidsTicket = 0;

					if (kid != null) {
						kids = Integer.parseInt(kid);
						kidsTicket = kids * rs.getInt("Childrens_Ticket");
					}

					rowCnt = rs.getRow();
			%>

					<div class="container">
						<form id="form" class="form-horizontal" action="booking.jsp" method="post">
							<div class='form-group'>
							
							 	<input class="form-control" style="border: none"  type="hidden" value="<%= departure %>" name="departure"/>
								<input class="form-control" style="border: none"  type="hidden" value="<%= arrival %>" name="arrival"/>
								
								<div class="table-responsive">
									<table class='table table-bordered'>
					
										<%if( k == 0){  %>
											
										<thead>
											<tr>
					
											<%	for(int i = 1; i <= columnCnt; i++){
												
													if(i == 11)
														if(kids == 0)
															continue;
												/*	String header = "";
													
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
													}*/ %> 
													
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
														name = "company_name";
													}
													else if(i == 2){
														name = "airplane_id";
													}
													else if(i == 3){
														name = "flight_id";
													}
													else if(i == 4){
														name = "departure";
													}
													else if(i == 5){
														name = "departure_date";
													}
													else if(i == 6){
														name = "departure_time"; 
													} 
													else if(i == 7){
														name = "arrival";
													}
													else if(i == 8){
														name = "arrival_date";
													}
													else if(i == 9){
														name = "arrival_time";
													}
													else if(i == 10){
														name = "adults_ticket";
													}
													else if(i == 11){
														if(kids == 0)
															continue;
														name = "childrens_ticket";
													}
													
													if(i != 10 && i != 11) {%>
														<td height='100'><input class='form-control' style='border: none' type="hidden" value="<%=  rs.getString(i) %>" name="<%= name %>" /> <%= rs.getString(i) %></td>
												 <% }else if(i == 10) { %>
														<td height='100'><input class='form-control' style='border: none' type="hidden" value="<%=  rs.getString(i) %>" name="<%= name %>" /> <%= adultsTicket %> &euro;</td>
												 <% }else if(i == 11)  { %>
														<td height='100'><input class='form-control' style='border: none' type="hidden" value="<%=  rs.getString(i) %>" name="<%= name %>" /> <%= kidsTicket %> &euro;</td>	
												<% 	}
												}%>
												
												<td height='100'><input type="submit" value="Book&nbsp;Now" /></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</form>
					</div>
 
		<%
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