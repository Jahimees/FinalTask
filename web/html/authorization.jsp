<%@ page contentType='text/html; charset=UTF-8' %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@ taglib uri="/WEB-INF/MyTag.tld" prefix="l"%>
<!DOCTYPE html>
<html lang="ru">
	<head>
		<meta charset="UTF-8"/>
		<title>IT ROAD. Авторизация</title>
		<link rel="stylesheet" href="../css/register_style.css" type="text/css"/>
		<link rel="stylesheet" href="../css/menu.css" type="text/css">
		<link rel="stylesheet" href="../css/media.css" type="text/css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Cuprum&display=swap"> 	
	</head>

	<body>
		<jsp:include page="common/header.jsp" />
		<form class="content" action="/html/controller?command=login" method="post">
			<div class="title">
				<l:locale name="auth"/>
			</div>

			<div class="registration">

				<div class="field_names">
					<h1><l:locale name="alogin"/></h1>
				</div>
				<input name="login" class="field" placeholder="ExampleLogin" required/>
				<div id = "login_error"></div>
				
				<div class="field_names">
					<h1><l:locale name="pass"/></h1>
				</div>
				<input class="field" name="password" type="password" placeholder="ExamplePassword1999" required/>
				<c:set var ="error" value="${errorMessage}"/>
				<c:if test="${error.length() > 0}">
					<p style="color: red; text-align: center; margin: 20px auto; font-size: 20px;">${error}</p>
				</c:if>
				<input name="authorization" id="authorization" value="<l:locale name="enter"/>" class="button" type="submit"/>
			</div>
		</form>

		<jsp:include page="common/footer.jsp" />

	</body>
	<script type="text/javascript" src = "../js/back_security.js"></script>
</html>