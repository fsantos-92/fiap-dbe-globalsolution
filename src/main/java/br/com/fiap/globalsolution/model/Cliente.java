package br.com.fiap.globalsolution.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "TB_FB_CLIENTE")
public class Cliente {
    @Id
    @Column(name = "id_cliente")
    private Long id;

    @NotBlank
    @Column(name = "nm_cliente")
    private String name;
    
    @Column(name = "nr_cpf")
    private String cpf;
    
    @NotBlank
    @Column(name = "nr_rg", length = 9)
    private String rg;

    @Email
    @Column(name = "ds_email")
    private String email;

    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(name = "ds_senha")
    private String password;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE)
    private List<Corrida> corridas;

    public Cliente() {
    }

    public Cliente(@NotBlank String name, String cpf, @NotBlank String rg, @Email String email, String password) {
        this.name = name;
        this.cpf = cpf;
        this.rg = rg;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addCorrida(Corrida corrida) {
        corrida.setCliente(this);
        corridas.add(corrida);
    }
    
}
