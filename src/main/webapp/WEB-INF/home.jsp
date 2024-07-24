
<%@include file="includes/header.jsp" %>
<%@include file="includes/navigation.jsp" %>
<%@ taglib prefix="sec" uri="https://bazlur.com/functions"%>
<div class="container">
    <div class="jumbotron">
        <h1>Welcome to e-shoppers! </h1>
        <img src="<c:url value="/image/cart.jpg"/>" style="height: 200px"
             alt=""/>
    </div>

    <div class="row">
        <c:forEach var="product" items="${products}">
            <div class="col-sm-4">
                <div class="card h-100 mb-4">
                    <div class="card-body">
                        <h5 class="card-title">
                            <c:out value="${product.name}"/>
                        </h5>
                        <p class="card-text">
                            <c:out value="${product.description}"/>
                        </p>
                        <p class="card-text">
                            Price: $ <c:out value="${product.price}"/>
                        </p>

                        <a href="#" class="card-link btn btn-outline-info">
                            Add toCart
                        </a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<div class="jumbotron">
    <c:if test="${sec:isAuthenticated(pageContext.request)}">
        <h1>Hello
            <c:out value="${sec:getCurrentUser(pageContext.request).firstName}"/>, welcome to e-Shoppers!
        </h1>
    </c:if>
    <img src="<c:url value="/image/cart.jpg"/>" style="height: 200px" alt=""/>



</div>
<%@include file="includes/footer.jsp" %>
