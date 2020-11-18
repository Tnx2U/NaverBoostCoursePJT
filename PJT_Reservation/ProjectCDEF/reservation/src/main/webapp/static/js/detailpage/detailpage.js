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
    handleClickDetailMoreOpen();
    handleClickDetailMoreClose();
    handleClickImageButton();
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

function handleClickImageButton(){
    if(document.querySelector("div.prev") === null){
        return;
    }

    const containerElement = document.querySelector("div.container_visual");
    const ulElement = document.querySelector("ul.visual_img");
    const liElements = document.querySelectorAll(".visual_img > li");
    const imageLength = liElements.length;
    const pagenationElement = document.querySelector("span.num");
    const imageWidth = 414;
    const slideSpeed = 300;
    const imagePrevElement = document.querySelector("div.prev");
    const imageNextElement = document.querySelector("div.nxt");
    let initialIdx = 0;
    let currentIdx = initialIdx;

    ulElement.style.width = imageWidth*(imageLength + 2) + "px";

    // 트릭을 위해 첫번째와 마지막 슬라이드 deepCopy
    let firstImageClone = ulElement.firstElementChild.cloneNode(true);
    let lastImageClone = ulElement.lastElementChild.cloneNode(true);

    // 복제한 슬라이드를 기존 슬라이드 리스트의 앞뒤에 추가
    ulElement.appendChild(firstImageClone);
    ulElement.insertBefore(lastImageClone, ulElement.firstElementChild);

    //사전 스타일 설정 및 초기화
    console.log((imageWidth * (initialIdx)));
    ulElement.style.transform = "translate3d(-" + (imageWidth * (initialIdx + 1)) + "px, 0px, 0px)";

    let currentImageElement = liElements[currentIdx];
    currentImageElement.classList.add('image_active');

    imageNextElement.addEventListener("click", function(){
        //이미지 좌측으로 이동
        if(currentIdx <= imageLength - 1){
            ulElement.style.transition = slideSpeed + "ms";
            ulElement.style.transform = "translate3d(-" + (imageWidth * (currentIdx + 2)) + "px, 0px, 0px)";
        }

        if(currentIdx === imageLength - 1){
            setTimeout(function(){
                ulElement.style.transition = "0ms";
                ulElement.style.transform = "translate3d(-" + imageWidth + "px, 0px, 0px)";
            }, slideSpeed);
            currentIdx = -1;
        }

        currentImageElement.classList.remove('image_active');
        currentImageElement = liElements[++currentIdx];
        currentImageElement.classList.add('image_active');
        //페이지네이션 수정 
        pagenationElement.innerText = currentIdx+1;
    });

    imagePrevElement.addEventListener("click", function(){
        //이미지 좌측으로 이동
        if(currentIdx >= 0){
            ulElement.style.transition = slideSpeed + "ms";
            ulElement.style.transform = "translate3d(-" + (imageWidth * currentIdx) + "px, 0px, 0px)";
        }

        if(currentIdx === 0){
            setTimeout(function(){
                ulElement.style.transition = "0ms";
                ulElement.style.transform = "translate3d(-" + (imageWidth*imageLength)+ "px, 0px, 0px)";
            }, slideSpeed);
            currentIdx = imageLength;
        }

        currentImageElement.classList.remove('image_active');
        currentImageElement = liElements[--currentIdx];
        currentImageElement.classList.add('image_active');
        //페이지네이션 수정
        pagenationElement.innerText = currentIdx+1;
    });
}


// ------------ 렌더 함수 영역 ----------------
function renderProductDetail(response){
    console.log("response : ", response);
    renderStoreDetail(response.displayInfo);
    renderEvent(response.displayInfo);
    renderComment(response.comments, response.averageScore);
    renderDetailArea(response.displayInfo);
    renderDetailLocation(response);
    renderPagenation(response.productImages.length);
    renderVisualImage(response.productImages, response.displayInfo.productDescription);

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

//페이지네이션 렌더
function renderPagenation(productImagesLength){
    //이미지가 2장 이상이더라도 2장까지만 표시하라는 명세서 항목반영
    const productImagesInfo = {
        productImagesLength : (productImagesLength >= 2) ? 2 : null,
    }

    let pagenationTemplate = document.querySelector("#template_pagination").innerText;
    let bindPagenationTemplate = Handlebars.compile(pagenationTemplate);
    let htmlResult = bindPagenationTemplate(productImagesInfo);
    
    let pagenationElement = document.querySelector(".pagination");
    pagenationElement.innerHTML += htmlResult;
}

function renderVisualImage(productImages, productDescription){
    //이미지가 2장 이상이더라도 2장까지만 표시하라는 명세서 항목반영
    const productImagesInfo = {
        productImages : productImages.map((element, index) =>{
                element.productDescription = productDescription;
                return element;
            }).slice(0,2),
        productDescription : productDescription,
        hasExtraImage : (productImages.length <= 1) ? false : true,
   }

    let visualImageTemplate = document.querySelector("#template_group_visual").innerText;
    let bindVisualImageTemplate = Handlebars.compile(visualImageTemplate);
    let htmlResult = bindVisualImageTemplate(productImagesInfo);
    
    let visualImageElement = document.querySelector(".group_visual");
    visualImageElement.innerHTML += htmlResult;
}

// ------------ 초기 실행 영역 ---------------
document.addEventListener("DOMContentLoaded", function() {
    const params = getUrlParams();

    //displayDetail 정보 로딩
    handleGetAjax(renderProductDetail, `products/${params.displayInfoId}`);
})