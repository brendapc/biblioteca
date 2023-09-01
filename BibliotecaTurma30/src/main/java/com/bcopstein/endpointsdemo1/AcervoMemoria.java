package com.bcopstein.endpointsdemo1;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class AcervoMemoria implements IAcervoRepository {
    private List<Livro> livros;

    public AcervoMemoria() {
        livros = new LinkedList<>();
        livros.add(new Livro((long) 10,"Introdução ao Java","Huguinho Pato",2022));
        livros.add(new Livro((long) 20,"Introdução ao Spring-Boot","Zezinho Pato",2020));
        livros.add(new Livro((long) 15,"Principios SOLID","Luizinho Pato",2023));
        livros.add(new Livro((long) 17,"Padroes de Projeto","Lala Pato",2019));
        livros.add(new Livro((long) 18,"Streams and Collectors","Huguinho Pato",2023));
    }

    @Override
    public Livro updateLivro(Long codigo, Livro livro) {
        Livro livroExistente = getPorId(codigo);

        boolean deleteSuccess = removeLivro(codigo);
        if(deleteSuccess){
            boolean livroResultado = cadastraLivroNovo(livro);
            if(livroResultado) return livro;
        }
    
        return livroExistente;
    }

    @Override
    public Livro getPorId(Long codigo) {
        return livros.stream()
                     .filter(livro->livro.codigo() == codigo)
                     .findFirst()
                     .orElse(null);
    }

    @Override
    public List<Livro> getAuthor(String autor) {
        return livros.stream()
                     .filter(livro->livro.autor().equals(autor))
                     .toList();
    }  

    @Override
    public List<Livro> getAno(int ano) {
        return livros.stream()
                     .filter(livro->livro.ano() == ano)
                     .toList();
    }

    @Override
    public List<Livro> getTitulo(String titulo) {
        return livros.stream()
                     .filter(livro->livro.titulo().equals(titulo))
                     .toList();
    }

    @Override
    public List<Livro> getAll() {
        return livros;
    }

    @Override
    public boolean cadastraLivroNovo(Livro livro) {
        livros.add(livro);
        return true;
    }

    @Override
    public boolean removeLivro(Long codigo) {
       List<Livro> tmp = livros.stream()
                               .filter(livro->livro.codigo() == codigo)
                               .toList();
        return tmp.removeAll(tmp);
    }

    @Override
    public List<String> getTitulos() {
        return livros.stream()
                     .map(livro->livro.titulo())
                     .toList();
    }

    @Override
    public Set<String> getAutores() {
        return livros.stream()
                     .map(livro->livro.autor())
                     .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getAutoresAno(int ano) {
        return livros.stream()
                     .filter(livro->livro.ano() == ano)
                     .map(livro->livro.autor())
                     .collect(Collectors.toSet());
    }

    @Override
    public List<String> getTitulosAutorAno(String autor, int ano) {
        return livros.stream()
                     .filter(livro->livro.ano() == ano)
                     .filter(livro->livro.autor().equals(autor))
                     .map(livro->livro.titulo())
                     .toList();
    }

}
