<%--
  Created by IntelliJ IDEA.
  User: crbe1606
  Date: 5/2/2022
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<div class="text-center d-flex justify-content-center">
    <h4>Register</h4>
</div>
<div class="container form d-flex justify-content-center" >
<form action="register" method="post" id="registerform">
    <div class="form-group">
        <label>First Name</label>
        <input type="text" class="form-control"   placeholder="Enter first name" name="firstname" required>
    </div>
    <div class="form-group">
        <label>Last Name</label>
        <input type="text" class="form-control"   placeholder="Enter last name" name="lastname" required>
    </div>
    <div class="form-group">
        <label >Email address</label>
        <input type="email" class="form-control"  placeholder="Enter email" name="email" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$">
        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else</small>
        <small hidden id="emailexist" class="form-text text-danger"><br>There is a user with the same email</small>
    </div>
    <div class="form-group ">
        <label>Password (6 to 20 characters)</label>
        <input type="password" class="form-control"  placeholder="Password" name="password" required minlength="6" maxlength="20">
    </div>
    <div class="form-group">
        <label>Confirm Password (6 to 20 characters)</label>
        <input type="password" class="form-control" placeholder="Password" name="passwordconfirm" required minlength="6" maxlength="20">
        <small hidden id="passinvalid" class="form-text text-danger"><c:if test="${matchPassword == 'false'}" >
            Passwords don't match!
        </c:if></small>
    </div>

    <button type="submit" class="btn btn-primary mt-3">Submit</button>
</form>
</div>