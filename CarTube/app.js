const app = Sammy("#container", function () {
    this.use("Handlebars", "hbs");

    this.get("#/", viewController.renderHomeView);

    this.get("#/register", viewController.renderRegisterPage);
    this.post("#/register", userController.register);

    this.get("#/login", viewController.renderLoginPage);
    this.post("#/login", userController.login);

    this.get("#/logout", userController.logout);

    this.get("#/allListings", viewController.renderAllListingPage);

    this.get("#/createListing", viewController.renderCreateListingView);
    this.post("#/createListing", listingController.createCar);

    this.get("#/edit/:_id", viewController.renderEditListingPage);
    this.post("#/edit/:_id", listingController.editCar);

    this.get("#/delete/:_id", listingController.deleteCar);

    this.get("#/myListings", viewController.renderMyListingPage);

    this.get("#/details/:_id", viewController.renderDetailsPage);


});


$(() => {
    app.run("#/");
});