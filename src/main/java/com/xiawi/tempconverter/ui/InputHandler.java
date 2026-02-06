package com.xiawi.tempconverter.ui;

import com.xiawi.tempconverter.model.Temperature;
import com.xiawi.tempconverter.model.Unit;

public class InputHandler {
    public Temperature parseTemperature(String valueString, String unitString) {
        double value;
        Unit unit;

        value = Double.parseDouble(valueString);
        unit = parseUnit(unitString);
        return new  Temperature(value, unit);
    }

    public Unit parseUnit(String unitString) {
        return switch(unitString) {
            case "C" -> Unit.CELSIUS;
            case "K" -> Unit.KELVIN;
            case "F" -> Unit.FAHRENHEIT;
            default -> throw new IllegalArgumentException("Invalid unit: " + unitString);
        };
    }
}