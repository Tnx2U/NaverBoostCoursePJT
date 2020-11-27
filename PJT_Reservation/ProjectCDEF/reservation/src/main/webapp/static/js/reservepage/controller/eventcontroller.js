import { convertPriceToNumber } from '../../share/util.js';

export default class EventController {

    // ------------- 이벤트 헨들러 셋팅 함수 영역------------
    static setBookingTicketEvent(){
        this.handleClickTicketButton();
    }

    static setBookingFormEvent(){

    }

    // ------------- 이벤트 헨들러 함수 영역 -------------

    // 티켓 증감버튼 클릭
    static handleClickTicketButton(){
        const addButtonElement = document.querySelector(".ticket_body");

        addButtonElement.addEventListener('click', function (event) {
            let clickedTicketElement = null;
            let isAddButton = false;
    
            if (event.target.tagName === 'A') {
                clickedTicketElement = event.target.parentNode.parentNode.parentNode;
                
                if(event.target.getAttribute('title') === '더하기'){
                    isAddButton = true;
                }
            }

            console.log(clickedTicketElement, isAddButton)

            if(clickedTicketElement == null){
                return;
            }

            const price = convertPriceToNumber(clickedTicketElement.querySelector(".product_price > .price"));

            if(isAddButton){
                // 가격정보 갱신
                let totalPrice = convertPriceToNumber(clickedTicketElement.querySelector(".individual_price > .total_price"));
                totalPrice += price;
                clickedTicketElement.querySelector(".individual_price > .total_price").innerText = totalPrice.toLocaleString();

                // 버튼사이 카운트 갱신
                clickedTicketElement.querySelector(".count_control_input").value = Number(clickedTicketElement.querySelector(".count_control_input").value)+1;
                
                // bookingform의 total개수 처리
                document.querySelector(".inline_txt > #totalCount").innerText = Number(document.querySelector(".inline_txt > #totalCount").innerText)+1;
                
                // disable, able 조건처리
                if(clickedTicketElement.querySelector(".count_control_input").value == "1"){
                    // 버튼과 카운트 비활성화
                    clickedTicketElement.querySelector(".ico_minus3").classList.remove("disabled");
                    clickedTicketElement.querySelector(".count_control_input").classList.remove("disabled");
                    clickedTicketElement.querySelector(".individual_price").classList.add("on_color");
                }

            }else{
                //0이면 예외처리
                if(clickedTicketElement.querySelector(".count_control_input").value == "0"){
                    return;
                }

                // 가격정보 갱신
                let totalPrice = convertPriceToNumber(clickedTicketElement.querySelector(".individual_price > .total_price"));
                totalPrice -= price;
                clickedTicketElement.querySelector(".individual_price > .total_price").innerText = totalPrice.toLocaleString();

                // 버튼사이 카운트 갱신
                clickedTicketElement.querySelector(".count_control_input").value -= 1;
                // bookingform의 total개수 처리
                document.querySelector(".inline_txt > #totalCount").innerText = Number(document.querySelector(".inline_txt > #totalCount").innerText)-1;
                
                // disable, able 조건처리
                if(clickedTicketElement.querySelector(".count_control_input").value == "0"){
                    // 버튼과 카운트 비활성화
                    clickedTicketElement.querySelector(".ico_minus3").classList.add("disabled");
                    clickedTicketElement.querySelector(".count_control_input").classList.add("disabled");
                    clickedTicketElement.querySelector(".individual_price").classList.remove("on_color");
                }
            }

        })
    }
}