function moveClickedCard(columnId, cardOrder, cardId){
    const columnSize = 3;
    //기존 돔 제거
    let targetCardDom = document.querySelector(`#card_${cardId}`);
    const targetCardHtml = targetCardDom.outerHTML;
    targetCardDom.remove();
    //이동 위치에 추가
    let targetColumnDom = document.querySelector(`#col_${++columnId}`).querySelector(`ul`);
    targetColumnDom.innerHTML += targetCardHtml;
    if(columnId == columnSize)
        targetColumnDom.querySelector(`#card_${cardId} .btn_next`).remove();
}

function sendAjaxRequest(columnId, cardOrder, cardId) {
    let xhRequest = new XMLHttpRequest();
    const cardInfo = { 'columnId': columnId, 'cardOrder': cardOrder, 'cardId': cardId };
    
    xhRequest.onreadystatechange = function() {
        if (xhRequest.readyState === xhRequest.DONE) {
            if (xhRequest.status === 200 || xhRequest.status === 201) {
                moveClickedCard(columnId, cardOrder, cardId);
            } else {
                console.error(xhRequest.responseText);
            }
        }
    };
    xhRequest.open('PUT', 'http://localhost:8080/Todo/card-order');
    xhRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhRequest.send(JSON.stringify(cardInfo));
}

function handleClickNext(e) {
    const parseArray = e.target.id.split("_");
    const columnId = parseArray[2];
    const cardOrder = parseArray[3];
    const cardId = parseArray[4];

    sendAjaxRequest(columnId, cardOrder, cardId);
}

function setEventListener() {
    let nextButtons = document.querySelectorAll(".btn_next");
    nextButtons.forEach((nextButton) => {
        nextButton.addEventListener("click", (e) => { handleClickNext(e) })
    })
}


setEventListener();