package br.com.fiap.globalsolution.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "TB_FB_PASSAGEIRO")
// @SequenceGenerator(name = "passageiro", sequenceName = "sq_tb_fb_passageiro", allocationSize = 1)
public class Passageiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "passageiro")
    @Column(name = "id_passageiro")
    private Long id;

    @NotBlank
    @Column(name = "nm_passageiro", length = 80)
    @Size(min = 5, max = 80, message = "Deve possuir entre 5 e 80 caracteres")
    private String nome;
    
    @Column(name = "nr_cpf", length = 11)
    @Size(min = 11, max = 11, message = "Deve possuir 11 caracteres")
    @Pattern(regexp="[\\d]{11}", message="Deve conter somente números")
    private String cpf;
    
    @NotBlank
    @Size(min = 9, max = 9, message = "Deve possuir 9 caracteres")
    @Column(name = "nr_rg", length = 9)
    private String rg;

    @Email
    @Column(name = "ds_email")
    @Pattern(regexp = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+).(\\.[a-z]{2,3})$", message = "Formato de email inválido")
    @NotBlank
    private String email;

    @JsonProperty(access = Access.WRITE_ONLY)
    @NotBlank
    @Size(min = 8, message = "Deve ter no mínimo 8 caracteres")
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
        User user = new User().name(nome).email(email).password(password);
        user.setId(id);
        return user;
    }
    
}
