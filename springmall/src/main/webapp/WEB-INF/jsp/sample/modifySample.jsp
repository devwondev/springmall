<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>modifySample.jsp</title>
<!-- bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- jquery CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(document).ready(()=>{
		$('#sampleId').focus();
		$('#sampleId').blur(()=>{
			if($('#sampleId').val().length<1){
				$('#sampleIdHelper').text('수정할 아이디를 다시 입력해주세요');
				$('#sampleId').focus();
			}else{
				$('#sampleIdHelper').text('');
			}
		});
		$('samplePw').blur(()=>{
			if($('#samplePw').val().length<1){
				$('#samplePwHelper').text('수정할 비밀번호를 다시 입력하세요');
				$('#samplePw').focus();
			}else{
				$('#samplePwHelper').text('');
			}
		});
		$('#modifyBtn').click(()=>{
			if($('#sampleId').val().length == 0 || $('#samplePw').val().length == 0){
				alert('모든 값을 입력하세요');
			}else{
				$('#modifyForm').submit();
			}
		});
	});
</script>
</head>
<body class="container">
	<h1 align = "center">modifySample</h1>
	<div class="form-group text-left">
			<a href="/sample/sampleList">
				<button type="button" class="btn btn-dark">목록으로</button>
			</a>
	</div>
	<form id="modifyForm" action="/sample/modifySample" method="post" enctype="multipart/form-data">
		<input class="form-control" type="hidden" name = "sampleNo" value="${sample.sampleNo}">
		<table class = "table">
			<tr>
				<td>id</td>
				<td colspan="2">
					<input class="form-control" type = "text" name = "sampleId" id="sampleId" value="${sample.sampleId}">
					<span id="sampleIdHelper"></span>
				</td>
			</tr>
			<tr>
				<td colspan="2">pw</td>
				<td>
					<input class="form-control" type = "password" name = "samplePw" id="samplePw" value="${sample.samplePw}">
					<span id="samplePwHelper"></span>
				</td>
			</tr>
			<tr>
				<td colspan="2">file</td>
				<td>
					<input class="form-control" type="text" name="formFileName" value="${sampleFile.sampleFileName}.${sampleFile.sampleFileExt}">
					<input class="form-control" type ="file" name ="multipartFile">
				</td>
			</tr>
			<tr>
				
			</tr>
		</table>
		<div class="form-group text-center">
			<input id="modifyBtn" class="btn" type="submit" value="수정">
		</div>
	</form>
</body>
</html>