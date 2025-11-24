package flo.ryan.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * This model represents each parsed reading.
 */
public class MeterReading {
    private String nmi;
    private LocalDateTime timestamp;
    private BigDecimal consumption;

    public MeterReading(String nmi, LocalDateTime timestamp, BigDecimal consumption) {
        this.nmi = nmi;
        this.timestamp = timestamp;
        this.consumption = consumption;
    }

    public String getNmi() {
        return nmi;
    }

    public void setNmi(String nmi) {
        this.nmi = nmi;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getConsumption() {
        return consumption;
    }

    public void setConsumption(BigDecimal consumption) {
        this.consumption = consumption;
    }

    @Override
    public String toString() {
        return "MeterReading{" +
                "nmi='" + nmi + '\'' +
                ", timestamp=" + timestamp +
                ", consumption=" + consumption +
                '}';
    }
}
