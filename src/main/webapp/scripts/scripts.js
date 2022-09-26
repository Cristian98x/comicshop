const LOCALHOST = "http://localhost:8080/";


function category() {
    $.ajax({
        url: LOCALHOST + "shop",
        type: "GET",
        success: function (data) {
            $('#indexcontent').html(data)
            $("ul.menu-category li:first-child a").click();
        },
        error: function () {
            $('#indexcontent').html("<span class='message'>Data could not be sent to the server (</span>");
        },
    });
}

function cartEvent() {
    $("#cart").click(function (e) {
        e.preventDefault();
        $.ajax({
            type: "GET",
            url: LOCALHOST + "cart",
            success: function (dataReceivedFromServer) {
                $("#indexcontent").html(dataReceivedFromServer);
            },
            error: function () {
                $('#indexcontent').html("<span class='message'>Data could not be sent to the server (</span>");
            }
        })
    });
}

function homeEvent() {
    $("#homelink").click(function (e) {
        e.preventDefault();
        category();
    });
}

function logoEvent() {
    $("#logohome").click(function (e) {
        e.preventDefault();
        category();
    });
}

function registerEvent() {
    $(document).on('click', '#registerlink', function (e) {
        e.preventDefault();
        $.ajax({
            type: "GET",
            url: LOCALHOST + "register",
            success: function (dataReceivedFromServer) {
                $('#indexcontent').html(dataReceivedFromServer);
                $('#registerform').submit(function (event) {
                    event.preventDefault();
                    console.log("Register form submit");
                    console.log($(this).serialize())
                    $.ajax({
                        type: 'POST',
                        url: LOCALHOST + "register",
                        data: $(this).serialize(),
                        statusCode: {
                            404: function () {
                                console.log("Error")
                            },
                            402: function () {
                                $('#emailexist').attr("hidden", false)
                                setTimeout(function () {
                                    $('#emailexist').attr("hidden", true)
                                }, 4000)
                            },
                            403: function () {
                                $('#passinvalid').attr("hidden", false)
                                setTimeout(function () {
                                    $('#passinvalid').attr("hidden", true)
                                }, 4000)
                            },
                            200: function () {
                                category();
                            },
                            500: function () {
                                console.log("Server error" + $(this).serialize())
                            }
                        }
                    });
                });
            },
            error: function () {
                $('#indexcontent').html("<span class='message'>Data could not be sent to the server (</span>");
            }
        });
    });
}

function loginEvent() {
    $("#loginlink").click(function (e) {
        e.preventDefault();
        $.ajax({
            type: "GET",
            url: LOCALHOST + "login",
            success: function (dataReceivedFromServer) {
                $('#indexcontent').html(dataReceivedFromServer)
            },
            error: function () {
                $('#indexcontent').html("<span class='message'>Data could not be sent to the server (</span>");
            }
        });
    });
}

function logoutEvent() {
    $("#logoutlink").click(function (event) {
        event.preventDefault();
        $.ajax({
            type: "POST",
            url: LOCALHOST + "logout",
            success: function () {
                $('#navbar').load(location.href + " #navbar>*", "", bindEvent);
                $('#carousel').load(location.href + " #carousel>*", "");
                category();
            },
            error: function () {
                $('#indexcontent').html("<span class='message'>Data could not be sent to the server (</span>");
            }
        });
    })
}

function orderEvent() {
    $("#myorderlink").click(function (e) {
        e.preventDefault();
        $.ajax({
            type: "GET",
            url: LOCALHOST + "order",
            success: function (dataReceivedFromServer) {
                $("#indexcontent").html(dataReceivedFromServer);
            },
            error: function () {
                $('#indexcontent').html("<span class='message'>Data could not be sent to the server (</span>");
            }
        })
    });
}


function bindEvent() {
    cartEvent();
    loginEvent();
    logoEvent();
    logoutEvent()
    registerEvent();
    homeEvent();
    orderEvent();
}

