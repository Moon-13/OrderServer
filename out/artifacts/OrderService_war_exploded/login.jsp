<%@ page language="java" contentType="text/html; charset=GB18030"
		 pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
	<title>管理员登录</title>
</head>
<body>
${error }
<h2>管理员登录</h2>
<hr>
<form action="adminlogin.do" method="post">

	<table>
		<tr><td>用户名：</td><td><input type="text" name="adminid"></td></tr>
		<tr><td>密码：</td><td><input type="text" name="adminpass"></td></tr>

	</table>
	<input type="submit" value="登录">
</form>

</body>
</html>

