<%@page import="com.sun.accessibility.internal.resources.accessibility"%>
<%@page import="model.Curso"%>
<%@page import="model.Professor"%>
<%@page import="model.ProfessorCurso"%>
<%@page import="java.util.List"%>
<%@page import="controller.ProfessorCursoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../navBar.jsp" %>
<%
    ProfessorCursoDAO pcDAO = new ProfessorCursoDAO();
    List<ProfessorCurso> professorCursos;

    if (request.getParameter("cbProfessor") == null) {
        professorCursos = pcDAO.listar();
    } else {
        ProfessorCurso pc = new ProfessorCurso();
        pc.setProfessorId(Integer.parseInt(request.getParameter("cbProfessor")));
        pc.setCursoId(Integer.parseInt(request.getParameter("cbCurso")));
        if ((pc.getProfessorId() == 0) && (pc.getCursoId() == 0)) {
            professorCursos = pcDAO.listar();
        } else {
            professorCursos = pcDAO.filtrar(pc);
        }
    }
    List<Professor> professores = pcDAO.professores();
    List<Curso> cursos = pcDAO.cursos();
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
                                <label for="cbProfessor">Professor:</label>
                                <select name="cbProfessor" id="cbProfessor" class="form-control">
                                    <option value="0"> Todos</option>
                                    <% for (int i = 0; i < professores.size(); i++) {%>
                                    <option value="<%=professores.get(i).getId()%>"><%=professores.get(i).getNome()%></option>
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
                                <a href="../professorCurso/lista.jsp"<button class="btn btn-info"><span class="glyphicon glyphicon-arrow-left"></span> Voltar</a>
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
                        <th>Profesor</th>
                        <th>Curso</th>
                        <th style="width: 200px;">Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (int i = 0; i < professorCursos.size(); i++) {%>
                    <tr>
                        <td><%=professorCursos.get(i).getProfessorNome()%></td>
                        <td><%=professorCursos.get(i).getCursoNome()%></td>

                        <td>
                            <button class="btn btn-info " onclick="document.location = 'editar.jsp?id=<%=professorCursos.get(i).getId()%>'">
                                <span class="glyphicon glyphicon-pencil"></span> Editar</button>
                            <button class="btn btn-danger " onclick="remover(<%=professorCursos.get(i).getId()%>)"><span class="glyphicon glyphicon-trash"></span> Deletar</button>
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