$(document).ready(function () {

    bindEvent();
    getProductShop();
    category();

    var quantity = 0;
    var product;
    const LOCALHOST = "http://localhost:8080/";


    $(".hover").mouseleave(
        function () {
            $(this).removeClass("hover");
        }
    );


    $(document).on('click', '#checkoutlink', function (e) {
        e.preventDefault();
        $.ajax({
            type: "POST",
            url: LOCALHOST + "order",
            success: function () {
                $('#navbar').load(" #navbar>*", "", bindEvent);
                $("#indexcontent").html("<div class='loader'>Loading...</div>");
                setTimeout(function () {
                    $.ajax({
                        type: "GET",
                        url: LOCALHOST + "order",
                        success: function (dataReceivedFromServer) {
                            $("#indexcontent").html(dataReceivedFromServer);
                        },
                        error: function () {
                            $('#indexcontent').html("<span class='message'>Data could not be sent to the server (</span>");
                        }
                    })
                }, 3000)
            },
            statusCode: {
                401: function () {
                    $.ajax({
                        type: "GET",
                        url: LOCALHOST + "login",
                        success: function (dataReceivedFromServer) {
                            $('#indexcontent').html(dataReceivedFromServer)
                        },
                        error: function () {
                            $('#indexcontent').html("<span class='message'>Data could not be sent to the server (</span>");
                        }
                    });
                },
                402: function () {
                    $('#indexcontent').append("<div class='alert alert-danger' role='alert'>You can't place an order!</div>")
                },
                500: function () {
                    $('#indexcontent').append("<div class='alert alert-danger' role='alert'>You can't place an order!</div>")
                }
            }
        })
    });

    $(document).on('submit', '#loginform', function (event) {
        event.preventDefault();
        const form = $('#loginform');
        $.ajax({
            url: '/login',
            method: 'POST',
            data: form.serialize(),
            success: function () {
                $('#navbar').load(location.href + " #navbar>*", "", bindEvent);
                $('#carousel').load(location.href + " #carousel>*", "");
                category();
                mergeCart();

            },
            error: function () {
                $.ajax({
                    type: "GET",
                    url: LOCALHOST + "login",
                    success: function (dataReceivedFromServer) {
                        $('#indexcontent').html(dataReceivedFromServer)
                        $('#invalidlogin').attr("hidden", false)

                    },
                    error: function () {
                        $('#indexcontent').html("<span class='message'>Data could not be sent to the server (</span>");
                    }
                });
            }
        })
    })

    $(document).on('click', '#add-product-cart', function (e) {
        e.preventDefault();
        $.ajax({
            type: "POST",
            url: LOCALHOST + "addtocart",
            data: {
                productId: $(this).val(),
            },
            success: function () {
                $('#navbar').load(" #navbar>*", "", bindEvent);
                addToCartWhenUserLogged();
            },
            error: function () {
            }
        })
    });

    $(document).on('click', '#removeitem', function (e) {
        e.preventDefault();
        $.ajax({
            type: "POST",
            url: LOCALHOST + "deletefromcart",
            data: {
                idOfProduct: $(this).val(),
            },
            success: function () {
                $.ajax({
                    type: "GET",
                    url: LOCALHOST + "cart",
                    success: function (dataReceivedFromServer) {
                        $("#indexcontent").html(dataReceivedFromServer);
                        $('#navbar').load(" #navbar>*", "", bindEvent);
                    },
                    error: function () {
                        $('#indexcontent').html("<span class='message'>Data could not be sent to the server (</span>");
                    }
                })
            },
            error: function () {
            }
        });
    });


    $(document).on('click', '#quantityminus', function (e) {
        e.preventDefault();
        product = $(this).val();
        quantity = $(this).closest("form").serialize();
        quantity = quantity.replace(/\D/g, '');
        quantity = parseInt(quantity) - 1;
        quantityChange();
    })


    $(document).on('click', '#quantityplus', function (e) {
        e.preventDefault();
        product = $(this).val();
        quantity = $(this).closest("form").serialize();
        quantity = quantity.replace(/\D/g, '');
        quantity = parseInt(quantity) + 1;
        quantityChange();
    })


    function quantityChange() {
        $.ajax({
            type: "POST",
            url: LOCALHOST + "cartquantity",
            data: {
                idOfProduct: product,
                newQuantity: quantity
            },
            success: function () {
                console.log("Quantity changed");
                $.ajax({
                    type: "GET",
                    url: LOCALHOST + "cart",
                    success: function (dataReceivedFromServer) {
                        $("#indexcontent").html(dataReceivedFromServer);
                        $('#navbar').load(" #navbar>*", "", bindEvent);
                    },
                    error: function () {
                        $('#indexcontent').html("<span class='message'>Data could not be sent to the server (</span>");
                    }
                })
            },
            error: function () {
            }
        });
    }


    function addToCartWhenUserLogged() {
        $.ajax({
            type: "POST",
            url: LOCALHOST + "cart",
            success: function () {
            },
            error: function () {
            }
        })
    }

    function mergeCart() {
        $.ajax({
            type: "POST",
            url: LOCALHOST + "mergecart",
            success: function () {
                $('#navbar').load(" #navbar>*", "", bindEvent);
            },
            error: function () {
            }
        })
    }

    $(document).on('click', 'ul.menu-category li', function () {
        $('li').removeClass('active');
        $(this).toggleClass('active');
    })

});
