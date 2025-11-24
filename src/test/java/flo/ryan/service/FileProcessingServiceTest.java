package flo.ryan.service;

import flo.ryan.model.MeterReading;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * tests the full end-to-end process, from reading the input file to writing the SQL output.
 */
class FileProcessingServiceTest {

    @InjectMocks
    private FileProcessingService fileProcessingService;

    @Mock
    private NEM12ParserService parserService;

    @Mock
    private SQLGeneratorService sqlGeneratorService;

    private File inputFile;
    private File outputFile;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);

        inputFile = File.createTempFile("test-input", ".csv");
        outputFile = File.createTempFile("test-output", ".sql");

        // Mock some meter readings
        List<MeterReading> mockReadings = List.of(
                new MeterReading("NEM1201009", LocalDateTime.of(2005, 3, 1, 0, 0), new BigDecimal("0.461")),
                new MeterReading("NEM1201009", LocalDateTime.of(2005, 3, 1, 0, 30), new BigDecimal("0.810"))
        );

        // Mock the parser service to return these readings
        when(parserService.parseNEM12File(inputFile)).thenReturn(mockReadings);

        // Mock the SQL generator to return some SQL statements
        when(sqlGeneratorService.generateInsertStatements(mockReadings)).thenReturn(
                List.of(
                        "INSERT INTO meter_readings (nmi, timestamp, consumption) VALUES ('NEM1201009', '2005-03-01T00:00', 0.461);",
                        "INSERT INTO meter_readings (nmi, timestamp, consumption) VALUES ('NEM1201009', '2005-03-01T00:30', 0.810);"
                )
        );
    }

    @Test
    void processNEM12File() throws IOException {
        fileProcessingService.processNEM12File(inputFile, outputFile);

        // Verify that the output file has the expected SQL statements
        List<String> lines = Files.readAllLines(outputFile.toPath());

        assertEquals(2, lines.size());
        assertEquals("INSERT INTO meter_readings (nmi, timestamp, consumption) VALUES ('NEM1201009', '2005-03-01T00:00', 0.461);", lines.get(0));
        assertEquals("INSERT INTO meter_readings (nmi, timestamp, consumption) VALUES ('NEM1201009', '2005-03-01T00:30', 0.810);", lines.get(1));

        // Verify interactions with the mocked services
        verify(parserService, times(1)).parseNEM12File(inputFile);
        verify(sqlGeneratorService, times(1)).generateInsertStatements(anyList());
    }
}
