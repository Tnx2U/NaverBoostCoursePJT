import DataController from '../controller/dataController.js';


export default class MySummary {
    constructor() {
        this.data = DataController.getMySummaryData();
    }

    getRenderedElement() {
        let mySummaryElement = document.createElement("div");
        mySummaryElement.classList.add("my_summary");
        mySummaryElement.innerHTML = this.getHtmlSrc();

        return mySummaryElement;
    }

    getHtmlSrc() {
        return `
        <div class="my_summary">
            <ul class="summary_board">
                <li class="item" id="li_summary_total">
                    <!--[D] 선택 후 .on 추가 link_summary_board --> <a href="#"
                    class="link_summary_board on"> <i class="spr_book2 ico_book2"></i>
                        <em class="tit">전체</em> <span class="figure">${this.data.totalCount}</span>
                </a>
                </li>
                <li class="item" id="li_summary_confirm"><a href="#" class="link_summary_board">
                        <i class="spr_book2 ico_book_ss"></i> <em class="tit">이용예정</em>
                        <span class="figure">${this.data.confirmCount}</span>
                </a></li>
                <li class="item" id="li_summary_used"><a href="#" class="link_summary_board">
                        <i class="spr_book2 ico_check"></i> <em class="tit">이용완료</em> <span
                        class="figure">${this.data.usedCount}</span>
                </a></li>
                <li class="item" id="li_summary_cancel"><a href="#" class="link_summary_board">
                        <i class="spr_book2 ico_back"></i> <em class="tit">취소·환불</em> <span
                        class="figure">${this.data.cancelCount}</span>
                </a></li>
            </ul>
        </div>			
        `;
    }
}