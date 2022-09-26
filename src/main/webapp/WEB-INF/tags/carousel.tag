<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header id="carousel">
    <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="https://images.pexels.com/photos/7524996/pexels-photo-7524996.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" class="d-block w-100" alt="...">
                <c:if test="${sessionScope.user!=null}">
                <div class="carousel-caption d-none d-md-block">
                    <h5 class= "text-dark bg-white mb-4 pl-3 pr-3 pt-1 pb-1 font-weight-bold" >Welcome, ${sessionScope.user.firstName}
                    </h5>
                    <p class="text-dark bg-white text-justify">Don't just sit back and browse the latest comics and manga. Good luck.</p>
                </div>
                </c:if>
            </div>
            <div class="carousel-item">
                <img src="https://images.pexels.com/photos/6654172/pexels-photo-6654172.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" class="d-block w-100" alt="...">
            </div>
            <div class="carousel-item">
                <img src="https://images.pexels.com/photos/6654177/pexels-photo-6654177.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" class="d-block w-100" alt="...">
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
</header>