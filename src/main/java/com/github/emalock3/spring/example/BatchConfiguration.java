package com.github.emalock3.spring.example;

import com.github.emalock3.spring.example.domain.Employee;
import com.github.emalock3.spring.example.domain.Employee2;
import com.github.emalock3.spring.example.domain.Employee2Repository;
import com.github.emalock3.spring.example.domain.EmployeeRepository;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    
    @Autowired
    private JobBuilderFactory jobs;
    @Autowired
    private StepBuilderFactory steps;
    @Autowired
    private EmployeeRepository empRepo;
    @Autowired
    private Employee2Repository emp2Repo;
    
    @Bean
    public Step genEmployeesStep() {
        RepositoryItemWriter writer = new RepositoryItemWriter();
        writer.setRepository(empRepo);
        writer.setMethodName("save");
        return steps.get("genEmployeesStep")
                .<Employee, Employee> chunk(10)
                .reader(new ListItemReader(IntStream.range(0, 100)
                        .mapToObj(i -> new Employee(null, "foo-" + i))
                        .collect(Collectors.toList())))
                .writer(writer)
                .build();
    }
    
    @Bean
    public Step syncEmployeesStep() {
        RepositoryItemReader<Employee> reader = new RepositoryItemReader<>();
        reader.setRepository(empRepo);
        reader.setMethodName("findAll");
        RepositoryItemWriter writer = new RepositoryItemWriter();
        writer.setRepository(emp2Repo);
        writer.setMethodName("save");
        return steps.get("syncEmployeesStep")
                .<Employee, Employee2> chunk(10)
                .reader(reader)
                .processor(emp -> new Employee2(null, emp.getName().toUpperCase()))
                .writer(writer)
                .build();
    }
    
    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory) {
        return jobs.get("importEmpJob")
                .incrementer(new RunIdIncrementer())
                .start(genEmployeesStep())
                .next(syncEmployeesStep())
                .build();
    }
}
