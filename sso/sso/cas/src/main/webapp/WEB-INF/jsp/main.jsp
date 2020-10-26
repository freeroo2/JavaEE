<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>main</title>
</head>
<body>
	<table width="600" cellpadding="1" cellspacing="1">
		<tr>
		</tr>
		<tr height="30">
			<td colspan="3" align="center" class="header">cookie名</td>
			<td colspan="3" align="center" class="header">cookie值</td>
		</tr>
		<%
			Cookie cookies[] = request.getCookies();
			for (Cookie cookie : cookies) {
				out.println(cookie.getName() + "=" + cookie.getValue() + "<br>");
		%>
		<tr height="30">
			<td colspan="3" align="center" class="data"><%=cookie.getName()%></td>
			<td colspan="3" align="center" class="data"><%=cookie.getValue()%></td>
		</tr>
		<%
			}
		%>
	</table>
	<tr height="30">
			<td colspan="3" align="center" class="data"><a href="http://localhost:8080/app1/view.do">子系统1</a></td>
			<td colspan="3" align="center" class="data"><a href="http://localhost:8080/app2/view.do">子系统2</a></td>
	</tr>
</body>
</html>