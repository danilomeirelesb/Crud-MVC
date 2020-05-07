
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="controller.AlunoDAO"%>
<%
    AlunoDAO alunoDAO = new AlunoDAO();
    alunoDAO.remover(Integer.parseInt(request.getParameter("id")));
%>
<script>
    document.location="lista.jsp";
</script>
