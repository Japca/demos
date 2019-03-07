package batch;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class BatchApplication {

    @Autowired
    JobRegistry jobRegistry;
    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    JobRepository jobRepository;

    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }

    @PostConstruct
    public void init() {
        System.out.println("JobRegistry: " + jobRegistry);
        System.out.println("JobLauncher: " + jobLauncher);
        System.out.println("JobRepository: " + jobRepository);
    }

}



