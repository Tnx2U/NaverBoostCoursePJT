import DataController from '../controller/dataController.js';

export default class BookingForm {
    constructor() {
        this.data = DataController.getBookingFormData();
    }

    getRenderedElement() {
        let bookingFormElement = document.createElement("div");
        bookingFormElement.classList.add("section_booking_form");
        bookingFormElement.innerHTML = this.getHtmlSrc();

        return bookingFormElement;
    }

    getHtmlSrc() {
        return `			
        <div class="booking_form_wrap">
            <div class="form_wrap">
                <h3 class="out_tit">예매자 정보</h3>
                <div class="agreement_nessasary help_txt">
                    <span class="spr_book ico_nessasary"></span> <span>필수입력</span>
                </div>
                <form class="form_horizontal">
                    <div class="inline_form">
                        <label class="label" for="name"> <span
                            class="spr_book ico_nessasary">필수</span> <span>예매자</span>
                        </label>
                        <div class="inline_control">
                            <input type="text" name="name" id="name" class="text"
                                placeholder="조현욱" maxlength="17">
                        </div>
                    </div>
                    <div class="inline_form">
                        <label class="label" for="tel"> <span
                            class="spr_book ico_nessasary">필수</span> <span>연락처</span>
                        </label>
                        <div class="inline_control tel_wrap">
                            <input type="tel" name="tel" id="tel" class="tel" value=""
                                placeholder="000-000(0)-0000">
                            <div class="warning_msg">형식이 틀렸거나 너무 짧아요</div>
                        </div>
                    </div>
                    <div class="inline_form">
                        <label class="label" for="email"> <span
                            class="spr_book ico_nessasary">필수</span> <span>이메일</span>
                        </label>
                        <div class="inline_control">
                            <input type="email" name="email" id="email" class="email"
                                value="" placeholder="hyunwook_cho@nts-corp.com" maxlength="50">
                        </div>
                    </div>
                    <div class="inline_form last">
                        <label class="label" for="message">예매내용</label>
                        <div class="inline_control">
                            <p class="inline_txt selected">
                                <span id="reservateDate">${this.data.reservateDate}</span>, 총 <span id="totalCount">${this.data.totalCount}</span>매
                            </p>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="section_booking_agreement">
            <div class="agreement all">
                <input type="checkbox" id="chk3" class="chk_agree"> <label
                    for="chk3" class="label chk_txt_label"> <span>이용자
                        약관 전체동의</span>
                </label>
                <div class="agreement_nessasary">
                    <span>필수동의</span>
                </div>
            </div>
            <!-- [D] 약관 보기 클릭 시 agreement에 open 클래스 추가 -->
            <div class="agreement">
                <span class="chk_txt_span"> <i
                    class="spr_book ico_arr_ipc2"></i> <span>개인정보 수집 및 이용 동의</span>
                </span> <a href="#" class="btn_agreement"> <span class="btn_text">보기</span>
                    <i class="fn fn-down2"></i>
                </a>
                <div class="useragreement_details">
                    &lt;개인정보 수집 및 이용 동의&gt;<br> <br> 1. 수집항목 : [필수] 이름,
                    연락처, [선택] 이메일주소<br> <br> 2. 수집 및 이용목적 : 사업자회원과 예약이용자의
                    원활한 거래 진행, 고객상담, 불만처리 등 민원 처리, 분쟁조정 해결을 위한 기록보존, 네이버 예약 이용 후
                    리뷰작성에 따른 네이버페이 포인트 지급 및 관련 안내<br> <br> 3. 보관기간<br>
                    - 회원탈퇴 등 개인정보 이용목적 달성 시까지 보관<br> - 단, 상법 및 ‘전자상거래 등에서의 소비자
                    보호에 관한 법률’ 등 관련 법령에 의하여 일정 기간 보관이 필요한 경우에는 해당 기간 동안 보관함<br>
                    <br> 4. 동의 거부권 등에 대한 고지: 정보주체는 개인정보의 수집 및 이용 동의를 거부할 권리가
                    있으나, 이 경우 상품 및 서비스 예약이 제한될 수 있습니다.<br>
                </div>
            </div>
            <!-- [D] 약관 보기 클릭 시 agreement에 open 클래스 추가 -->
            <div class="agreement">
                <span class="chk_txt_span"> <i
                    class="spr_book ico_arr_ipc2"></i> <span>개인정보 제3자 제공 동의</span>
                </span> <a href="#" class="btn_agreement"> <span class="btn_text">보기</span>
                    <i class="fn fn-down2"></i>
                </a>
                <div class="useragreement_details custom_details_wrap">
                    <div class="custom_details">
                        &lt;개인정보 제3자 제공 동의&gt;<br> <br> 1. 개인정보를 제공받는 자 :
                        미디어앤아트<br> <br> 2. 제공하는 개인정보 항목 : [필수] 네이버 아이디, 이름,
                        연락처 [선택] 이메일 주소<br> <br> 3. 개인정보를 제공받는 자의 이용목적 :
                        사업자회원과 예약이용자의 원활한 거래 진행, 고객상담, 불만처리 등 민원 처리, 서비스 이용에 따른 설문조사 및
                        혜택 제공, 분쟁조정 해결을 위한 기록보존<br> <br> 4. 개인정보를 제공받는 자의
                        개인정보 보유 및 이용기간 : 개인정보 이용목적 달성 시 까지 보관합니다.<br> <br> 5.
                        동의 거부권 등에 대한 고지 : 정보주체는 개인정보 제공 동의를 거부할 권리가 있으나, 이 경우 상품 및 서비스
                        예약이 제한될 수 있습니다.<br>
                    </div>
                </div>
            </div>
        </div>
        <div class="box_bk_btn">
            <!-- [D] 약관 전체 동의가 되면 disable 제거 -->
            <div class="bk_btn_wrap disable">
                <button type="button" class="bk_btn">
                    <i class="spr_book ico_naver_s"></i> <span>예약하기</span>
                </button>
            </div>
        </div>
        `;
    }
}