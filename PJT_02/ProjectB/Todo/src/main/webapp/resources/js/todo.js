function moveClickedCard(columnId, cardOrder, cardId){
    console.log("moveClickedCard activate : ",columnId, cardOrder, cardId);
}

function sendAjaxRequest(columnId, cardOrder, cardId) {
    let xhRequest = new XMLHttpRequest();
    const cardInfo = { 'columnId': columnId, 'cardOrder': cardOrder, 'cardId': cardId };
    
    xhRequest.onreadystatechange = function() {
        if (xhRequest.readyState === xhr.DONE) {
            if (xhr.status === 200 || xhr.status === 201) {
                moveClickedCard(columnId, cardOrder, cardId);
            } else {
                console.error(xhRequest.responseText);
            }
        }
    };
    xhr.open('PUT', 'http://localhost:8080/Todo/card-order');
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.send(JSON.stringify(cardInfo));
}

function handleClickNext(e) {
    const parseArray = e.target.id.split("_");
    const columnId = parseArray[2];
    const cardOrder = parseArray[3];
    const cardId = parseArray[4];
    console.log("col_id :", parseArray[2], "cardOrder :", parseArray[3], "cardId :", parseArray[4]);

    sendAjaxRequest(columnId, cardOrder, cardId);
}

function setEventListener() {
    let nextButtons = document.querySelectorAll(".btn_next");
    nextButtons.forEach((nextButton) => {
        nextButton.addEventListener("click", (e) => { handleClickNext(e) })
    })
}


setEventListener();