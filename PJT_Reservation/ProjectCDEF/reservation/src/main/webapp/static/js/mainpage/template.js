// -------- 간단한 html 템플릿 함수 영역 --------
function getTabHtml(category) {
    return `<li class="item" data-category="${category.id}"><a class="anchor"><span>${category.name}<span/></li>`;
}