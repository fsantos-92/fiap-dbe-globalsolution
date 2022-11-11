package br.com.fiap.globalsolution.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TB_FB_VEICULO")
// @SequenceGenerator(name = "veiculo", sequenceName = "sq_tb_fb_veiculo", allocationSize = 1)
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "veiculo")
    @Column(name = "id_veiculo")
    private Long id;

    // @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_motorista")
    @NotNull
    private Motorista motorista;

    @NotBlank
    @Column(name = "ds_modelo")
    @Size(min = 3, max = 50, message = "Deve possuir entre 3 e 50 caracteres")
    private String modelo;

    @NotNull
    @Min(1990)
    @Max(2022)
    @Column(name = "ds_ano", scale = 4)
    private int ano;

    @NotBlank
    @Column(name = "ds_cor")
    @Size(min = 3, max = 30, message = "Deve possuir entre 3 e 30 caracteres")
    private String cor;

    @NotBlank
    @Column(name = "nr_placa", length = 7)
    @Size(min = 7, max = 7, message = "Deve possuir 7 caracteres")
    private String placa;

    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.REMOVE)
    private List<Corrida> corridas;

    public Veiculo() {
    }

    public Veiculo(@NotBlank Motorista motorista, @NotBlank String modelo, @NotBlank int ano, @NotBlank String cor,
            @NotBlank String placa) {
        this.motorista = motorista;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.placa = placa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void addCorrida(Corrida corrida) {
        corrida.setVeiculo(this);
        corridas.add(corrida);
    }

    @Override
    public String toString() {
        return "Veiculo [id=" + id + /*", motorista=" + motorista.getNome() +*/ ", modelo=" + modelo + ", ano=" + ano + ", cor=" + cor
                + ", placa=" + placa + "]";
    }

    
}
