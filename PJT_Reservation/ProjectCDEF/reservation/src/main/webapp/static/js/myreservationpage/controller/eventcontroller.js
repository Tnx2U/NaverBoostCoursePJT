import DataController from './dataController.js';
import { handlePutAjax } from '../../share/ajaxHandler.js';

export default class EventController {

    // ------------- 이벤트 헨들러 셋팅 함수 영역------------
    static setMyReservationEvent() {
        this.handleClickCancelButton();
    }

    // ------------- 이벤트 헨들러 함수 영역 -------------

    static handleClickCancelButton(){
        const cardConfirmElement = document.querySelector(".card.confirmed");
        const paramFunc = this.handleClickCancelResult;

        cardConfirmElement.addEventListener('click', (event) =>{
            let clickedReservationId = -1;

            if(event.target.tagName === 'BUTTON'){
                clickedReservationId = parseInt(event.target.id.replace("btn_",''));
                if(confirm("예약을 취소하시겠습니까?") == false){
                    return;
                }
            }else if(event.target.tagName === 'SPAN'){
                clickedReservationId = parseInt(event.target.parentNode.id.replace("btn_",''));
                if(confirm("예약을 취소하시겠습니까?") == false){
                    return;
                }
            }

            if(clickedReservationId == -1){
                return;
            }

            //put요청
            const url = `reservations/${clickedReservationId}`
            handlePutAjax(paramFunc, url);
        })
    }

    static handleClickCancelResult(request){
        if(request.status === 200 || request.status === 201){
            alert("예약정보가 취소되었습니다.");

            // 취소된 카드DOM 이동
            const targetId = JSON.parse(request.response).reservationInfoId;
            const tagetCardElement = document.querySelector(`.card_item#card_${targetId}`);
            const cancelCardListElement = document.querySelector(`.card.cancel`);
            const bookinCancelElement = tagetCardElement.querySelector(".booking_cancel");
            
            tagetCardElement.querySelector('.card_detail').removeChild(bookinCancelElement);
            // 두번째 인자가 null이면 자동으로 맨 끝에 옮겨붙인다.
            cancelCardListElement.insertBefore(tagetCardElement, null);

            // 상단 요약숫자 변경
            let summaryConfirm = document.querySelector("#li_summary_confirm span").innerText;
            let summaryCancel = document.querySelector("#li_summary_cancel span").innerText;

            document.querySelector("#li_summary_confirm span").innerText = Number(summaryConfirm) - 1;
            document.querySelector("#li_summary_cancel span").innerText = Number(summaryCancel) + 1;

        }else{
            console.log(request.responseText);
            alert("예약취소에 문제가 발생했습니다. Response Status : ", request.status);
        }
    }
}