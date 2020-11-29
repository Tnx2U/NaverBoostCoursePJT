import DataController from '../controller/datacontroller.js';


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
        return `			
`;
    }
}