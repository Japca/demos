package batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.util.ClassUtils;

import batch.entity.UserRegistration;
import batch.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class UserJob {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private UserRegistrationRepository repository;

    @Value("classpath:registrations.csv")
    private Resource input;

    @Bean
    public Job insertIntoDbFromCsvJob() throws Exception {
        return jobs.get("User Registration Import Job")
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() throws Exception {
        return steps.get("User Registration CSV To DB Step")
                .<UserRegistration,UserRegistration>chunk(5)
                .reader(csvFileReader())
                .writer(itemWriter())
                .build();
    }

    @Bean
    public FlatFileItemReader<UserRegistration> csvFileReader() throws Exception {
        log.info("loading registrations from file");
        return new FlatFileItemReaderBuilder<UserRegistration>()
                .name(ClassUtils.getShortName(FlatFileItemReader.class))
                .resource(input)
                .targetType(UserRegistration.class)
                .delimited()
                .names(new String[]{"firstName","lastName","email", "address","city","state","zip","county","phoneNumber"})
                .build();
     }

    @Bean
    public ItemWriter<UserRegistration> itemWriter() {
       log.info("saving to repository:");
       return items -> repository.saveAll(items);
    }
}
