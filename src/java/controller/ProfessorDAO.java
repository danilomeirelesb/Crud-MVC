package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Professor;

public class ProfessorDAO implements CrudDAO<Professor>{
    public void gravar(Professor professor) {
        try {
            Connection conexao = Conexao.getConnection();
            String sql = "INSERT INTO professor (nome,formacao) VALUES (?,?);";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getFormacao());

            stmt.execute();
            stmt.close();
            conexao.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void editar(Professor professor) {
        
        try {

            Connection conexao = Conexao.getConnection();
            String sql = "UPDATE professor SET nome = ?, formacao = ? WHERE id = ?;";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getFormacao());
            stmt.setInt(3, professor.getId());

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
            String sql = "DELETE FROM professor WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.execute();
            stmt.close();
            conexao.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Professor> listar() {
        try {
            Connection conexao = Conexao.getConnection();
            String sql = "SELECT id,nome,formacao FROM professor;";
            PreparedStatement stmt = conexao.prepareStatement(sql);

            // Resutset armazen o que retorna do banco de dados
            ResultSet rs = stmt.executeQuery();

            //como nao sabemso a quantidade de dados, deve ser fazer uma lista
            ArrayList<Professor> professores = new ArrayList();
            while (rs.next()) {
                //Apresentação dos dados
                Professor professor = new Professor(rs.getInt("id"), rs.getString("nome"), rs.getString("formacao"));
 
                professores.add(professor);
            }
            return professores;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Professor pesquisar(int id) {
        try {
            Connection conexao = Conexao.getConnection();
            String sql = "SELECT id,nome,formacao FROM professor WHERE id = ?; ";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            
            Professor professor = new Professor();
            professor.setId(rs.getInt("id"));
            professor.setNome(rs.getString("nome"));
            professor.setFormacao(rs.getString("formacao"));

            return professor;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public List<Professor> filtrar(Professor professor) {
        try {
            Connection conexao = Conexao.getConnection();
            String sql = "SELECT id,nome,formacao FROM professor WHERE  nome like ? or formacao = ?; ";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            if (professor.getNome().equals("")) {
                stmt.setString(1, professor.getNome());
            } else {
                stmt.setString(1, "%" + professor.getNome() + "%");
            }
            
            stmt.setString(2, professor.getFormacao());

            ResultSet rs = stmt.executeQuery();
            List<Professor> professores = new ArrayList<>();
            while (rs.next()) {
                //Apresentação dos dados
                Professor a = new Professor(rs.getInt("id"), rs.getString("nome"), rs.getString("formacao"));

                professores.add(a);
            }
            return professores;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
