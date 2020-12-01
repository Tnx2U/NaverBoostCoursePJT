import { convertPriceToNumber } from '../../share/util.js';
import DataController from './dataController.js';
import { handlePostAjax } from '../../share/ajaxHandler.js';

export default class EventController {

    // ------------- 이벤트 헨들러 셋팅 함수 영역------------
    static setBookingTicketEvent() {
        this.handleClickTicketButton();
    }

    static setBookingFormEvent() {
        this.handleValidCheck();
        this.handlePolicyAgree();
        this.handleSubmit();
    }

    // ------------- 이벤트 헨들러 함수 영역 -------------

    // 티켓 증감버튼 클릭
    static handleClickTicketButton() {
        const addButtonElement = document.querySelector(".ticket_body");

        addButtonElement.addEventListener('click', function (event) {
            let clickedTicketElement = null;
            let isAddButton = false;

            if (event.target.tagName === 'A') {
                clickedTicketElement = event.target.parentNode.parentNode.parentNode;

                if (event.target.getAttribute('title') === '더하기') {
                    isAddButton = true;
                }
            }

            if (clickedTicketElement == null) {
                return;
            }

            const price = convertPriceToNumber(clickedTicketElement.querySelector(".product_price > .price"));

            if (isAddButton) {
                // 가격정보 갱신
                let totalPrice = convertPriceToNumber(clickedTicketElement.querySelector(".individual_price > .total_price"));
                totalPrice += price;
                clickedTicketElement.querySelector(".individual_price > .total_price").innerText = totalPrice.toLocaleString();

                // 버튼사이 카운트 갱신
                clickedTicketElement.querySelector(".count_control_input").value = Number(clickedTicketElement.querySelector(".count_control_input").value) + 1;

                // bookingform의 total개수 처리
                document.querySelector(".inline_txt > #totalCount").innerText = Number(document.querySelector(".inline_txt > #totalCount").innerText) + 1;

                // disable, able 조건처리
                if (clickedTicketElement.querySelector(".count_control_input").value == "1") {
                    // 버튼과 카운트 비활성화
                    clickedTicketElement.querySelector(".ico_minus3").classList.remove("disabled");
                    clickedTicketElement.querySelector(".count_control_input").classList.remove("disabled");
                    clickedTicketElement.querySelector(".individual_price").classList.add("on_color");
                }

                //데이터 컨트롤러의 데이터 수정
                DataController.setPrices(true, clickedTicketElement.id.replace(/qty_/g, ''));

            } else {
                //0이면 예외처리
                if (clickedTicketElement.querySelector(".count_control_input").value == "0") {
                    return;
                }

                // 가격정보 갱신
                let totalPrice = convertPriceToNumber(clickedTicketElement.querySelector(".individual_price > .total_price"));
                totalPrice -= price;
                clickedTicketElement.querySelector(".individual_price > .total_price").innerText = totalPrice.toLocaleString();

                // 버튼사이 카운트 갱신
                clickedTicketElement.querySelector(".count_control_input").value -= 1;
                // bookingform의 total개수 처리
                document.querySelector(".inline_txt > #totalCount").innerText = Number(document.querySelector(".inline_txt > #totalCount").innerText) - 1;

                // disable, able 조건처리
                if (clickedTicketElement.querySelector(".count_control_input").value == "0") {
                    // 버튼과 카운트 비활성화
                    clickedTicketElement.querySelector(".ico_minus3").classList.add("disabled");
                    clickedTicketElement.querySelector(".count_control_input").classList.add("disabled");
                    clickedTicketElement.querySelector(".individual_price").classList.remove("on_color");
                }

                //데이터 컨트롤러의 데이터 수정
                DataController.setPrices(false, clickedTicketElement.id.replace(/qty_/g, ''));
            }
        })
    }

    static handleValidCheck() {
        let nameInput = document.querySelector(".form_horizontal #name");
        let telInput = document.querySelector(".form_horizontal #tel");
        let emailInput = document.querySelector(".form_horizontal #email");

        nameInput.addEventListener('focusout', function () {
            const nameChecker = /^[가-힣a-zA-Z]+$/;
            let inputValue = nameInput.value;
            if (!nameChecker.test(inputValue) && inputValue != "") {
                alert("이름 형식이 틀렸어요.");
                nameInput.focus();
            } else {
                DataController.setReservationName(inputValue);
            }
        });

        telInput.addEventListener('focusout', function () {
            const telChecker = /^\d{3}-\d{3,4}-\d{4}$/;
            let inputValue = telInput.value;
            if (!telChecker.test(inputValue) && inputValue != "") {
                alert("전화번호 형식이 틀렸어요.");
                telInput.focus();
            } else {
                DataController.setReservationTelephone(inputValue);
            }
        });

        emailInput.addEventListener('focusout', function () {
            const emailChecker = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
            let inputValue = emailInput.value;
            if (!emailChecker.test(inputValue) && inputValue != "") {
                alert("이메일 형식이 틀렸어요.");
                emailInput.focus();
            } else {
                DataController.setReservationEmail(inputValue);
            }
        });
    }

    static handlePolicyAgree() {
        const agreeAllElement = document.querySelector("input.chk_agree");
        const agreementElements = document.querySelectorAll(".agreement");

        agreeAllElement.addEventListener('click', function () {
            document.querySelector(".bk_btn_wrap").classList.toggle("disable");

            DataController.toggleAgreeAll();
        })

        agreementElements.forEach((element) => {
            if (element.classList.contains("all")) {
                return;
            }

            element.addEventListener('click', function (event) {
                event.preventDefault();
                element.classList.toggle("open");
            })
        })
    }

    static handleSubmitResult(request){
        if(request.status === 200 || request.status === 201){
            alert("정상적으로 예약되었습니다. 공연정보 화면으로 이동합니다.");
            window.history.back();
        }else{
            console.log(request.responseText);
            alert("예약기능에 문제가 발생했습니다. Response Status : ", request.status);
        }
    }

    static handleSubmit() {
        const submitButtonElement = document.querySelector(".box_bk_btn .bk_btn");
        const paramFunc = this.handleSubmitResult;
        
        submitButtonElement.addEventListener('click', function () {
            let reservateDate = document.querySelector(".inline_form.last #reservateDate").innerText;
            DataController.setReservationYearMonthDay(reservateDate.replaceAll('/', '.'));
            const reservationParam = DataController.getReservationParam();
            const agreeAll = DataController.getAgreeAll;

            if (reservationParam.reservationName == "") {
                alert("이름을 입력하지 않았습니다.");
            } else if (reservationParam.reservationTelephone == "") {
                alert("연락처를 입력하지 않았습니다.");
            } else if (reservationParam.reservationEmail == "") {
                alert("이메일을 입력하지 않았습니다.");
            } else if(reservationParam.prices.length == 0){
                alert("선택한 티켓이 없습니다.");
            }else if (!agreeAll) {
                alert("약관에 동의하지 않았습니다.");
            } else {
                const targetUrl = `reservations`
                
                handlePostAjax(paramFunc, targetUrl, reservationParam);
            }
        })
    }
}