package gpersonnelcrde.config;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.Formatter;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.number.NumberFormatAnnotationFormatterFactory;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan
public class AppGpsnlConfig implements WebMvcConfigurer{
	// Divers
	public static final String DEFAULT_DATE_PATTERN = "yyyyMMdd";
	public static final String ZONED_DATE_PATTERN = "yyyyMMdd HH:mm:ss.nnn VV";
	
	/*@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:messages");
		messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setFallbackToSystemLocale(true);
		messageSource.setCacheSeconds(0);
		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean validatorFactoryBean(MessageSource messageSource) {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.setValidationMessageSource(messageSource);
		return localValidatorFactoryBean;
	}*/

	@Bean // for formatting type system (PRÉFÉRÉ 2)
	public FormattingConversionService conversionService() { 
		var formattingConversionServiceBean = new DefaultFormattingConversionService(true); 
		formattingConversionServiceBean.addFormatter(localDateFormatter()); 
		return formattingConversionServiceBean; 
	} 
	protected Formatter<LocalDate> localDateFormatter() { 
		return new Formatter<LocalDate>() { 
			@Override 
			public LocalDate parse(String source, Locale locale) throws ParseException {
				return LocalDate.parse(source, getDateTimeFormatter()); 
			} 
			
			@Override 
			public String print(LocalDate source, Locale locale) { 
				return source.format(getDateTimeFormatter()); 
			} 
			
			protected DateTimeFormatter getDateTimeFormatter(){ 
				return DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
			} 
		}; 
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addCorsMappings(registry);
	}

	@Override
	public Validator getValidator() {
		return WebMvcConfigurer.super.getValidator();
	}
}
