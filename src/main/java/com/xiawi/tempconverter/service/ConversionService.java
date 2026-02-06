package com.xiawi.tempconverter.service;

import com.xiawi.tempconverter.model.Temperature;
import com.xiawi.tempconverter.model.Unit;

public class ConversionService {
    public Temperature convert(Temperature source, Unit targetUnit) {
        double valueInCelsius;
        double convertedValue;

        if (source.unit() == targetUnit)
            return source;
        valueInCelsius = toCelsius(source);
        convertedValue = fromCelsius(valueInCelsius, targetUnit);
        return new Temperature(convertedValue, targetUnit);
    }

    private double toCelsius(Temperature source) {
        return switch (source.unit()) {
            case CELSIUS -> source.value();
            case FAHRENHEIT -> (source.value() + 32) * 5 / 9;
            case KELVIN -> source.value() - 273.15;
        };
    }

    private double fromCelsius(double source, Unit targetUnit)
    {
        return switch (targetUnit) {
            case CELSIUS -> source;
            case FAHRENHEIT -> source * 9 / 5 + 32;
            case KELVIN -> source + 273.15;
        };
    }
}