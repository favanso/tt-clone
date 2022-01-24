<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TWEETS</title>
        <jsp:include page="views/topNav.jsp" />
    </head>
    <body style='margin-left: 15px;'>
        <div id="page-container">
            <h1>Tweets</h1>
            <table class="tablePerson">
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Content</th>
                    <th>Date/Time</th>
                    <th>Likes</th>
                </tr>
                <c:forEach var="tweets" items="${tweets}">
                    <tr>
                        <td><c:out value="${tweets.id}" /></td>
                        <td><c:out value="${tweets.userId}" /></td>
                        <td><c:out value="${tweets.content}" /></td>
                        <td><c:out value="${tweets.dateTime}" /></td>
                        <td><c:out value="${tweets.likes}" /></td>
                    </tr>
                </c:forEach>
            </table>
            <h2>Add Tweet</h2>
            <form action="TweetsServlet" method="post">
                <div>
                    <input type='hidden' name='action' value='addTweet'/>
                    <input id="actionButton" type='submit' value='Add Tweet'/></br>
                </div>
            </form>

            <h2>Update Tweet</h2>
            <form action="TweetsServlet" method="post">
                <div>
                    <label>ID</label>
                    <input type="text" name="id"/>
                    <label>Username</label>
                    <input type="text" name="userId"/></br> 
                    <label>Content</label>
                    <textarea name="content" id="" cols="100%" rows="3" placeholder="What's happening?" ></textarea><br>
                    <label>DateTime</label>
                    <input type="text" name="dateTime"/></br>
                    <label>Likes</label>
                    <input type="text" name="likes"/></br> 
                </div>
                <div>
                    <input type='hidden' name='action' value='updateTweet'/>
                    <input id="actionButton" type='submit' value='Update Tweet'/></br>
                </div>
            </form>
            <br>
            <h2>You can't delete a Tweet</h2>
            <jsp:include page="views/footer.jsp" />
        </div>
    </body>
