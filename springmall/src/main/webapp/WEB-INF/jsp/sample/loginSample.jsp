<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>loginSample.jsp</title>
</head>
<body>
	<h1>login</h1>
	<form action = "/sample/loginSample" method="post">
		<input type="text" name="sampleId">
		<input type="password" name="samplePw">
		<input type="submit" value="로그인">
	</form>
</body>
</html>