<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="utf-8">
<meta name="description"
	content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
<title>네이버 예약</title>
<link href="static/css/style.css" rel="stylesheet">
</head>

<body>
	<div id="container">
		<div class="header fade">
			<header class="header_tit">
				<h1 class="logo">
					<a href="./main" class="lnk_logo" title="네이버"> <span
						class="spr_bi ico_n_logo">네이버</span>
					</a> <a href="./main" class="lnk_logo" title="예약"> <span
						class="spr_bi ico_bk_logo">예약</span>
					</a>
				</h1>
				<a href="#" class="btn_my"> <span title="예약확인">예약확인</span>
				</a>
			</header>
		</div>
		<div class="ct">
			<div class="ct_wrap">
				<div class="top_title review_header">
					<a href="javascript:history.back()" class="btn_back" title="이전 화면으로 이동">
						<i class="fn fn-backward1"></i>
					</a>
					<h2>
						<span class="title">${requestScope.productDescription}</span>
					</h2>
				</div>
				<!-- 리뷰 별점 -->
				<!-- //리뷰 별점 -->

				<!-- 리뷰 입력 -->
				<!-- //리뷰 입력 -->

				<!-- 리뷰 작성 푸터 -->
					<!-- 리뷰 포토 -->
					<!-- //리뷰 포토 -->
				<!-- //리뷰 작성 푸터 -->

				<!-- 리뷰 등록 -->
				<!-- //리뷰 등록 -->
			</div>
		</div>
	</div>
	<footer>
		<div class="gototop">
			<a href="#" class="lnk_top"> <span class="lnk_top_text">TOP</span>
			</a>
		</div>
		<div id="footer" class="footer">
			<p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및
				환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
			<span class="copyright">© NAVER Corp.</span>
		</div>
	</footer>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="module" src="static/js/reviewWritePage/reviewWritePage.js"></script>
</body>
</html>