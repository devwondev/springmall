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
		<a href="/sample/loginSample">�α���</a>
	</c:if>
	<c:if test="${sessionSample.sampleId!=null}">
		${sessionSample.sampleId}�� �α�����
		<a href="/sample/sampleList">���ø���Ʈ</a>
		<a href="/sample/logoutSample">�α׾ƿ�</a>
	</c:if>
</body>
</html>