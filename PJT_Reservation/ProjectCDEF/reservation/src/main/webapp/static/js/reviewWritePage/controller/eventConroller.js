import DataController from './dataConroller.js';
import { handlePostAjax } from '../../share/ajaxHandler.js';

export default class EventController {

    // ------------- 이벤트 헨들러 셋팅 함수 영역------------
    static setReviewWriteEvent() {
        this.handleClickScore();
        this.handleWriteComment();
        this.handleUploadImage();
        this.handleSubmitForm();
    }

    // ------------- 이벤트 헨들러 함수 영역 -------------
    static handleClickScore(){
        const checkboxElements = document.querySelector(".rating");
        const starRankElement = document.querySelector(".star_rank");

        checkboxElements.addEventListener("click", (event) => {
            event.preventDefault(); 
            let clickedScore = -1;

            if(event.target.tagName === 'INPUT'){
                clickedScore = event.target.value;
            }else{
                return;
            }

            //checked 클래스 설정
            checkboxElements.childNodes.forEach(element => {
                if(element.tagName === 'INPUT' && element.value <= clickedScore){
                    element.classList.add('checked');
                }else if(element.tagName === 'INPUT' && element.value > clickedScore){
                    element.classList.remove('checked');
                }
            });

            starRankElement.classList.remove("gray_star");
            starRankElement.innerText = clickedScore;

            // data score 설정
            DataController.setScore(clickedScore);
        })
    }

    static handleWriteComment(){
        const reviewContentsElement = document.querySelector(".review_contents");
        const reviewInfoElement = document.querySelector(".review_write_info");
        const reviewTextAreaElement = document.querySelector(".review_textarea");
        const guideReviewLengthElement = document.querySelector(".guide_review > span");

        reviewContentsElement.addEventListener('click', (event) =>{
            reviewInfoElement.style.display = "none";
            reviewTextAreaElement.focus();
        })

        reviewTextAreaElement.addEventListener('focusout', (event) => {
            let CommentLengthChecker = /.{5,400}/;

            if(reviewTextAreaElement.value == ""){
                reviewInfoElement.style.display = "block";
                return;
            }

            if(CommentLengthChecker.test(reviewTextAreaElement.value)){
                DataController.setComment(reviewTextAreaElement.value);
            }else{
                alert("글자수가 올바르지 않습니다.");
                reviewTextAreaElement.focus();
            }
        })

        reviewTextAreaElement.addEventListener('input', (event) => {
            guideReviewLengthElement.innerText = reviewTextAreaElement.value.length;
        })
    }

    static handleUploadImage(){
        console.log("handleUploadImage Event active");
    }

    static handleSubmitForm(){

    }
}