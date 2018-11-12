<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>addSample.jsp</title>
<!-- bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- jquery CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(document).ready(()=>{
		$('#sampleId').focus();
		$('#sampleId').blur(()=>{
			if($('#sampleId').val().length<1){
				$('#sampleIdHelper').text('���̵� �Է��ϼ���');
				$('#sampleId').focus();
			}else{
				$('#sampleIdHelper').text('');
			}
		});
		$('samplePw').blur(()=>{
			if($('#samplePw').val().length<1){
				$('#samplePwHelper').text('��й�ȣ�� �Է��ϼ���');
				$('#samplePw').focus();
			}else{
				$('#samplePwHelper').text('');
			}
		});
		$('#addBtn').click(()=>{
			if($('#sampleId').val().length == 0 || $('#samplePw').val().length == 0){
				alert('��� ���� �Է��ϼ���');
			}else{
				$('#addForm').submit();
			}
		});
	});
</script>
</head>
<body class="container">
	<h1 align = "center">addSample</h1>
	<form id="addForm" action="/sample/addSample" method="post" enctype="multipart/form-data">
		<table class ="table">
			<tr>
				<td>id</td>
				<td colspan="2">
					<input class="form-control" type="text" name="sampleId" id="sampleId" placeholder="���̵� �Է��ϼ���">
					<span id="sampleIdHelper"></span>
				</td>
			</tr>
			<tr>
				<td>pw</td>
				<td colspan="2">
					<input class="form-control" type ="password" name ="samplePw" id="samplePw" placeholder="��й�ȣ�� �Է��ϼ���">
					<span id="samplePwHelper"></span>
				</td>
			</tr>
			<tr>
				<td>file</td>
				<td>
					<input class="form-control" type ="file" name ="multipartFile">
				</td>
			</tr>
		</table>
		<div class="form-group text-center">
			<input id="addBtn" class="btn" type="submit" value="�Է�">
		</div>
	</form>
</body>
</html>