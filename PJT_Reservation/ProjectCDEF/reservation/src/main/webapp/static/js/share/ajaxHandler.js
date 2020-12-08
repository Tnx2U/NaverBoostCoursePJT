import { getParamUrlByParams } from './util.js';

// ajax 이벤트
function handleGetAjax(renderFunction, target, params) {
    let xhRequest = new XMLHttpRequest();
    const baseUrl = `api/${target}`;
    let paramUrl = "";

    paramUrl = getParamUrlByParams(params);

    xhRequest.onreadystatechange = function () {
        if (xhRequest.readyState === xhRequest.DONE) {
            if (xhRequest.status === 200 || xhRequest.status === 201) {
                if(renderFunction != null){
                    renderFunction(JSON.parse(xhRequest.response));
                }
            } else {
                console.error(xhRequest.status, xhRequest.responseText);
            }
        }
    };
    xhRequest.open('GET', baseUrl + paramUrl);
    xhRequest.send();
}

function handlePostAjax(callbackFunc, target, params){
    let xhRequest = new XMLHttpRequest();
    const baseUrl = `api/${target}`;

    xhRequest.onreadystatechange = function () {
        if (xhRequest.readyState === xhRequest.DONE) {
            if (xhRequest.status === 200 || xhRequest.status === 201) {
                callbackFunc(xhRequest);
            } else {
                console.error(xhRequest.status, xhRequest.responseText);
                callbackFunc(xhRequest);
            }
        }
    };
    xhRequest.open('POST', baseUrl);
    xhRequest.setRequestHeader('Content-Type', 'application/json');
    xhRequest.send(JSON.stringify(params));
}

// 편의성을 위해 multipart formData 전송은 jquery 문법 사용
function handlePostAjaxMultipart(callbackFunc, target, formData){
    $.ajax({
        url : `api/${target}`,
        type : 'POST',
        enctype : 'multipart/form-data',
        data : formData,
        asynsc : true,
        cache : false,
        contentType: false,
        processData: false,
        success: function(result){
            callbackFunc(result);
        },
        error: function(errorResult){
            callbackFunc(errorResult);
        }
    })
}

function handlePutAjax(callbackFunc, target, params){
    let xhRequest = new XMLHttpRequest();
    const baseUrl = `api/${target}`;

    xhRequest.onreadystatechange = function () {
        if (xhRequest.readyState === xhRequest.DONE) {
            if (xhRequest.status === 200 || xhRequest.status === 201) {
                callbackFunc(xhRequest);
            } else {
                console.error(xhRequest.status, xhRequest.responseText);
                callbackFunc(xhRequest);
            }
        }
    };
    xhRequest.open('PUT', baseUrl);
    xhRequest.setRequestHeader('Content-Type', 'application/json');
    if(params == null){
        xhRequest.send();
    }else{
        xhRequest.send(JSON.stringify(params));
    }
}

export { handleGetAjax, handlePostAjax, handlePostAjaxMultipart, handlePutAjax }