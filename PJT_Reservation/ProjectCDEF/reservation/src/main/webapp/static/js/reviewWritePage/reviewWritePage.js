import DataController from './controller/dataConroller.js';
import EventController from './controller/eventConroller.js';
import { getParamsByUrl } from '../share/util.js';
import ReviewWriteForm from './component/reviewWriteForm.js';

document.addEventListener("DOMContentLoaded", function () {
    const params = getParamsByUrl();

    initializePage(params);
})

function initializePage(params) {
    DataController.initializeData(params);
    renderComponents();
    setEventHandlers();
}

function renderComponents() {
    //타깃 dom 선언부
    const ctWrapElement = document.querySelector(".ct_wrap");

    //component 선언 호출부
    ctWrapElement.appendChild(new ReviewWriteForm().getRenderedElement());
}

function setEventHandlers() {
    EventController.setReviewWriteEvent();
}