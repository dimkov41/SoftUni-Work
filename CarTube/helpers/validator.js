const validator = (function () {
    const validateRegisterInput = function (username, password, repeatPass) {
        const usernameRegex = /^[A-Za-z]*$/;
        const passwordRegex = /^[A-Za-z0-9]*$/;

        if (usernameRegex.test(username) && username.length >= constants.usernameMinLenght) {

            if (passwordRegex.test(password)
                && password === repeatPass
                && password.length >= constants.passwordMinLenght) {

                return true;

            } else {
                handler.showError("Invalid password");
            }

        } else {
            handler.showError("Invalid username");
        }

        return false;
    };

    const validateCarInput = function (data) {
        if (data.title.length <= constants.titleMaxLenght && data.title.length > 0) {

            if (data.description.length >= constants.descriptionMinLenght
                && data.description.length <= constants.descriptionMaxLenght) {

                if (data.brand.length <= constants.brandFuelModelMaxLenght && data.brand.length > 0) {

                    if (data.fuelType.length <= constants.brandFuelModelMaxLenght && data.fuelType.length > 0) {

                        if (validateImageUrl(data.imageUrl)) {

                            if (data.model.length <= constants.brandFuelModelMaxLenght
                                && data.model.length >= constants.modelMinLenght) {

                                if (data.year.length === constants.yearExactlyLenght) {

                                    if (+data.price <= constants.maxPrice) {

                                        return true;
                                    } else {
                                        handler.showError("Invalid price");
                                    }
                                } else {
                                    handler.showError("Invalid year");
                                }
                            } else {
                                handler.showError("Invalid model");
                            }
                        } else {
                            handler.showError("Invalid image url");
                        }
                    } else {
                        handler.showError("Invalid fuelType");
                    }
                } else {
                    handler.showError("Invalid brand");
                }
            } else {
                handler.showError("Invalid description");
            }
        } else {
            handler.showError("Invalid title");
        }
        return false;
    };


    function validateImageUrl(imageUrl) {
        return String(imageUrl).substring(0, 4) === "http";
    }

    return {
        validateRegisterInput,
        validateCarInput
    }
})
();