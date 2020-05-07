<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="controller.ProfessorCursoDAO"%>
<%
   ProfessorCursoDAO professorCursoDAO = new ProfessorCursoDAO();
    professorCursoDAO.remover(Integer.parseInt(request.getParameter("id")));
%>
<script>
    document.location="lista.jsp";
</script>