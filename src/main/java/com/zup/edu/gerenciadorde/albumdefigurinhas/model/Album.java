package com.zup.edu.gerenciadorde.albumdefigurinhas.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false)
    @Positive
    @Max(100)
    @Min(10)
    private Integer paginas;

    @OneToMany(mappedBy = "album")
    private List<Figurinha> figurinhas = new ArrayList<>();

    public Album(String titulo, String descricao, Integer paginas) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.paginas = paginas;
    }

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Album() {}

}
