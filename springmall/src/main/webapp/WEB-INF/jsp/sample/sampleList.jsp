<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<!-- bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- jquery CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
	<h1>sampleList</h1>
	<table class = "table">
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
			
			<!-- model안의 sampleList 가져와서 사용 -->
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
	<c:if test="${currentPage>1}">
		<a href="/sample/sampleList?currentPage=${currentPage-1}">이전</a>
	</c:if>
	<c:if test="${currentPage<lastPage}">
		<a href="/sample/sampleList?currentPage=${currentPage+1}">다음</a>
	</c:if>
</body>
</html>