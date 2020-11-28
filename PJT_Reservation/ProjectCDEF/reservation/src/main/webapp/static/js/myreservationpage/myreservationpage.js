import { handleGetAjax } from '../share/ajaxhandler.js';
import { getParamsByUrl } from '../share/util.js';

document.addEventListener("DOMContentLoaded", function () {
    const params = getParamsByUrl();

    //displayDetail 정보를 DataController에 주입
    handleGetAjax(initializePage, `reservations`, params);
})

function initializePage(displayInfoResponse){
    // DataController.initializeData(displayInfoResponse);
    console.log(displayInfoResponse);
    renderComponents();
    setEventHandlers();
}

function renderComponents(){
    // const ctWrapElement = document.querySelector(".ct_wrap");

    // document.querySelector(".top_title .title").innerText = DataController.getTitle();
    // ctWrapElement.appendChild(new GroupVisual().getRenderedElement());
    // ctWrapElement.appendChild(new StoreDetail().getRenderedElement());
    // ctWrapElement.appendChild(new BookingTicket().getRenderedElement());
    // ctWrapElement.appendChild(new BookingForm().getRenderedElement());
}

function setEventHandlers(){
    // EventController.setBookingTicketEvent();
    // EventController.setBookingFormEvent();
}