export default class ReviewWriteForm {
    constructor() {}

    getRenderedElement() {
        // 컴포넌트 기본 src 
        let reviewFormElement = document.createElement("div");
        reviewFormElement.classList.add("review_write_form");
        reviewFormElement.innerHTML = this.getHtmlSrc();

        // 자식 컴포넌트 바인딩
        

        return reviewFormElement;
    }

    getHtmlSrc() {
        return `
            <div class="err">
                <i class="spr_book ico_info_nolist"></i>
                <h1 class="tit">예약 리스트가 없습니다</h1>
            </div>
        `;
    }
}