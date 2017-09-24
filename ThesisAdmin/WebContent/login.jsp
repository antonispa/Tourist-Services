<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Tangerine">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>
	$(document).ready(function(){
			$().css("background-color","#CD5C5C");
	});
</script>

<style>
body {
	margin-bottom: 50px;
}
.center-block {
  display: block;
  margin-left: 35%;
  margin-right: auto;
}

.jumbotron {
	height: 100px;
	padding-bottom: 100px;
}

</style>
</head>
<body>

	<% 
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
	
		if (session.getAttribute("user") != null){
		response.sendRedirect("index.jsp");
	}%>
	
	<div class="jumbotron">
		<h2 style="padding-left:15px">Back-End System</h2>
	</div>
	
	<div class="container-fluid">
		<div class="col-md-5 center-block">
			<h2>Login</h2> <br><br>
		
			<form class="form-horizontal" action="../ThesisAdmin/CheckLogin" method="post">
				<div class="form-group">
					<label class="control-label col-xs-12 col-sm-4 col-md-4" for="username"><b>Username:</b></label>
					<div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
						<input class="form-control" type="text" placeholder="Enter username" id="username" name="username" required>
					</div>
				</div>	
				
				<div class="form-group">
					<label class="control-label col-xs-12 col-sm-4 col-md-4 col-lg-4" for="password"><b>Password:</b></label>
					<div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
						<input class="form-control" type="password" placeholder="Enter password" id="password" name="password" required>
					</div>
				</div>	
				
				<div class="col-sm-offset-5 col-md-offset-5">
					<button type="submit" class="btn btn-info" name="login-btn">Login</button>
				</div>	
				
			</form>
		</div> <br>
	</div>
	
	<div class="container-fluid">
		<div style="border:1px solid lightgrey; padding-bottom:4px;" class="col-md-4 pull-left">
			
			<h3>New user</h3> <br>
			
			<form class="form-horizontal" action="../ThesisAdmin/RegisterUser" method="post">
				<div class="form-group">
					<label class="control-label col-xs-12 col-sm-4 col-md-4" for="reg_username">Username: </label>
					<div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
						<input class="form-control" type="text" placeholder="Enter username" id="reg_username" name="reg_username" required>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-xs-12 col-sm-4 col-md-4" for="reg_password">Password: </label>
					<div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
						<input class="form-control" type="password" placeholder="Enter password" id="reg_password" name="reg_password" required>
					</div>
				</div>
				
				<div class="col-sm-offset-5 col-md-offset-3">
					<button type="submit" class="btn btn-danger" name="register-btn">Register</button>
				</div>	
			</form>
		</div>
	</div>

</body>
</html>