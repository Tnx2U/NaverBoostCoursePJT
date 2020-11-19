/**
 * 페이지 구분없이 공용으로 사용 가능한 유틸함수 모듈
 */

function getParamsByUrl() {
    let params = {};
    window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(str, key, value) { params[key] = value; });
    return params;
}


function getParamUrlByParams(params){
    let paramUrl = '';

    if (params != null) {
        paramUrl += "?";
        for (key in params) {
            paramUrl += `${key}=${params[key]}&`;
            // js에서 맨 뒤의 params문법 삭제해주는 라이브러리? 기능 공부
        }
        paramUrl = paramUrl.slice(0,-1);
    }

    return paramUrl;
}

export { getParamsByUrl, getParamUrlByParams };