package com.zup.edu.gerenciadorde.albumdefigurinhas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Figurinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer pagina;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne(optional = false)
    private Album album;

    public Figurinha(Integer pagina, String descricao) {
        this.pagina = pagina;
        this.descricao = descricao;
    }

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Figurinha() {}

    public Long getId() {
        return id;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

}
