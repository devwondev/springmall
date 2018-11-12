<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>sampleList.jsp</title>
<!-- bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
</head>
<body class="container">
	<h1 align = "center">sampleList</h1>
		<div class="form-group text-right">
			<a href="/sample/addSample">
				<button type="button" class="btn btn-dark">�Է�</button>
			</a>
		</div>
	<table class = "table table-striped">
		<thead>
			<tr>
				<td>SAMPLE NO</td>
				<td>SAMPLE ID</td>
				<td>SAMPLE PW</td>
				<td>DELETE</td>
				<td>UPDATE</td>
			</tr>
		</thead>
		<tbody>
			<!-- model���� sampleList �����ͼ� ��� -->
			<c:forEach var="sample" items="${sampleList}">
				<tr>
					<td>${sample.sampleNo}</td>
					<td>${sample.sampleId}</td>
					<td>${sample.samplePw}</td>
					<td><a href="/sample/removeSample?sampleNo=${sample.sampleNo}">DELETE</a></td>
					<td><a href="/sample/modifySample?sampleNo=${sample.sampleNo}">UPDATE</a></td>
				</tr>
			</c:forEach>
			
		</tbody>
	</table>
	<div class="form-group text-center">
		<c:if test ="${currentPage<=1}">
			<button type="button" class="btn btn-light">����</button>
		</c:if>
		<c:if test ="${currentPage>1}">
			<a href="/sample/sampleList?page=${currentPage-1}">
				<button type="button" class="btn btn-primary">����</button>
			</a>
		</c:if>
		<c:forEach var = "i" begin="${startPage}" end="${endPage}" step="1">
			<c:if test = "${i!=currentPage}">
				<a href="/sample/sampleList?page=${i}">${i}</a>
			</c:if>
			<c:if test = "${i==currentPage}">
				${i}
			</c:if>
		</c:forEach>
		<c:if test="${currentPage>=lastPage}">
			<button type="button" class="btn btn-light">����</button>
		</c:if>
		<c:if test="${currentPage<lastPage}">
			<a href="/sample/sampleList?page=${currentPage+1}">
				<button type="button" class="btn btn-primary">����</button>
			</a>
		</c:if>
	</div>
	<!-- <form action="/sample/sampleList" method="post" class="form-group text-center">
		<select name="searchKey" class="form-control-sm">
			<option value="">::����::</option>
			<option value="searchNo">��ȣ</option>
			<option value="searchId">���̵�</option>
		</select>
		<input type="text" name="searchWord" class="form-control-sm">
		<input class="btn" type="submit" value="�˻�">
	</form> -->
</body>
</html>