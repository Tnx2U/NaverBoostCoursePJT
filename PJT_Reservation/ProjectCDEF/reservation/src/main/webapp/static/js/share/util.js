/**
 * 페이지 구분없이 공용으로 사용 가능한 유틸함수 모듈
 */

function getUrlParams() {
    let params = {};
    window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(str, key, value) { params[key] = value; });
    return params;
}

export { getUrlParams };