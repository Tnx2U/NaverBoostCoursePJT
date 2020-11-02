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
@charset "UTF-8";

@import
	url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap")
	;

@import
	url('https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap')
	;

@import
	url('https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap')
	;

@import
	url('https://fonts.googleapis.com/css2?family=Gugi&display=swap');

/* 전역 변수 및 설정 */
:root {
	--main-text-color: #92817a;
	--main-address-color: #477373;
}

a {
	text-decoration: none;
}

li {
	list-style: none;
}

ul {
	padding-inline-start: 0;
}

/* 전체 레이아웃 */
#wrap {
	display: block;
	padding: 3px;
	background: darkturquoise;
}

header, #container {
	padding: 10px;
	margin: 10px;
	border-radius: 7px;
	background: floralwhite;
}

header {
	overflow: auto;
}

#content {
	display: flex;
	flex-direction: row;
}

/* 헤더 링크 버튼 */
header>a {
	display: block;
	float: right;
	border: solid 2px #ec524b; border-radius : 15px; width : 160px;
	text-align: center; background-color : #f5b461;
	color: var(- -main-address-color);
	font-family: 'Noto Sans KR', sans-serif;
	font-size: medium;
	background-color: #f5b461;
	width: 160px;
	border-radius: 15px;
}

header>a:hover {
	color: #f8f1f1;
	-webkit-transition: color 500ms linear;
}

/* 컬럼 및 카드 설정 */
.card {
	display: flex;
	margin: 10px;
	padding: 10px;
	background-color: #a3d8f4;
}

.card_title {
	font-size: 17px;
	font-weight: bold;
}

.card_btnwrap{
    margin-left: 10px;
    width: 50px;
}

.btn_next{
    border: 0;
    border-radius: 5px;
    color: white;
    font-size: 15px;
    background-color: #ffe05d;
}

.btn_next:hover {
  box-shadow: 0 4px 4px 0 rgba(0,0,0,0.24), 0 5px 12px 0 rgba(0,0,0,0.19);
}

.card_desc {
	color: var(--main-text-color);
}

.coltitle {
	margin: 10px;
	padding: 10px;
	color: white;
	font-size: 20px;
	font-weight: bold;
	background-color: #00587a;
}
</style>
<link rel="stylesheet" href="css/todo.css">
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