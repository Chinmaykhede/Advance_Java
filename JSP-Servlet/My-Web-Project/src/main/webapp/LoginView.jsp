
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp" %>
	<form>
		<div align="center">
			<h1>Login</h1>
			<table>
				<tr>
					<th>Login</th>
					<td><input type="email" name="loinId" value=""
						placeholder="enter your email"></td>
				</tr>
				<tr>
					<th>Password</th>
					<td><input type="password" name="password" value=""
						placeholder="enter your password"></td>
				<tr>
					<th></th>
					<td><input type="submit" name="operation" value="signIn"></td>
				</tr>
			</table>
		</div>
	</form>
	<%@ include file="Footer.jsp" %>
</body>
</html>