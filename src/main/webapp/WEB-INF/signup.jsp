<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 7/13/2024
  Time: 7:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="includes/header.jsp"%>
<%@include file="includes/navigation.jsp"%>
<div class="container">
    <h2 class="h2">Sign Up</h2>
    <hr class="mb-4">

    <form class="form-horizontal" role="form" action="<c:url value="/signup"/>" method="post">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" value="${userDto.username}"
                   name="username" placeholder=""/>
            <c:if test="${errors.username != null}">
                <small class="text-danger">${errors.username}</small>
            </c:if>

        </div>

        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email"
                   name="email" value="${userDto.email}"
                   placeholder="you@example.com"/>
            <c:if test="${errors.email!= null}">
                <small class="text-danger">${errors.email}</small>
            </c:if>

        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password"
                   name="password" value="${userDto.password}">
            <c:if test="${errors.password != null}">
                <small class="text-danger">${errors.password}</small>
            </c:if>

        </div>

        <div class="form-group">
            <label for="passwordConfirmed">Password Confirmed</label>
            <input type="password" class="form-control"
                   id="passwordConfirmed"
                   name="passwordConfirmed" value="${userDto.passwordConfirmed}">
            <c:if test="${errors.passwordConfirmed != null}">
                <small class="text-danger">${errors.passwordConfirmed}</small>
            </c:if>

        </div>

        <div class="form-group">
            <label for="email">Last Name</label>
            <input type="text" class="form-control" id="firstName"

                   name="firstName" value="${userDto.firstName}"/>
            <c:if test="${errors.firstName != null}">
                <small class="text-danger">${errors.firstName}</small>
            </c:if>
        </div>

        <div class="form-group">
            <label for="email">Last Name</label>
            <input type="text" class="form-control" id="lastName"

                   name="lastName" value="${userDto.lastName}"/>
            <c:if test="${errors.lastName != null}">
                <small class="text-danger">${errors.lastName}</small>
            </c:if>
        </div>

        <hr class="mb-4">
        <div class="form-group">
            <button class="btn btn-primary btn-lg" type="submit" onclick="validatePassword()">Signup
            </button>
        </div>
    </form>
    <script type="text/javascript">
        function validatePassword(){
            var password = document.getElementById("passwordConfirmed").value;
            var confirmPassword = document.getElementById("passwordConfirmed").value;

            if(password !== confirmPassword){
                return false;
            }
            return true;
        }
    </script>
</div>



<%@include file="includes/footer.jsp" %>