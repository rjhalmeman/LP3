<%-- 
    Document   : index
    Created on : 19/06/2019, 08:15:55
    Author     : radames


ATENÇÃO

            Tem que adicionar a biblioteca jstl-1.2.jar no projeto
                 o arquivo está pasta src deste projeto
--%>




<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transferir informações no projeto</title>
    </head>
    <body>
        <h1>Form 1</h1>
         <%request.getAttribute("beanPessoa");%>
        <form name="formUm" action="${pageContext.request.contextPath}\MeuServlet" method="POST">
            <label>Id:</label>
            <input type="text" name="id" value="<c:out value="${beanPessoa.id}"/>" size="20" />
            <br>
           
            <label>Nome:</label>
            <input type="text" name="nome" value="<c:out value="${beanPessoa.nome}"/>" size="50" />
            <br>
            <input type="submit" value="Enviar" name="btEnviar" />

        </form>


    </body>
</html>
