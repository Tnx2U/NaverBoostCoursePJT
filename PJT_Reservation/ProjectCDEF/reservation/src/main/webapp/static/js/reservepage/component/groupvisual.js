import DataController from '../datacontroller.js';


export default class GroupVisual {
    constructor() {
        this.data = DataController.getGroupVisualData();
    }

    // 어차피 클래스별 1개만 존재하므로 네이밍 컨벤션에서 대상 삭제
    getElement() {
        let groupVisulElement = document.createElement("div");
        groupVisulElement.classList.add("group_visual");
        groupVisulElement.innerHTML = this.getHtmlSrc();

        return groupVisulElement;
    }

    getHtmlSrc() {
        return `			
        <div class="container_visual" style="width: 414px;">
            <ul class="visual_img">
                <li class="item" style="width: 414px;"><img alt="${this.data.fileName}"
                    class="img_thumb"
                    src="${this.data.saveFileName}">
                    <span class="img_bg"></span>
                    <div class="preview_txt">
                        <h2 class="preview_txt_tit"></h2>
                        <em class="preview_txt_dsc">₩12,000 ~ </em><em
                            class="preview_txt_dsc">2017.2.17.(금)~2017.4.18.(화), 잔여티켓
                            2769매</em>
                    </div>
                </li>
            </ul>
        </div>`;
    }
}