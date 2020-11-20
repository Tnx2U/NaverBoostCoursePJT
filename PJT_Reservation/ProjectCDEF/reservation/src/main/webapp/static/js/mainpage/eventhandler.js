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

//카로셀 뷰 구현을 위한 이벤트
function setCarouselEvent(){
    let promotionUlElement = document.querySelector(".container_visual > .visual_img");
    let promotionLiElements = document.querySelectorAll(".visual_img > .item");
    let itemLength = promotionLiElements.length;
    let showIdx = 0;

    promotionUlElement.style.position = "relative";
        promotionLiElements.forEach((promotion,index) => {
            if(index == 0){
                promotion.style.zIndex ="20";
                promotion.style.right ="0";
            }
            promotion.style.position = "absolute";
            promotion.style.display = "inline-block";
    })

    
    //TODO : class 적용이 안되는 문제 추후 해결
    setInterval(function(){
        promotionLiElements.forEach((promotion, index) => {
            // promotion.classList.remove(".hide_promotion");
            // promotion.classList.remove(".show_promotion");
            promotion.style.zIndex ="0";
            promotion.style.right ="-414px";


            if(index == showIdx){
                //promotion.classList.add(".show_promotion");
                promotion.style.zIndex ="20";
                promotion.style.right ="0";
            }else if(index == showIdx - 1 || (index == itemLength-1 && showIdx==0)) {
                promotion.style.zIndex ="10";
                promotion.style.right ="414px";
                //promotion.classList.add(".hide_promotion");
            }
        });

        if(showIdx == itemLength-1){
            showIdx = 0;
        }else{
            showIdx++;
        }

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