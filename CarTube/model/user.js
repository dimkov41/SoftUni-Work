const user = (function () {
    let createRegistration = function (username, password) {
        let data = {
            username,
            password
        };

        return $.ajax({
            type: "POST",
            url: storage.baseUrl + "user/" + storage.appKey,
            headers: storage.baseAppAuth,
            data: data
        });
    };

    let loginUser = function (username,password) {
        let data = {
            username,
            password
        };

        return $.ajax({
            type: "POST",
            url: storage.baseUrl + "user/" + storage.appKey + "/login",
            headers: storage.baseAppAuth,
            data: data
        });
    };

    let logoutUser = function () {
        return $.ajax({
            type: "POST",
            url: storage.baseUrl + "user/" + storage.appKey + "/_logout",
            headers: storage.authTokenAuth()
        })
    };


    return{
        createRegistration,
        loginUser,
        logoutUser
    }
})();