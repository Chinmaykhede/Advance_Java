<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<div align="center">
		<h1>
			Welcome to my web-site<%=user != null ? "(" + user.getFirstName() + ")" : ""%>
		</h1>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>