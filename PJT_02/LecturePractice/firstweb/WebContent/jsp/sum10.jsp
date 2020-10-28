<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="http://localhost:8080/firstweb/jsp/inner.jsp" var="urlValue" scope="request"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		int total = 0;
	for (int i = 0; i <= 10; i++) {
		total += i;
	}
	%>
	1부터 10까지 합 :
	<%=total%>
	<%
		try {
		int count = (int) application.getAttribute("count");
		application.setAttribute("count", count+1);
	%>
	app scope value :
	<%=count%>
	<%
		} catch (NullPointerException e) {
		out.print("app scope 값이 설정되지 않음 : "+e);
	}
	%>
	<br>
	el표기법 : ${ applicationScope.count + 5 } <br>
	<c:if test="${count == 0} }">
		count == 0 입니다.
	</c:if>
	
	<%
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		
		request.setAttribute("list", list);
	%>
	
	<c:forEach items="${list }" var="item" begin="1">
		${item } <br>
	</c:forEach>
	${urlValue }
	<c:redirect url="http://localhost:8080/firstweb/ApplicationScope01"></c:redirect> 
</body>
</html>