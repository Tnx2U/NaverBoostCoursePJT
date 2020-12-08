export default class ReviewSubmit {
    constructor() {}

    getRenderedElement() {
        let reviewSubmitElement = document.createElement("div");
        reviewSubmitElement.classList.add("box_bk_btn");
        reviewSubmitElement.innerHTML = this.getHtmlSrc();

        return reviewSubmitElement;
    }

    getHtmlSrc() {
        return `
            <button class="bk_btn">
                <span class="btn_txt">리뷰 등록</span>
            </button>
        `;
    }
}