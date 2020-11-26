export default class DataController {
    static displayInfoResponse = null;

    static initializeData(displayInfoResponse) {
        console.log(displayInfoResponse);
        this.displayInfoResponse = displayInfoResponse;
    };

    static getGroupVisualData() {
        const groupVisualData = {
            fileName: this.displayInfoResponse.displayInfoImage.fileName,
            saveFileName: 'static/' + this.displayInfoResponse.displayInfoImage.saveFileName
        }

        return groupVisualData;
    }
}