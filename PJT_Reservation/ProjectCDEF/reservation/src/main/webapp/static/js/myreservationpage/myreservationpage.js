import { handleGetAjax } from '../share/ajaxHandler.js';
import { getParamsByUrl } from '../share/util.js';
import DataController from './controller/dataController.js';
import EventController from './controller/eventController.js';
import EmptyErr from './component/emptyErr.js';
import MySummary from './component/mySummary.js';
import MyList from './component/myList.js';

document.addEventListener("DOMContentLoaded", function () {
    const params = getParamsByUrl();

    handleGetAjax(initializePage, `reservations`, params);
})

function initializePage(reservationResponse){
    DataController.initializeData(reservationResponse);
    renderComponents();
    setEventHandlers();
}

function renderComponents(){
    const ctElement = document.querySelector(".section_my");

    if(DataController.getIsEmpty()){
        ctElement.appendChild(new EmptyErr().getRenderedElement());
    }else{
        ctElement.appendChild(new MySummary().getRenderedElement());
        ctElement.appendChild(new MyList().getRenderedElement());
    }
}

function setEventHandlers(){
    EventController.setMyReservationEvent(); 
}