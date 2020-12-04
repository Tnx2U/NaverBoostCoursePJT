import DataController from '../controller/dataController.js';


export default class MyList {
    constructor() {
        this.data = DataController.getMyListData();
    }

    getRenderedElement() {
        let myListElement = document.createElement("div");
        myListElement.classList.add("wrap_mylist");
        myListElement.innerHTML = this.getHtmlSrc();

        return myListElement;
    }

    getHtmlSrc() {
        let getCardHtmlSrc = this.getCardHtmlSrc;
        return `
        <div class="wrap_mylist">
            <ul class="list_cards" ng-if="bookedLists.length > 0">
                <!--[D] 예약확정: .confirmed, 취소된 예약&이용완료: .used 추가 card -->
                <li class="card confirmed">
                    <div class="link_booking_details">
                        <div class="card_header">
                            <div class="left"></div>
                            <div class="middle">
                                <!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
                                <i class="spr_book2 ico_check2"></i> <span class="tit">예약 확정</span>
                            </div>
                            <div class="right"></div>
                        </div>
                    </div>
                    ` + this.data.confirmReserves.reduce((carHtmlSrcs, element) => {
            const cardHtmlsrc = getCardHtmlSrc('confirm', element)
            return carHtmlSrcs + cardHtmlsrc;
        }, '') + `
                </li>
                <li class="card used">
                    <div class="link_booking_details">
                        <div class="card_header">
                            <div class="left"></div>
                            <div class="middle">
                                <!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
                                <i class="spr_book2 ico_check2"></i> <span class="tit">이용
                                    완료</span>
                            </div>
                            <div class="right"></div>
                        </div>
                    </div>
                    ` + this.data.usedReserves.reduce((carHtmlSrcs, element) => {
            const cardHtmlsrc = getCardHtmlSrc('used', element)
            return carHtmlSrcs + cardHtmlsrc;
        }, '') + `
                </li>
                <li class="card used cancel">
                    <div class="link_booking_details">
                        <div class="card_header">
                            <div class="left"></div>
                            <div class="middle">
                                <!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
                                <i class="spr_book2 ico_cancel"></i> <span class="tit">취소된
                                    예약</span>
                            </div>
                            <div class="right"></div>
                        </div>
                    </div>
                    ` + this.data.cancelReserves.reduce((carHtmlSrcs, element) => {
            const cardHtmlsrc = getCardHtmlSrc('cancel', element)
            return carHtmlSrcs + cardHtmlsrc;
        }, '') + `
                </li>
            </ul>
        </div>
        `;
    }

    getCardHtmlSrc(type, data) {
        let bookingCancelHtmlSrc;

        if (type == "confirm") {
            bookingCancelHtmlSrc = `
            <div class="booking_cancel">
                <button class="btn" id="btn_${data.reservationInfoId}">
                    <span>취소</span>
                </button>
            </div>`
        } else if (type == "used") {
            bookingCancelHtmlSrc = `
            <div class="booking_cancel">
                <a href="./reviewWrite?reservationInfoId=${data.reservationInfoId}&productId=${data.productId}&reservationEmail=${data.reservationEmail}"
                    id="a_${data.reservationInfoId}">
                    <button class="btn">
                        <span>예매자 리뷰 남기기</span>
                    </button>
                </a>
            </div>
            `
        } else if (type == "cancel") {
            bookingCancelHtmlSrc = ``;
        }

        return `
        <article class="card_item" id="card_${data.reservationInfoId}">
            <a href="#" class="link_booking_details">
                <div class="card_body">
                    <div class="left"></div>
                    <div class="middle">
                        <div class="card_detail">
                            <em class="booking_number">No.${data.reservationInfoId}</em>
                            <h4 class="tit">${data.displayInfo.productDescription}</h4>
                            <ul class="detail">
                                <li class="item"><span class="item_tit">일정</span> <em
                                    class="item_dsc"> ${data.reservationDate} </em></li>
                                <li class="item"><span class="item_tit">내역</span> <em
                                    class="item_dsc"> ${data.displayInfo.productContent.substring(0, 100)}... </em></li>
                                <li class="item"><span class="item_tit">장소</span> <em
                                    class="item_dsc"> ${data.displayInfo.placeStreet}</em></li>
                                <li class="item"><span class="item_tit">업체</span> <em
                                    class="item_dsc"> ${data.displayInfo.placeName} </em></li>
                            </ul>
                            <div class="price_summary">
                                <span class="price_tit">결제 예정금액</span> <em
                                    class="price_amount"> <span>${data.totalPrice.toLocaleString()}</span> <span
                                    class="unit">원</span>
                                </em>
                            </div>
                            <!-- [D] 예약 신청중, 예약 확정 만 취소가능, 취소 버튼 클릭 시 취소 팝업 활성화 -->
                            ` + bookingCancelHtmlSrc + `   
                        </div>
                    </div>
                    <div class="right"></div>
                </div>
                <div class="card_footer">
                    <div class="left"></div>
                    <div class="middle"></div>
                    <div class="right"></div>
                </div>
            </a> <a href="#" class="fn fn-share1 naver-splugin btn_goto_share"
                title="공유하기"></a>
        </article>`
    }
}