

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="topNav.jsp" />

<link rel="stylesheet" href="views/styles.css"/>
<body id='body'>
    <div id="page-container">
        <div id="errorDiv">
            <h2 id = error>
                <% out.println("Please try Again! Something went wrong");%> 
            </h2>
            <img  src="views/twhale.png">
        </div>

        <jsp:include page="footer.jsp" />
    </div>
</body>
