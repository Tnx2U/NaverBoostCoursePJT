import DataController from "./controller/dataController.js";
import EventController from "./controller/eventController.js";
import { getParamsByUrl } from "../share/util.js";
import { handleGetAjax } from '../share/ajaxHandler.js';
import GroupVisual from './component/groupVisual.js';
import StoreDetail from "./component/storeDetail.js";
import BookingTicket from "./component/bookingTicket.js";
import BookingForm from "./component/bookingForm.js";

document.addEventListener("DOMContentLoaded", function () {
    const params = getParamsByUrl();

    //displayDetail 정보를 DataController에 주입
    handleGetAjax(initializePage, `products/${params.displayInfoId}`);
})

function initializePage(displayInfoResponse){
    DataController.initializeData(displayInfoResponse);
    renderComponents();
    setEventHandlers();
}

function renderComponents(){
    const ctWrapElement = document.querySelector(".ct_wrap");

    document.querySelector(".top_title .title").innerText = DataController.getTitle();
    ctWrapElement.appendChild(new GroupVisual().getRenderedElement());
    ctWrapElement.appendChild(new StoreDetail().getRenderedElement());
    ctWrapElement.appendChild(new BookingTicket().getRenderedElement());
    ctWrapElement.appendChild(new BookingForm().getRenderedElement());
}

function setEventHandlers(){
    EventController.setBookingTicketEvent();
    EventController.setBookingFormEvent();
}