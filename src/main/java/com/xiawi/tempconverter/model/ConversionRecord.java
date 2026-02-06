package com.xiawi.tempconverter.model;

import java.time.Instant;

public record ConversionRecord(Temperature source, Temperature converted, Instant timestamp){
    public String toFileString() {
        return source.value() + "," + source.unit() + "," + converted.value() + "," + converted.unit() + "," + timestamp;
    }

    public static ConversionRecord fromFileString(String line) {
        String[] parts;
        Temperature source;
        Temperature converted;

        parts = line.split(",");
        source = new Temperature(
                Double.parseDouble(parts[0]),
                Unit.valueOf(parts[1])
        );
        converted = new Temperature(
                Double.parseDouble(parts[2]),
                Unit.valueOf(parts[3])
        );
        return new ConversionRecord(source, converted, Instant.parse(parts[4]));
    }
}