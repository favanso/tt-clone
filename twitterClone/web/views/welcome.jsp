

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="styles.css"/>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Welcome User</title>
    <jsp:include page="topNav.jsp" />
</head>
<body id='body'>
    <div id="page-container">
        <h1>
            <span id='Hello'>Hi there ${username}</span>
        </h1>

        <h3>What's happening? </h3>
        
            <div>
                <form action="TweetsServlet">
                    <div>
                        <textarea name="tweet" id="" cols="100%" rows="3" placeholder="What's happening?" ></textarea>
                    </div>
                    <input type='hidden' name='action' value='addTweetReal'/>
                    <input id="tweetButton" type='submit' value='Add Tweet'/></br>
                </form>

            </div>


            <div>
                <div>
            <img src="GetImage?username=${username}" width="350" height="300"/>
        </div>
                <form action="Upload" method="post" enctype="multipart/form-data">

                    <input type="file" accept="image/*" name="file"><br>
                    <input id="actionButton" type="submit" value="Upload"><br>

                </form>
            </div>

        
        
        <jsp:include page="footer.jsp" />
    </div>
</body>

