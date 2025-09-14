package gpersonnelcrde.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	public final static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(authorize -> authorize
					//.requestMatchers("/resources/static/css/**").permitAll()
					//.requestMatchers("/resources/static/images/**").permitAll()
					//.requestMatchers("/resources/static/scripts/**").permitAll()
					
					.requestMatchers("/css/**").permitAll()
					.requestMatchers("/images/**").permitAll()
					.requestMatchers("/scripts/**").permitAll()
					.requestMatchers("/sql/**").permitAll()
					//.requestMatchers("/flavicom.ico").permitAll()
					.requestMatchers("/resources/**").permitAll()
					.requestMatchers("/webjars/**").permitAll()
					.requestMatchers("/registry/**").permitAll()
					.requestMatchers("/accueil.html").hasAnyRole("USER", "ADMIN")
					.requestMatchers("/login.html/*").hasAnyRole("USER", "ANONYMOUS")
					.requestMatchers("/logout").hasAnyRole("USER", "ADMIN")
					.requestMatchers("/signup.html/*").hasAnyRole("USER", "ANONYMOUS")
					.requestMatchers("/errors/**").permitAll()
					.requestMatchers("/admin/h2-console/**").access(new WebExpressionAuthorizationManager("isFullyAuthenticated() and hasRole('ADMIN')"))
					.requestMatchers("/admin/").hasRole("ADMIN")
					.requestMatchers("/**").hasRole("USER")

				)
				.exceptionHandling(exception -> exception.accessDeniedPage("/errors/403"))
				.formLogin(form -> form
					.loginPage("/login.html")
					.loginProcessingUrl("/login.html")
					.failureForwardUrl("/login.html?error")
					.usernameParameter("idconnexion")
					.passwordParameter("motdepasse")
					.defaultSuccessUrl("/accueil.html", true).permitAll()
				)
				.logout(form -> form
					.logoutUrl("/logout")
					.logoutSuccessUrl("/login.html?logout").permitAll()
				)
				.csrf(AbstractHttpConfigurer::disable);

				// Pour le console H2
				http.headers(header -> header.frameOptions(FrameOptionsConfig::disable));

		return http.build();
	}

	/*@Bean
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .requestMatchers("/resources/**", "/static/**","/webjars/**");
    }*/

	@Bean
	public InMemoryUserDetailsManager userDetailsService(){
		UserDetails user = User.builder()
					.username("crde")
					.password(passwordEncoder().encode("user123"))
					.roles("USER")
					.build();

		UserDetails admin = User.builder()
					.username("admin")
					.password(passwordEncoder().encode("admin123"))
					.roles("USER", "ADMIN")
					.build();
		logger.info("user pwd : {}", passwordEncoder().encode("user123").replaceAll("A", "dobatii"));
		logger.info("admin pwd : {}", passwordEncoder().encode("admin123").replaceAll("a", "agab"));

		return new InMemoryUserDetailsManager(user, admin);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	} 
}
