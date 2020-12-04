export default class ReviewFooter {
    constructor() {}

    getRenderedElement() {
        // 컴포넌트 기본 src 
        let reviewFooterElement = document.createElement("div");
        reviewFooterElement.classList.add("review_write_footer_wrap");
        reviewFooterElement.innerHTML = this.getHtmlSrc();

        return reviewFooterElement;
    }

    getHtmlSrc() {
        return `
        <div class="review_write_footer">
            <label class="btn_upload" for="reviewImageFileOpenInput">
                <i class="fn fn-image1" aria-hidden="true"></i> <span
                class="text_add_photo">사진 추가</span>
            </label> 
            <input type="file" name="img" class="hidden_input" id="input_image">
            <div class="guide_review">
                <span>0</span>/400 <span>(최소5자이상)</span>
            </div>
        </div>

        <!-- 리뷰 포토 -->
        <div class="review_photos review_photos_write">
            <div class="item_preview_thumbs">
                <ul class="lst_thumb">
                    <li class="item" style="display: none;">
                        <a class="anchor"> <span class="spr_book ico_del">삭제</span></a>
                        <!-- 썸네일 이미지 -->
                        <span class="img_border"></span>
                    </li>
                </ul>
            </div>
        </div>
        <!-- //리뷰 포토 -->
        `;
    }
}