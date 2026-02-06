package com.xiawi.tempconverter.model;

public record Temperature(double value, Unit unit) {
    public Temperature {
        if (unit == Unit.KELVIN && value < 0)
            throw new IllegalArgumentException("Kelvin cannot be negative.");
    }
}