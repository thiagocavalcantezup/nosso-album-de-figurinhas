package com.zup.edu.gerenciadorde.albumdefigurinhas.model;

import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class AlbumDTO {

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @NotNull
    @Positive
    @Max(100)
    @Min(10)
    private Integer paginas;

    @NotEmpty
    private Set<FigurinhaDTO> figurinhas;

    public AlbumDTO() {}

    public AlbumDTO(@NotBlank String titulo, @NotBlank String descricao,
                    @NotNull @Positive @Max(100) @Min(10) Integer paginas,
                    @NotEmpty Set<FigurinhaDTO> figurinhas) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.paginas = paginas;
        this.figurinhas = figurinhas;
    }

    public Album toModel() {
        Album album = new Album(titulo, descricao, paginas);

        figurinhas.stream().map(FigurinhaDTO::toModel).forEach(album::adicionar);

        return album;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public Set<FigurinhaDTO> getFigurinhas() {
        return figurinhas;
    }

}
