import { getParamsByUrl } from '../share/util.js';
import { renderProductDetail } from './renderer.js';
import { handleGetAjax } from '../share/ajaxHandler.js';

document.addEventListener("DOMContentLoaded", function () {
    const params = getParamsByUrl();

    //displayDetail 정보 로딩
    handleGetAjax(renderProductDetail, `products/${params.displayInfoId}`);
})