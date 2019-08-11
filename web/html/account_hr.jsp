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
		<style>

		</style>
	</head>
	
	<body>
	<c:if test="${id==null}">
		<c:redirect url="/html/authorization.jsp"/>;
	</c:if>
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
				<l:locale name="ahtitle"/>
			</div>
			<jsp:useBean id="hrDao" class="by.epam.ft.dao.HrDAO"/>
			<c:set var="Hr" value="${hrDao.showByAccountId(idAcc)}"/>
			<div>
			<table class="info">
				<caption><l:locale name="ainfo"/></caption>
					<tr>
						<td>
							<l:locale name="ahuridhr"/>
						</td>
						<td>
							${Hr.idHr}
						</td>
					</tr>
					<tr>
						<td>
							<l:locale name="alogin"/>
						</td>
						<td>	
							${login}
						</td>
					</tr>
					<tr>
						<td>
							<l:locale name="aname"/>
						</td>
						<td>
							${name}
						</td>
					</tr>
					<tr>
						<td>
							<l:locale name="asur"/>
						</td>
						<td>
							${surname}
						</td>
					</tr>
					<tr>
						<td>
							Email
						</td>
						<td>
							${mail}
						</td>
					</tr>
					<tr>
						<td>
							<l:locale name="abirthday"/>
						</td>
						<td>
							${birthday}
						</td>
					</tr>
					
			</table>

			<jsp:useBean id="vacDao" class="by.epam.ft.dao.VacancyDAO"  scope="session"/>
			<jsp:useBean id="accountDao" class="by.epam.ft.dao.AccountDAO"/>
			<c:set var="vacList" value="${vacDao.showAll()}"  scope="session"/>

			<table class="vacancies">
				<caption><l:locale name="avacancies"/></caption>
				<tr>
					<th><l:locale name="aidvac"/></th>
					<th><l:locale name="avac"/></th>
					<th><l:locale name="ahdescription"/></th>
					<th><l:locale name="ahdelete"/></th>
				</tr>
				<c:forEach var="item" items="${vacList}">
					<tr>
						<td>${item.idVacancy}</td>
						<td>${item.name}</td>
						<td>${item.description}</td>
						<td>
							<a type="button" class="simple-btn" href='/html/controller?command=delete_vacancy&idVacancy=${item.idVacancy}'><l:locale name="ahdeletevac"/></a>
						</td>
					</tr>
				</c:forEach>
			</table>

			<a class="show-btn" href="javascript:void(0)" onclick = "document.getElementById('envelope').style.display='block';document.getElementById('show-btn').style.display='none'" id="show-btn"><l:locale name="ahaddvac"/></a>
			<div id="envelope" class="envelope">
				<form method="post" action="/html/controller?command=add_vacancy">
					<a class="close-btn" href="javascript:void(0)" onclick = "document.getElementById('envelope').style.display='none';document.getElementById('show-btn').style.display='block'"><l:locale name="ahclose"/></a>
					<p class="txt vacancy-name"><l:locale name="ahnvacname"/></p>
					<input type="text" name="vacName" class="vacancy-name input_fields" required/>
					<p class="txt vacancy-name"><l:locale name="ahnvacdescription"/></p>
					<input type="text" name="description" class="vacancy-name input_fields" required/>
					<input type="submit" name="send" value="<l:locale name="ahadd"/>" class="confirm_modified">
				</form>
			</div>

			<jsp:useBean id="selectionDao" class="by.epam.ft.dao.SelectionDAO"/>
			<c:set var="selectionList" value="${selectionDao.showAll()}"/>
			<form method="post">
				<table class="vacancies">
					<caption><l:locale name="ahrequest"/></caption>
					<tr>
						<th><l:locale name="ahidrequest"/></th>
						<th><l:locale name="ahnamesurnamecand"/></th>
						<th><l:locale name="avac"/></th>
						<th><l:locale name="ahnamesurnamehr"/></th>
						<th><l:locale name="astatus"/></th>
						<th><l:locale name="aselectiondate"/></th>
						<th><l:locale name="ahdelete"/></th>
					</tr>
					<c:forEach var="item" items="${selectionList}">
						<c:set var="accountCandidate" value="${accountDao.showByIdUser(item.idCandidate, false)}"/>
						<c:set var="accountHR" value="${accountDao.showByIdUser(item.idHr, true)}"/>
						<tr>
							<td>${item.idSelection}</td>
							<td>${accountCandidate.name} ${accountCandidate.surname}</td>
							<td>${vacDao.showById(item.idVacancy).name}</td>
							<td name="changable">
								<c:set var="nameSurname" value="${accountHR.name} ${accountHR.surname}"/>
								<c:choose>
									<c:when test="${accountHR.name==null&&accountHR.surname==null}">
										<l:locale name="anohr"/>
									</c:when>
									<c:otherwise>
										<c:out value="${nameSurname}"/>
									</c:otherwise>
								</c:choose>
							</td>
							<td name="changable">${item.status}</td>
							<td name="changable">${item.selectionDate}</td>
							<td>
								<a type="button" class="simple-btn" href='/html/controller?command=revoke_vacancy&idSelection=${item.idSelection}'><l:locale name="arevoke"/></a>
							</td>
						</tr>
					</c:forEach>
				</table>

			</form>
			<!-- Модальное окно -->
			<a href="#x" class="overlay" id="win1"></a>
			<div class="popup">
				<div class="ipopup">
					<form>
						<h1><l:locale name="mchangerequest"/></h1>
						<p><l:locale name="mnumberrequest"/></p>
						<input type="text" class="inputp" name="idSelection" required/>
						<p>ID HR</p>
						<input type="text" class="inputp" name="idHr" placeholder="<l:locale name="murid"/> ${Hr.idHr}"/>
						<p><l:locale name="astatus"/></p>
						<select name="status" class="status">
							<option value="Заявка на рассмотрении"selected><l:locale name="mreqconsid"/></option>
							<option value="Ожидание собеседования"><l:locale name="mwaiting"/></option>
							<option value="Не прошел собеседование"><l:locale name="mnotpass"/></option>
							<option value="Прошел собеседование"><l:locale name="mpass"/></option>
						</select>
						<p><l:locale name="aselectiondate"/></p>
						<input type="date" name="selectionDate" class="date"/>
						<input type="submit" formaction="/html/controller?command=change_selection" class="show-btn" formmethod="post" value="<l:locale name="aconfirm"/>"/>
					</form>
				</div>
				<a class="close"title="<l:locale name="ahclose"/>" href="#close"></a>
			</div>
			<a type="button" href='#win1' class="show-btn"><l:locale name="mchange"/></a>

				<form action="/html/controller?command=change_password" name = "vform" method="post">
					<div class="password_change">
						<h2><l:locale name="achangepass"/></h2>
						<p><l:locale name="aoldpass"/></p>
						<input type="password" name="oldpass" class="field" required/>
						<p><l:locale name="anewpass"/></p>
						<input type="password" name="password" class="field" required/>
						<p><l:locale name="arepeatpass"/></p>
						<input type="password" name="repeat_password" class="field" required/>
						</p>
						<input type="submit" id="registration" value="<l:locale name="aconfirm"/>" class="confirm_change"/>
					</div>
				</form>
			</div>
		</main>

		<div class="footer">
			<l:locale name="foot"/>
		</div>
	
	</body>
	<script type="text/javascript" src = "../js/back_security.js"></script>
	<script type="text/javascript" src = "../js/change_password_script.js"></script>
</html>