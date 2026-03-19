package br.com.projetoMarajoara.Model;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
public class AchadosPerdidos {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String nome;
    private LocalTime hora_encontro;
    private String lugar_encontro;

    private String imageNome;
    private String imageTipo;

    @Lob
    private byte[] imageDados;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalTime getHora_encontro() {
        return hora_encontro;
    }

    public void setHora_encontro(LocalTime hora_encontro) {
        this.hora_encontro = hora_encontro;
    }

    public String getLugar_encontro() {
        return lugar_encontro;
    }

    public void setLugar_encontro(String lugar_encontro) {
        this.lugar_encontro = lugar_encontro;
    }

    public String getImageNome() {
        return imageNome;
    }

    public void setImageNome(String imageNome) {
        this.imageNome = imageNome;
    }

    public String getImageTipo() {
        return imageTipo;
    }

    public void setImageTipo(String imageTipo) {
        this.imageTipo = imageTipo;
    }

    public byte[] getImageDados() {
        return imageDados;
    }

    public void setImageDados(byte[] imageDados) {
        this.imageDados = imageDados;
    }
}
