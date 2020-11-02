<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main Page</title>
<style type="text/css">
</style>
<link rel="stylesheet" href="resources/css/todo.css">
</head>
<body>
	<div id="wrap">
		<header>
			<a href="addpage">새로운 TODO 등록</a>
		</header>
		<div id="container">
			<div id="content">
				<c:forEach items="${requestScope.columnList}" var="column">
					<c:set var="columnId" value="${column.getId()}" scope="page" />
					<div id="col_${column.getId()}" class="column">
						<div class="coltitle">${column.getTitle()}</div>
						<div class="card_container">
							<ul>
								<c:forEach
									items="${requestScope.orderedCards[pageScope.columnId-1]}"
									var="card">
									<li class="card" id="card_${card.getId()}">
									    <div class="card_content">
									       <div class="card_title">${card.getTitle()}</div>
									       <div class="card_desc">
                                                <span>등록날짜:${card.getRegistedDate()}, </span> <span>${card.getTitle()},</span>
                                                <span>우선순위${card.getPriority()}</span>
                                            </div> 
									    </div>
									    <div class="card_btnwrap">
									        <c:if test="${card.getColumnId() != 3}">
                                                <button class="btn_next" id="btn_card_${card.getId()}"
                                                    onclick="handleClickNext(${card.getId()}, ${card.getColumnId()}, ${card.getCardOrder()})">></button>
                                            </c:if>
									    </div>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>