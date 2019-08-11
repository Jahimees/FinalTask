<%@ page contentType='text/html; charset=UTF-8' %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="/WEB-INF/MyTag.tld" prefix="l"%>
<!DOCTYPE html>
<html lang="ru">

	<head>
		<meta charset="UTF-8"/>
		<title>IT ROAD. Регистрация</title>
		<link rel="stylesheet" href="../css/register_st1yle.css" type="text/css"/>
		<link rel="stylesheet" href="../css/menu.css" type="text/css"> 
		<link rel="stylesheet" href="../css/media.css" type="text/css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Cuprum&display=swap"> 
	</head>

	<body>

		<header>
			<nav class="menu">			
				<input type="checkbox" name="toggle" id="menu" class="toggleMenu">
				<label for="menu" class="toggleMenu"><i class="fa fa-bars"></i>Меню</label>
				<ul>
					<li><a href="main.jsp"><i class="fa fa-bookmark"></i><l:locale name="navMain"/></a></li>
					<li><a href="contacts.jsp"><i class="fa fa-address-book"></i><l:locale name="navContacts"/></a></li>
					<li><a href="/html/controller?command=open_vacancies"><i class="fa fa-list-ul"></i><l:locale name="navVacancies"/></a></li>
					<li>
						<input type="checkbox" name="toggle" class="toggleSubmenu" id="sub_m1">
						<a href="/html/controller?command=open_account"><i class="fa fa-laptop"></i><l:locale name="navAccount"/></a>
						<label for="sub_m1" class="toggleSubmenu"><i class="fa"></i></label>
						<ul>
							<c:set var="session" value="${id}"/>
							<c:choose>
								<c:when test="${session==null}">
									<li><a href="authorization.jsp"><l:locale name="navAuthorization"/></a></li>
									<li><a href="register.jsp"><l:locale name="navRegistration"/></a></li>
								</c:when>
								<c:otherwise>
									<li><a href="/html/controller?command=logout"><l:locale name="navLogout"/></a></li>
								</c:otherwise>
							</c:choose>
						</ul>
					</li>
					<li>
						<input type="checkbox" name="toggle" class="toggleSubmenu" id="sub_m2">
						<a href=""><i class="fa fa-globe"></i><l:locale name="navLanguage"/></a>
						<label for="sub_m2" class="toggleSubmenu"><i class="fa"></i></label>
						<ul>
							<li><a href="/html/controller?command=change_locale&locale=_" name="locale" value="_">Русский</a></li>
							<li><a href="/html/controller?command=change_locale&locale=en_US" name="locale" value="en_US">English</a></li>
						</ul>
					</li>
				</ul>				
			</nav>
		</header>
		
		<main class="content">
			<div class="title">
				<l:locale name="registration"/>
			</div>

			<form class="box" action="/html/controller?command=register" method="post" onsubmit="public_static_void_main()" name = "vform">
				<div class="registration">
					<c:set var ="error" value="${errorMessage}"/>
					<c:if test="${error.length() > 0}">
						<p style="color: red; text-align: center; margin: 20px auto; font-size: 20px;">${error}</p>
					</c:if>
					<div class="field_names">
						<h1><l:locale name="alogin"/></h1>
					</div>
					<input name="login" class="field" placeholder="ExampleLogin" required/>
					<div class="field_names">
						<h1>Email</h1>
					</div>
					<div id = "email_div">
						<input type="text" class="field" id="mailID" name = "mail" placeholder="examplemail@gmail.com" required/>
						<div id = "email_error" class = "ValError"></div>
					</div>

					<div class="field_names">
						<h1><l:locale name="pass"/></h1>
					</div>
					<input class="field" name="password" type="password" placeholder="ExamplePassword1999" required/>
				
					<div class="field_names">
						<h1><l:locale name="arepeatpass"/></h1>
					</div>
					<input class="field" name="repeat_password" type="password" placeholder="ExamplePassword1999" required/>
							
					<div class="field_names">
						<h1><l:locale name="aname"/></h1>
					</div>
					<input class="field" name="name" placeholder="Alexander" required/>
					<div class="field_names">
						<h1><l:locale name="asur"/></h1>
					</div>
					<input class="field" name="surname" placeholder="Voronin" required/>
					<div class="field_names">
						<h1><l:locale name="abirthday"/></h1>
					</div>
					<div id ="age_div">
						<input name="age" class="field" placeholder="yyyy-mm-dd" required/>
						<div id = "age_error"></div>
					</div>
					<input type="submit" id="registration" value="<l:locale name="aconfirm"/>" class="button"/>

				</div>
			</form>

		</main>
		
		<div class="footer">
			<l:locale name="foot"/>
		</div>

		
	</body>
	<script type="text/javascript" src = "../js/register_scrip1t.js"></script>
	
</html>