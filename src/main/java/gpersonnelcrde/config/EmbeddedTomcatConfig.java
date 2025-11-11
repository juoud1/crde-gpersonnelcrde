package gpersonnelcrde.config;

import java.util.concurrent.Executors;

import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
	Permettre au serveur Tomcat les capacités d'exploitater les Threads virtuels
 */
@Configuration
public class EmbeddedTomcatConfig {
	@Bean
	public TomcatProtocolHandlerCustomizer<?> virtualThreadExecutor(){
		return protocoleHandler -> protocoleHandler.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
	}
}
