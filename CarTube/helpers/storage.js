const storage = (function () {
    const appKey = "kid_r18TB_R14";
    const appSecret = "e61db60d69364d8ca6590ec8675e6b96";
    const baseUrl = "https://baas.kinvey.com/";
    let baseAppAuth = {
        Authorization: "Basic " + btoa(appKey + ":" + appSecret)
    };

    let authToken = function () {
        return sessionStorage.getItem("authToken");
    };

    let authTokenAuth = function () {
        return {
            Authorization: "Kinvey " + authToken()
        };
    };


    return {
        appKey,
        appSecret,
        baseUrl,
        baseAppAuth,
        authToken,
        authTokenAuth
    }
})();