package flo.ryan.service;

import flo.ryan.model.MeterReading;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ensures that the MeterReading objects are correctly converted into SQL statements.
 */
class SQLGeneratorServiceTest {

    private SQLGeneratorService sqlGeneratorService;

    @BeforeEach
    void setUp() {
        sqlGeneratorService = new SQLGeneratorService();
    }

    @Test
    void generateInsertStatements() {
        List<MeterReading> readings = List.of(
                new MeterReading("NEM1201009", LocalDateTime.of(2005, 3, 1, 0, 0), new BigDecimal("0.461")),
                new MeterReading("NEM1201009", LocalDateTime.of(2005, 3, 1, 0, 30), new BigDecimal("0.810"))
        );

        List<String> sqlStatements = sqlGeneratorService.generateInsertStatements(readings);

        assertNotNull(sqlStatements);
        assertEquals(2, sqlStatements.size());

        assertEquals("INSERT INTO meter_readings (nmi, timestamp, consumption) VALUES ('NEM1201009', '2005-03-01T00:00', 0.461);", sqlStatements.get(0));
        assertEquals("INSERT INTO meter_readings (nmi, timestamp, consumption) VALUES ('NEM1201009', '2005-03-01T00:30', 0.810);", sqlStatements.get(1));
    }
}
