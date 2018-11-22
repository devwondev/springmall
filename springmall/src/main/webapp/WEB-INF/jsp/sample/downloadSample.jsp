<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>downloadSample.jsp</title>
<!-- bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
</head>
<body class="container">
	<h1 align = "center">dwonloadSample</h1>
	<div class="form-group text-left">
			<a href="/sample/sampleList">
				<button type="button" class="btn btn-dark">목록으로</button>
			</a>
	</div>
	<div class="form-group text-center">
	<input value="${sample.sampleNo}" type="hidden" readonly><br>
	<a href="/download/file/${sampleFile.sampleFileName}.${sampleFile.sampleFileExt}">${sampleFile.sampleFileName}.${sampleFile.sampleFileExt}</a>
	</div>
</body>
</html>