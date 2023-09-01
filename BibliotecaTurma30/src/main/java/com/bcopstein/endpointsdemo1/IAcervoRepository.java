package com.bcopstein.endpointsdemo1;

import java.util.List;
import java.util.Set;

public interface IAcervoRepository {
    List<Livro> getAll();
    Livro getPorId(Long id);
    List<Livro> getAuthor(String autor);
    List<Livro> getTitulo(String titulo);
    List<Livro> getAno(int ano);
    boolean cadastraLivroNovo(Livro livro);
    boolean removeLivro(Long id);
    List<String> getTitulos();
    Set<String> getAutores();
    Set<String> getAutoresAno(int ano);
    List<String> getTitulosAutorAno(String autor,int ano);
    boolean updateLivro(Long id, Livro livro);
}
