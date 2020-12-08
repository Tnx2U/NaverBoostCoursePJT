import DataController from '../controller/dataController.js';


export default class StoreDetail {
    constructor() {
        this.data = DataController.getStoreDetailData();
    }

    getRenderedElement() {
        let storeDetailElement = document.createElement("div");
        storeDetailElement.classList.add("section_store_details");
        storeDetailElement.innerHTML = this.getHtmlSrc();

        return storeDetailElement;
    }

    getHtmlSrc() {
        return `
            <div class="store_details">
                <h3 class="in_tit">장소</h3>
                <p class="dsc">
                    ${this.data.placeName}
                </p>
                <h3 class="in_tit">관람시간</h3>
                <p class="dsc">
                    ${this.data.openingHours.replace(/(- [ㄱ-ㅎ|ㅏ-ㅣ|가-힣])/g, '<br>$1')}
                </p>
                <h3 class="in_tit">요금</h3>
                <p class="dsc">
                ` + this.getPriceHtmlSrc(this.data.productPrices) + `
                </p>
            </div>
                `;
    }

    getPriceHtmlSrc(productPrices){
        const priceTypeDetail = {
            'A' : "성인(만 19~64세)",
            'Y' : "청소년(만 13~18세)",
            'B' : "어린이(만 4~12세)",
            'S' : "20인 이상 단체",
            'D' : "장애인",
            'E' : "얼리버드",
            'V' : "VIP",
            'R' : "R석",
            'C' : "지역주민"
        }

        let priceHtmlSrc = ``;

        productPrices.forEach((element) => {
            priceHtmlSrc += `${priceTypeDetail[element.priceTypeName]} ${element.price.toLocaleString()}원 <br>`
        });

        return priceHtmlSrc;
    }
}