<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<link rel="stylesheet" href="views/styles.css"/>

<div id="bg-div">
    <h1 id="WelcomeTitle">Twitter</h1>
    <img id="logo-img" src="views/twitter_logo.png">
    <div id="welcomeDiv">
        <% if (session.getAttribute("username") == null) { %>
        <a id="WelcomeLinks" href="Login">Login</a>
        <% } else {%>
        <a id="WelcomeLinks" href="Twitter">Users Page</a>
        <a id="WelcomeLinks" href="TweetsServlet">Tweets</a>
        <a id="WelcomeLinks" href="Logout">Logout</a>
        <% }%>
    </div>
</div>