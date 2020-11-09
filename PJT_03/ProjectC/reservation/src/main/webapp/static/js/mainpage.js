function getTabHtml(category) {
    return `<li class="item" data-category="${category.id}"><a class="anchor"><span>${category.name}<span/></li>`;
}

function setCategoryEvent() {
    let eventTabElement = document.querySelector(".event_tab_lst");
    
    eventTabElement.addEventListener('click', function(event) {
        let selectCategoryId = -1;
        let params;
        if (event.target.tagName === 'SPAN') {
            selectCategoryId = event.target.parentNode.parentNode.getAttribute("data-category");
        } else if (event.target.tagName === 'A') {
            selectCategoryId = event.target.parentNode.getAttribute("data-category");
        } else if (event.target.tagName === 'LI') {
            selectCategoryId = event.target.getAttribute("data-category");
        }
        
        params = {
            "categoryId" : selectCategoryId,
            "start" : 0,
        }
        handleGetAjax(renderProductList, "products", params);
    })
}

function renderCategoryList(response) {
    let categoryList = response.items;
    let eventTabElement = document.querySelector(".event_tab_lst");

    //카테고리 리스트별로 반복하며 추가
    for (let idx = 0, len=categoryList.length; idx < len; idx++) {
        eventTabElement.innerHTML += getTabHtml(categoryList[idx]);
    }

    setCategoryEvent();
    //event deligation으로 해당 카테고리버튼별 이벤트 추가
}

function renderPromotionList(response) {
    console.log(response);
}

function renderProductList(response) {
    console.log(response);
}

function handleGetAjax(renderFunction, target, params) {
    let xhRequest = new XMLHttpRequest();
    const baseUrl = `http://localhost:8080/reservation/api/${target}`;
    let paramUrl = "";
    
    if(params != null){
        paramUrl += "?";
        for(key in params){
            paramUrl += `${key}=${params[key]}&`;
        }
    }

    xhRequest.onreadystatechange = function() {
        if (xhRequest.readyState === xhRequest.DONE) {
            if (xhRequest.status === 200 || xhRequest.status === 201) {
                renderFunction(JSON.parse(xhRequest.response));
            } else {
                console.error(xhRequest.status, xhRequest.responseText);
            }
        }
    };
    xhRequest.open('GET', baseUrl+paramUrl);
    xhRequest.send();
}

function sendInitPromotionAjax() {
    //ajax
    // renderPromotionList();
}

function sendInitProductAjax() {
    //ajax
    // renderProductList();
}

function handleClickMoreButton() {

}

function handleClickCategory() {

}

function setEventListener() {
}


document.addEventListener("DOMContentLoaded", function() {
    handleGetAjax(renderCategoryList, "categories");
    // handleAjax(renderPromotionList, "promotions");
    // handleAjax(renderProductList, "products");
    setEventListener();
})