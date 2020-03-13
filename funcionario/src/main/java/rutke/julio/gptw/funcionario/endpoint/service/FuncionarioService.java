package rutke.julio.gptw.funcionario.endpoint.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rutke.julio.gptw.core.model.Funcionario;
import rutke.julio.gptw.core.repository.FuncionarioRepository;


@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    public Iterable<Funcionario> list(Pageable pageable){
        log.info("Listando tudo");
        return funcionarioRepository.findAll(pageable);
    }
}
