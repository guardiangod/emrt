package flo.ryan.service;

import flo.ryan.model.MeterReading;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * This service parses the NEM12 data file.
 */
@Service
public class NEM12ParserService {

//    public List<MeterReading> parseNEM12File(File file) throws IOException {
//        List<MeterReading> readings = new ArrayList<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            String line;
//            String nmi = null;
//            int intervalLength = 0;
//
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split(",");
//                if (line.startsWith("200")) {
//                    nmi = parts[1];
//                    intervalLength = Integer.parseInt(parts[8]);
//                } else if (line.startsWith("300")) {
//                    LocalDate intervalDate = LocalDate.parse(parts[1], DateTimeFormatter.ofPattern("yyyyMMdd"));
//                    for (int i = 3; i < parts.length; i++) {
//                        if (!parts[i].isEmpty()) {
//                            MeterReading reading = new MeterReading(nmi, intervalDate.atTime(LocalTime.of((i - 3) * intervalLength / 60, 0)), new BigDecimal(parts[i]));
//                            readings.add(reading);
//                        }
//                    }
//                }
//            }
//        }
//
//        return readings;
//    }

    public List<MeterReading> parseNEM12File(File file) throws IOException {
        List<MeterReading> readings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String nmi = null;
            int intervalLength = 0;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (line.startsWith("200")) {
                    nmi = parts[1];
                    intervalLength = Integer.parseInt(parts[8]);
                } else if (line.startsWith("300")) {
                    LocalDate intervalDate = LocalDate.parse(parts[1], DateTimeFormatter.ofPattern("yyyyMMdd"));
                    for (int i = 14; i < parts.length; i++) { // Start from index 14 to skip leading zeros
                        if (!parts[i].trim().isEmpty() && !parts[i].equals("0")) { // Ignore zero values
                            LocalDateTime timestamp = intervalDate.atTime(LocalTime.of((i - 14) * intervalLength / 60, (i - 14) * intervalLength % 60));
                            MeterReading reading = new MeterReading(nmi, timestamp, new BigDecimal(parts[i]));
                            readings.add(reading);
                        }
                    }
                }
            }
        }

        return readings;
    }
}
