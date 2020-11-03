//유효성 검사 함수
function validCheck() {
    if (form_card.title.value == "") {
        alert("타이틀을 입력해 주세요.");
        form_card.title.focus();
        return false;
    }else if (form_card.manager.value == "") {
        alert("담당자를 입력해 주세요.");
        form_card.manager.focus();
        return false;
    }else{
        return true;
    }
}