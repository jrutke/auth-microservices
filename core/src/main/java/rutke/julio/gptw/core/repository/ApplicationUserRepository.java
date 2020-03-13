package rutke.julio.gptw.core.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import rutke.julio.gptw.core.model.ApplicationUser;
import rutke.julio.gptw.core.model.Example;


public interface ApplicationUserRepository extends PagingAndSortingRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);

    boolean existsByUsername(String username);
}
