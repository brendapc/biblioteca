package com.bcopstein.endpointsdemo1;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class AcervoBanco implements IAcervoRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AcervoBanco(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean updateLivro(Long codigo, Livro livro) {
        String sql = "UPDATE Livros SET titulo = ?, autor = ?, ano_publicacao = ? WHERE codigo = ?";
        
        int rowsAffected = jdbcTemplate.update(sql, livro.titulo(), livro.autor(), livro.ano(), codigo);
    
        return rowsAffected > 0;
    }
    

    @Override
    public Set<String> getAutoresAno(int ano) {
        return jdbcTemplate.query("SELECT autor FROM Livros WHERE ano = ?",
                                  (rs, rowNum) -> rs.getString("autor"),
                                  ano)
                           .stream()
                           .collect(Collectors.toSet());
    }

    @Override
    public List<Livro> getTitulo (String titulo) {
        return jdbcTemplate.query("SELECT * FROM Livros WHERE titulo = ?",
                                  (rs, rowNum) -> new Livro(rs.getLong("codigo"),
                                                            rs.getString("titulo"),
                                                            rs.getString("autor"),
                                                            rs.getInt("ano")),
                                  titulo);
    }

    @Override
    public List<String> getTitulosAutorAno(String autor, int ano) {
        return jdbcTemplate.query("SELECT titulo FROM Livros WHERE autor = ? AND ano = ?",
                                  (rs, rowNum) -> rs.getString("titulo"),
                                  autor,ano);
    }

    @Override
    public Set<String> getAutores() {
        return jdbcTemplate.query("SELECT autor FROM Livros",
                                  (rs, rowNum) -> rs.getString("autor"))
                           .stream()
                           .collect(Collectors.toSet());
    }

    @Override
    public List<String> getTitulos() {
        return jdbcTemplate.query("SELECT titulo FROM Livros",
                                  (rs, rowNum) -> rs.getString("titulo"));
    }

    @Override
    public List<Livro> getAuthor(String autor) {
        return jdbcTemplate.query("SELECT * FROM Livros WHERE autor = ?",
                                  (rs, rowNum) -> new Livro(rs.getLong("codigo"),
                                                            rs.getString("titulo"),
                                                            rs.getString("autor"),
                                                            rs.getInt("ano")),
                                  autor);
    }

    @Override
    public Livro getPorId(Long codigo) {
        return jdbcTemplate.queryForObject("SELECT * FROM Livros WHERE codigo = ?",
                                            (rs, rowNum) -> new Livro(rs.getLong("codigo"),
                                                                      rs.getString("titulo"),
                                                                      rs.getString("autor"),
                                                                      rs.getInt("ano")),
                                            codigo);
    }

    @Override
    public List<Livro> getAno(int ano) {
        return jdbcTemplate.query("SELECT * FROM Livros WHERE ano = ?",
                                  (rs, rowNum) -> new Livro(rs.getLong("codigo"),
                                                            rs.getString("titulo"),
                                                            rs.getString("autor"),
                                                            rs.getInt("ano")),
                                  ano);
    }

    @Override
    public List<Livro> getAll() {
        return jdbcTemplate.query("SELECT * FROM Livros",
                                  (rs, rowNum) -> new Livro(rs.getLong("codigo"),
                                                            rs.getString("titulo"),
                                                            rs.getString("autor"),
                                                            rs.getInt("ano")));
    }

    @Override 
    public boolean removeLivro(Long codigo) {
        return jdbcTemplate.update("DELETE FROM Livros WHERE codigo = ?",codigo) > 0;
    }

    @Override
    public boolean cadastraLivroNovo(Livro livro) {
        return jdbcTemplate.update("INSERT INTO Livros VALUES (?,?,?,?)",
                                    livro.codigo(),
                                    livro.titulo(),
                                    livro.autor(),
                                    livro.ano()) > 0;
    }
} 
