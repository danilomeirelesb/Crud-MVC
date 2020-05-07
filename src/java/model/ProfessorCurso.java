package model;
public class ProfessorCurso {
    private int id;
    private int professorId;
    private int cursoId;
    
    private String professorNome;
    private String cursoNome;

    public String getProfessorNome() {
        return professorNome;
    }

    public void setProfessorNome(String professorNome) {
        this.professorNome = professorNome;
    }

    public String getCursoNome() {
        return cursoNome;
    }

    public void setCursoNome(String cursoNome) {
        this.cursoNome = cursoNome;
    }
    
    public ProfessorCurso(){
    
    }
    
    public ProfessorCurso(int id, int professorId, int cursoId){
       this.id = id;
       this.professorId = professorId;
       this.cursoId = cursoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }
    
    
}
