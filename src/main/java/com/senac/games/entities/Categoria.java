package com.senac.games.entities;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id")
    private Integer id;
    @Column(name = "categoria_nome")
    private String nome;
    @Column(name = "categoria_status")
    private int status;
    @OneToMany(mappedBy = "categoria")
    private Set<Jogo> jogos;

    public Set<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(Set<Jogo> jogos) {
        this.jogos = jogos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
