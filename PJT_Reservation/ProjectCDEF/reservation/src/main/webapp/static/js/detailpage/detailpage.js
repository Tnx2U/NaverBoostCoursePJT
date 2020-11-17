import { SERVER_IP, TARGET_PORT } from '/reservation/static/js/share/properties.js';
import { getUrlParams } from '/reservation/static/js/share/util.js';

// 이벤트 처리 함수 영역
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

function setEventhandler(){
    handleClickInfoTab();
    //펼쳐보기 추가

}

function handleClickInfoTab(){
    const infoTabListElement = document.querySelector(".info_tab_lst");

    infoTabListElement.addEventListener('click', function(event){
        let clickedTabElement = null;
    
        if(event.target.tagName === 'SPAN'){
            clickedTabElement = event.target.parentNode.parentNode;
            console.log(event.target);
        }else if(event.target.tagName === 'A'){
            clickedTabElement = event.target.parentNode;
            console.log(event.target);
        }else if(event.target.tagName === 'LI'){
            clickedTabElement = event.target;
            console.log(event.target);
        }
        
        //예외처리
        if(clickedTabElement == null){
            window.alert("탭 선택 이벤트에서 문제가 발생했습니다. Console을 확인해 주세요.");
            console.log(`Event Error Occured in handleClickInfoTab(). eventTarget is ${event.target}`);
            return;
        }
        
        //본인 및 하위 a태그 앵커 설정
        // 전체 삭제
        let infoTabLiElements = document.querySelectorAll(".info_tab_lst > li");
        infoTabLiElements.forEach((infoTabLi) => {
            infoTabLi.classList.remove("active");
            infoTabLi.querySelector("a").classList.remove("active");
        })
        // 선택된 태그만 active 추가
        clickedTabElement.classList.add("active");
        clickedTabElement.querySelector("a").classList.add("active");

        //선택된 탭의 detail_area_wrap, detail_location을 판단해 적절히 hide 클래스 추가,제거
        if(clickedTabElement.classList.contains("_detail")){
            document.querySelector(".detail_area_wrap").classList.remove("hide");
            document.querySelector(".detail_location").classList.add("hide");
        }else if(clickedTabElement.classList.contains("_path")){
            document.querySelector(".detail_location").classList.remove("hide");
            document.querySelector(".detail_area_wrap").classList.add("hide");
        }
    })


    // eventTabElement.addEventListener('click', function(event) {
    //     let selectCategoryId = -1;
    //     let params;
    //     if (event.target.tagName === 'SPAN') {
    //         selectCategoryId = event.target.parentNode.parentNode.getAttribute("data-category");
    //     } else if (event.target.tagName === 'A') {
    //         selectCategoryId = event.target.parentNode.getAttribute("data-category");
    //     } else if (event.target.tagName === 'LI') {
    //         selectCategoryId = event.target.getAttribute("data-category");
    //     }

    //     globalCategoryId = selectCategoryId;
    //     params = {
    //         "categoryId": (selectCategoryId != null) ? selectCategoryId : "",
    //         "start": 0,
    //     }
}


// ------------ 렌더 함수 영역 ----------------
function renderProductDetail(response){
    renderStoreDetail(response.displayInfo);
    renderEvent(response.displayInfo);
    renderComment(response.comments, response.averageScore);
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

function getValueWidth(score){
    score *= 20; 
    return score.toFixed(1);
}

function renderComment(comments, averageScore){
    //데이터 전처리
    const modifyComments = {
        comments : comments.slice(0,3),
        commentsLength : comments.length,
        averageScore : averageScore.toFixed(1),
        graphValueWidth : getValueWidth(averageScore),
    }

    // comment부분만 사용할 수 있도록 수정, average 속성 추가, width를 위한 폭 값 추가
    // 3개만 나오도록 설정

    let reviewElement = document.querySelector(".short_review_area");

    let commentGradeTemplate = document.querySelector("#template_grade_area").innerText;
    let bindCommentGradeTemplate = Handlebars.compile(commentGradeTemplate);
    let commentGradeHtmlResult = bindCommentGradeTemplate(modifyComments);
    reviewElement.innerHTML += commentGradeHtmlResult;

    let commentListTemplate = document.querySelector("#template_list_short_review").innerText;
    let bindCommentListTemplate = Handlebars.compile(commentListTemplate);
    let commentListHtmlResult = bindCommentListTemplate(modifyComments);
    reviewElement.innerHTML += commentListHtmlResult;
}

document.addEventListener("DOMContentLoaded", function() {
    const params = getUrlParams();
    console.log(params);

    //displayDetail 정보 로딩
    handleGetAjax(renderProductDetail, `products/${params.displayInfoId}`);
    setEventhandler();
})