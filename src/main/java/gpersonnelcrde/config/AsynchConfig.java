package gpersonnelcrde.config;

import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class AsynchConfig {
	@Bean
	public AsyncTaskExecutor appliAsyncTaskExecutor(){
		return new TaskExecutorAdapter(Executors.newVirtualThreadPerTaskExecutor());
	}
}
