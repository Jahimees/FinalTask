<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType='text/html; charset=UTF-8' %>
<%@ taglib uri="/WEB-INF/MyTag.tld" prefix="l" %>
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
    <link rel="stylesheet" type="text/css" href="../css/modal_contact.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>

<body>
<c:if test="${role==null}">
    <c:redirect url="/html/main.jsp"/>;
</c:if>

<jsp:include page="common/header.jsp"/>
<jsp:include page="common/confirmPopup.jsp"/>

<jsp:useBean id="vacancyDao" class="by.epam.ft.dao.VacancyDAO" scope="session"/>

<main class="content">
    <div class="title">
        <l:locale name="ahtitle"/>
    </div>

    <div id="air" style="margin: 0 auto; width: 100%; height: 400px"></div>
    <%--TODO STYLES --%>
    <div style="display: inline-block; position: relative; left: 30%;">
        <p style="display: inline">С</p>
        <input type="date" id="since_popular_vacancies_date" style="display: inline"/>
        <p style="display: inline">По</p>
        <input style="display: inline" type="date" id="to_popular_vacancies_date"/>
        <input style="display: inline" type="button" value="<l:locale name="aconfirm"/>" id="datesTopVacancies">
    </div>

</main>
<jsp:include page="common/footer.jsp"/>
</body>
</html>

<script src="https://www.google.com/jsapi"></script>
<script>

    $("#datesTopVacancies").on("click", function () {
            var sinceDate = $("#since_popular_vacancies_date")[0].value;
            var toDate = $("#to_popular_vacancies_date")[0].value;
            $.ajax({
                url: "/html/controller?command=filter_analytics" +
                    "&noredirect=true" +
                    "&chart_name=chartName" +
                    "&since_date=" + sinceDate +
                    "&to_date=" + toDate,
                dataType: "javascript",
                method: "GET",
                data: {
                    "since_date": "dateTest",
                    "to_date": "dateTest"
                },
                success: function (data) {
                    drawChart(eval(data.responseText));
                },
                error: function (data) {
                    drawChart(eval(data.responseText));
                }
            });
        }
    );

    google.load("visualization", "1", {packages: ["corechart"]});
    google.setOnLoadCallback(drawChart);
    //TODO LOCALE!!!!!!
    function drawChart(dataInfo) {
        var options = {
            title: 'Самые популярные вакансии',
            is3D: true,
            pieResidueSliceLabel: 'Остальное'
        };
        var chart = new google.visualization.PieChart(document.getElementById('air'));
        chart.draw(dataInfo, options);
    }

    $(document).ready(function () {
        $("#datesTopVacancies").click();
    });
</script>
