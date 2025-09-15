package gpersonnelcrde.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       /*  registry.addResourceHandler("/resources/**")
          .addResourceLocations("/resources/");*/
		registry.addResourceHandler("/resources/static/images/**").addResourceLocations("/resources/static/images/");
        registry.addResourceHandler("/resources/static/css/**").addResourceLocations("/resources/static/css/");
        registry.addResourceHandler("/resources/static/scripts/**").addResourceLocations("/resources/static/scripts/");
    }
	
	@Override
	public void addViewControllers(final ViewControllerRegistry registry) {
		registry.addViewController("/login.html")
				.setViewName("connexionutilisateur");
	}
}
