const userController = (function () {
    const register = function (context) {
        let username = context.params.username;
        let password = context.params.password;

        let isValid = validator.validateRegisterInput(username, password, context.params.repeatPass);

        if (isValid) {
            user.createRegistration(username, password)
                .then((data) => successLoginRegister(data, "User registration successful.", context))
                .catch(handler.handleError);
        }
    };

    const login = function (context) {
        let username = context.params.username;
        let password = context.params.password;

        user.loginUser(username, password)
            .then((data) => successLoginRegister(data, "Login successful.", context))
            .catch(handler.handleError);

    };

    const logout = function (context) {
        if(storage.authToken()) {
            user.logoutUser()
                .then(function () {
                    handler.showInfo("Logout successful.");
                    sessionStorage.clear();

                    viewController.renderLoginPage(context);
                })
                .catch(handler.handleError);
        } else {
            viewController.renderHomeView(context);
        }
    };

    function successLoginRegister(data, message, context) {
        sessionStorage.setItem("authToken", data._kmd.authtoken);
        sessionStorage.setItem("username", data.username);
        sessionStorage.setItem("userId", data._id);

        handler.showInfo(message);
        viewController.renderAllListingPage(context);
    }

    return {
        register,
        login,
        logout
    }
})();