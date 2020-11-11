// -------- 기타 파생 이벤트 영역 --------
//카테고리 클릭시 이벤트
function handleCategoryClick() {
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

        globalCategoryId = selectCategoryId;
        params = {
            "categoryId": (selectCategoryId != null) ? selectCategoryId : "",
            "start": 0,
        }

        //TODO : 예매가능 행사 표시
        showMoreButton();
        removeProduct();
        handleGetAjax(renderProductList, "products", params);
        toggleCategoryActive(selectCategoryId);
    })
}

// 더보기 버튼 클릭 이벤트
function handleClickMoreButton() {
    document.querySelector("#button_more").addEventListener("click", function() {
        const productParmas = { "categoryId": (globalCategoryId != null) ? globalCategoryId : "", "start": globalLoadCount };
        handleGetAjax(renderProductList, "products", productParmas);
    })
}

// 기존의 html 요소들 이벤트 등록
function setInitialEventListener() {
    handleClickMoreButton();
}

// -------- 기타 파생 이벤트 영역 --------
//카로셀 뷰 구현을 위한 이벤트
function setCarouselEvent(){
    let promotionUlElement = document.querySelector(".visual_img");
    let promotionLiElements = document.querySelectorAll(".visual_img > .item");
    let itemlength = promotionLiElements.length-1;
    promotionUlElement.style.right = "0px";
    
    //TODO : 마지막 도달시 계속 같은방향으로 부드럽게 나타나도록 구현
    setInterval(function(){
        let nextRight = parseInt(promotionUlElement.style.right) + 414; 
        if(nextRight >= 414*itemlength){
            nextRight = 0;
        }
        promotionUlElement.style.right = `${nextRight}px`;
    }, 3000);
}


// 클릭된 카테고리에 active클래스 추가
function toggleCategoryActive(selectCategoryId) {
    selectCategoryId = Number(selectCategoryId) + 1;
    let eventTabElement = document.querySelector(".event_tab_lst");
    eventTabElement.querySelector(".anchor.active").classList.remove("active");
    eventTabElement.querySelector(`li:nth-child(${selectCategoryId}) a`).classList.add("active");
}

function removeProduct() {
    document.querySelector(`.lst_event_box:nth-child(1)`).innerHTML = "";
    document.querySelector(`.lst_event_box:nth-child(2)`).innerHTML = "";
    globalLoadCount = 0;
}

function hideMoreButton() {
    document.querySelector(".more").style.display = "none";
}

function showMoreButton() {
    document.querySelector(".more").style.display = "block";
}