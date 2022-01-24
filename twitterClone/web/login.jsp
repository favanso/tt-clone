<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<link rel="stylesheet" href="styles.css"/>
<jsp:include page="views/topNav.jsp" />
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
</head>    
<body style='margin-left: 15px;'>
    <div id="page-container">
        <h3><%=request.getParameter("loginMsg")%></h3>
        <h2>Login</h2>
        <form action="Login" method="post">
            <div>
                <label>Username</label>
                <input type="text" name="username"/></br> 
                <label>Password</label>
                <input type="password" name="password"/></br> 
            </div>
            <div>
                <input type='hidden' name='action' value='login'/>
                <input id="actionButton" type='submit' value='Login'/></br>
            </div>
        </form>
        <br>

        <h2>Create Account</h2>
        <form action="Login" method="post">
            <div>
                <label>Username</label>
                <input type="text" name="username"/></br> 
                <label>Password</label>
                <input type="password" name="password"/></br> 
            </div>
            <div>
                <input type='hidden' name='action' value='create_account'/>
                <input id="accountButton" type='submit' value='Create Account'/></br>
            </div>
        </form>
        <jsp:include page="views/footer.jsp" />
    </div>
</body>

