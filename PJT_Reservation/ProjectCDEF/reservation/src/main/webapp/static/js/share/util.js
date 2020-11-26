function getParamsByUrl() {
    let params = {};
    window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (str, key, value) { params[key] = value; });
    return params;
}


function getParamUrlByParams(params) {
    //TODO : ajaxaHandler에서 url을 절대경로로 변경 후 URL.searchParams.set으로 다시 시도.
    let paramUrl = '';

    if (params != null) {
        paramUrl += "?";
        for (let key in params) {
            paramUrl += `${key}=${params[key]}&`;
            // js에서 맨 뒤의 params문법 삭제해주는 라이브러리? 기능 공부
        }
        paramUrl = paramUrl.slice(0, -1);
    }

    return paramUrl;
}

export { getParamsByUrl, getParamUrlByParams };