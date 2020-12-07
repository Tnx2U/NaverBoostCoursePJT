export default class DataController {
    static reservationInfoId = null;
    static reservationEmail = null;
    static productId = null;
    static comment = null;
    static score = null;
    static image = null;

    static initializeData(param) {
        this.reservationInfoId = param.reservationInfoId;
        this.reservationEmail = param.reservationEmail;
        this.productId = param.productId;
        this.score = "0";
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

    static getReservationEmail(){
        return this.reservationEmail;
    }
}