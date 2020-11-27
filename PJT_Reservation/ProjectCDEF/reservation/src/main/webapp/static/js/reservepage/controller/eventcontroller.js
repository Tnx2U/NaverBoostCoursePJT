import { convertPriceToNumber } from '../../share/util.js';

export default class EventController {

    // ------------- 이벤트 헨들러 셋팅 함수 영역------------
    static setBookingTicketEvent(){
        this.handleClickTicketButton();
    }

    static setBookingFormEvent(){

    }

    // ------------- 이벤트 헨들러 함수 영역 -------------
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
                // disable, able 조건처리
                // bookingform의 total개수 처리
            }else{
                // 가격정보 갱신
                let totalPrice = convertPriceToNumber(clickedTicketElement.querySelector(".individual_price > .total_price"));
                totalPrice -= price;
                clickedTicketElement.querySelector(".individual_price > .total_price").innerText = totalPrice.toLocaleString();

                // 버튼사이 카운트 갱신
                // disable, able 조건처리
                // bookingform의 total개수 처리
            }

        })
    }
}