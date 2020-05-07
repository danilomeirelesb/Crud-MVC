package controller;

import java.util.List;

public interface CrudDAO<T> {

    public void gravar(T objeto);

    public void editar(T objeto);

    public void remover(int id);

    public List<T> listar();

    public T pesquisar(int id);

    public List<T> filtrar(T objeto);
}
