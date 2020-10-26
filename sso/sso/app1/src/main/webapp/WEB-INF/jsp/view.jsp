<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style.css" " rel="stylesheet" type="text/css">
<title>APP1</title>
</head>
<body>
<table width="600" cellpadding="1" cellspacing="1">

			<tr height="30">
				<td colspan="3" align="center" class="header">id</td>
				<td colspan="3" align="center" class="header">name</td>
				<td colspan="3" align="center" class="header">age</td>
				<td colspan="3" align="center" class="header">email</td>
				</tr>
				<tr>
				<td colspan="3" align="center" class="data">${user.id }</td>
				<td colspan="3" align="center" class="data">${user.name }</td>
				<td colspan="3" align="center" class="data">${user.age }</td>
				<td colspan="3" align="center" class="data">${user.email }</td>
			</tr>
			</table>
		<a href="http://localhost:8080/cas/logout.do?LOCAL_SERVICE=http://localhost:8080/app1/view.do">注销</a>
	</p>
</body>
</html>