function getParamsByUrl() {
    let params = {};
    window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (str, key, value) { params[key] = value; });
    return params;
}


function getParamUrlByParams(params) {
    let paramUrl = '';

    if (params != null) {
        paramUrl += "?";
        for (let key in params) {
            paramUrl += `${key}=${params[key]}&`;
        }
        paramUrl = paramUrl.slice(0, -1);
    }

    return paramUrl;
}

function convertPriceToNumber(element) {
    let result = element.innerText;
    result = result.replace(/,|원/g, '');

    return parseInt(result);
}

export { getParamsByUrl, getParamUrlByParams, convertPriceToNumber };