import DataController from './dataConroller.js';
import { handlePostAjaxMultipart } from '../../share/ajaxHandler.js';
import { getParamUrlByParams } from '../../share/util.js';

export default class EventController {

    // ------------- 이벤트 헨들러 셋팅 함수 영역------------
    static setReviewWriteEvent() {
        this.handleClickScore();
        this.handleWriteComment();
        this.handleUploadImage();
        this.handleClickCancelImage();
        this.handleSubmitForm();
    }

    // ------------- 이벤트 헨들러 함수 영역 -------------
    static handleClickScore() {
        const checkboxElements = document.querySelector(".rating");
        const starRankElement = document.querySelector(".star_rank");

        checkboxElements.addEventListener("click", (event) => {
            event.preventDefault();
            let clickedScore = -1;

            if (event.target.tagName === 'INPUT') {
                clickedScore = event.target.value;
            } else {
                return;
            }

            //checked 클래스 설정
            checkboxElements.childNodes.forEach(element => {
                if (element.tagName === 'INPUT' && element.value <= clickedScore) {
                    element.classList.add('checked');
                } else if (element.tagName === 'INPUT' && element.value > clickedScore) {
                    element.classList.remove('checked');
                }
            });

            starRankElement.classList.remove("gray_star");
            starRankElement.innerText = clickedScore;

            // data score 설정
            DataController.setScore(clickedScore);
        })
    }

    static handleWriteComment() {
        const reviewContentsElement = document.querySelector(".review_contents");
        const reviewInfoElement = document.querySelector(".review_write_info");
        const reviewTextAreaElement = document.querySelector(".review_textarea");
        const guideReviewLengthElement = document.querySelector(".guide_review > span");

        reviewContentsElement.addEventListener('click', (event) => {
            reviewInfoElement.style.display = "none";
            reviewTextAreaElement.focus();
        })

        reviewTextAreaElement.addEventListener('focusout', (event) => {
            let CommentLengthChecker = /.{5,400}/;

            if (reviewTextAreaElement.value == "") {
                reviewInfoElement.style.display = "block";
                return;
            }

            if (CommentLengthChecker.test(reviewTextAreaElement.value)) {
                DataController.setComment(reviewTextAreaElement.value);
            } else {
                alert("글자수가 올바르지 않습니다.");
                reviewTextAreaElement.focus();
            }
        })

        reviewTextAreaElement.addEventListener('input', (event) => {
            guideReviewLengthElement.innerText = reviewTextAreaElement.value.length;
        })
    }

    static handleUploadImage() {
        const inputImageElement = document.querySelector("#input_image");
        const thumnailLiElement = document.querySelector(".item_preview_thumbs li.item");

        inputImageElement.addEventListener("change", (event) => {
            console.log("event.target.files", event.target.files);
            const selectedImage = event.target.files[0];

            if (!this.validImageType(selectedImage)) {
                alert("지원하지 않는 이미지 확장자 입니다.");
                return;
            }

            // 기존에 선택된 이미지가 있으면 대체
            const thumnailImgElement = document.querySelector("li.item .item_thumb");
            if (thumnailImgElement != null) {
                thumnailImgElement.remove();
            }

            DataController.setImage(selectedImage);

            let thumnailImgElemnt = document.createElement("img");
            thumnailImgElemnt.classList.add("item_thumb");
            thumnailImgElemnt.src = window.URL.createObjectURL(selectedImage);
            thumnailImgElemnt.style.width = "130px";

            thumnailLiElement.appendChild(thumnailImgElemnt);
            thumnailLiElement.style.display = "inline-block";
        })
    }

    static handleClickCancelImage() {
        const inputImageElement = document.querySelector("#input_image");
        const thumnailLiElement = document.querySelector(".item_preview_thumbs li.item");
        const cancelAnchorElement = document.querySelector(".anchor");

        cancelAnchorElement.addEventListener('click', () => {
            const thumnailImgElement = document.querySelector("li.item .item_thumb");
            if (confirm("선택한 이미지 업로드를 취소하시겠습니까?")) {
                inputImageElement.value = null;
                thumnailImgElement.remove();
                thumnailLiElement.style.display = "none";
                DataController.setImage(null);
                alert("삭제하였습니다.");
            }
        })
    }

    static handleSubmitForm() {
        const submitButtonElement = document.querySelector(".bk_btn");
        const callbackFunc = this.handleSubmitResult;

        submitButtonElement.addEventListener('click', (event) => {
            event.preventDefault();

            let formData = new FormData();
            formData.append("attachedImage", DataController.getImage());

            console.log(formData.getAll("attachedImage"));
            formData.append("comment", DataController.getComment());
            formData.append("productId", DataController.getProductId());
            formData.append("score", DataController.getScore());

            const targetUrl = `reservations/${DataController.getReservationInfoId()}/comments`;
            handlePostAjaxMultipart(callbackFunc, targetUrl, formData);
        })
    }

    static handleSubmitResult(response) {
        console.log(`response : ${response}`);
    }

    // --------- 기타 지역 유틸 함수 ---------
    static validImageType(image) {
        const allowTypes = ['image/jpeg', 'image/jpg', 'image/png'];

        return allowTypes.indexOf(image.type) > -1;
    }
}