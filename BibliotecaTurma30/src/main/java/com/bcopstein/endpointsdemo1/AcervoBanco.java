package com.bcopstein.endpointsdemo1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class AcervoBanco implements IAcervoRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AcervoBanco(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
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
