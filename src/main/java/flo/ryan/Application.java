package flo.ryan;

import flo.ryan.service.FileProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

/**
 * This class serves as the entry point for the console application.
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private FileProcessingService fileProcessingService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * This app will read the input file, process it, and write the SQL statements to the output file.
     */
    @Override
    public void run(String... args) throws Exception {
        // Hardcoded file paths
        String inputFilePath = "src/main/resources/input/nem12.csv";
        String outputFilePath = "src/main/resources/output/meter_readings.sql";

        File inputFile = new File(inputFilePath);
        File outputFile = new File(outputFilePath);

        // Process the file
        fileProcessingService.processNEM12File(inputFile, outputFile);

        System.out.println("Processing complete. SQL output written to: " + outputFilePath);
    }
}
