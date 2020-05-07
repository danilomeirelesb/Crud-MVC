package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Aluno;

/**
 *
 * @author Danilo
 */
@WebServlet(name = "AlunoServlet", urlPatterns = {"/AlunoServlet"})
public class AlunoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AlunoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AlunoServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        //processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h3>Olá Mundo Servlet</h3>");
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

        /*
        para testar se esta recebendo os dados
        PrintWriter out = response.getWriter();
        out.println(request.getParameter("txNome"));
        out.println(request.getParameter("txCpf"));
        out.println(request.getParameter("txMatricula"));*/
        Aluno aluno = new Aluno();
        AlunoDAO alunoDAO = new AlunoDAO();
        /*Aqui pega dos dados q foram digitado nas caixinhas reconhecido pelas ids*/
        aluno.setNome(request.getParameter("txNome"));
        aluno.setCpf(request.getParameter("txCpf"));
        aluno.setMatricula(request.getParameter("txMatricula"));
        
        /*System.out.println("teste: "+request.getParameter("txId"));*/
        
        if (request.getParameter("txId")== null || request.getParameter("txId").equals("")) {
            /*Aqui chama a função gravar do alunoDAO*/
            alunoDAO.gravar(aluno);
            
        } else {
            aluno.setId(Integer.parseInt(request.getParameter("txId")));
            alunoDAO.editar(aluno);
        }

        response.sendRedirect("aluno/lista.jsp");

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
