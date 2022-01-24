<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>USERS</title>
        <jsp:include page="views/topNav.jsp" />
    </head>
    <body style='margin-left: 15px;'>
        <div id="page-container">
            <h2>User List</h2>
            <table class="tablePerson">
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                </tr>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td><c:out value="${user.id}" /></td>
                        <td><c:out value="${user.userName}" /></td>
                    </tr>
                </c:forEach>
            </table>
            <h3>Add User</h3>
            <form action="Twitter" method="post">
                <div>
                    <label>Username</label>
                    <input type="text" name="username"/></br> 
                    <label>Password</label>
                    <input type="password" name="password"/></br> 
                </div>
                <div>
                    <input type='hidden' name='action' value='addUser'/>
                    <input id="actionButton" type='submit' value='Create User'/></br>
                </div>
            </form>

            <h3>Update User</h3>
            <form action="Twitter" method="post">
                <div>
                    <label>ID</label>
                    <input type="text" name="id"/></br> 
                    <label>Username</label>
                    <input type="text" name="username"/></br> 
                    <label>Password</label>
                    <input type="password" name="password"/></br> 
                </div>
                <div>
                    <input type='hidden' name='action' value='updateUser'/>
                    <input id="actionButton" type='submit' value='Update User'/></br>
                </div>
            </form>
            <h3>Delete User</h3>
            <form action="Twitter" method="post">
                <div>
                    <label>ID</label>
                    <input type="text" name="id"/>
                </div>
                <div>
                    <input type='hidden' name='action' value='deleteUser'/>
                    <input id="buttonDelete" type='submit' value='Delete User'/></br>
                </div>
            </form>
            <h3>Follow</h3>
            <form action="Twitter" method="post">
                <div>
                    <label>ID</label>
                    <input type="text" name="id"/>
                    <input type='hidden' name='action' value='following'/>
                    <input id="followingButton" type='submit' value='Follow'/></br>
                </div>
                
            </form>
            <jsp:include page="views/footer.jsp" />
        </div>
    </body>
