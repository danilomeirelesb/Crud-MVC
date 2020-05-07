package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Curso;
import model.Professor;
import model.ProfessorCurso;

public class ProfessorCursoDAO implements CrudDAO<ProfessorCurso> {

    @Override
    public void gravar(ProfessorCurso professorCurso) {
        try {
            Connection conexao = Conexao.getConnection();
            String sql = "INSERT INTO professorcurso (professorID,cursoID) VALUES (?,?);";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, professorCurso.getProfessorId());
            stmt.setInt(2, professorCurso.getCursoId());

            stmt.execute();
            stmt.close();
            conexao.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void editar(ProfessorCurso professorCurso) {
        try {
            Connection conexao = Conexao.getConnection();
            String sql = "UPDATE professorcurso SET professorID = ?, cursoID = ? WHERE id = ?;";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, professorCurso.getProfessorId());
            stmt.setInt(2, professorCurso.getCursoId());
            stmt.setInt(3, professorCurso.getId());

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
            String sql = "DELETE FROM professorcurso WHERE id = ?";
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
    public List<ProfessorCurso> listar() {
        try {
            Connection conexao = Conexao.getConnection();
            String sql = "SELECT pc.id, pc.professorId, pc.cursoId, p.nome, c.nome FROM professorcurso AS pc INNER JOIN professor AS p ON pc.professorId = p.id INNER JOIN curso AS c ON pc.cursoId = c.id;";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            List<ProfessorCurso> professorCursos = new ArrayList<>();

            while (rs.next()) {
                ProfessorCurso ac = new ProfessorCurso();
                ac.setId(rs.getInt(1));
                ac.setProfessorId(rs.getInt(2));
                ac.setCursoId(rs.getInt(3));
                ac.setProfessorNome(rs.getString(4));
                ac.setCursoNome(rs.getString(5));
                professorCursos.add(ac);
            }
            return professorCursos;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public ProfessorCurso pesquisar(int id) {
        try {
            Connection conexao = Conexao.getConnection();
            String sql = "SELECT id,professorID,cursoID FROM professorcurso WHERE id = ?; ";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            ProfessorCurso professorCurso = new ProfessorCurso();
            professorCurso.setId(rs.getInt("id"));
            professorCurso.setProfessorId(rs.getInt("professorID"));
            professorCurso.setCursoId(rs.getInt("cursoID"));
            return professorCurso;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<ProfessorCurso> filtrar(ProfessorCurso professorCurso) {
        try {
            Connection conexao = Conexao.getConnection();
            String sql="SELECT pc.id, pc.professorId, pc.cursoId, p.nome, c.nome FROM professorcurso AS pc INNER JOIN professor AS p ON pc.professorId = p.id INNER JOIN curso AS c ON pc.cursoId = c.id WHERE p.id = ? OR c.id = ?;";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1,professorCurso.getProfessorId());
            stmt.setInt(2,professorCurso.getCursoId());
            ResultSet rs = stmt.executeQuery();
            
            List<ProfessorCurso> professorCursos = new ArrayList<>();
            while (rs.next()){
                ProfessorCurso pc = new ProfessorCurso();
                pc.setId(rs.getInt(1));
                pc.setProfessorId(rs.getInt(2));
                pc.setCursoId(rs.getInt(3));
                pc.setProfessorNome(rs.getString(4));
                pc.setCursoNome(rs.getString(5));
                professorCursos.add(pc);
            }
            return professorCursos;
        } catch (SQLException ex) {
            return null;
        }
    }

    public List<Professor> professores() {
        try {
            Connection conexao = Conexao.getConnection();
            String sql = "SELECT id,nome FROM professor;";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Professor> professores = new ArrayList<>();
            while (rs.next()) {
                Professor professor = new Professor();
                professor.setId(rs.getInt("id"));
                professor.setNome(rs.getString("nome"));
                professores.add(professor);
            }
            return professores;

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
