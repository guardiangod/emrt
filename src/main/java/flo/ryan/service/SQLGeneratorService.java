package flo.ryan.service;

import flo.ryan.model.MeterReading;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This service generates the SQL INSERT statements.
 */
@Service
public class SQLGeneratorService {

    public List<String> generateInsertStatements(List<MeterReading> readings) {
        return readings.stream()
                .map(reading -> String.format("INSERT INTO meter_readings (nmi, timestamp, consumption) VALUES ('%s', '%s', %s);",
                        reading.getNmi(), reading.getTimestamp(), reading.getConsumption()))
                .collect(Collectors.toList());
    }
}
