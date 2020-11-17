import { SERVER_IP, TARGET_PORT } from '/reservation/static/js/share/properties.js';
import { getUrlParams } from '/reservation/static/js/share/util.js';

function handleGetAjax(renderFunction, target, params) {
    let xhRequest = new XMLHttpRequest();
    const baseUrl = `http://${SERVER_IP}:${TARGET_PORT}/reservation/api/${target}`;
    let paramUrl = "";

    if (params != null) {
        paramUrl += "?";
        for (key in params) {
            paramUrl += `${key}=${params[key]}&`;
            // js에서 맨 뒤의 params문법 삭제해주는 라이브러리? 기능 공부
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

// ------------ 렌더 함수 영역 ----------------
function renderProductDetail(response){
    renderStoreDetail(response.displayInfo);
    renderEvent(response.displayInfo);
    renderComment(response.comments);
    console.log(response);
}

function renderStoreDetail(displayInfo){
    let storeDetailTemplate = document.querySelector("#template_store_detail").innerText;
    let bindStoreDetailTemplate = Handlebars.compile(storeDetailTemplate);
    let htmlResult = bindStoreDetailTemplate(displayInfo);
    
    let storeDetailElement = document.querySelector(".section_store_details");
    storeDetailElement.innerHTML += htmlResult;
}

function renderEvent(displayInfo){
    let eventTemplate = document.querySelector("#template_event").innerText;
    let bindEventTemplate = Handlebars.compile(eventTemplate);
    let htmlResult = bindEventTemplate(displayInfo);
    
    let eventElement = document.querySelector(".section_event");
    eventElement.innerHTML += htmlResult;
}

function renderComment(comments){
    //데이터 전처리
    const modifyComments = {
        comments : comments,
        graphValueWidth : 
    }

    // comment부분만 사용할 수 있도록 수정, average 속성 추가, width를 위한 폭 값 추가
    // 3개만 나오도록 설정

    let reviewElement = document.querySelector(".short_review_area");

    let commentGradeTemplate = document.querySelector("#template_grade_area").innerText;
    let bindCommentGradeTemplate = Handlebars.compile(commentGradeTemplate);
    let commentGradeHtmlResult = bindCommentGradeTemplate(displayInfo);
    reviewElement.innerHTML += commentGradeHtmlResult;

    let commentListTemplate = document.querySelector("#template_list_short_review").innerText;
    let bindCommentListTemplate = Handlebars.compile(commentListTemplate);
    let commentListHtmlResult = bindCommentListTemplate(displayInfo);
    reviewElement.innerHTML += commentListHtmlResult;
}

document.addEventListener("DOMContentLoaded", function() {
    const params = getUrlParams();
    console.log(params);

    //displayDetail 정보 로딩
    handleGetAjax(renderProductDetail, `products/${params.displayInfoId}`);
})