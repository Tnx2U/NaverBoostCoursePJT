// import { handleGetAjax } from '/reservation/static/js/ajaxhandler.js';

// -------- 전역 변수 영역 -------- 
let globalCategoryId = null;
let globalLoadCount = 0;
let promotionElementLength = 0;

// -------- 초기 실행 함수 --------
document.addEventListener("DOMContentLoaded", function() {
    let productParmas = { "start": 0 };

    //카테고리 리스트 로딩
    handleGetAjax(renderCategoryList, "categories");

    //초기 제품리스트 로딩
    handleGetAjax(renderProductList, "products", productParmas);
    
    //초기 프로모션 로딩
    handleGetAjax(renderPromotionList, "promotions");
    setInitialEventListener();
})