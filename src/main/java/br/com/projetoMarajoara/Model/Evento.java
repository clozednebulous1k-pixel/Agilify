package br.com.projetoMarajoara.Model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private LocalDate data;
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

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
    
    
}
