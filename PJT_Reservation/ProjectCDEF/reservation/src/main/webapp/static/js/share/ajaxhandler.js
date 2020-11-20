import { getParamUrlByParams } from './util.js';
import { SERVER_IP, TARGET_PORT } from './properties.js';

// ajax 이벤트
function handleGetAjax(renderFunction, target, params) {
    let xhRequest = new XMLHttpRequest();
    //const baseUrl = `http://${SERVER_IP}:${TARGET_PORT}/reservation/api/${target}`;
    const baseUrl = `api/${target}`;
    let paramUrl = "";

    paramUrl = getParamUrlByParams(params);

    xhRequest.onreadystatechange = function () {
        if (xhRequest.readyState === xhRequest.DONE) {
            if (xhRequest.status === 200 || xhRequest.status === 201) {
                renderFunction(JSON.parse(xhRequest.response));
            } else {
                console.error(xhRequest.status, xhRequest.responseText);
            }
        }
    };
    xhRequest.open('GET', baseUrl + paramUrl);
    xhRequest.send();
}

export { handleGetAjax }