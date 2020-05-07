package model;
public class AlunoCurso {
    private int id;
    private int alunoId;
    private int cursoId;
    
    private String alunoNome;
    private String cursoNome;
    
    
    public AlunoCurso(){
        
    }
    public AlunoCurso(int id, int alunoId, int cursoId){
    this.id = id;
    this.alunoId = alunoId;
    this.cursoId = cursoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }  

    public String getAlunoNome() {
        return alunoNome;
    }

    public void setAlunoNome(String alunoNome) {
        this.alunoNome = alunoNome;
    }

    public String getCursoNome() {
        return cursoNome;
    }

    public void setCursoNome(String cursoNome) {
        this.cursoNome = cursoNome;
    }
    
    

}