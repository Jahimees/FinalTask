<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/MyTag.tld" prefix="l"%>
<!DOCTYPE html>
<html>
	<head>
		<title>IT ROAD. Открытые вакансии</title>
		<meta charset="UTF-8"/>		
		<meta name="viewport" content="width=device-width, initial-scale=1"/>
		<link rel="stylesheet" href="../css/menu.css" type="text/css"/>
		<link rel="stylesheet" href="../css/media.css" type="text/css"/>
        <link rel="stylesheet" href="../css/account_style.css" type="text/css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Cuprum&display=swap"/>
		<link rel="stylesheet" href="../css/vacancy_style.css" type="text/css"/>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<link rel="stylesheet" href="../css/media_contacts.css" type="text/css"/>
		<link rel="stylesheet" type="text/css" href="../css/modal_contact.css" />
	</head>
	
	<body>
		<c:if test="${id==null}">
			<c:redirect url="/html/authorization.jsp"/>;
		</c:if>
		<jsp:include page="common/header.jsp" />
		<jsp:include page="common/confirmPopup.jsp" />
		<main class="content">
			<div class="title">
				<l:locale name="navVacancies"/>
			</div>

			<jsp:useBean id="daoVacancy" class="by.epam.ft.dao.VacancyDAO" scope="session" />
			<c:set var="resultList" value="${daoVacancy.showVacancies(true)}" scope="session"/>

			<table id="vacancyTable" name="thetable">
				<tr>
					<th><l:locale name="aidvac"/></th>
					<th><l:locale name="ahnvacname"/></th>
					<th><l:locale name="ahnvacdescription"/></th>
                    <th><l:locale name="candidatecount"/></th>
					<th></th>
                    <th></th>
				</tr>
				<c:forEach var="row" items="${resultList}">
					<tr>
						<td><p class="p_simple"><c:out value="${row.idVacancy}"/></p></td>
						<td><p class="p_simple"><c:out value="${row.name}"/></p></td>
						<td><p class="p_simple"><c:out value="${row.description}"/></p></td>
						<td><p class="p_simple"><c:out value="${row.candidateCount}"/></p></td>
						<td>
							<c:choose>
								<c:when test="${hr.equals('false')}">
									<c:set var="flag" value="false"/>
									<c:forEach items="${checkedVacancies}" var="idVac">
										<c:if test="${row.idVacancy == idVac}">
											<c:set var="flag" value="true"/>
										</c:if>
									</c:forEach>
									<c:choose>
										<c:when test="${flag.equals('false')}">
											<a type="button" class="button" href='/html/controller?command=state_vacancy&idVacancy=${row.idVacancy}'><l:locale name="registration"/></a>
										</c:when>
										<c:otherwise>
											<p class="p_simple"><l:locale name="alreadyregistered"/></p>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<a type="button" class="button" id="closeVacancyAction_${row.idVacancy}" href='#confirmPopup'><l:locale name="ahdeletevac"/></a>
								</c:otherwise>
							</c:choose>
						</td>
                        <td>
                            <a type="button" href='#changeVacancyForm' id="changeBtn_${row.idVacancy}" class="button">
                                <l:locale name="mchange"/>
                            </a>
                        </td>
					</tr>
				</c:forEach>
			</table>
			<c:choose>
				<c:when test="${hr.equals('true')}">
					<a class="show-btn" href="javascript:void(0)" onclick = "document.getElementById('envelope').style.display='block';document.getElementById('show-btn').style.display='none'" id="show-btn"><l:locale name="ahaddvac"/></a>
					<div id="envelope" class="envelope">
						<form method="post" action="/html/controller?command=add_vacancy">
							<a class="close-btn" href="javascript:void(0)" onclick = "document.getElementById('envelope').style.display='none';document.getElementById('show-btn').style.display='block'"><l:locale name="ahclose"/></a>
							<p class="txt vacancy-name"><l:locale name="ahnvacname"/></p>
							<input type="text" name="vacName" class="input_fields" required />
							<p class="txt vacancy-name"><l:locale name="ahnvacdescription"/></p>
							<input type="text" name="description" class="input_fields" required />
							<input type="submit" name="send" value="<l:locale name="ahadd"/>" class="confirm_modified">
						</form>
					</div>
				</c:when>
			</c:choose>

            <!-- Модальное окно изменения -->
            <a href="#x" class="overlay" id="changeVacancyForm"></a>
            <div class="popup">
                <a class="close" title="<l:locale name="ahclose"/>" href="#close"></a>
                <div class="ipopup">
                    <form>
                        <h1><l:locale name="changevacancy"/></h1>
                        <p class="inputh"><l:locale name="aidvac"/></p>
                        <input type="text" class="inputp" name="idVacancy" id="idVacancy_change" required readonly/>
                        <p class="inputh"><l:locale name="ahnvacname"/></p>
                        <input type="text" class="inputp" name="vacName" id="vacancyName_change"/>
						<p class="inputh"><l:locale name="ahnvacdescription"/></p>
                        <textarea rows="5" class="input_text" name="description" id="vacancyDescription_change"></textarea>

                        <input type="submit" formaction="/html/controller?command=change_vacancy" class="show-btn" formmethod="post" value="<l:locale name="aconfirm"/>"/>
                    </form>
                </div>
            </div>

			<div class="pic_container">
				<img class="pic" src="../img/vacancy.png" width="50.5%" />
			</div>
			
		</main>

		<jsp:include page="common/footer.jsp" />

	</body>
	<script type="text/javascript" src = "../js/back_security.js"></script>

</html>

<script type="text/javascript" src="../js/popup_generator.js"></script>
<script>
    $(document).ready(function () {

        //Подстановка значения id вакансии при изменении значения
        <c:forEach items="${resultList}" var="vacancy">
            $("#changeBtn_${vacancy.idVacancy}").on('click', function () {
                $("#idVacancy_change").attr("value", "${vacancy.idVacancy}");
                $("#vacancyName_change").attr("value", "${vacancy.name}");
                $("#vacancyDescription_change")[0].value = "${vacancy.description}"
            });

			$("#closeVacancyAction_${vacancy.idVacancy}").on('click', function () {
				$("#popup_title")[0].innerText = "<l:locale name="confirm_action"/>";
				$("#popup_text")[0].innerText = "<l:locale name="confirm_close_vacancy_action"/>";
				$("#popup_small_text")[0].innerText = "<l:locale name="its_will_send_email"/>";

				var popup_confirm = "popup_confirm";
				addConfirmButton(popup_confirm);
				$("#" + popup_confirm).attr("formaction",
						"/html/controller?command=close_vacancy&idVacancy=${vacancy.idVacancy}");
				$("#" + popup_confirm)[0].value="<l:locale name="aconfirm"/>";
			});
        </c:forEach>
    })
</script>