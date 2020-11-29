import DataController from '../controller/datacontroller.js';


export default class BookingTicket {
    constructor() {
        this.data = DataController.getbookingTicketData();
    }

    getRenderedElement() {
        let bookingTicketElement = document.createElement("div");
        bookingTicketElement.classList.add("section_booking_ticket");
        bookingTicketElement.innerHTML = this.getHtmlSrc();

        return bookingTicketElement;
    }

    getHtmlSrc() {
        const priceTypeDetail = {
            'A': "성인",
            'Y': "청소년",
            'B': "어린이",
            'S': "단체",
            'D': "장애인",
            'E': "얼리버드"
        }

        return `<div class="ticket_body">`
            + this.data.reduce((qtiesHtmlSrc, element) => {
                return qtiesHtmlSrc + `
                <div class="qty" id="qty_${element.productPriceId}">
                    <div class="count_control">
                        <!-- [D] 수량이 최소 값이 일때 ico_minus3, count_control_input에 disabled 각각 추가, 수량이 최대 값일 때는 ico_plus3에 disabled 추가 -->
                        <div class="clearfix">
                            <a class="btn_plus_minus spr_book2 ico_minus3 disabled" title="빼기"> </a> 
                            <input type="tel"
                                class="count_control_input disabled" value="0" readonly
                                title="수량"> 
                            <a class="btn_plus_minus spr_book2 ico_plus3" title="더하기"> </a>
                        </div>
                        <!-- [D] 금액이 0 이상이면 individual_price에 on_color 추가 -->
                        <div class="individual_price">
                            <span class="total_price">0</span><span class="price_type">원</span>
                        </div>
                    </div>
                    <div class="qty_info_icon">
                        <strong class="product_amount"> 
                            <span>${priceTypeDetail[element.priceTypeName]}</span>
                        </strong> 
                        <strong class="product_price"> 
                            <span class="price">${element.price.toLocaleString()}원</span>
                        </strong> 
                        <em class="product_dsc">${element.price.toLocaleString()}원 (${element.discountRate}% 할인가)</em>
                    </div>
                </div>`
            }, ``) +
            `</div>`;
    }
}