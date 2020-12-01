
export default class EmptyErr {
    constructor() {}

    getRenderedElement() {
        let emptyErrElement = document.createElement("div");
        emptyErrElement.classList.add("err");
        emptyErrElement.innerHTML = this.getHtmlSrc();

        return emptyErrElement;
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