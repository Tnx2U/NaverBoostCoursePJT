<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	int total = 0;
	for(int i = 0; i<= 10; i++){
		total += i;
	}

%>
1부터 10까지 합 : <%=total %>
</body>
</html>