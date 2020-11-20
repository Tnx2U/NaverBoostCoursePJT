
// 초기 이벤트 함수 할당
function setEventhandler() {
    handleClickInfoTab();
    handleClickDetailMoreOpen();
    handleClickDetailMoreClose();
    handleClickImageButton();
}

// 상세정보 펼쳐보기 이벤트
function handleClickDetailMoreOpen() {
    const bkMoreOpenElement = document.querySelector(".bk_more._open");

    bkMoreOpenElement.addEventListener('click', function () {
        document.querySelector(".bk_more._open").style.display = "none";
        document.querySelector(".bk_more._close").style.display = "block";
        document.querySelector(".store_details").classList.remove("close3");
    })
}

// 상세보기 접기 이벤트
function handleClickDetailMoreClose() {
    const bkMoreCloseElement = document.querySelector(".bk_more._close");

    bkMoreCloseElement.addEventListener('click', function () {
        document.querySelector(".bk_more._close").style.display = "none";
        document.querySelector(".bk_more._open").style.display = "block";
        document.querySelector(".store_details").classList.add("close3");
    })
}

// 하단 탭전환 이벤트
function handleClickInfoTab() {
    const infoTabListElement = document.querySelector(".info_tab_lst");

    infoTabListElement.addEventListener('click', function (event) {
        let clickedTabElement = null;

        if (event.target.tagName === 'SPAN') {
            clickedTabElement = event.target.parentNode.parentNode;
        } else if (event.target.tagName === 'A') {
            clickedTabElement = event.target.parentNode;
        } else if (event.target.tagName === 'LI') {
            clickedTabElement = event.target;
        }

        //예외처리
        if (clickedTabElement == null) {
            window.alert("탭 선택 이벤트에서 문제가 발생했습니다. Console을 확인해 주세요.");
            console.log(`Event Error Occured in handleClickInfoTab(). eventTarget is ${event.target}`);
            return;
        }

        //본인 및 하위 a태그 앵커 설정
        // 전체 삭제
        let infoTabLiElements = document.querySelectorAll(".info_tab_lst > li");
        infoTabLiElements.forEach((infoTabLi) => {
            infoTabLi.classList.remove("active");
            infoTabLi.querySelector("a").classList.remove("active");
        })
        // 선택된 태그만 active 추가
        clickedTabElement.classList.add("active");
        clickedTabElement.querySelector("a").classList.add("active");

        //선택된 탭의 detail_area_wrap, detail_location을 판단해 적절히 hide 클래스 추가,제거
        if (clickedTabElement.classList.contains("_detail")) {
            document.querySelector(".detail_area_wrap").classList.remove("hide");
            document.querySelector(".detail_location").classList.add("hide");
        } else if (clickedTabElement.classList.contains("_path")) {
            document.querySelector(".detail_location").classList.remove("hide");
            document.querySelector(".detail_area_wrap").classList.add("hide");
        }
    })
}

// 슬라이드 좌우 클릭 이벤트
function handleClickImageButton() {
    if (document.querySelector("div.prev") === null) {
        return;
    }

    const containerElement = document.querySelector("div.container_visual");
    const ulElement = document.querySelector("ul.visual_img");
    const liElements = document.querySelectorAll(".visual_img > li");
    const imageLength = liElements.length;
    const pagenationElement = document.querySelector("span.num");
    const imageWidth = 414;
    const slideSpeed = 300;
    const imagePrevElement = document.querySelector("div.prev");
    const imageNextElement = document.querySelector("div.nxt");
    let initialIdx = 0;
    let currentIdx = initialIdx;

    ulElement.style.width = imageWidth * (imageLength + 2) + "px";

    // 트릭을 위해 첫번째와 마지막 슬라이드 deepCopy
    let firstImageClone = ulElement.firstElementChild.cloneNode(true);
    let lastImageClone = ulElement.lastElementChild.cloneNode(true);

    // 복제한 슬라이드를 기존 슬라이드 리스트의 앞뒤에 추가
    ulElement.appendChild(firstImageClone);
    ulElement.insertBefore(lastImageClone, ulElement.firstElementChild);

    //사전 스타일 설정 및 초기화
    ulElement.style.transform = "translate3d(-" + (imageWidth * (initialIdx + 1)) + "px, 0px, 0px)";

    let currentImageElement = liElements[currentIdx];
    currentImageElement.classList.add('image_active');

    imageNextElement.addEventListener("click", function () {
        //이미지 좌측으로 이동
        if (currentIdx <= imageLength - 1) {
            ulElement.style.transition = slideSpeed + "ms";
            ulElement.style.transform = "translate3d(-" + (imageWidth * (currentIdx + 2)) + "px, 0px, 0px)";
        }

        if (currentIdx === imageLength - 1) {
            setTimeout(function () {
                ulElement.style.transition = "0ms";
                ulElement.style.transform = "translate3d(-" + imageWidth + "px, 0px, 0px)";
            }, slideSpeed);
            currentIdx = -1;
        }

        currentImageElement.classList.remove('image_active');
        currentImageElement = liElements[++currentIdx];
        currentImageElement.classList.add('image_active');
        //페이지네이션 수정 
        pagenationElement.innerText = currentIdx + 1;
    });

    imagePrevElement.addEventListener("click", function () {
        //이미지 좌측으로 이동
        if (currentIdx >= 0) {
            ulElement.style.transition = slideSpeed + "ms";
            ulElement.style.transform = "translate3d(-" + (imageWidth * currentIdx) + "px, 0px, 0px)";
        }

        if (currentIdx === 0) {
            setTimeout(function () {
                ulElement.style.transition = "0ms";
                ulElement.style.transform = "translate3d(-" + (imageWidth * imageLength) + "px, 0px, 0px)";
            }, slideSpeed);
            currentIdx = imageLength;
        }

        currentImageElement.classList.remove('image_active');
        currentImageElement = liElements[--currentIdx];
        currentImageElement.classList.add('image_active');
        //페이지네이션 수정
        pagenationElement.innerText = currentIdx + 1;
    });
}


export { setEventhandler, handleClickDetailMoreOpen, handleClickDetailMoreClose, handleClickInfoTab, handleClickImageButton };