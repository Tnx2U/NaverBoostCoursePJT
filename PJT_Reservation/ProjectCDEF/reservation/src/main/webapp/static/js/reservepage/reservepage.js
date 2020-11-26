import DataController from "./datacontroller.js";
import { getParamsByUrl } from "../share/util.js";
import { handleGetAjax } from '../share/ajaxhandler.js';
import GroupVisual from './component/groupvisual.js';

document.addEventListener("DOMContentLoaded", function () {
    const params = getParamsByUrl();

    //displayDetail 정보 로딩
    handleGetAjax(initializePage, `products/${params.displayInfoId}`);
})

function initializePage(displayInfoResponse){
    DataController.initializeData(displayInfoResponse);
    renderComponents();
}

function renderComponents(){
    const ctWrapElement = document.querySelector(".ct_wrap");
    ctWrapElement.appendChild(new GroupVisual().getElement());
}