const viewController = (function () {

    const renderHomeView = function (context) {
        if (sessionStorage.getItem("authToken")) {
            renderAllListingPage(context)
        } else {
            window.location.hash = "#/";
            context.partial('views/homeView.hbs', {loggedIn: checkLoggedIn, username: checkUsername});
        }
    };

    const renderRegisterPage = function (context) {
        context.partial('views/registerView.hbs', {loggedIn: checkLoggedIn, username: checkUsername});
    };

    const renderLoginPage = function (context) {
        window.location.hash = "#/login";
        context.partial('views/loginView.hbs', {loggedIn: checkLoggedIn, username: checkUsername});
    };

    const renderAllListingPage = function (context) {
        window.location.hash = "#/allListings";

        listingController.listAllCars()
            .then(function (data) {
                data.forEach(a => {
                    //chek if user is listing owner
                    a.owner = a._acl.creator === sessionStorage.getItem("userId");
                });

                context.partial('views/allListingView.hbs',
                    {
                        loggedIn: checkLoggedIn,
                        username: checkUsername,
                        data,
                        carsExists: checkCarsExists(data)
                    }
                );
            })
            .catch(function (error) {
                handler.handleError(error);
                window.location.hash = "#/";
            })
    };

    const renderCreateListingView = function (context) {
        context.partial("views/createListingView.hbs", {loggedIn: checkLoggedIn, username: checkUsername});
    };

    const renderEditListingPage = function (context) {
        let id = context.params._id;

        listingController.getSpecifiedCar(id)
            .then(function (data) {
                context.partial('views/editListingView.hbs', {loggedIn: checkLoggedIn, username: checkUsername, data});
            })
            .catch((error) => {
                handler.handleError(error);
                //if id is invalid go to home page
                window.location.hash = "#/";
            });
    };

    const renderMyListingPage = function (context) {
        let username = sessionStorage.getItem("username");

        if(username) {
            listingController.getSpecifiedUserCars(username)
                .then(function (data) {
                    context.partial("views/myListingView.hbs",{loggedIn:checkLoggedIn,username: checkUsername,data,carsExists: checkCarsExists(data)})
                })
                .catch(handler.handleError)
        } else {
            renderHomeView(context);
        }
    };

    const renderDetailsPage = function (context) {
        let id = context.params._id;

        listingController.getSpecifiedCar(id)
            .then(function (data) {
                context.partial("views/detailsView.hbs",{loggedIn:checkLoggedIn,username: checkUsername,data});
            })
            .catch(handler.handleError)
    };

    function checkCarsExists(data) {
        return data.length !== 0;
    }

    function checkLoggedIn() {
        return sessionStorage.getItem("authToken") !== null
    }

    function checkUsername() {
        return sessionStorage.getItem("username");
    }


    return {
        renderHomeView,
        renderRegisterPage,
        renderLoginPage,
        renderAllListingPage,
        renderCreateListingView,
        renderEditListingPage,
        renderMyListingPage,
        renderDetailsPage
    }
})();