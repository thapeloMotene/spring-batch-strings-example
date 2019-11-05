package com.springbatchprocessing.batchprocessor.config;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatchprocessing.batchprocessor.etlstep.Processor;
import com.springbatchprocessing.batchprocessor.etlstep.Reader;
import com.springbatchprocessing.batchprocessor.etlstep.Writer;
import com.springbatchprocessing.batchprocessor.listener.JobCompletionListener;

@Configuration
@EnableBatchProcessing
public class BatchProcessingConfig {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job processJob() {
		return jobBuilderFactory.get("processJob")
				.incrementer(new RunIdIncrementer()).listener(listener())
				.flow(orderStep1()).end().build();
	}

	@Bean
	public Step orderStep1() {
		return  stepBuilderFactory.get("orderStep1").<String, String> chunk(10)
				.reader(new Reader()).processor(new Processor())
				.writer(new Writer()).build();
	}

	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionListener();
	}
}
