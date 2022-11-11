package br.com.fiap.globalsolution.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "TB_FB_MOTORISTA")
// @SequenceGenerator(name = "motorista", sequenceName = "sq_tb_fb_motorista", allocationSize = 1)
public class Motorista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "motorista")
    @Column(name = "id_motorista")
    private Long id;
    
    @NotBlank
    @Size(min = 5, max = 80, message = "Deve possuir entre 5 e 80 caracteres")
    @Column(name = "nm_motorista", length = 80)
    private String nome;
    
    @Size(min = 11, max = 11, message = "Deve possuir 11 caracteres")
    @Pattern(regexp="[\\d]{11}", message="Deve conter somente números")
    @Column(name = "nr_cpf", length = 11)
    private String cpf;
    
    @NotBlank
    @Size(min = 11, max = 11, message = "Deve possuir 11 caracteres")
    @Pattern(regexp="[\\d]{11}", message="Deve conter somente números")
    @Column(name = "nr_cnh", length = 11)
    private String cnh;

    @Column(name = "st_ativo")
    private boolean cadastroAtivo;

    @NotNull
    @Column(name = "dt_cadastro")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dataCadastro;

    @Email
    @Column(name = "ds_email")
    @NotBlank
    @Pattern(regexp = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+).(\\.[a-z]{2,3})$", message = "Formato de email inválido")
    private String email;

    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(name = "ds_senha")
    @NotBlank
    @Size(min = 8, message = "Deve ter no mínimo 8 caracteres")
    private String password;

    @OneToMany(mappedBy = "motorista", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
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
