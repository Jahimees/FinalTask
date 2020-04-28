<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType='text/html; charset=UTF-8' %>
<%@ taglib uri="/WEB-INF/MyTag.tld" prefix="l"%>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8"/>
    <title>IT ROAD. Аккаунт</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../css/account_style.css" type="text/css">
    <link rel="stylesheet" href="../css/menu.css" type="text/css">
    <link rel="stylesheet" href="../css/media.css" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Cuprum&display=swap">
    <link rel="stylesheet" type="text/css" href="../css/modal_contact.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>

<body>
    <c:if test="${role==null}">
        <c:redirect url="/html/main.jsp"/>;
    </c:if>

    <jsp:include page="common/header.jsp" />
    <jsp:include page="common/confirmPopup.jsp" />


        ${idAcc}
    <jsp:include page="common/footer.jsp" />
</body>
</html>
