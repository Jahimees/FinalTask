<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/MyTag.tld" prefix="l" %>
<!DOCTYPE html>
<html>
    <head>
        <title>IT ROAD. Закрытые вакансии</title>
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <link rel="stylesheet" href="../css/account_style.css" type="text/css">
        <link rel="stylesheet" href="../css/menu.css" type="text/css"/>
        <link rel="stylesheet" href="../css/media.css" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Cuprum&display=swap"/>
        <link rel="stylesheet" href="../css/vacancy_style.css" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <link rel="stylesheet" href="../css/media_contacts.css" type="text/css"/>
        <link rel="stylesheet" href="../css/closed_vacancy_modal.css" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="../css/modal_contact.css"/>
    </head>
    <body>
        <c:if test="${id==null}">
            <c:redirect url="/html/authorization.jsp"/>;
        </c:if>
        <jsp:include page="common/header.jsp"/>
        <main class="content">
        <div class="title">
            <l:locale name="navVacancies"/>
        </div>

        <jsp:useBean id="daoVacancy" class="by.epam.ft.dao.VacancyDAO" scope="session"/>
        <jsp:useBean id="daoSelection" class="by.epam.ft.dao.SelectionDAO" scope="session"/>
        <jsp:useBean id="daoCandidate" class="by.epam.ft.dao.CandidateDAO" scope="session"/>
        <jsp:useBean id="daoAccount" class="by.epam.ft.dao.AccountDAO" scope="session"/>
        <c:set var="resultList" value="${daoVacancy.showVacancies(false)}" scope="page"/>

         <table id="vacancyTable" name="thetable">
             <tr>
                 <th><l:locale name="aidvac"/></th>
                 <th><l:locale name="ahnvacname"/></th>
                 <th><l:locale name="ahnvacdescription"/></th>
                 <th><l:locale name="candidatecount"/></th>
                <c:choose>
                    <c:when test="${hr.equals('true')}">
                        <th></th>
                        <th></th>
                    </c:when>
                </c:choose>
             </tr>
                <c:forEach var="row" items="${resultList}">
                    <tr>
                        <td><p><c:out value="${row.idVacancy}"/></p></td>
                        <td><p><c:out value="${row.name}"/></p></td>
                        <td><p><c:out value="${row.description}"/></p></td>
                        <td><p><c:out value="${row.candidateCount}"/></p></td>
                        <c:choose>
                            <c:when test="${hr.equals('true')}">
                                <td>
                                    <a type="button" class="button"
                                       href='/html/controller?command=open_vacancy&idVacancy=${row.idVacancy}'><l:locale
                                            name="ahopenvac"/></a>

                                </td>
                                <td>
                                    <a type="button" href='#stats' id="showInfoBtn_${row.idVacancy}" class="button">
                                        <l:locale name="vacancystat"/>
                                    </a>
                                </td>
                            </c:when>
                        </c:choose>
                    </tr>
                </c:forEach>
            </table>
            <a href="#x" class="overlay" id="stats"></a>
            <div class="popup" style="overflow-x: hidden; overflow-y: scroll; height: 85%;">
                <a class="close"title="<l:locale name="ahclose"/>" href="#close"></a>
                <div class="ipopup">
                    <form>
                        <h1><l:locale name="vacancystat"/></h1>
                        <div class="row_div">
                            <p class="p_title"><l:locale name="id_vacancy_c"/></p>
                            <p class="p_text" id="idVacancy"></p>
                        </div>
                        <div class="row_div">
                            <p class="p_title"><l:locale name="title_c"/></p>
                            <p class="p_text" id="vacancyName"></p>
                        </div>
                        <br>
                        <div class="row_div">
                            <p class="p_title"><l:locale name="candidate_count_c"/></p>
                            <p class="p_text" id="candidateCount"></p>
                        </div>
                        <br>
                        <div class="row_div">
                            <p class="p_title"><l:locale name="accept_c"/></p>
                            <input type="checkbox" id="accept" class="hide">
                            <label class="p_text" for="accept" id="acceptLabel"><%--PlaceHokder--%></label>
                            <div id="acceptDiv">
                                <%--Placeholder--%>
                            </div>
                        </div>
                        <div class="row_div">
                            <p class="p_title"><l:locale name="failed_c"/></p>
                            <input type="checkbox" id="failed" class="hide">
                            <label class="p_text" for="failed" id="failedLabel"><%--PlaceHokder--%></label>
                            <div id="failedDiv">
                                <%--Placeholder--%>
                            </div>
                        </div>
                        <div class="row_div">
                            <p class="p_title"><l:locale name="waiting_c"/></p>
                            <input type="checkbox" id="waiting" class="hide">
                            <label class="p_text" for="waiting" id="waitingLabel"><%--PlaceHokder--%></label>
                            <div id="waitingDiv">
                                <%--Placeholder--%>
                            </div>
                        </div>
                        <div class="row_div">
                            <p class="p_title"><l:locale name="request_c"/></p>
                            <input type="checkbox" id="request" class="hide">
                            <label class="p_text" for="request" id="requestLabel"><%--PlaceHokder--%></label>
                            <div id="requestDiv">
                                <%--Placeholder--%>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <div class="pic_container">
                <img class="pic" src="../img/vacancy.png" width="50.5%"/>
            </div>
        </main>

        <jsp:include page="common/footer.jsp"/>

    </body>
