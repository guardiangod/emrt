package flo.ryan.service;

import flo.ryan.model.MeterReading;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * This class processes the NEM12 data file and writes the SQL statements to an output file.
 */
@Service
public class FileProcessingService {

    @Autowired
    private NEM12ParserService parserService;

    @Autowired
    private SQLGeneratorService sqlGeneratorService;

    public void processNEM12File(File inputFile, File outputFile) throws IOException {
        // Parse the NEM12 file
        List<MeterReading> readings = parserService.parseNEM12File(inputFile);

        // Generate SQL insert statements
        List<String> sqlStatements = sqlGeneratorService.generateInsertStatements(readings);

        // Write the SQL statements to the output file
        writeSQLToFile(sqlStatements, outputFile);
    }

    private void writeSQLToFile(List<String> sqlStatements, File outputFile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (String statement : sqlStatements) {
                writer.write(statement);
                writer.newLine();
            }
        }
    }
}
