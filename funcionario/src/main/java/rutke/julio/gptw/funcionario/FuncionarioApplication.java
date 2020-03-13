package rutke.julio.gptw.funcionario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import rutke.julio.gptw.core.property.JwtConfiguration;


@SpringBootApplication
@EntityScan({"rutke.julio.gptw.core.model"})
@EnableJpaRepositories({"rutke.julio.gptw.core.repository"})
@EnableConfigurationProperties(value = JwtConfiguration.class)
@ComponentScan("rutke.julio.gptw")
public class FuncionarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(FuncionarioApplication.class, args);
    }

}
