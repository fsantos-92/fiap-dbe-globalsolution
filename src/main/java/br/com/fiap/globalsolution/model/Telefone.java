package br.com.fiap.globalsolution.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TB_FB_TELEFONE")
// @SequenceGenerator(name = "telefone", sequenceName = "sq_tb_fb_telefone", allocationSize = 1)
public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "telefone")
    @Column(name = "id_telefone")
    private Long id;

    @NotBlank
    @Column(name = "nr_telefone")
    @Size(min = 7, max = 9, message = "Deve ter entre 7 e 9 dígitos")
    @Pattern(regexp="[\\d]{7-9}", message="Deve conter somente números")
    private String numero;

    @NotBlank
    @Column(name = "nr_ddd")
    @Size(min = 2, max = 2, message = "Deve possuir 2 digitos")
    @Pattern(regexp="[\\d]{2}", message="Deve conter somente números")
    private String ddd;

    // @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_motorista")
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
