import { getParamUrlByParams } from '../share/util.js';

document.addEventListener("DOMContentLoaded", function () {
    setInitialEventListener();
})


function setInitialEventListener() {
    handleClickSubmit();
}


function handleClickSubmit() {
    const submitElement = document.querySelector("#form1 button.login_btn");
    const emailInput = document.querySelector("#resrv_id");

    submitElement.addEventListener('click', function (event) {
        event.preventDefault();
        const emailChecker = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
        let inputValue = emailInput.value;
        if (!emailChecker.test(inputValue) || inputValue == "") {
            alert("이메일 형식이 틀렸거나 입력되지 않았습니다.");
            emailInput.focus();
        } else {
            const param = {
                reservationEmail: inputValue
            }
            const paramsUrl = getParamUrlByParams(param);
            location.href = `./myreservation${paramsUrl}`;
        }
    })
}