<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ page import="java.io.IOException"%>
<%@	page import="java.io.PrintWriter"%>
<%@	page import="java.sql.Connection"%>
<%@	page import="java.sql.DriverManager"%>
<%@	page import="java.sql.ResultSet"%>
<%@	page import="java.sql.Statement"%>
<%@ page import="java.sql.ResultSetMetaData"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Landmarks</title>
<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Tangerine">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script>
	$(document).ready(function(){
			$(landmarks).css("background-color","#CD5C5C");
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

	<%
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

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
			PrintWriter pw = response.getWriter();

			query = "select * from Landmarks";

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "root", "root");
			stm = conn.createStatement();
			rs = stm.executeQuery(query);
			metaData = rs.getMetaData();
			columnCnt = metaData.getColumnCount(); %>

	<div class="container">

			<h1>Landmarks</h1>

			<br>
			<br>
			<br>
			<br>
			<br>
	</div>		
			


	<div class="container">

		<div class="table-responsive">
			<table class="table table-bordered">

				<%if( k == 0){  %>

				<thead>
					<tr>

						<%	for(int i = 1; i <= columnCnt; i++){
								
							String name = "";
							
							if(i == 1)
								name = "Landmark";
							else if(i == 2)
								name = "Address";
							else if(i == 3)
								name = "Phone";
							else if(i == 4)
								name = "Adult's Ticket";
							else if(i == 5)
								name = "Children's Ticket";	
							else if(i == 6)
								name = "Opening Time";
							else
								name="Closing Time";
								%>

						<th><%= name %></th>
						<%	} %>

					</tr>
				</thead>
				<% 	k = 1; 
						} %>

				<tbody>
					
					<%	while (rs.next()) {
							rowCnt = rs.getRow();
					%>
					<tr>
						<% for(int i = 1; i <= columnCnt; i++){ 
						 

						%>
					
						<td height='100'><input class='form-control' style='border: none' type="hidden"	value="<%= rs.getString(i) %>" name=" " /> <%= rs.getString(i) %></td>

						<% 		 
						  }%>

					</tr>

	<% } %>
	
						
				</tbody>

			</table>
		</div>
	</div>
		
	<% }catch(Exception e){
		e.printStackTrace();
	}
	
	%>


</body>
</html>