package com.Task1.EmployeeData.config;

import com.Task1.EmployeeData.model.Employee;
import com.Task1.EmployeeData.repository.EmployeeRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

    private final EmployeeRepository employeeRepository;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Value("${file.input}")
    private String fileInput;

    public BatchConfig(EmployeeRepository employeeRepository, JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.employeeRepository = employeeRepository;
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public FlatFileItemReader<Employee> reader() {
        return new FlatFileItemReaderBuilder<Employee>()
                .name("Reading-The-Data")
                .resource(new FileSystemResource(fileInput))
                .linesToSkip(1)
                .delimited()
                .names("id", "name", "age", "gender", "salary")
                .targetType(Employee.class)
                .build();
    }

    @Bean
    public ItemProcessor<Employee, Employee> itemProcesser() {
        return new CustomProcessor();
    }

    @Bean
    public RepositoryItemWriter<Employee> itemWriter() {
        RepositoryItemWriter<Employee> writer = new RepositoryItemWriter<>();
        writer.setRepository(employeeRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Job createJob() {
        return new JobBuilder("Data-Transfer", jobRepository)
                .start(steps())
                .build();
    }

    @Bean
    public Step steps() {
        return new StepBuilder("My-Steps", jobRepository)
                .<Employee, Employee>chunk(20, transactionManager)
                .reader(reader())
                .processor(itemProcesser())
                .writer(itemWriter())
                .build();
    }


}
