
export default class DataController {
    static displayInfoResponse = null;
    static reservationParam = {};
    static agreeAll = false;

    static initializeData(displayInfoResponse) {
        console.log(displayInfoResponse);
        this.displayInfoResponse = displayInfoResponse;
        this.reservationParam = {
            displayInfoId: displayInfoResponse.displayInfo.displayInfoId,
            productId: displayInfoResponse.displayInfo.productId,
            reservationEmail: "",
            reservationName: "",
            reservationTelephone: "",
            reservationYearMonthDay: "",
            prices: []
        };
    };

    static getGroupVisualData() {
        const groupVisualData = {
            fileName: this.displayInfoResponse.productImages[0].fileName,
            saveFileName: 'static/' + this.displayInfoResponse.productImages[0].saveFileName,
            startPrice: this.displayInfoResponse.productPrices.reduce((prev, current) =>
                (prev.price < current.price) ? prev.price : current.price),
            displayDate: this.displayInfoResponse.displayInfo.openingHours.split(/- [ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{3,4}:?/g)[1],
            randomRemain: Math.floor(Math.random() * 1000)
        }

        return groupVisualData;
    }

    static getStoreDetailData() {
        const storeDetailData = {
            openingHours: this.displayInfoResponse.displayInfo.openingHours,
            productPrices: this.displayInfoResponse.productPrices,
            placeName: this.displayInfoResponse.displayInfo.placeName
        }

        return storeDetailData;
    }

    static getbookingTicketData() {
        const bookingTicketDatas = this.displayInfoResponse.productPrices;

        return bookingTicketDatas;
    }

    static getBookingFormData() {
        const maxDay = [-1,31,29,31,30,31,30,31,31,30,31,30,31];

        let nowDate = new Date();
        let date = nowDate.getDate() + Math.floor(Math.random() * 5);
        date = (date < 2) ? date + 2 : date;
        date = (date > maxDay[nowDate.getMonth()]) ? nowDate.getMonth() : date;
        const bookingFormData = {
            reservateDate: `${nowDate.getFullYear()}/${nowDate.getMonth() + 1}/${date}`,
            totalCount: 0
        }

        return bookingFormData;
    }

    static getTitle() {
        return this.displayInfoResponse.displayInfo.productDescription;
    }

    static getReservationParam(){
        return this.reservationParam;
    }

    static getAgreeAll(){
        return this.agreeAll;
    }

    static setReservationEmail(email) {
        this.reservationParam.reservationEmail = email;
    }

    static setReservationName(name) {
        this.reservationParam.reservationName = name;
    }

    static setReservationTelephone(tel) {
        this.reservationParam.reservationTelephone = tel;
    }

    static setReservationYearMonthDay(yearMonthDay) {
        this.reservationParam.reservationYearMonthDay = yearMonthDay;
    }

    static setPrices(isPlus, productPriceId) {
        if (isPlus) {
            let contains = false;
            this.reservationParam.prices.forEach((element) => {
                if (element.productPriceId == productPriceId) {
                    element.count += 1;
                    contains = true;
                }
            })
            if (!contains) {
                this.reservationParam.prices.push({
                    count: 1,
                    productPriceId: productPriceId
                })
            }
        } else {
            let deleteIdx = -1;
            this.reservationParam.prices.forEach((element, idx) => {
                if (element.productPriceId == productPriceId) {
                    element.count -= 1;
                    if (element.count == 0) {
                        deleteIdx = idx;
                    }
                }
            })
            if (deleteIdx != -1) {
                this.reservationParam.prices.splice(deleteIdx, 1);
            }
        }
    }

    static toggleAgreeAll(){
        this.agreeAll = !this.agreeAll;
    }
}