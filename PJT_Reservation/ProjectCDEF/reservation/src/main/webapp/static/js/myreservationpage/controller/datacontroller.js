
export default class DataController {
    static reservationResponse = null;
    static cancelReserves = null;
    static confirmReserves = null;
    static usedReserves = null;
    static isEmpty = true;

    static initializeData(reservationResponse) {
        this.reservationResponse = reservationResponse;
        console.log(this.reservationResponse);

        if(this.reservationResponse.length() == 0){
            return;
        }
        
        this.isEmpty = false;

        const nowDate = new Date();
        // 예약확정
        this.confirmReserves = this.reservationResponse.filter((element) => {
            if(!element.cancelYn){
                const dateInfo = element.reservationDate.split("-");
                let reserveDate = new Date();
                reserveDate.setFullYear(dateInfo[0],dateInfo[1]-1,dateInfo[2]);

                if(reserveDate.getTime() >= nowDate.getTime()){
                    return true;
                }
            }

            return false;
        })

        // 이용 완료
        this.usedReserves = this.reservationResponse.filter((element) => {
            if(!element.cancelYn){
                const dateInfo = element.reservationDate.split("-");
                let reserveDate = new Date();
                reserveDate.setFullYear(dateInfo[0],dateInfo[1]-1,dateInfo[2]);

                if(reserveDate.getTime() < nowDate.getTime()){
                    return true;
                }
            }

            return false;
        })

        // 예약 취소
        this.cancelReserves = this.reservationResponse.filter((element) => {
            return element.cancelYn;
        })
    };
}