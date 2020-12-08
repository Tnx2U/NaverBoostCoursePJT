export default class DataController {
    static reservations = null;
    static cancelReserves = null;
    static confirmReserves = null;
    static usedReserves = null;
    static isEmpty = true;

    static initializeData(reservationResponse) {
        this.reservations = reservationResponse.reservations;
        console.log(this.reservations);

        if (this.reservations.length == 0) {
            return;
        }

        this.isEmpty = false;

        const nowDate = new Date();

        this.confirmReserves = this.reservations.filter((element) => {
            if (!element.cancelYn) {
                const dateInfo = element.reservationDate.split("-");
                let reserveDate = new Date();
                reserveDate.setFullYear(dateInfo[0], dateInfo[1] - 1, dateInfo[2]);

                if (reserveDate.getTime() >= nowDate.getTime()) {
                    return true;
                }
            }

            return false;
        })

        // 이용 완료
        this.usedReserves = this.reservations.filter((element) => {
            if (!element.cancelYn) {
                const dateInfo = element.reservationDate.split("-");
                let reserveDate = new Date();
                reserveDate.setFullYear(dateInfo[0], dateInfo[1] - 1, dateInfo[2]);

                if (reserveDate.getTime() < nowDate.getTime()) {
                    return true;
                }
            }

            return false;
        })

        // 예약 취소
        this.cancelReserves = this.reservations.filter((element) => {
            return element.cancelYn;
        })
    }


    static getIsEmpty() {
        return this.isEmpty;
    }

    static getMySummaryData() {
        const mySummayData = {
            totalCount: this.reservations.length,
            confirmCount: this.confirmReserves.length,
            usedCount: this.usedReserves.length,
            cancelCount: this.cancelReserves.length
        }

        return mySummayData;
    }

    static getMyListData() {
        const mylistData = {
            confirmReserves: this.confirmReserves,
            usedReserves: this.usedReserves,
            cancelReserves: this.cancelReserves
        }

        return mylistData;
    }
}