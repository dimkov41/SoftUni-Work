const handler = (function () {
    $(document)
        .ajaxStart(function () {
            $("#loadingBox").show();
        })
        .ajaxComplete(function () {
            $("#loadingBox").fadeOut();
        });

    const showInfo = function (message) {
        $("#infoBox span").text(message);
        let infoBox  = $("#infoBox");
        infoBox.show();
        setTimeout(() => {
            infoBox.hide();
        },3000);

    };

    const showError = function (message) {
        $("#errorBox span").text(message);
        let errorBox  = $("#errorBox");
        errorBox.show();
        setTimeout(() => {
            errorBox.hide();
        },3000);
    };

    const handleError = function (message) {
        showError(message.responseJSON.description);
    };

    return{
        showInfo,
        showError,
        handleError
    }
})();