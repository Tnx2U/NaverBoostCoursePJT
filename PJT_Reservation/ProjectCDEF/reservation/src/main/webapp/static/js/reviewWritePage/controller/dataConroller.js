export default class DataController {
    static reservationInfoId = null;
    static productId = null;
    static comment = null;
    static score = null;
    static image = null;

    static initializeData(param) {
        console.log("param", param);
        this.reservationInfoId = param.reservationInfoId;
        this.productId = param.productId;
    }

    // ----------- setter ------------
    static setScore(score){
        this.score = score;
    }

    static setComment(comment){
        this.comment = comment;
    }

    static setImage(image){
        this.image = image;
    }

    // ----------- getter ------------
    static getImage(){
        return this.image;
    }

    static getReservationInfoId(){
        return this.reservationInfoId;
    }

    static getProductId(){
        return this.productId;
    }

    static getComment(){
        return this.comment;
    }

    static getScore(){
        return this.score;
    }
}