<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>
		<label>EMAIL VALIDATE</label>
	</div>
	<form action="emailresult" method="POST">
		<input placeholder="Email" name="email"/>
		<input type="submit" value="Validate Email"/>
	</form>
	<div>
		<h1></h1>
		<label>PASSWORD VALIDATE</label>
	</div>
	<form action="passwordresult" method="POST">
		<input placeholder="Password" name="password"/>
		<input type="submit" value="Validate Password"/>
	</form>
	<div>
		<h1></h1>
		<label>DATE VALIDATE</label>
	</div>
	<form action="dateresult" method="POST">
		<input type="date" placeholder="Date" name="date"/>
		<input type="submit" value="Validate Date"/>
	</form>
	<div>
		<h1></h1>
		<label>USER VALIDATE</label>
	</div>
	<form action="userresult" method="POST">
		<div>
			<input placeholder="Name" name="username"/>
		</div>
		<div>
			<input placeholder="Age" name="userage"/>
		</div>
		<div>
			<input type="date" placeholder="Date" name="userdate"/>
		</div>
		<div>
			<input placeholder="Email" name="useremail"/>
		</div>
		<div>
			<input placeholder="Password" name="userpassword"/>
		</div>
		<div>
			<input type="submit"/>
		</div>
	</form>
</body>
</html>