package com.zup.edu.gerenciadorde.albumdefigurinhas.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false)
    @Positive
    @Max(100)
    @Min(10)
    private Integer numeroPaginas;

    @OneToMany(mappedBy = "album")
    private List<Figurinha> figurinhas= new ArrayList<>();


    public Album(String titulo, String descricao, Integer numeroPaginas) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.numeroPaginas = numeroPaginas;
    }

    /**
     * @deprecated construtor para uso exclusivo do hibernate
     */
    @Deprecated
    public Album(){

    }

}
