export default class DataController {
    static displayInfoResponse = null;

    static initializeData(displayInfoResponse) {
        console.log(displayInfoResponse);
        this.displayInfoResponse = displayInfoResponse;
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
        let nowDate = new Date();
        let date = nowDate.getDate() + Math.floor(Math.random() * 5);
        date = (date < 2) ? date + 2 : date;
        const bookingFormData = {
            reservateDate: `${nowDate.getFullYear()}/${nowDate.getMonth()+1}/${date}`,
            totalCount: 0
        }

        return bookingFormData;
    }
}