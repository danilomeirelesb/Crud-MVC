package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Aluno;

public class AlunoDAO implements CrudDAO<Aluno> {

    public void gravar(Aluno aluno) {
        try {
            Connection conexao = Conexao.getConnection();
            String sql = "INSERT INTO aluno (nome,cpf,matricula) VALUES (?,?,?);";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getMatricula());

            stmt.execute();
            stmt.close();
            conexao.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void editar(Aluno aluno) {
        try {

            Connection conexao = Conexao.getConnection();
            String sql = "UPDATE aluno SET nome = ?, cpf = ?, matricula = ? WHERE id = ?;";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getMatricula());
            stmt.setInt(4, aluno.getId());

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
            String sql = "DELETE FROM Aluno WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.execute();
            stmt.close();
            conexao.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Aluno> listar() {

        try {
            Connection conexao = Conexao.getConnection();
            String sql = "SELECT id,nome,cpf,matricula FROM aluno;";
            PreparedStatement stmt = conexao.prepareStatement(sql);

            // Resutset armazen o que retorna do banco de dados
            ResultSet rs = stmt.executeQuery();

            //como nao sabemso a quantidade de dados, deve ser fazer uma lista
            ArrayList<Aluno> alunos = new ArrayList();
            while (rs.next()) {
                //Apresentação dos dados
                Aluno aluno = new Aluno(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("matricula"));

                /*
                quando e passado parametro nao a necessidade de setar cada varaiavel, passa no parametro
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setMatricula(rs.getString("matricula"));*/
                //o alunos.add adiciona a cada posição no array 
                alunos.add(aluno);
            }
            return alunos;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Aluno pesquisar(int id) {
        try {
            Connection conexao = Conexao.getConnection();
            String sql = "SELECT id,nome,cpf, matricula FROM Aluno WHERE id = ?; ";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            Aluno aluno = new Aluno();
            aluno.setId(rs.getInt("id"));
            aluno.setNome(rs.getString("nome"));
            aluno.setCpf(rs.getString("cpf"));
            aluno.setMatricula(rs.getString("matricula"));

            return aluno;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public List<Aluno> filtrar(Aluno aluno) {
        try {
            Connection conexao = Conexao.getConnection();
            String sql = "SELECT id,nome,cpf,matricula FROM aluno WHERE  nome like ? or cpf = ? or matricula = ?; ";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            if (aluno.getNome().equals("")) {
                stmt.setString(1, aluno.getNome());
            } else {
                stmt.setString(1, "%" + aluno.getNome() + "%");
            }
            
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getMatricula());

            ResultSet rs = stmt.executeQuery();
            List<Aluno> alunos = new ArrayList<>();
            while (rs.next()) {
                //Apresentação dos dados
                Aluno a = new Aluno(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("matricula"));

                alunos.add(a);
            }
            return alunos;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
