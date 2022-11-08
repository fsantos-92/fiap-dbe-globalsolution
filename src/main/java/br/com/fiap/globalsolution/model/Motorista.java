package br.com.fiap.globalsolution.model;

import java.time.LocalDate;
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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "TB_FB_MOTORISTA")
public class Motorista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_motorista")
    private Long id;
    
    @NotBlank
    @Column(name = "nm_motorista")
    private String nome;
    
    @Column(name = "nr_cpf")
    private String cpf;
    
    @NotBlank
    @Column(name = "nr_cnh")
    private String cnh;

    @Column(name = "st_ativo")
    private boolean cadastroAtivo;

    @NotNull
    @Column(name = "dt_cadastro")
    private LocalDate dataCadastro;

    @Email
    @Column(name = "ds_email")
    private String email;

    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(name = "ds_senha")
    private String password;

    @OneToMany(mappedBy = "motorista", cascade = CascadeType.REMOVE)
    private List<Telefone> telefones;

    @OneToMany(mappedBy = "motorista", cascade = CascadeType.REMOVE)
    private List<Veiculo> veiculos;

    @OneToMany(mappedBy = "motorista", cascade = CascadeType.REMOVE)
    private List<Corrida> corridas;

    public Motorista() {
    }

    public Motorista(@NotBlank String nome, String cpf, @NotBlank String cnh, @NotBlank boolean cadastroAtivo,
            @NotBlank LocalDate dataCadastro, @Email String email, String password) {
        this.nome = nome;
        this.cpf = cpf;
        this.cnh = cnh;
        this.cadastroAtivo = cadastroAtivo;
        this.dataCadastro = dataCadastro;
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

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public boolean isCadastroAtivo() {
        return cadastroAtivo;
    }

    public void setCadastroAtivo(boolean cadastroAtivo) {
        this.cadastroAtivo = cadastroAtivo;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
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

    public void addTelefone(Telefone telefone){
        telefone.setMotorista(this);
        this.telefones.add(telefone);
    }

    public void addVeiculo(Veiculo veiculo) {
        veiculo.setMotorista(this);
        veiculos.add(veiculo);
    }

    public void addCorrida(Corrida corrida) {
        corrida.setMotorista(this);
        corridas.add(corrida);
    }

    @Override
    public String toString() {
        return "Motorista [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", cnh=" + cnh + ", cadastroAtivo="
                + cadastroAtivo + ", dataCadastro=" + dataCadastro + ", email=" + email + ", password=" + password
                + "]";
    }

    public User toUser() {
        User user = new User().name(nome).email(email).password(password);
        user.setId(id);
        return user;
    }
    
}
