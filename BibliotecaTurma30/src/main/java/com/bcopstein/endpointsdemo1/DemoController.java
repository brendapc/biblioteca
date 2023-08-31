package com.bcopstein.endpointsdemo1;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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
    private IAcervoRepository acervo;

    @Autowired
    public DemoController(IAcervoRepository acervo){
        this.acervo = acervo;
    }

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public String getSaudacao() {
        return "Bem vindo as biblioteca central!";
    }

    @GetMapping("/livros")
    @CrossOrigin(origins = "*")
    public List<Livro> getLivros() {
        return acervo.getAll();
    }

    // Solucao da dinâmica
    @GetMapping("/titulos")
    @CrossOrigin(origins = "*")
    public List<String> getTitulos() {
        return acervo.getTitulos();
    }

    @GetMapping("/autores")
    @CrossOrigin(origins = "*")
    public Set<String> getAutores() {
        return acervo.getAutores();
    }
    
    @GetMapping("/autoresAno")
    @CrossOrigin(origins = "*")
    public Set<String> getAutoresAno(@RequestParam(value="ano")int ano) {
        return acervo.getAutoresAno(ano);
    }    

    @GetMapping("/tituloAutor/{autor}/ano/{ano}")
    @CrossOrigin(origins = "*")
    public List<String> getTitulosAutorAno(@PathVariable(value="autor")String autor,
                                           @PathVariable(value="ano")int ano){
        return acervo.getTitulosAutorAno(autor,ano);
    }    

    @PostMapping("/livroNovo")
    @CrossOrigin(origins = "*")
    public boolean criaLivroNovo(@RequestBody()Livro livro){
        return acervo.cadastraLivroNovo(livro);
    }    

}