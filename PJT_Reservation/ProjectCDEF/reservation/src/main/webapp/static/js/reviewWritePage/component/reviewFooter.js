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
            </label> <input type="file" class="hidden_input"
                id="reviewImageFileOpenInput" accept="image/*" multiple>
            <div class="guide_review">
                <span>0</span>/400 <span>(최소5자이상)</span>
            </div>
        </div>

        <!-- 리뷰 포토 -->
        <div class="review_photos review_photos_write">
            <div class="item_preview_thumbs">
                <ul class="lst_thumb">
                    <li class="item" style="display: none;"><a href="#"
                        class="anchor"> <span class="spr_book ico_del">삭제</span>
                    </a> <img
                        src="http://naverbooking.phinf.naver.net/20170306_3/1488772023601A4195_JPEG/image.jpg?type=f300_300"
                        width="130" alt="" class="item_thumb"> <span
                        class="img_border"></span></li>
                </ul>
            </div>
        </div>
        <!-- //리뷰 포토 -->
        `;
    }
}