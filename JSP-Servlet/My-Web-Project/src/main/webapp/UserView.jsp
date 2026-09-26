<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<%
	String succMsg = (String) request.getAttribute("succMsg");
	%>

	<div align="center">
		<h1>User Registration</h1>
		<h2 style="color: green">
			<%=succMsg != null ? succMsg : ""%></h2>
	</div>
	<form action="UserCtl" method="post">
		<div align="center">
			<table>
				<tr>
					<th>FirstName</th>
					<td><input type="text" name="firstName" value=""
						placeholder="enter your firstName"></td>
				</tr>
				<tr>
					<th>LastName</th>
					<td><input type="text" name="lastName" value=""
						placeholder="enter your lastName"></td>
				</tr>
				<tr>
					<th>Email Id</th>
					<td><input type="email" name="loginId" value=""
						placeholder="enter your email id"></td>
				</tr>
				<tr>
					<th>Password</th>
					<td><input type="password" name="password" value=""
						placeholder="enter your password"></td>
				</tr>
				<tr>
					<th>DateofBirth</th>
					<td><input type="date" name="dob" value=""
						placeholder="enter your dob"></td>
				</tr>
				<tr>
					<th></th>
					<td><input type="submit" name="operation" value="save"></td>
				</tr>
			</table>
		</div>
	</form>
	<%@ include file="Footer.jsp"%>
</body>
</html>