export default class ReviewScore {
    constructor() {}

    getRenderedElement() {
        let reviewScoreElement = document.createElement("div");
        reviewScoreElement.classList.add("write_act");
        reviewScoreElement.innerHTML = this.getHtmlSrc();

        return reviewScoreElement;
    }

    getHtmlSrc() {
        return `
            <p class="title_star">별점과 이용경험을 남겨주세요.</p>
            <div class="review_rating rating_point">
                <div class="rating">
                    <!-- [D] 해당 별점이 선택될 때 그 점수 이하의 input엘리먼트에 checked 클래스 추가 -->
                    <input type="checkbox" name="rating2" value="1"
                        class="rating_rdo" title="1점"> <span class="span"></span>
                    <input type="checkbox" name="rating3" value="2"
                        class="rating_rdo" title="2점"> <span class="span"></span>
                    <input type="checkbox" name="rating4" value="3"
                        class="rating_rdo" title="3점"> <span class="span"></span>
                    <input type="checkbox" name="rating5" value="4"
                        class="rating_rdo" title="4점"> <span class="span"></span>
                    <input type="checkbox" name="rating6" value="5"
                        class="rating_rdo" title="5점"> <span class="span"></span>
                    <!-- [D] 0점일 때 gray_star 추기 -->
                    <span class="star_rank gray_star">0</span>
                </div>
            </div>
        `;
    }
}