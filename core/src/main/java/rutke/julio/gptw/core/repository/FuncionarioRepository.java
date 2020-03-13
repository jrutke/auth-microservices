package rutke.julio.gptw.core.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import rutke.julio.gptw.core.model.Funcionario;


public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Long> {
    Funcionario findByNome(String nome);
    boolean existsByNomeAndCpf(String nome, String cpf);
}
