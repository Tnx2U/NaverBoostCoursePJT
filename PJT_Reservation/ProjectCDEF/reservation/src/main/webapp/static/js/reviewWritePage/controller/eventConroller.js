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

    }

    static handleUploadImage(){
        console.log("handleUploadImage Event active");
    }

    static handleSubmitForm(){

    }
}