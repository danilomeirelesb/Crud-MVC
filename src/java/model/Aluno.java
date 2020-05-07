package model;

public class Aluno {

    private int id;
    private String nome;
    private String cpf;
    private String matricula;
    
    public  Aluno(){
    
    }
    
    public Aluno(int id, String nome, String cpf,String matricula){
        this.id  = id;
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = matricula;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

}