</html>

<script>
    $(document).ready(function () {

        <c:forEach var="vacancy" items="${resultList}" >
            $("#showInfoBtn_${vacancy.idVacancy}").on('click', function () {
                $("#acceptDiv")[0].textContent = "";
                $("#failedDiv")[0].textContent = "";
                $("#waitingDiv")[0].textContent = "";
                $("#requestDiv")[0].textContent = "";

                $("#idVacancy")[0].textContent = "${vacancy.idVacancy}";
                $("#vacancyName")[0].textContent = "${daoVacancy.showById(vacancy.idVacancy).name}";
                $("#candidateCount")[0].textContent = "${vacancy.candidateCount}";

                <c:set var="acceptList" value="${daoSelection.showSelectionsByIdVacancyAndStatus(vacancy.idVacancy, \"Прошел собеседование\")}"/>
                <c:set var="failedList" value="${daoSelection.showSelectionsByIdVacancyAndStatus(vacancy.idVacancy, \"Не прошел собеседование\")}"/>
                <c:set var="waitingList" value="${daoSelection.showSelectionsByIdVacancyAndStatus(vacancy.idVacancy, \"Ожидание собеседования\")}"/>
                <c:set var="requestList" value="${daoSelection.showSelectionsByIdVacancyAndStatus(vacancy.idVacancy, \"Заявка на рассмотрении\")}"/>

                <c:forEach var="selection" items="${acceptList}">
                    <c:set var="candidateName" value="${daoAccount.showById(daoCandidate.showById(selection.idCandidate).idAccount).toString()}" />
                    $("#acceptDiv").append ("<p class='p_little'>${candidateName}</p>");
                </c:forEach>

                <c:forEach var="selection" items="${failedList}">
                    <c:set var="candidateName" value="${daoAccount.showById(daoCandidate.showById(selection.idCandidate).idAccount).toString()}" />
                    $("#failedDiv").append ("<p class='p_little'>${candidateName}</p>");
                </c:forEach>

                <c:forEach var="selection" items="${waitingList}">
                    <c:set var="candidateName" value="${daoAccount.showById(daoCandidate.showById(selection.idCandidate).idAccount).toString()}" />
                    $("#waitingDiv").append ("<p class='p_little'>${candidateName}</p>");
                </c:forEach>

                <c:forEach var="selection" items="${requestList}">
                    <c:set var="candidateName" value="${daoAccount.showById(daoCandidate.showById(selection.idCandidate).idAccount).toString()}" />
                    $("#requestDiv").append ("<p class='p_little'>${candidateName}</p>");
                </c:forEach>

                $("#acceptLabel")[0].textContent = "${acceptList.size()}";
                $("#failedLabel")[0].textContent = "${failedList.size()}";
                $("#waitingLabel")[0].textContent = "${waitingList.size()}";
                $("#requestLabel")[0].textContent = "${requestList.size()}";

            });
        </c:forEach>
    });
</script>
