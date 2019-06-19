<%-- 
    Document   : aviso.jsp - gerado 
    Created on : 19/06/2019 - 05:28:54
    Author     : Radames J Halmeman  - rjhalmeman@gmail.com
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Aviso</title>
        <link href="${pageContext.request.contextPath}/css/estilos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="box">             
            <h2>Aviso:<br> <c:out value="${aviso}"></c:out></h2>
            <input type="submit" value="< Voltar" name="btVoltar" onClick="history.go(-1)"/>
        </div>
    </body>
</html>