<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Happy+Monkey" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Design Patterns</title>
</head>
<body style="font-family: 'Happy Monkey', cursive;">
	<div class="container">
		<h2 class="text-info">EMAIL VALIDATE</h2>

		<form class="form-group" action="emailresult" method="POST">
			<input class="form-control" placeholder="Email" name="email"/>
			<input class="btn btn-success" type="submit" value="Validate Email"/>
		</form>

		<h2 class="text-info">PASSWORD VALIDATE</h2>
		<form class="form-group" action="passwordresult" method="POST">
			<input class="form-control" placeholder="Password" name="password"/>
			<input class="btn btn-success" type="submit" value="Validate Password"/>
		</form>

		<h2 class="text-info">DATE VALIDATE</h2>
		<form class="form-group" action="dateresult" method="POST">
			<input class="form-control" type="date" placeholder="Date" name="date"/>
			<input class="btn btn-success" type="submit" value="Validate Date"/>
		</form>

		<h2 class="text-info">USER VALIDATE</h2>
		<form class="form-group" action="userresult" method="POST">

			<input class="form-control" placeholder="Name" name="username"/>

			<input class="form-control" placeholder="Age" name="userage"/>

			<input class="form-control" type="date" placeholder="Date" name="userdate"/>
	
			<input class="form-control" placeholder="Email" name="useremail"/>
		
			<input class="form-control" placeholder="Password" name="userpassword"/>
		
			<input class="btn btn-success" type="submit"/>

		</form>
	</div>
	

	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>

</html>