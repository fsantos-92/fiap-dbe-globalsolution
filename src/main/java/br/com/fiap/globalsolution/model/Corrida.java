package br.com.fiap.globalsolution.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TB_FB_CORRIDA")
public class Corrida {
    @Id
    @Column(name = "id_corrida")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_motorista")
    @NotBlank
    private Motorista motorista;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_cliente")
    @NotBlank
    private Cliente cliente;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_veiculo")
    @NotBlank
    private Veiculo veiculo;

    @NotBlank
    @Column(name = "ds_origem")
    private String origem;
    
    @NotBlank
    @Column(name = "ds_destino")
    private String destino;

    @NotBlank
    @Column(name = "dt_corrida")
    private LocalDate data;

    @NotBlank
    @Column(name = "vl_corrida")
    private BigDecimal valor;

    @NotBlank
    @Column(name = "st_finalizada")
    private boolean isFinalizada;

    public Corrida() {
    }

    public Corrida(@NotBlank Motorista motorista, @NotBlank Cliente cliente, @NotBlank Veiculo veiculo,
            @NotBlank String origem, @NotBlank String destino, @NotBlank LocalDate data, @NotBlank BigDecimal valor,
            @NotBlank boolean isFinalizada) {
        this.motorista = motorista;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.origem = origem;
        this.destino = destino;
        this.data = data;
        this.valor = valor;
        this.isFinalizada = isFinalizada;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public boolean isFinalizada() {
        return isFinalizada;
    }

    public void setFinalizada(boolean isFinalizada) {
        this.isFinalizada = isFinalizada;
    }

}
