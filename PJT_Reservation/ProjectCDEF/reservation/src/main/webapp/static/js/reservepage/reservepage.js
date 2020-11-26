import DataController from "./datacontroller.js";
import { getParamsByUrl } from "../share/util.js";
import { handleGetAjax } from '../share/ajaxhandler.js';
import GroupVisual from './component/groupvisual.js';
import StoreDetail from "./component/storedetail.js";
import BookingTicket from "./component/bookingticket.js";

document.addEventListener("DOMContentLoaded", function () {
    const params = getParamsByUrl();

    //displayDetail 정보를 DataController에 주입
    handleGetAjax(initializePage, `products/${params.displayInfoId}`);
})

function initializePage(displayInfoResponse){
    DataController.initializeData(displayInfoResponse);
    renderComponents();
}

function renderComponents(){
    const ctWrapElement = document.querySelector(".ct_wrap");
    ctWrapElement.appendChild(new GroupVisual().getRenderedElement());
    ctWrapElement.appendChild(new StoreDetail().getRenderedElement());
    ctWrapElement.appendChild(new BookingTicket().getRenderedElement());
}