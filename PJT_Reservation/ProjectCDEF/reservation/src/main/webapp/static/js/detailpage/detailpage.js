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
    handleClickDetailMoreOpen();
    handleClickDetailMoreClose();
}

function handleClickDetailMoreOpen(){
    const bkMoreOpenElement = document.querySelector(".bk_more._open");

    bkMoreOpenElement.addEventListener('click', function(){
        document.querySelector(".bk_more._open").style.display = "none";
        document.querySelector(".bk_more._close").style.display = "block";
        document.querySelector(".store_details").classList.remove("close3");
    })
}

function handleClickDetailMoreClose(){
    const bkMoreCloseElement = document.querySelector(".bk_more._close");

    bkMoreCloseElement.addEventListener('click', function(){
        document.querySelector(".bk_more._close").style.display = "none";
        document.querySelector(".bk_more._open").style.display = "block";
        document.querySelector(".store_details").classList.add("close3");
    })
}

function handleClickInfoTab(){
    const infoTabListElement = document.querySelector(".info_tab_lst");

    infoTabListElement.addEventListener('click', function(event){
        let clickedTabElement = null;
    
        if(event.target.tagName === 'SPAN'){
            clickedTabElement = event.target.parentNode.parentNode;
        }else if(event.target.tagName === 'A'){
            clickedTabElement = event.target.parentNode;
        }else if(event.target.tagName === 'LI'){
            clickedTabElement = event.target;
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
}


// ------------ 렌더 함수 영역 ----------------
function renderProductDetail(response){
    console.log("response : ", response);
    renderStoreDetail(response.displayInfo);
    renderEvent(response.displayInfo);
    renderComment(response.comments, response.averageScore);
    renderDetailArea(response.displayInfo);
    renderDetailLocation(response);

    //렌더가 완료된 이후 이벤트 핸들러 세팅
    setEventhandler();
}

// 상단 상세설명 렌더
function renderStoreDetail(displayInfo){
    let storeDetailTemplate = document.querySelector("#template_store_detail").innerText;
    let bindStoreDetailTemplate = Handlebars.compile(storeDetailTemplate);
    let htmlResult = bindStoreDetailTemplate(displayInfo);
    
    let storeDetailElement = document.querySelector(".section_store_details");
    storeDetailElement.innerHTML += htmlResult;
}

// 이벤트 렌더
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

// 한줄평 렌더
function renderComment(comments, averageScore){
    const modifyComments = {
        comments : comments.slice(0,3),
        commentsLength : comments.length,
        averageScore : averageScore.toFixed(1),
        graphValueWidth : getValueWidth(averageScore),
    }

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

// 상세정보 탭 렌더
function renderDetailArea(displayInfo){
    let detailAreaTemplate = document.querySelector("#template_detail_area").innerText;
    let bindDetailAreaTemplate = Handlebars.compile(detailAreaTemplate);
    let htmlResult = bindDetailAreaTemplate(displayInfo);
    
    let detailAreaElement = document.querySelector(".detail_area_wrap");
    detailAreaElement.innerHTML += htmlResult;
}

// 오시는길 탭 렌더
function renderDetailLocation(response){
    let detailLocationTemplate = document.querySelector("#template_detail_location").innerText;
    let bindDetailLocationTemplate = Handlebars.compile(detailLocationTemplate);
    let htmlResult = bindDetailLocationTemplate(response);
    
    let detailLocationElement = document.querySelector(".detail_location");
    detailLocationElement.innerHTML += htmlResult;
}

// ------------ 초기 실행 영역 ---------------
document.addEventListener("DOMContentLoaded", function() {
    const params = getUrlParams();

    //displayDetail 정보 로딩
    handleGetAjax(renderProductDetail, `products/${params.displayInfoId}`);
})