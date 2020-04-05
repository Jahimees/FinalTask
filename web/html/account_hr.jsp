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
		<jsp:useBean id="vacancyDao" class="by.epam.ft.dao.VacancyDAO"  scope="session"/>
		<c:set var="topVacancies" value="${vacancyDao.takePopularVacancies()}"/>
		<script src="https://www.google.com/jsapi"></script>
		<%--todo REFACTOR--%>
		<script>

			google.load("visualization", "1", {packages:["corechart"]});
			google.setOnLoadCallback(drawChart);
				function drawChart() {
				var data1 = new Map();
				data1.set('Название', 'Значение');

				<c:forEach var="item" items="${topVacancies}" >
					data1.set('${item.getKey()}', '${item.getValue()}');
				</c:forEach>

				var data = google.visualization.arrayToDataTable([
					['Название', 'Значение'],
					<c:forEach var="item" items="${topVacancies}" >
						['${item.getKey()}', ${item.getValue()}],
					</c:forEach>
				]);
				var options = {
					title: 'Самые популярные вакансии',
					is3D: true,
					pieResidueSliceLabel: 'Остальное'
				};
				var chart = new google.visualization.PieChart(document.getElementById('air'));
				chart.draw(data, options);
			}
		</script>

		<c:if test="${id==null}">
			<c:redirect url="/html/authorization.jsp"/>;
		</c:if>
		<jsp:include page="common/header.jsp" />

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
			    		<td><l:locale name="ahuridhr"/></td>
			    		<td>${Hr.idHr}</td>
			    	</tr>
			    	<tr>
			    		<td><l:locale name="alogin"/></td>
			    		<td>${login}</td>
			    	</tr>
			    	<tr>
			    		<td><l:locale name="aname"/></td>
			    		<td>${name}</td>
			    	</tr>
			    	<tr>
			    		<td><l:locale name="asur"/></td>
			    		<td>${surname}</td>
			    	</tr>
			    	<tr>
			    		<td>Email</td>
			    		<td>${mail}</td>
			    	</tr>
			    	<tr>
			    		<td><l:locale name="abirthday"/></td>
			    		<td>${birthday}</td>
			    	</tr>
			    </table>

			    <jsp:useBean id="vacDao" class="by.epam.ft.dao.VacancyDAO"  scope="session" />
			    <jsp:useBean id="accountDao" class="by.epam.ft.dao.AccountDAO" />
			    <jsp:useBean id="selectionDao" class="by.epam.ft.dao.SelectionDAO" />
			    <c:set var="selectionList" value="${selectionDao.showAll()}"/>

			    <div id="air" style="margin: 0 auto;"></div>

			    <form method="post">
			    	<table class="vacancies">
			    		<caption><l:locale name="ahrequest"/></caption>
			    		<tr bgcolor="#225e83" style="color: white">
			    			<th><l:locale name="ahidrequest"/></th>
			    			<th><l:locale name="ahnamesurnamecand"/></th>
			    			<td><l:locale name="usermail"/></td>
			    			<th><l:locale name="avac"/></th>
			    			<th><l:locale name="vacstatus"/></th>
			    			<th><l:locale name="ahnamesurnamehr"/></th>
			    			<th><l:locale name="astatus"/></th>
			    			<th><l:locale name="aselectiondate"/></th>
			    			<th><l:locale name="aregistrationdate"/></th>
                            <th><l:locale name="mchange"/></th>
			    			<th><l:locale name="ahdelete"/></th>
			    		</tr>
                        <c:choose>
                            <c:when test="${filter_list != null}">
                                <c:set var="selectionList" value="${filter_list}"/>
                            </c:when>
                        </c:choose>
			    		<c:forEach var="item" items="${selectionList}">
			    			<c:set var="accountCandidate" value="${accountDao.showByIdUser(item.idCandidate, false)}"/>
			    			<c:set var="accountHR" value="${accountDao.showByIdUser(item.idHr, true)}"/>
			    			<tr id="selection_row_${item.idSelection}">
			    				<td>${item.idSelection}</td>
			    				<td>${accountCandidate.name} ${accountCandidate.surname}</td>
			    				<td>${accountCandidate.email}</td>
			    				<td>${vacDao.showById(item.idVacancy).name}</td>
			    				<c:choose>
			    					<c:when test="${vacancyDao.showById(item.idVacancy).status.equals('closed')}">
			    						<td bgcolor="#ffdab9">
			    							<l:locale name="closed"/>
			    						</td>
			    					</c:when>
			    					<c:otherwise>
			    						<td bgcolor="#afffaa">
			    							<l:locale name="opened"/>
			    						</td>
			    					</c:otherwise>
			    				</c:choose>
			    				<td>
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
			    				<td class="selectionStatus_table">${item.status}</td>
			    				<td class="selectionDate_table">
                                    <c:choose>
			    					    <c:when test="${item.selectionDate!=null}">
			    					    	<c:out value="${item.selectionDate}"/>
			    					    </c:when>
			    					    <c:otherwise>
			    					    	<c:out value="--"/>
			    					    </c:otherwise>
			    				    </c:choose>
                                </td>
			    				<td>
			    					<c:choose>
			    						<c:when test="${item.registrationDate!=null}">
			    							<c:out value="${item.registrationDate}"/>
			    						</c:when>
			    						<c:otherwise>
			    							<c:out value="--"/>
			    						</c:otherwise>
			    					</c:choose>
			    				</td>
                                <td>
                                    <a type="button" href='#changeSelectionForm' id="changeBtn_${item.idSelection}" class="simple-btn">
                                        <l:locale name="mchange"/>
                                    </a>
                                </td>
			    				<td>
			    					<a type="button" class="simple-btn" href='/html/controller?command=revoke_vacancy&idSelection=${item.idSelection}'><l:locale name="arevoke"/></a>
			    				</td>
			    			</tr>
			    		</c:forEach>
			    	</table>
			    </form>

			    <!-- Модальное окно фильтрации-->
			    <a href="#x" class="overlay" id="filterForm"></a>
			    <div class="popup">
			    	<a class="close" title="<l:locale name="ahclose"/>" href="#close"></a>
			    	<div class="ipopup">
			    		<form>
			    			<h1><l:locale name="afilter"/></h1>
			    			<p><l:locale name="ahnamesurnamehr"/></p>
			    			<input type="text" class="inputp" name="hr_name" />
			    			<p><l:locale name="ahnamesurnamecand"/></p>
			    			<input type="text" class="inputp" name="candidate_name"/>
			    			<p><l:locale name="astatus"/></p>
			    			<input type="text" name="status" class="inputp"/>
			    			<p><l:locale name="ahnvacname"/></p>
			    			<input type="text" name="vacancy_name" class="inputp"/>
			    			<p><l:locale name="aselectiondate"/></p>
			    			<input type="date" name="selection_date" class="date"/>
			    			<p><l:locale name="aregistrationdate"/></p>
			    			<input type="date" name="registration_date" class="date"/>
			    			<input type="submit" formaction="/html/controller?command=open_account" class="show-btn" formmethod="post" value="<l:locale name="aconfirm"/>"/>
			    		</form>
			    	</div>
			    </div>
			    <a type="button" href='#filterForm' class="show-btn"><l:locale name="afilter"/></a>

			    <!-- Модальное окно изменения -->
			    <a href="#x" class="overlay" id="changeSelectionForm"></a>
			    <div class="popup">
			    	<a class="close" title="<l:locale name="ahclose"/>" href="#close"></a>
			    	<div class="ipopup">
			    		<form>
			    			<h1><l:locale name="mchangerequest"/></h1>
			    			<p><l:locale name="mnumberrequest"/></p>
			    			<input type="text" class="inputp" name="idSelection" id="idSelection_change" required readonly/>
			    			<p>ID HR</p>
			    			<input type="text" class="inputp" name="idHr" id="idHr_change" placeholder="<l:locale name="murid"/> ${Hr.idHr}"/>
			    			<p><l:locale name="astatus"/></p>
			    			<select name="status" class="status" id="selectionStatus_change">
			    				<option value="Заявка на рассмотрении" selected><l:locale name="mreqconsid"/></option>
			    				<option value="Ожидание собеседования"><l:locale name="mwaiting"/></option>
			    				<option value="Не прошел собеседование"><l:locale name="mnotpass"/></option>
			    				<option value="Прошел собеседование"><l:locale name="mpass"/></option>
			    			</select>
			    			<p id="selectionDateLabel_change"><l:locale name="aselectiondate"/></p>
			    			<input type="date" name="selectionDate" class="date" id="selectionDateInput_change"/>
			    			<input type="submit" formaction="/html/controller?command=change_selection" class="show-btn" formmethod="post" value="<l:locale name="aconfirm"/>"/>
			    		</form>
			    	</div>
			    </div>

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
		<jsp:include page="common/footer.jsp" />
	</body>

	<script type="text/javascript" src = "../js/back_security.js"></script>
	<script type="text/javascript" src = "../js/change_password_script.js"></script>
    <script>
        $(document).ready(function () {

            //Подстановка значения id заявки при изменении значения
            <c:forEach items="${selectionList}" var="selection">
                $("#changeBtn_${selection.idSelection}").on('click', function () {
                    $("#idSelection_change").attr("value", "${selection.idSelection}");
                    $("#idHr_change").attr("value", "${selection.idHr != 0 ? selection.idHr : ""}");
					fillTheFields(${selection.idSelection});
					var status = $("#selection_row_${selection.idSelection} > .selectionStatus_table")[0].innerText;
					$("#selectionStatus_change")[0].value = status;
					checkStatusPicker();
                });
            </c:forEach>

            $("#selectionStatus_change").on('change', function () {
                fillTheFields($("#idSelection_change")[0].value);
                checkStatusPicker()
            });

            function fillTheFields(idSelection) {
                var date = $("#selection_row_" + idSelection + " > .selectionDate_table")[0].innerText;
                $("#selectionDateInput_change").attr("value", date);
            }

            function checkStatusPicker() {
                switch ($("#selectionStatus_change")[0].value) {
                    case "Заявка на рассмотрении": {
                        $("#selectionDateInput_change").hide();
                        $("#selectionDateLabel_change").hide();
                        break;
                    }
                    case "Ожидание собеседования": {
                        $("#selectionDateInput_change").show();
                        $("#selectionDateLabel_change").show();
                        break;
                    }
                    case "Не прошел собеседование": {
                        $("#selectionDateInput_change").hide();
                        $("#selectionDateLabel_change").hide();
                        break;
                    }
                    case "Прошел собеседование": {
                        $("#selectionDateInput_change").hide();
                        $("#selectionDateLabel_change").hide();
                    }
                }
            }
        })
    </script>
</html>