package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Curso;

public class CursoDAO implements CrudDAO<Curso> {

    public void gravar(Curso curso) {
        try {
            Connection conexao = Conexao.getConnection();
            String sql = "INSERT INTO Curso (nome,cargaHoraria) VALUES (?,?);";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, curso.getNome());
            stmt.setInt(2, curso.getCargaHoraria());

            stmt.execute();
            stmt.close();
            conexao.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void editar(Curso curso) {
        
        try {

            Connection conexao = Conexao.getConnection();
            String sql = "UPDATE curso SET nome = ?, cargaHoraria = ? WHERE id = ?;";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, curso.getNome());
            stmt.setInt(2, curso.getCargaHoraria());
            stmt.setInt(3, curso.getId());

            stmt.execute();
            stmt.close();
            conexao.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void remover(int id) {
        try {
            Connection conexao = Conexao.getConnection();
            String sql = "DELETE FROM curso WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.execute();
            stmt.close();
            conexao.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Curso> listar() {
        try {
            Connection conexao = Conexao.getConnection();
            String sql = "SELECT id,nome,cargaHoraria FROM curso;";
            PreparedStatement stmt = conexao.prepareStatement(sql);

            // Resutset armazen o que retorna do banco de dados
            ResultSet rs = stmt.executeQuery();

            //como nao sabemso a quantidade de dados, deve ser fazer uma lista
            ArrayList<Curso> cursos = new ArrayList();
            while (rs.next()) {
                //Apresentação dos dados
                Curso curso = new Curso(rs.getInt("id"), rs.getString("nome"), rs.getInt("cargaHoraria"));
                cursos.add(curso);
            }
            return cursos;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Curso pesquisar(int id) {
        try {
            Connection conexao = Conexao.getConnection();
            String sql = "SELECT id,nome,cargaHoraria FROM curso WHERE id = ?;";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            
            Curso curso = new Curso();
            curso.setId(rs.getInt("id"));
            curso.setNome(rs.getString("nome"));
            curso.setCargaHoraria(rs.getInt("cargaHoraria"));
            
            return curso;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public List<Curso> filtrar(Curso curso) {
        try {
            Connection conexao = Conexao.getConnection();
            String sql = "SELECT id,nome,cargaHoraria FROM curso WHERE  nome like ? or cargaHoraria = ?; ";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            if (curso.getNome().equals("")) {
                stmt.setString(1, curso.getNome());
            } else {
                stmt.setString(1, "%" + curso.getNome() + "%");
            }
            
            
            stmt.setInt(2, curso.getCargaHoraria());

            ResultSet rs = stmt.executeQuery();
            List<Curso> cursos = new ArrayList<>();
            while (rs.next()) {
                //Apresentação dos dados
                Curso a = new Curso(rs.getInt("id"), rs.getString("nome"), rs.getInt("cargaHoraria"));

                cursos.add(a);
            }
            return cursos;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
