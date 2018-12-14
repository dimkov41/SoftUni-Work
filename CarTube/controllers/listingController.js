const listingController = (function () {
    let listAllCars = function () {
        if (storage.authToken()) {
            return listing.getAllCars();
        } else {
            return Promise.reject(new Error("You don't have permission to do that"));
        }
    };

    let createCar = function (context) {
        let title = context.params.title;
        let description = context.params.description;
        let brand = context.params.brand;
        let model = context.params.model;
        let year = context.params.year;
        let imageUrl = context.params.imageUrl;
        let fuelType = context.params.fuelType;
        let price = context.params.price;

        let data = {
            brand,
            description,
            fuelType,
            imageUrl,
            "isAuthor": true,
            model,
            price,
            "seller": sessionStorage.getItem("username"),
            title,
            year
        };

        if (validator.validateCarInput(data) && sessionStorage.getItem("username") !== null) {
            //create cars
            listing.create(data)
                .then(function () {
                    handler.showInfo("Listing created.");
                    viewController.renderAllListingPage(context);
                })
                .catch(handler.handleError);
        }

    };

    let editCar = function (context) {
        let inputData = context.params;

        let isValid = validator.validateCarInput(inputData);

        if(isValid) {
            listing.edit(inputData)
                .then(function (data) {
                    handler.showInfo(`Listing ${data.title} updated.`);
                    viewController.renderAllListingPage(context);
                })
                .catch(handler.handleError)
        }
    };

    let getSpecifiedCar = function (id) {
        if (storage.authToken()) {
            return listing.getSpecifiedCar(id)
        } else {
            return Promise.reject(new Error("You don't have permission to do that"));
        }
    };

    let deleteCar = function (context) {
        let carId = context.params._id;

        listing.del(carId)
            .then(function () {
                handler.showInfo("Listing deleted.");
                viewController.renderAllListingPage(context);
            })
            .catch(handler.handleError)
    };

    let getSpecifiedUserCars = function (username) {
        return listing.getUserCars(username);
    };

    function checkLoggedIn() {
        return sessionStorage.getItem("authToken") !== null
    }

    function checkUsername() {
        return sessionStorage.getItem("username");
    }

    return {
        listAllCars,
        createCar,
        getSpecifiedCar,
        editCar,
        deleteCar,
        getSpecifiedUserCars
    }
})();