export default class DataController {
    static reservationInfoId = null;
    static productId = null;
    static comment = null;
    static score = null;

    static initializeData(param) {
        console.log("param", param);
        this.reservationInfoId = param.reservationInfoId;
        this.productId = param.productId;
    }

    static setScore(score){
        this.score = score;
    }

    static setComment(comment){
        this.comment = comment;
    }
}