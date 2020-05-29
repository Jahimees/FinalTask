<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType='text/html; charset=UTF-8' %>
<%@ taglib uri="/WEB-INF/MyTag.tld" prefix="l"%>
<html>
<head>
    <title>IT ROAD. О программе</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../css/menu.css" type="text/css">
    <link rel="stylesheet" href="../css/media.css" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Cuprum&display=swap">
    <link rel="stylesheet" href="../css/contacts.css" type="text/css">
    <link rel="stylesheet" href="../css/media_contacts.css" type="text/css">
</head>
    <body>
        <jsp:include page="common/header.jsp" />
        <main class="content">
            <div class="title">
                <l:locale name="navAbout"/>
            </div>
            <div class="txt">
                <p><l:locale name="about_text1"/></p>
            </div>
            <div class="txt">
                <p><l:locale name="about_text2"/></p>
            </div>
            <div class="txt">
                <p><l:locale name="about_text3"/></p>
            </div>
            <div class="txt">
                <p><l:locale name="about_text4"/></p>
            </div>
        </main>
        <jsp:include page="common/footer.jsp"/>
    </body>
</html>
