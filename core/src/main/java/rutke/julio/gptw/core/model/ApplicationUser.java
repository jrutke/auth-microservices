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
public class ApplicationUser implements AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull(message = "O campo 'Usuário' é obrigatório!")
    @Column(nullable = false)
    private String username;
    @NotNull(message = "O campo 'Nome' é obrigatório!")
    @Column(nullable = false)
    private String name;
    @NotNull(message = "O campo 'E-mail' é obrigatório!")
    @Column(nullable = false)
    private String email;
    @NotNull(message = "O campo 'Telefone' é obrigatório!")
    @Column(nullable = false)
    private String telefone;
    @NotNull(message = "O campo 'senha' é obrigatório!")
    @Column(nullable = false)
    @ToString.Exclude
    private String password;
    private String authemail;
    private String authtel;
    @NotNull(message = "O campo 'role' é obrigatório!")
    @Column(nullable = false)
    @Builder.Default
    private String role = "USER";

    public ApplicationUser(@NotNull ApplicationUser applicationUser) {
        this.id = applicationUser.getId();
        this.username = applicationUser.getUsername();
        this.password = applicationUser.getPassword();
        this.role = applicationUser.getRole();
    }

    public ApplicationUser(String username, String password, String name, String email, String telefone, String role) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.telefone = telefone;
        this.password = password;
        this.role = role;
    }
}
