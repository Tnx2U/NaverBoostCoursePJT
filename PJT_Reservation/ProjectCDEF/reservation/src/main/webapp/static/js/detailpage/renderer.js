import { setEventhandler } from './eventhandler.js';

function renderProductDetail(response) {
    console.log("response : ", response);
    renderStoreDetail(response.displayInfo);
    renderEvent(response.displayInfo);
    renderComment(response.comments, response.averageScore);
    renderDetailArea(response.displayInfo);
    renderDetailLocation(response);
    renderPagenation(response.productImages.length);
    renderVisualImage(response.productImages, response.displayInfo.productDescription);
    renderMoreComment(response.displayInfo);

    //렌더가 완료된 이후 이벤트 핸들러 세팅
    setEventhandler();
}

// 한줄평 더보기 렌더
function renderMoreComment(displayInfo) {
    let moreCommentTemplate = document.querySelector("#template_more_comment").innerText;
    let bindMoreCommentTemplate = Handlebars.compile(moreCommentTemplate);
    let htmlResult = bindMoreCommentTemplate(displayInfo);

    let reviewListElement = document.querySelector(".section_review_list");
    reviewListElement.innerHTML += htmlResult;

}

// 상단 상세설명 렌더
function renderStoreDetail(displayInfo) {
    let storeDetailTemplate = document.querySelector("#template_store_detail").innerText;
    let bindStoreDetailTemplate = Handlebars.compile(storeDetailTemplate);
    let htmlResult = bindStoreDetailTemplate(displayInfo);

    let storeDetailElement = document.querySelector(".section_store_details");
    storeDetailElement.innerHTML += htmlResult;
}

// 이벤트 렌더
function renderEvent(displayInfo) {
    let eventTemplate = document.querySelector("#template_event").innerText;
    let bindEventTemplate = Handlebars.compile(eventTemplate);
    let htmlResult = bindEventTemplate(displayInfo);

    let eventElement = document.querySelector(".section_event");
    eventElement.innerHTML += htmlResult;
}

function getValueWidth(score) {
    score *= 20;
    return score.toFixed(1);
}

// 한줄평 렌더
function renderComment(comments, averageScore) {
    const modifyComments = {
        comments: comments.slice(0, 3),
        commentsLength: comments.length,
        averageScore: averageScore.toFixed(1),
        graphValueWidth: getValueWidth(averageScore),
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
function renderDetailArea(displayInfo) {
    let detailAreaTemplate = document.querySelector("#template_detail_area").innerText;
    let bindDetailAreaTemplate = Handlebars.compile(detailAreaTemplate);
    let htmlResult = bindDetailAreaTemplate(displayInfo);

    let detailAreaElement = document.querySelector(".detail_area_wrap");
    detailAreaElement.innerHTML += htmlResult;
}

// 오시는길 탭 렌더
function renderDetailLocation(response) {
    let detailLocationTemplate = document.querySelector("#template_detail_location").innerText;
    let bindDetailLocationTemplate = Handlebars.compile(detailLocationTemplate);
    let htmlResult = bindDetailLocationTemplate(response);

    let detailLocationElement = document.querySelector(".detail_location");
    detailLocationElement.innerHTML += htmlResult;
}

//페이지네이션 렌더
function renderPagenation(productImagesLength) {
    //이미지가 2장 이상이더라도 2장까지만 표시하라는 명세서 항목반영
    const productImagesInfo = {
        productImagesLength: (productImagesLength >= 2) ? 2 : null,
    }

    let pagenationTemplate = document.querySelector("#template_pagination").innerText;
    let bindPagenationTemplate = Handlebars.compile(pagenationTemplate);
    let htmlResult = bindPagenationTemplate(productImagesInfo);

    let pagenationElement = document.querySelector(".pagination");
    pagenationElement.innerHTML += htmlResult;
}

function renderVisualImage(productImages, productDescription) {
    //이미지가 2장 이상이더라도 2장까지만 표시하라는 명세서 항목반영
    const productImagesInfo = {
        productImages: productImages.map((element, index) => {
            element.productDescription = productDescription;
            return element;
        }).slice(0, 2),
        productDescription: productDescription,
        hasExtraImage: (productImages.length <= 1) ? false : true,
    }

    let visualImageTemplate = document.querySelector("#template_group_visual").innerText;
    let bindVisualImageTemplate = Handlebars.compile(visualImageTemplate);
    let htmlResult = bindVisualImageTemplate(productImagesInfo);

    let visualImageElement = document.querySelector(".group_visual");
    visualImageElement.innerHTML += htmlResult;
}

export {
    renderProductDetail, renderMoreComment, renderStoreDetail,
    renderEvent, renderComment, renderDetailArea, renderDetailLocation,
    renderPagenation, renderVisualImage
};