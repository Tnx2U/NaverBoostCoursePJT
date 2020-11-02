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

/* 하단 버튼 */
.button_wrap{
    margin-top: 5px;
    display: flex;
    flex-direction: row;
}

.button{
    margin: 5px;
    padding: 2px;
    width: 100px;
    height: 40px;
    border: solid 1.5px #898b8a;
    font-size: 15px;
}

#a_back{
    margin-right: auto;
    line-height: 2em;
    width: 90px;
    height: 35px;
    text-align: center;
    border-radius: 5px;
    background-color: white;
       
}

#input_submit, #btn_remove{
    background-color: #bc6ff1;
    color: white;
    font-weight: border;
    border-radius: 5px;
    cursor: pointer;
}

.input_text{
    height: 25px;
}

</style>
</head>
<body>
	<div id="wrap">
		<header>
			<h1>할일 등록</h1>
		</header>
		<div id="container">
			<form action="card" method="post" accept-charset="utf-8">
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
					<button class="button" id="btn_remove" onclick="handleClickRemove()">내용 지우기</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>