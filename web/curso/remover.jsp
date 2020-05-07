
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="controller.CursoDAO"%>
<%
    CursoDAO cursoDAO = new CursoDAO();
    cursoDAO.remover(Integer.parseInt(request.getParameter("id")));
%>
<script>
    document.location="listar.jsp";
</script>