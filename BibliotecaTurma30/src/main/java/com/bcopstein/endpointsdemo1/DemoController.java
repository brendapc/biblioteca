package com.bcopstein.endpointsdemo1;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/biblioteca")
public class DemoController{
    private List<Livro> livros;

    public DemoController(){
        livros = new LinkedList<>();

        livros.add(new Livro(10,"Introdução ao Java","Huguinho Pato",2022));
        livros.add(new Livro(20,"Introdução ao Spring-Boot","Zezinho Pato",2020));
        livros.add(new Livro(15,"Principios SOLID","Luizinho Pato",2023));
        livros.add(new Livro(17,"Padroes de Projeto","Lala Pato",2019));
        livros.add(new Livro(18,"Streams and Collectors","Huguinho Pato",2023));
    }

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public String getSaudacao() {
        return "Bem vindo as biblioteca central!";
    }

    @GetMapping("/livros")
    @CrossOrigin(origins = "*")
    public List<Livro> getLivros() {
        return livros;
    }

    // Solucao da dinâmica
    @GetMapping("/titulos")
    @CrossOrigin(origins = "*")
    public List<String> getTitulos() {
        return livros.stream()
               .map(livro->livro.titulo())
               .toList();
    }

    @GetMapping("/autores")
    @CrossOrigin(origins = "*")
    public Set<String> getAutores() {
        return livros.stream()
               .map(livro->livro.autor())
               .collect(Collectors.toSet());
    }
    
    @GetMapping("/autoresAno")
    @CrossOrigin(origins = "*")
    public Set<String> getAutoresAno(@RequestParam(value="ano")int ano) {
        return livros.stream()
               .filter(livro->livro.ano() == ano)
               .map(livro->livro.autor())
               .collect(Collectors.toSet());
    }    

    @GetMapping("/tituloAutor/{autor}/ano/{ano}")
    @CrossOrigin(origins = "*")
    public List<String> getTitulosAutorAno(@PathVariable(value="autor")String autor,
                                           @PathVariable(value="ano")int ano){
        return livros.stream()
               .filter(livro->livro.ano() == ano)
               .filter(livro->livro.autor().equals(autor))
               .map(livro->livro.titulo())
               .toList();
    }    

    @PostMapping("/livroNovo")
    @CrossOrigin(origins = "*")
    public boolean criaLivroNovo(@RequestBody()Livro livro){
        livros.add(livro);
        return true;
    }    

}