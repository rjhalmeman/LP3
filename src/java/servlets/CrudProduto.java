package servlets;

import DAOs.DAOProduto;
import Entidades.Produto;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import myUtil.BotoesDoCrud;

/**
 * Created on : 19/06/2019 - 05:28:53 Author : Radames J Halmeman -
 * rjhalmeman@gmail.com
 */
@WebServlet(name = "crudProduto", urlPatterns = {"/CrudProduto"})
public class CrudProduto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String acao = request.getParameter("acao");
            if (acao != null) {
                DAOProduto daoProduto = new DAOProduto();
                Produto produto;
                List<Produto> listaProduto;
                switch (acao) {
                    case "listar":
                        listaProduto = daoProduto.listInOrderId();
                        request.setAttribute("listaProduto", listaProduto);
                        break;
                    case "procurar":
                        //validar pk
                        if (request.getParameter("id").trim().isEmpty()) {
                            throw new Exception("Campo id não pode ser vazio");
                        }
                        produto = daoProduto.obter(Integer.valueOf(request.getParameter("id")));
                        if (produto != null) {
                            request.setAttribute("produto", produto);
                            request.setAttribute("botoesDoCrud", new BotoesDoCrud("c", "r", "u", "d", "p", "l", "", "", ""));
                        } else {//nao achou no bd                      
                            throw new Exception("Não encontrou o id = " + request.getParameter("id") + " no banco de dados");
                        }
                        break;
                    case "editar":
                        produto = daoProduto.obter(Integer.valueOf(request.getParameter("id")));
                        request.setAttribute("produto", produto);
                        request.setAttribute("botoesDoCrud", new BotoesDoCrud("", "", "", "", "", "", "s", "c", "readonly=\"readonly\""));
                        break;
                    case "novo":
                        produto = new Produto(daoProduto.autoId());
                        request.setAttribute("produto", produto);
                        request.setAttribute("botoesDoCrud", new BotoesDoCrud("", "", "", "", "", "", "s", "c", "readonly=\"readonly\""));
                        break;
                    case "excluir":
                        //   BotoesDoCrud botoesDoCrud1 = new BotoesDoCrud("create", "read", "update", "delete", "list", "procurar", "save", "cancelar", "chaveReadOnly");
                        produto = daoProduto.obter(Integer.valueOf(request.getParameter("id")));
                        if (produto != null) {//pode excluir
                            daoProduto.remover(produto);
                        }
                        produto = new Produto();
                        request.setAttribute("produto", produto);
                        request.setAttribute("botoesDoCrud", new BotoesDoCrud("", "read", "", "", "list", "procurar", "", "", ""));
                        break;
                    case "salvar":
                        //   BotoesDoCrud botoesDoCrud1 = new BotoesDoCrud("create", "read", "update", "delete", "list", "procurar", "save", "cancelar", "chaveReadOnly");
                        produto = daoProduto.obter(Integer.valueOf(request.getParameter("id")));
                        request.setCharacterEncoding("UTF-8");
                        //validações

                        if (request.getParameter("nome").trim().isEmpty()) {
                            throw new Exception("O nome  não pode ser vazio");
                        }
                        boolean ehNovo = false;
                        if (produto == null) {
                            produto = new Produto();
                            ehNovo = true;
                        }
                        produto.setId(Integer.valueOf(request.getParameter("id")));
                        produto.setNome(String.valueOf(request.getParameter("nome")));
                        produto.setPreco(Double.valueOf(request.getParameter("preco")));
                        produto.setQuantidade(Integer.valueOf(request.getParameter("quantidade")));

                        if (ehNovo) {
                            daoProduto.inserir(produto);
                        } else {
                            daoProduto.atualizar(produto);
                        }
                        request.setAttribute("produto", produto);
                        request.setAttribute("botoesDoCrud", new BotoesDoCrud("create", "read", "", "", "list", "procurar", "", "", ""));
                        break;
                    case "cancelar":
                        request.setAttribute("produto", new Produto());
                        request.setAttribute("botoesDoCrud", new BotoesDoCrud("", "read", "", "", "list", "procurar", "", "", ""));
                        break;
                    default:
                        produto = daoProduto.obter(Integer.valueOf(acao));
                        request.setAttribute("produto", produto);
                        request.setAttribute("botoesDoCrud", new BotoesDoCrud("create", "read", "update", "delete", "list", "procurar", "", "", ""));
                        break;
                }
                request.getRequestDispatcher("jsp/produto.jsp").forward(request, response);
            }//fecha o try
        } catch (Exception e) {
            request.setAttribute("aviso", e.getMessage());
            request.getRequestDispatcher("jsp/aviso.jsp").forward(request, response);
            e.getStackTrace();
        }
    } //fecha doPostdoGet
// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}