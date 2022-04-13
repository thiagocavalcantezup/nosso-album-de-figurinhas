package com.zup.edu.gerenciadorde.albumdefigurinhas.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class FigurinhaDTO {

    @NotNull
    @Positive
    private Integer pagina;

    @NotBlank
    private String descricao;

    public FigurinhaDTO() {}

    public FigurinhaDTO(@NotNull @Positive Integer pagina, @NotBlank String descricao) {
        this.pagina = pagina;
        this.descricao = descricao;
    }

    public Figurinha toModel() {
        return new Figurinha(pagina, descricao);
    }

    public Integer getPagina() {
        return pagina;
    }

    public String getDescricao() {
        return descricao;
    }

}
