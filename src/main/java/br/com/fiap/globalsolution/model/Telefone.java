package br.com.fiap.globalsolution.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TB_FB_TELEFONE")
public class Telefone {
    @Id
    @Column(name = "id_telefone")
    private Long id;

    @NotBlank
    @Column(name = "nr_telefone")
    private String numero;

    @NotBlank
    @Column(name = "nr_ddd")
    private String ddd;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_motorista")
    @NotBlank
    private Motorista motorista;
    

    public Telefone() {
    }

 
    
    public Telefone(Long id, @NotBlank String numero, @NotBlank String ddd, Motorista motorista) {
        this.id = id;
        this.numero = numero;
        this.ddd = ddd;
        this.motorista = motorista;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

}
