<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add TODO Page</title>
<link rel="stylesheet" href="resources/css/add_todo.css">
</head>
<body>
	<div id="wrap">
		<header>
			<h1>할일 등록</h1>
		</header>
		<div id="container">
			<form action="card" name="form_card" method="post" onsubmit="return validCheck()" accept-charset="utf-8">
				<div>
					<label>어떤 일인가요?</label><input class="input_text" id="input_title"
						name="title" type="text" placeholder="프로젝트 완성하기(24자리까지)" />
				</div>

				<div>
					<label>누가 할일인가요?</label><input class="input_text" id="input_manager"
						name="manager" type="text" placeholder="조현욱" />
				</div>

				<div>
					<label>우선순위를 선택하세요</label>
					<div class="radio_wrap">
						<input id="input_priority1" name="priority" type="radio" value=1 checked>
						<label>1순위</label> <input id="input_priority2" name="priority"
							type="radio" value=2> <label>2순위</label> <input
							id="input_priority3" name="priority" type="radio" value=3>
						<label>3순위</label>
					</div>
				</div>

				<div class="button_wrap">
					<a class="button" id="a_back" href="main">이전</a>
				    <input class="button" id="input_submit" type="submit" value="제출">
					<button class="button" id="btn_remove" type="reset">내용 지우기</button>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="resources/js/add_todo.js"></script>
</body>
</html>