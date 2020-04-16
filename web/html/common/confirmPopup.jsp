<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType='text/html; charset=UTF-8' %>
<%@ taglib uri="/WEB-INF/MyTag.tld" prefix="l"%>

<a href="#x" class="overlay" id="confirmPopup"></a>
<div class="popup">
    <a class="close" title="<l:locale name="ahclose"/>" href="#close"></a>
    <div class="ipopup">
        <form id="popup_form">
            <h1 id="popup_title"></h1>
            <p style="padding: 15px 0 30px 0" id="popup_text"></p>
            <p style="font-size: 10px" id="popup_small_text"></p>
        </form>
    </div>
</div>