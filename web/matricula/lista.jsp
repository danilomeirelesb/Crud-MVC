<%@page import="model.Curso"%>
<%@page import="model.Aluno"%>
<%@page import="model.AlunoCurso"%>
<%@page import="java.util.List"%>
<%@page import="controller.AlunoCursoDAO"%>
<%@include file="../navBar.jsp" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    AlunoCursoDAO acDAO = new AlunoCursoDAO();
    List<AlunoCurso> alunoCursos;

    if (request.getParameter("cbAluno") == null) {
        alunoCursos = acDAO.listar();
    } else {
        AlunoCurso ac = new AlunoCurso();
        ac.setAlunoId(Integer.parseInt(request.getParameter("cbAluno")));
        ac.setCursoId(Integer.parseInt(request.getParameter("cbCurso")));
        if ((ac.getAlunoId() == 0) && (ac.getCursoId() == 0)) {
            alunoCursos = acDAO.listar();
        } else {
            alunoCursos = acDAO.filtrar(ac);
        }
    }
    List<Aluno> alunos = acDAO.alunos();
    List<Curso> cursos = acDAO.cursos();
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
                                <label for="cbAluno">Aluno:</label>
                                <select name="cbAluno" id="cbAluno" class="form-control">
                                    <option value="0"> Todos</option>
                                    <% for (int i = 0; i < alunos.size(); i++) {%>
                                    <option value="<%=alunos.get(i).getId()%>"><%=alunos.get(i).getNome()%></option>
                                    <%}%>
                                </select>
                            </div>
                            <div class="col-md">
                                <label for="cbCurso">Curso:</label>
                                <select name="cbCurso" id="cbCurso" class="form-control">
                                    <option value="0"> Todos</option>
                                    <% for (int i = 0; i < cursos.size(); i++) {%>
                                    <option value="<%=cursos.get(i).getId()%>"><%=cursos.get(i).getNome()%></option>
                                    <%}%>
                                </select>
                            </div>

                            <div class="col-md" style="margin-top: 30px;">
                                <button type="submit" class="btn btn-info" ><span class="glyphicon glyphicon-search"></span> Pesquisar</button>
                                <a href="../aluno/lista.jsp"<button class="btn btn-info"><span class="glyphicon glyphicon-arrow-left"></span> Voltar</a>
                                </a>
                            </div>
                        </div>                       
                    </form>
                </div>
            </div>
            <br/>
            <button style="margin-left: 14px;" class="btn btn-success " onclick="document.location = 'cadastro.jsp'"><span class="glyphicon glyphicon-plus"></span> Novo</button>
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Aluno</th>
                        <th>Curso</th>
                        <th style="width: 200px;">Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (int i = 0; i < alunoCursos.size(); i++) {%>
                    <tr>
                        <td><%=alunoCursos.get(i).getAlunoNome()%></td>
                        <td><%=alunoCursos.get(i).getCursoNome()%></td>

                        <td>
                            <button class="btn btn-info " onclick="document.location = 'editar.jsp?id=<%=alunoCursos.get(i).getId()%>'">
                                <span class="glyphicon glyphicon-pencil"></span> Editar</button>
                            <button class="btn btn-danger " onclick="remover(<%=alunoCursos.get(i).getId()%>)"><span class="glyphicon glyphicon-trash"></span> Deletar</button>
                        </td>
                    </tr> 
                    <% }%>

                </tbody>
            </table>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>    
    </body>
</html>
