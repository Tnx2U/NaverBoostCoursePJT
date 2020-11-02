<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add TODO Page</title>
<style type="text/css">
:root {
    --main-text-color: #734046;
    --main-button-color: #bc6ff1;
}


#wrap {
	margin: 10%;
	padding: 10%;
	border-radius: 15px;
	background-color: floralwhite;
}

h1{
    margin: 15px;
}

form > div{
    display: flex;
    flex-direction: column;
    margin: 15px;
}

label,input{
    margin: 3px;
}

label{
    color: var(--main-text-color);
}

</style>
</head>
<body>
	<div id="wrap">
		<header>
			<h1>할일 등록</h1>
		</header>
		<div id="container">
			<form action="">
				<div>
					<label>어떤 일인가요?</label><input id="input_title"
						name="title" type="text" placeholder="프로젝트 완성하기(24자리까지)" />
				</div>

				<div>
					<label>누가 할일인가요?</label><input id="input_manager"
						name="manager" type="text" placeholder="조현욱" />
				</div>

				<div>
					<label>우선순위를 선택하세요</label>
					<div class="radio_wrap">
						<input id="input_priority1" name="priority" type="radio" value=1>
						<label>1순위</label> <input id="input_priority2" name="priority"
							type="radio" value=2> <label>2순위</label> <input
							id="input_priority3" name="priority" type="radio" value=3>
						<label>3순위</label>
					</div>
				</div>

				<div>
					<a href="main">이전
						</button> <input type="submit" value="제출">
						<button>내용 지우기</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>