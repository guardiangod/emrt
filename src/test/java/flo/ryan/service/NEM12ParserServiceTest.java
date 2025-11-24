package flo.ryan.service;

import flo.ryan.model.MeterReading;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * verifies that the NEM12 data is parsed correctly into MeterReading objects.
 */
class NEM12ParserServiceTest {

    private NEM12ParserService parserService;
    private File testFile;

    @BeforeEach
    void setUp() throws IOException {
        parserService = new NEM12ParserService();

        // Create a temporary test file with sample NEM12 data
        testFile = File.createTempFile("nem12", ".csv");

        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("100,NEM12,200506081149,UNITEDDP,NEMMCO\n");
            writer.write("200,NEM1201009,E1E2,1,E1,N1,01009,kWh,30,20050610\n");
            writer.write("300,20050301,0,0,0,0,0,0,0,0,0,0,0,0,0.461,0.810,0.568,1.234,1.353,1.507,1.344,1.773,0.848\n");
            writer.write("300,20050302,0,0,0,0,0,0,0,0,0,0,0,0,0.235,0.567,0.890,1.123,1.345,1.567,1.543,1.234,0.987\n");
        }
    }

    @Test
    void parseNEM12File() throws IOException {
        List<MeterReading> readings = parserService.parseNEM12File(testFile);

        assertNotNull(readings);
        assertEquals(18, readings.size());

        MeterReading firstReading = readings.get(0);
        System.out.println("First Reading: " + firstReading.toString());
        assertEquals("NEM1201009", firstReading.getNmi());
        assertEquals("2005-03-01T00:00", firstReading.getTimestamp().toString());
        assertEquals("0.461", firstReading.getConsumption().toString());

        MeterReading secondReading = readings.get(1);
        System.out.println("Second Reading: " + secondReading.toString());
        assertEquals("NEM1201009", secondReading.getNmi());
        assertEquals("2005-03-01T00:30", secondReading.getTimestamp().toString());
        assertEquals("0.810", secondReading.getConsumption().toString());
    }
}
