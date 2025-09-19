package com.senac.games.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "premio")
public class Premio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "premio_id")
    private Integer id;
    @Column(name = "premio_descricao")
    private String descricao;
    @Column(name = "premio_ordem_premiacao")
    private int ordem;
    @Column(name = "premio_categoria")
    private int categoria;
    @Column(name = "premio_status")
    private int status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}

