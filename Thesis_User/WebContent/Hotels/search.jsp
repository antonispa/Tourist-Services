<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.io.IOException" %>
<%@	page import="java.io.PrintWriter" %>
<%@	page import="java.sql.Connection" %>
<%@	page import="java.sql.DriverManager" %>
<%@	page import="java.sql.ResultSet"%>
<%@	page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSetMetaData" %>
<%@	page import="java.util.Date" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="java.time.Duration"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Room Search</title>
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
		$(hotels).css("background-color","#CD5C5C");
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
		<form id="form" class="form-horizontal" action="search.jsp" method="post" target="">
			<h1> Find a place to stay between many exclusive hotels! </h1>

			
			<br><br><br><br>
			  
				<div class="form-group">
					<label class="control-label col-xs-12 col-sm-4 col-md-2"  for="checkin">Check In:</label>
					<div class="col-xs-12 col-sm-4 col-md-2">
						<input type="date" class="form-control" id="checkin" name="checkin">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-12 col-sm-4 col-md-2" for="checkout">Check Out:</label>
					<div class="col-xs-12 col-sm-4 col-md-2">
						<input type="date" class="form-control" id="checkout" name="checkout">
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
				
				<div class="form-group">
					<label class="control-label col-xs-12 col-sm-4 col-md-2" for="category">Category:</label>
					<div class="col-xs-12 col-sm-4 col-md-2">
						<select class="form-control" id="category" name="category">
							<option>A</option>
							<option>B</option>
							<option>C</option>
							<option>D</option>
						</select>
					</div>
				</div>

				<div class="col-sm-offset-5 col-md-offset-2">
					<button type="submit" class="btn btn-info" name="hotelSearch">Search</button>
				</div>	

		</form>
		<br><br><br><br><br><br>
		</div>

	<%
		String checkIn = request.getParameter("checkin");
		String checkOut = request.getParameter("checkout");
		String adults = request.getParameter("adults");
		String kids = request.getParameter("kids");
		String rooms = request.getParameter("rooms");
		String category = request.getParameter("category");
		
		DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
		LocalDate date1 = null;
		LocalDate date2 = null;
		
		if((checkIn != "" ) && (checkOut != "")){
			date1 = LocalDate.parse(checkIn,dtf);
			date2 = LocalDate.parse(checkOut,dtf);


	

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
			int columnCnt = 0;
			String[] header = null;
			int k = 0;
			int rowCnt = 0;
			String[] rowValues = null;
			String roomType = "";
			PreparedStatement ps = null;
			int result = 0;
	
			
			
			//set room type 
			
			int persons = Integer.parseInt(adults) + Integer.parseInt(kids);
	
			if (persons == 1)
				roomType = "Single";
			else if (persons == 2)
				roomType = "Double";
			else if (persons == 3)
				roomType = "Triple";
	
		try{
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","root","root");
	
			query = "select Rooms.Hotel_Name,Rooms.Room_Number,Rooms.Type,RoomServices.Services,Rooms.Price from Rooms inner join RoomServices on Rooms.Hotel_name=RoomServices.Hotel_Name and " +
					"RoomServices.Room_Number=Rooms.Room_Number and Rooms.Type=? "+
					"and not exists (select Room_Number from hotel_bookings where Rooms.Room_Number=hotel_bookings.Room_Number and hotel_bookings.Check_in>=? and hotel_bookings.Check_out<=?)";
	
			ps = conn.prepareStatement(query);
			ps.setString(1, roomType);
			ps.setDate(2 , java.sql.Date.valueOf(checkIn));
			ps.setDate(3 , java.sql.Date.valueOf(checkOut));
			rs = ps.executeQuery();
	
			Duration duration = Duration.between(date1.atStartOfDay(), date2.atStartOfDay());
			long days = duration.toDays();
			
			if(rs.isBeforeFirst()){
				
				metaData = rs.getMetaData();
				columnCnt = metaData.getColumnCount();
				
				
				while(rs.next()){
					rowCnt = rs.getRow(); 
					
					//calculate charge 
					
					long price = Integer.parseInt(rs.getString("Price")) * days;		
	
					%>
		
		
			<div class="container">
				<form id="hotelsForm" class="form-horizontal" action="booking.jsp" method="post" target="_self">
					<div class='form-group'>
					
						<input class="form-control" style="border: none"  type="hidden" value="<%= checkIn %>" name="checkin"/>
						<input class="form-control" style="border: none"  type="hidden" value="<%= checkOut %>" name="checkout"/> 
						<input class="form-control" style="border: none"  type="hidden" value="<%= adults %>" name="adults"/> 
						<input class="form-control" style="border: none"  type="hidden" value="<%= kids %>" name="kids"/> 
						
						<div class="table-responsive">
						<table class='table table-bordered'>
		
								<%//if( k == 0){  %>
								
								<thead>
									<tr>
		
									<%	for(int i = 1; i <= columnCnt; i++){ %>
		
									<th><%= metaData.getColumnName(i) %></th>
									<%	} %>
		
								</tr>
							</thead>
								<% //	k = 1; 
							//} %>
		
							<tbody>
								<tr>
					
									<% for(int i = 1; i <= columnCnt; i++){
										String name = "";
						
										
										if(i == 1){
											name = "hotel";
										}
										else if(i == 2){
											name = "number";
										}
										else if(i == 3){
											name = "type";
										}
										else if(i == 4){
											name = "services";
										}
										else if(i == 5){
											name = "price";
										}
									
									if(i == 5) { %>
										<td height='100' class="col-xs-12 col-md-2"><input class='form-control' style='border: none' type='hidden' value="<%=price%>" name="<%=name%>" /> <%= price  %> &euro;</td>
								<%	 } else { %>
										<td height='100' class="col-xs-12 col-md-2"><input class='form-control' style='border: none' type="hidden" value="<%=rs.getString(i)%>" name="<%=name%>" /> <%= rs.getString(i) %></td>
								<% 		} 
									}%>
														 
										<td height='100' class="col-xs-12 col-md-2"><button class="btn btn-danger btn-sm" type="submit" value="Book&nbsp;Now">Book&nbsp;Now</button></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</form>
		
			</div>
		
			<%	 } 
					rs.close();
					ps.close();
					conn.close();
				
				}else{ %>
					<script> alert("No results for these dates!");</script>
			<%	}
					
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
		
		} else { %>
				<script type="text/javascript"> alert("Please fill check in and check out");</script>	
		<%	} %>

</body>
</html>