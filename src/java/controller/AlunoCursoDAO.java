package controller;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Aluno;
import model.AlunoCurso;
import model.Curso;

public class AlunoCursoDAO implements CrudDAO<AlunoCurso> {

    @Override
    public void gravar(AlunoCurso alunoCurso) {
        try {
            Connection conexao = Conexao.getConnection();
            String sql = "INSERT INTO alunocurso (alunoID,cursoID) VALUES (?,?);";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, alunoCurso.getAlunoId());
            stmt.setInt(2, alunoCurso.getCursoId());

            stmt.execute();
            stmt.close();
            conexao.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void editar(AlunoCurso alunoCurso) {
        try {
            Connection conexao = Conexao.getConnection();
            String sql = "UPDATE alunocurso SET alunoID = ?, cursoID = ? WHERE id = ?;";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, alunoCurso.getAlunoId());
            stmt.setInt(2, alunoCurso.getCursoId());
            stmt.setInt(3, alunoCurso.getId());

            stmt.execute();
            stmt.close();
            conexao.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void remover(int id) {
        try {
            Connection conexao = Conexao.getConnection();
            String sql = "DELETE FROM alunocurso WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.execute();
            stmt.close();
            conexao.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<AlunoCurso> listar() {
        try {
            Connection conexao = Conexao.getConnection();
            String sql = "SELECT ac.id, ac.alunoId, ac.cursoId, a.nome, c.nome FROM alunocurso AS ac INNER JOIN aluno AS a ON ac.alunoId = a.id INNER JOIN curso AS c ON ac.cursoId = c.id;";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            List<AlunoCurso> alunoCursos = new ArrayList<>();

            while (rs.next()) {
                AlunoCurso ac = new AlunoCurso();
                ac.setId(rs.getInt(1));
                ac.setAlunoId(rs.getInt(2));
                ac.setCursoId(rs.getInt(3));
                ac.setAlunoNome(rs.getString(4));
                ac.setCursoNome(rs.getString(5));
                alunoCursos.add(ac);
            }
            return alunoCursos;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public AlunoCurso pesquisar(int id) {
        try {
            Connection conexao = Conexao.getConnection();
            String sql = "SELECT id,alunoID,cursoID FROM alunoCurso WHERE id = ?; ";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            AlunoCurso alunoCurso = new AlunoCurso();
            alunoCurso.setId(rs.getInt("id"));
            alunoCurso.setAlunoId(rs.getInt("alunoID"));
            alunoCurso.setCursoId(rs.getInt("cursoID"));
            return alunoCurso;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<AlunoCurso> filtrar(AlunoCurso alunoCurso) {
        try {
            Connection conexao = Conexao.getConnection();
            String sql="SELECT ac.id, ac.alunoId, ac.cursoId, a.nome, c.nome FROM alunocurso AS ac INNER JOIN aluno AS a ON ac.alunoId = a.id INNER JOIN curso AS c ON ac.cursoId = c.id WHERE a.id = ? OR c.id = ?;";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1,alunoCurso.getAlunoId());
            stmt.setInt(2,alunoCurso.getCursoId());
            ResultSet rs = stmt.executeQuery();
            
            List<AlunoCurso> alunoCursos = new ArrayList<>();
            while (rs.next()){
                AlunoCurso ac = new AlunoCurso();
                ac.setId(rs.getInt(1));
                ac.setAlunoId(rs.getInt(2));
                ac.setCursoId(rs.getInt(3));
                ac.setAlunoNome(rs.getString(4));
                ac.setCursoNome(rs.getString(5));
                alunoCursos.add(ac);
            }
            return alunoCursos;
        } catch (SQLException ex) {
            return null;
        }
    }

    public List<Aluno> alunos() {
        try {
            Connection conexao = Conexao.getConnection();
            String sql = "SELECT id,nome FROM aluno;";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Aluno> alunos = new ArrayList<>();
            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                alunos.add(aluno);
            }
            return alunos;

        } catch (SQLException ex) {
            return null;
        }
    }

    public List<Curso> cursos() {
        try {
            Connection conexao = Conexao.getConnection();
            String sql = "SELECT id,nome FROM curso;";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Curso> cursos = new ArrayList<>();
            while (rs.next()) {
                Curso curso = new Curso();
                curso.setId(rs.getInt("id"));
                curso.setNome(rs.getString("nome"));
                cursos.add(curso);
            }
            return cursos;

        } catch (SQLException ex) {
            return null;
        }

    }
}
