<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="controller.AlunoCursoDAO"%>
<%
    AlunoCursoDAO alunoCursoDAO = new AlunoCursoDAO();
    alunoCursoDAO.remover(Integer.parseInt(request.getParameter("id")));
%>
<script>
    document.location="lista.jsp";
</script>