import DataController from './dataConroller.js';
import { handlePostAjax } from '../../share/ajaxHandler.js';

export default class EventController {

    // ------------- 이벤트 헨들러 셋팅 함수 영역------------
    static setReviewWriteEvent() {
        this.handleUploadImage();
    }

    // ------------- 이벤트 헨들러 함수 영역 -------------

    static handleUploadImage(){
        console.log("handleUploadImage Event active");
    }
}