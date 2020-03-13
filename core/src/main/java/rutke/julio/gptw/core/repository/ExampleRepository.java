package rutke.julio.gptw.core.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import rutke.julio.gptw.core.model.Example;


public interface ExampleRepository extends PagingAndSortingRepository<Example, Long> {
}
