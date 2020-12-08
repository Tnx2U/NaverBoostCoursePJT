export default class ReviewContent {
    constructor() {}

    getRenderedElement() {
        let reviewContentElement = document.createElement("div");
        reviewContentElement.classList.add("review_contents","write");
        reviewContentElement.innerHTML = this.getHtmlSrc();

        return reviewContentElement;
    }

    getHtmlSrc() {
        return `
            <!-- [D] review_write_info 클릭 시 자신을 숨기고 review_textarea 에 focus를 보낸다. -->
            <a href="#" class="review_write_info"> <span class="middot">
                    실 사용자의 리뷰는 상품명의 더 나은 서비스 제공과 다른 사용자들의 선택에 큰 도움이 됩니다. </span><br> <span
                class="middot"> 소중한 리뷰에 대한 감사로 네이버페이 포인트 500원을 적립해드립니다. </span> <span
                class="left_space">(단, 리뷰 포인트는 ID 당 1일 최대 5건까지 지급됩니다.)</span>
            </a>
            <textarea cols="30" rows="10" class="review_textarea"></textarea>
        `;
    }
}