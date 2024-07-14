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
            <input type="text" class="form-control" id="username"
                   name="username" placeholder=""/>

        </div>

        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email"
                   name="email"
                   placeholder="you@example.com"/>

        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password"
                   name="password">

        </div>

        <div class="form-group">
            <label for="passwordConfirmed">Password Confirmed</label>
            <input type="password" class="form-control"
                   id="passwordConfirmed"
                   name="passwordConfirmed">

        </div>

        <div class="form-group">
            <label for="email">First Name</label>
            <input type="text" class="form-control" id="firstName"
                   name="firstName"/>

        </div>

        <div class="form-group">
            <label for="email">Last Name</label>
            <input type="text" class="form-control" id="lastName"
                   value="${userDto.lastName}"
                   name="lastName"
            />
        </div>

        <hr class="mb-4">
        <div class="form-group">
            <button class="btn btn-primary btn-lg" type="submit" onclick="validatePassword()">Signup
            </button>
        </div>
    </form>
    <script type="text/javascript">
        function validatePassword(){
            var password = document.getElementsByName("password").value;
            var confirmPassword = document.getElementById("passwordConfirmed").value;
            if(password !== confirmPassword){
                alert("Passwords do not match.")
            }
            return true;
        }
    </script>
</div>



<%@include file="includes/footer.jsp" %>