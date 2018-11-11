<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>index.jsp</title>
</head>
<body>
	<h1>index</h1>
	<c:if test="${sessionSample.sampleId==null}">
		<a href="/sample/loginSample">로그인</a>
	</c:if>
	<c:if test="${sessionSample.sampleId!=null}">
		${sessionSample.sampleId}님 로그인중
		<a href="/sample/sampleList">샘플리스트</a>
		<a href="/sample/logoutSample">로그아웃</a>
	</c:if>
</body>
</html>