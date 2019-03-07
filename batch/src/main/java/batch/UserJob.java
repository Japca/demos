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

@Configuration
public class UserJob {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private UserRegistrationRepository repository;

    @Value("file:${user.home}/batches/registrations.csv")
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

        return new FlatFileItemReaderBuilder<UserRegistration>()
                .name(ClassUtils.getShortName(FlatFileItemReader.class))
                .resource(input)
                .targetType(UserRegistration.class)
                .delimited()
                .names(new String[]{"firstName","lastName","company","address","city","state","zip","county","url","phoneNumber","fax"})
                .build();
    }

    @Bean
    public ItemWriter<UserRegistration> itemWriter() {
       return items -> repository.saveAll(items);
    }
}
