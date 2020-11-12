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
    const promotionList = response.items;
    let htmlTemplate = document.querySelector("#promotionItem").innerHTML;
    let targetElement = document.querySelector(".visual_img");
    
    promotionList.forEach((promotion) => {
        targetElement.innerHTML += htmlTemplate.replace("{productImageUrl}", promotion.productImageUrl);
    })
    
    //TODO : 한쪽으로 이동구현
    setCarouselEvent();
}