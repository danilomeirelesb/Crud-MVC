<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../navBar.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../bootstrap/css/bootstrap.css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    </head>
    <body>

        <div class="container-fluid">
            <br/>

            <h3 style="text-align: center">Cadastro de Professores</h3>

            <br/>

            <form action="../ProfessorServlet" method="post">
                <div class="row">
                    <div class="col-md-4">
                        <label for="txNome">Nome: </label>
                        <input type="text" maxlength="25" name="txNome" id="txNome" class="form-control"/>
                    </div>

                    <div class="col-md-4">
                        <label for="txFormacao">Formação: </label>
                        <input type="text" maxlength="25" name="txFormacao" id="txFormacao" class="form-control"/>
                    </div>
                </div>
                <br/>
                <div class="row">
                    <div class="col-md-2" style="margin-top: 30px;">
                        <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-floppy-disk"></span> Gravar</button>
                        <a href="../professor/lista.jsp"<button class="btn btn-info"><span class="glyphicon glyphicon-arrow-left"></span> Voltar</a>
                    </div>
                </div>
            </form>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    </body>
</html>

