package br.com.fiap.globalsolution.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "TB_FB_PASSAGEIRO")
public class Passageiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_passageiro")
    private Long id;

    @NotBlank
    @Column(name = "nm_passageiro")
    private String nome;
    
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

    @OneToMany(mappedBy = "passageiro", cascade = CascadeType.REMOVE)
    private List<Corrida> corridas;

    public Passageiro() {
    }

    public Passageiro(@NotBlank String nome, String cpf, @NotBlank String rg, @Email String email, String password) {
        this.nome = nome;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        corrida.setPassageiro(this);
        corridas.add(corrida);
    }

    public User toUser() {
        return new User().name(nome).email(email).password(password);
    }
    
}
