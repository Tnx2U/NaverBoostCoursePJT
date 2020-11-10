// 함수 순서 정리 및 파일분할하기

// -------- 전역 변수 영역 -------- 
let globalCategoryId = null;
let globalLoadCount = 0;


// -------- 간단한 html 템플릿 함수 영역 --------
function getTabHtml(category) {
    return `<li class="item" data-category="${category.id}"><a class="anchor"><span>${category.name}<span/></li>`;
}


// -------- AJAX 함수 영역 --------
function handleGetAjax(renderFunction, target, params) {
    let xhRequest = new XMLHttpRequest();
    const baseUrl = `http://localhost:8080/reservation/api/${target}`;
    let paramUrl = "";

    if (params != null) {
        paramUrl += "?";
        for (key in params) {
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
    xhRequest.open('GET', baseUrl + paramUrl);
    xhRequest.send();
}


// -------- 이벤트 핸들러 함수 영역 --------
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
        let productParmas = { "categoryId": (globalCategoryId != null) ? globalCategoryId : "", "start": globalLoadCount };
        handleGetAjax(renderProductList, "products", productParmas);
    })
}

// 기존의 html 요소들 이벤트 등록
function setInitialEventListener() {
    handleClickMoreButton();
}


// -------- 렌더 함수 영역 --------
//불러온 카테고리 정보로 탭을 랜더
function renderCategoryList(response) {
    let categoryList = response.items;
    let eventTabElement = document.querySelector(".event_tab_lst");

    categoryList.forEach((category) => {
        eventTabElement.innerHTML += getTabHtml(category);
    })

    handleCategoryClick();
}

// product 랜더
function renderProductList(response) {
    const productList = response.items;
    const totalCount = response.totalCount;

    let htmlTemplate = document.querySelector("#itemList").innerHTML;
    
    document.querySelector(".event_lst_txt > .pink").innerText = `${totalCount}개`;

    productList.forEach((product, index) => {
        let childNumber = index % 2 + 1;
        targetElement = document.querySelector(`.lst_event_box:nth-child(${childNumber})`);
        targetElement.innerHTML += htmlTemplate.replace("{productId}", product.productId)
            .replaceAll("{productDescription}", product.productDescription)
            .replace("{placeName}", product.placeName)
            .replace("{productContent}", product.productContent)
            .replace("{productImageUrl}", product.productImageUrl);
        globalLoadCount++;
    })

    if (totalCount == globalLoadCount) {
        hideMoreButton();
    }
}

// 프로모션 랜더
function renderPromotionList(response) {
    console.log(response);
}


// -------- 기타 파생 이벤트 영역 --------
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


// -------- 초기 실행 함수 --------
document.addEventListener("DOMContentLoaded", function() {
    let productParmas = { "start": 0 };

    //카테고리 리스트 로딩
    handleGetAjax(renderCategoryList, "categories");

    //초기 제품리스트 로딩
    handleGetAjax(renderProductList, "products", productParmas);
    //handleGetAjax(renderPromotionList, "promotions");
    setInitialEventListener();
})