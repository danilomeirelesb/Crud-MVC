<%@page import="java.util.ArrayList"%>
<%@page import="model.Professor"%>
<%@page import="java.util.List"%>
<%@page import="controller.ProfessorDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../navBar.jsp" %>
<%
    ProfessorDAO professorDAO = new ProfessorDAO();
    List<Professor> professores = professorDAO.listar();
    if (request.getParameter("txNome") == null) {
        professores = professorDAO.listar();
    } else {
        Professor professor = new Professor();
        professor.setNome(request.getParameter("txNome"));
        professor.setFormacao(request.getParameter("txFormacao"));
        professores = professorDAO.filtrar(professor);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../bootstrap/css/bootstrap.css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <script>
            function remover(id) {
                if (confirm("Deseja realmente remover ?")) {
                    document.location = "remover.jsp?id=" + id;
                }
            }
        </script>
        <title>Gestão Escola</title>
    </head>
    <body>

        <div class="container-fluid">
            <br/>

            <div class="card">

                <h5 class="card-header">Filtrar</h5>
                <div class="card-body">

                    <form action="">
                        <div class="row">
                            <div class="col-md">
                                <label for="txNome">Nome:</label>
                                <input name="txNome" class="form-control">
                            </div>
                            <div class="col-md">
                                <label for="txFormacao">Formação:</label>
                                <input name="txFormacao" class="form-control">
                            </div>
                            <div class="col-md" style="margin-top: 30px;">
                                <button type="submit" class="btn btn-info" ><span class="glyphicon glyphicon-search"></span> Pesquisar</button>
                                <a href="../professor/lista.jsp"<button class="btn btn-info"><span class="glyphicon glyphicon-arrow-left"></span> Voltar</a>
                            </div>
                        </div>                       
                    </form>

                </div>
            </div>

        </div>
        <br/>
        <button style="margin-left: 14px;" class="btn btn-success " onclick="document.location = 'cadastro.jsp'"><span class="glyphicon glyphicon-plus"></span> Novo</button>
        <table class="table table-striped " >
            <thead class="thead-dark">
            <th>Id:</th>
            <th>Nome:</th>
            <th>Formação:</th>
            <th style="width: 200px;"></th>
        </thead>
        <tbody>
            <% for (int i = 0; i < professores.size(); i++) {%>
            <tr>
                <td><%=professores.get(i).getId()%></td>
                <td><%=professores.get(i).getNome()%></td>
                <td><%=professores.get(i).getFormacao()%></td>

                <td>
                    <button class="btn btn-info " onclick="document.location = 'editar.jsp?id=<%=professores.get(i).getId()%>'">
                        <span class="glyphicon glyphicon-pencil"></span> Editar
                    </button>


                    <button class="btn btn-danger " onclick="remover(<%=professores.get(i).getId()%>)"><span class="glyphicon glyphicon-trash"></span> Deletar</button>
                </td>
            </tr>
            <% }%>
        <tbody>

    </table>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>
