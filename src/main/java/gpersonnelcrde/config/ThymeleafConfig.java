package gpersonnelcrde.config;

import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class ThymeleafConfig {
	@Bean
	public SpringTemplateEngine templateEngine(final ITemplateResolver templateResolver){
		SpringTemplateEngine stEngine = new SpringTemplateEngine();
		stEngine.setTemplateResolver(templateResolver);
		stEngine.setAdditionalDialects(new HashSet<>(){{
					//add(new LayoutDialect());
					add(new SpringSecurityDialect());
				}});

		return stEngine;		 
	}
}
