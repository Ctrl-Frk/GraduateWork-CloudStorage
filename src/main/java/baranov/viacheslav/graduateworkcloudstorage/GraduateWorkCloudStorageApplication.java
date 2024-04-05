package baranov.viacheslav.graduateworkcloudstorage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GraduateWorkCloudStorageApplication {
    private static final Logger logger = LogManager.getLogger(GraduateWorkCloudStorageApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GraduateWorkCloudStorageApplication.class, args);
        logger.info("App is running");
    }
}