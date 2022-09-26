<%--
  Created by IntelliJ IDEA.
  User: crbe1606
  Date: 5/5/2022
  Time: 10:07 AM
  To change this template use File | Settings | File Templates.
--%>
<div class="text-center d-flex justify-content-center">
    <h4>Login</h4>
</div>
<div class="login">
<div class="container mt-5 d-flex justify-content-center">
    <form action="login" method="post" id="loginform">
        <div class="form-group">
            <label >Email address</label>
            <input type="email" class="form-control"  placeholder="Enter email" name="email" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$">
            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
        </div>
        <div class="form-group ">
            <label>Password</label>
            <input type="password" class="form-control"  placeholder="Password" name="password" required minlength="6" maxlength="20">
            <small hidden id="invalidlogin" class="form-text text-muted">Invalid Credentials.</small>
        </div>

        <button type="submit" class="btn btn-primary mt-3 d-flex justify-content-center" id="loginbtn">Submit</button>
    </form>
</div>
</div>