<%-- 
    Document   : CRUDProduto
    Created on : 19/06/2019 - 05:28:51
    Author     : Radames J Halmeman  - rjhalmeman@gmail.com
--%>

<%@page import="myUtil.BotoesDoCrud"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
    <head>
        <script>
            function atribuirAcao(valor) {
                document.getElementById('acao').value = valor;
            }
            function confirmar(valor) {
                decisao = confirm('Excluir o registro?');
                if (decisao) {
                    document.getElementById('acao').value = valor;
                } else {
                    document.getElementById('acao').value = 'cancelar';
                }
            }
        </script>
        <style>
            /*------------------------------------------ centralizando ----------------------------------------------------------*/
            .cpCentralizado {
                margin: 0 auto;
                width: 800px;
                background: #bce8f1;
                padding: 10px;
                color: #000;
            }
            .cpCentralizado h3{color: white;}
            .botaoPK input {
                background:#bce8f1;
                color: #000;
                border-style: outset;
                border-color: #1b6d85;
                height: 25px;
                width: 100px;
                font: bold 15px arial, sans-serif;
                text-shadow:none;
                padding: 3px;
            }
            .cpCentralizado table tr{border:1px black solid}

            /*Definido cor das linhas pares*/
            .cpCentralizado table tr:nth-child(even) {background: #FFF}

            /*Definindo cor das Linhas imp√°res*/
            .cpCentralizado table tr:nth-child(odd) {background: #bce8f1}       
            .cpCentralizado table td {border: 1px black solid}

        </style>
        <title>Produto</title>
    </head>
    <body>
       
        <main>
            <div class="cpCentralizado">

                <h3>Produto</h3>
                <%request.getAttribute("produto");%>
                <% BotoesDoCrud bts = (BotoesDoCrud) request.getAttribute("botoesDoCrud");
                    try {
                        if (bts == null) {
                            bts = new BotoesDoCrud("create", "read", "", "", "procurar", "listar", "", "", "");
                        }
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                %>
                <form name="formCrudProduto" id="formCrudProduto" action="${pageContext.request.contextPath}/CrudProduto" method="POST">
                    <!--////////////////// ------- atritutos -------------- ///////////////-->
                    <div class="row">
                        <div class="col-25">
                            <label for="id">id</label>
                        </div>
                        <div class="col-75">
                            <input type="text" name="id" value="<c:out value="${produto.id}"/>" size="10" <% out.print(bts.getReadOnly());%> />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-25">
                            <label for="nome">nome</label>
                        </div>
                        <div class="col-75">
                            <input type="text" name="nome" value="<c:out value="${produto.nome}"/>" size="70" <% out.print(bts.getReadOnly().isEmpty() ? "readonly=\"readonly\"" : "");%> />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-25">
                            <label for="preco">preco</label>
                        </div>
                        <div class="col-75">
                            <input type="text" name="preco" value="<c:out value="${produto.preco}"/>" size="10" <% out.print(bts.getReadOnly().isEmpty() ? "readonly=\"readonly\"" : "");%> />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-25">
                            <label for="quantidade">quantidade</label>
                        </div>
                        <div class="col-75">
                            <input type="text" name="quantidade" value="<c:out value="${produto.quantidade}"/>" size="10" <% out.print(bts.getReadOnly().isEmpty() ? "readonly=\"readonly\"" : "");%> />
                        </div>
                    </div>

                    <%
                        if (!bts.getCreate().isEmpty()) {
                            out.print("<input  type = \"submit\" value = \"Novo (prÛximo registro)\" name = \"Novo\" onclick = \"atribuirAcao('novo')\"/>");
                        }
                        if (!bts.getProcurar().isEmpty()) {
                            out.println("<input  type = \"submit\" value = \"Procurar\" name = \"Procurar\" onclick = \"atribuirAcao('procurar')\"/>");
                        }
                        if (!bts.getUpdate().isEmpty()) {
                            out.println("<input  type = \"submit\" value = \"Editar\" name = \"Editar\" onclick = \"atribuirAcao('editar')\"/>");
                        }
                        if (!bts.getDelete().isEmpty()) {
                            out.print("<input  type = \"submit\" value = \"Excluir\" name = \"Excluir\" onclick = \"confirmar('excluir')\"/>");
                        }
                        if (!bts.getList().isEmpty()) {
                            out.print("<input  type = \"submit\" value = \"Listar\" name = \"Listar\" onclick = \"atribuirAcao('listar')\"/>");
                        }
                        if (!bts.getSave().isEmpty()) {
                            out.println("<input  type = \"submit\" value = \"Salvar\" name = \"Salvar\" onclick = \"atribuirAcao('salvar')\"/>");
                        }
                        if (!bts.getCancelar().isEmpty()) {
                            out.println("<input  type = \"submit\" value = \"Cancelar\" name = \"Cancelar\" onclick = \"atribuirAcao('cancelar')\"/>");
                        }

                    %>
                    <input type="hidden" value="" name="acao" id="acao" />


                    <%request.getAttribute("listaProduto");%>
                    <table  class="zebra" >
                        <c:forEach items="${listaProduto}" var="produto">
                            <tr>
                                <td align="center"><div class="botaoPK"> <input type="submit" value="${produto.id}" name="btEscolhido" onclick="atribuirAcao(${produto.id})"/></div></td>
                                <td>${produto.nome}</td>
                                <td>${produto.preco}</td>
                                <td>${produto.quantidade}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </form>


            </div>
        </main>
    </body>

</html>