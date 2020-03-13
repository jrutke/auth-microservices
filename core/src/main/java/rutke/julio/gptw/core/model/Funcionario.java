package rutke.julio.gptw.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Funcionario implements AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull(message = "O campo 'Nome' é obrigatório!")
    @Column(nullable = false)
    private String nome;
    @NotNull(message = "O campo 'CPF' é obrigatório!")
    @Column(nullable = false)
    private String cpf;
    @NotNull(message = "O campo 'E-mail' é obrigatório!")
    @Column(nullable = false)
    private String email;
    @NotNull(message = "O campo 'Telefone' é obrigatório!")
    @Column(nullable = false)
    private String telefone;
    @NotNull(message = "O campo 'função' é obrigatório!")
    @Column(nullable = false)
    private String funcao;
    @NotNull(message = "O campo 'salário' é obrigatório!")
    @Column(nullable = false)
    private Double salario;

    public Funcionario(@NonNull Funcionario funcionario) {
        this.nome = funcionario.getNome();
        this.cpf = funcionario.getCpf();
        this.email = funcionario.getEmail();
        this.telefone = funcionario.getTelefone();
        this.funcao = funcionario.getFuncao();
        this.salario = funcionario.getSalario();
    }

    public Funcionario(String nome, String cpf, String email, String telefone, String funcao, Double salario) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.funcao = funcao;
        this.salario = salario;
    }
}
