function getProductShop() {
    $(document).on('click', 'ul.menu-category li a', function (e) {
        e.preventDefault();
        var categoryClicked = $(this).attr("href");
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/product",
            data: {
                category: categoryClicked
            },
            success: function (dataReceivedFromServer) {
                $("#productshop").html(dataReceivedFromServer);
            },
            error: function () {
                $('#productshop').html("<span class='message'>Data could not be sent to the server (</span>");
            }

        })
    });
}