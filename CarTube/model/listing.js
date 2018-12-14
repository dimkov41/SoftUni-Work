const listing = (function () {
    let getAllCars = function () {
        return $.ajax({
            type: "GET",
            url: storage.baseUrl + "appdata/" + storage.appKey + "/cars?query={}&sort={\"_kmd.ect\": -1}",
            headers: storage.authTokenAuth()
        });
    };

    let create = function (data) {
        return $.ajax({
            type: "POST",
            url: storage.baseUrl + "appdata/" + storage.appKey + "/cars",
            headers: storage.authTokenAuth(),
            data: data
        });
    };

    let getSpecifiedCar = function (id) {
        return $.ajax({
            url: storage.baseUrl + "appdata/" + storage.appKey + "/cars/" + id,
            headers: storage.authTokenAuth()
        })
    };

    let edit = function (data) {
            return $.ajax({
                method: "PUT",
                url: storage.baseUrl + "appdata/" + storage.appKey + "/cars/" + data._id,
                headers: storage.authTokenAuth(),
                data: {
                    "brand": data.brand,
                    "description": data.description,
                    "fuel": data.fuelType,
                    "imageUrl": data.imageUrl,
                    "isAuthor": true,
                    "model": data.model,
                    "price": data.price,
                    "seller": sessionStorage.getItem("username"),
                    "title": data.title,
                    "year": data.year,
                }
            })
    };

    let del = function (id) {
        return $.ajax({
            method: "DELETE",
            //https://baas.kinvey.com/appdata/app_id/cars/car_id
            url: storage.baseUrl + "appdata/" + storage.appKey + "/cars/" + id,
            headers: storage.authTokenAuth()
        })
    };

    let getUserCars = function (username) {
        return $.ajax({
            url: storage.baseUrl + "appdata/" + storage.appKey + `/cars?query={"seller":"${username}"}&sort={"_kmd.ect": -1}`,
            headers: storage.authTokenAuth()
        })
    };

    return {
        getAllCars,
        create,
        getSpecifiedCar,
        edit,
        del,
        getUserCars
    }
})();