
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="controller.ProfessorDAO"%>
<%
    ProfessorDAO professorDAO = new ProfessorDAO();
    professorDAO.remover(Integer.parseInt(request.getParameter("id")));
%>
<script>
    document.location="lista.jsp";
</script>