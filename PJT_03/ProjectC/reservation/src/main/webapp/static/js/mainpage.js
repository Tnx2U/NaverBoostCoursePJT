function getTabHtml(category){
    return `<li class="item" data-category="${category.id}"><a class="anchor"><span>${category.name}<span/></li>`;
}

function renderCategoryList(response){
    let categoryList = response.items;
    console.log(categoryList);
    let eventTabElement = document.querySelector(".event_tab_lst");
    
    //카테고리 리스트별로 반복하며 추가
    for(let idx = 0, len=categoryList.length; idx < len; idx++){
        eventTabElement.innerHTML += getTabHtml(categoryList[idx]);
    }

    //event deligation으로 해당 카테고리버튼별 이벤트 추가
    eventTabElement.addEventListener('click', function(event){
        console.log(event.target);
        if(event.target.tagName === 'span'){
            console.log(event.target.parentNode.parentNode.attr("data-category"));
        }else if(event.target.tagName === 'a'){
            console.log(event.target.parentNode.attr("data-category"));
        }
    })

}

function renderPromotionList(response){
    console.log(response);
}

function renderProductList(response){
    console.log(response);
}

function handleAjax(renderFunction, target){
    let xhRequest = new XMLHttpRequest();
    const baseUrl = `http://localhost:8080/reservation/api/${target}`;
    
    xhRequest.onreadystatechange = function() {
        if (xhRequest.readyState === xhRequest.DONE) {
            if (xhRequest.status === 200 || xhRequest.status === 201) {
                renderFunction(JSON.parse(xhRequest.response));
            } else {
                console.error(xhRequest.status, xhRequest.responseText);
            }
        }
    };
    xhRequest.open('GET', baseUrl);
    xhRequest.send();
}

function sendInitPromotionAjax(){
    //ajax
    // renderPromotionList();
}

function sendInitProductAjax(){
    //ajax
    // renderProductList();
}

function handleClickMoreButton(){

}

function handleClickCategory(){

}

function setEventListener(){
}


 document.addEventListener("DOMContentLoaded", function(){
    handleAjax(renderCategoryList, "categories");
    // handleAjax(renderPromotionList, "promotions");
    // handleAjax(renderProductList, "products");
    setEventListener();
 })