<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>loginSample.jsp</title>
<!-- bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- jquery CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body class="container">
	<h1 align = "center">login</h1>
	<form action = "/sample/loginSample" method="post">
		<input class="form-control" type="text" name="sampleId">
		<input class="form-control" type="password" name="samplePw">
		<div class="form-group text-center">
			<input class="btn" type="submit" value="로그인">
		</div>
	</form>
</body>
</html>