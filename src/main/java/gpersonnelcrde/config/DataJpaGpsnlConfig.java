package gpersonnelcrde.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan({ "gpersonnelcrde.domain.entities", "gpersonnelcrde.repository"})
public class DataJpaGpsnlConfig {

}
