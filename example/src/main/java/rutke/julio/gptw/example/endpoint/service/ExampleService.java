package rutke.julio.gptw.example.endpoint.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rutke.julio.gptw.core.model.Example;
import rutke.julio.gptw.core.repository.ExampleRepository;


@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExampleService {
    private final ExampleRepository exampleRepository;

    public Iterable<Example> list(Pageable pageable){
        log.info("Listando tudo");
        return exampleRepository.findAll(pageable);
    }
}
