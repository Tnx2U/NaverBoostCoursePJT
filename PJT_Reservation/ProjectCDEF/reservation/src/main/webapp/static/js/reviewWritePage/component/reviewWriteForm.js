import ReviewScore from './reviewScore.js';
import ReviewContent from './reviewContent.js';
import ReviewFooter from './reviewFooter.js';
import ReviewSubmit from './reviewSubmit.js';

export default class ReviewWriteForm {
    constructor() {}

    getRenderedElement() {
        // 컴포넌트 기본 src 
        let reviewFormElement = document.createElement("div");
        reviewFormElement.classList.add("review_write_form");

        // 자식 컴포넌트 바인딩
        reviewFormElement.appendChild(new ReviewScore().getRenderedElement());
        reviewFormElement.appendChild(new ReviewContent().getRenderedElement());
        reviewFormElement.appendChild(new ReviewFooter().getRenderedElement());
        reviewFormElement.appendChild(new ReviewSubmit().getRenderedElement());

        return reviewFormElement;
    }
}