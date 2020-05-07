package model;

public class Professor {

    private int id;
    private String nome;
    private String formacao;
    
    public Professor(){
    }
    
    public Professor(int id, String nome, String formacao){
        this.id = id;
        this.nome = nome;
        this.formacao = formacao;
    
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

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

}
